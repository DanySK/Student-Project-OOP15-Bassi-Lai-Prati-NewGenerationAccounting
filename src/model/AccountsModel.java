package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

import dataEnum.Natures;
import dataEnum.Sections;
import dataModel.Account;
import dataModel.DBDataModel;
import dataModel.IDataTableModel;
import dataModel.Operation;

/**
 * classe implementativa per la gestione dell'anagrafica dei conti
 * 
 * @author niky
 *
 */
public class AccountsModel extends AbstractModel {

    private final static String NATURA = "Natura Conto";
    private final static String NOME = "Nome Conto";
    private final static String SALDO = "Saldo Conto";
    private final static String SEZIONE = "Sezione del Conto";
    private boolean trovato = false;
    Natures natura;
    private final DBDataModel db;
    private final LinkedList<Account> listaaccount = new LinkedList<Account>();

    public AccountsModel(DBDataModel db) {
        this.db = db;
    }

    public static LinkedList<Account> chartOfAccounts() {
        return new LinkedList<Account>();
    }

    @Override
    protected void addElem(Map<String, Object> elem) throws InstanceAlreadyExistsException {
        if (elem.get(NOME) == "" || elem.get(NATURA) == null || (Float) elem.get(SALDO) != 0) {
            throw new IllegalArgumentException("nome, natura non valide o saldo diverso da 0");
        }
        Account a = new Account((String) elem.get(NOME), (Natures) elem.get(NATURA), 0);
        if (listaaccount.contains(a)) {
            throw new InstanceAlreadyExistsException("elemento già esistente in lista");
        }
        listaaccount.add(a);
    }

    @Override
    protected void editElem(IDataTableModel obj, Map<String, Object> elemDaModificare)
                    throws InstanceNotFoundException { // modifica elementi
        trovato = false;
        if (!listaaccount.contains(obj)) {
            throw new InstanceNotFoundException("elemento da modificare non presente in lista");
        } else {
            if ((float) elemDaModificare.get(SALDO) != 0 && (Natures) elemDaModificare.get(NATURA) != null) {
                throw new IllegalArgumentException("non posso modificare il saldo o la natura di un conto");
            }
            if (obj instanceof Account) {
                Account a = (Account) obj;
                for (Account elem : listaaccount) {
                    if (elem.getName().equals(a.getName())) {
                        elem.setName(a.getName());
                        trovato = true;
                    }
                }
                if (trovato == false) {
                    throw new InstanceNotFoundException("elemento da modificare non presente in lista");
                }
            } else
                throw new IllegalArgumentException("l'oggetto inserito non è un Conto");
        }
    }

    @Override
    public Map<String, Object> getMap(IDataTableModel obj) {
        if (obj == null) {
            Map<String, Object> mappaVuota = new HashMap<>();
            mappaVuota.put(NOME, new String(""));
            mappaVuota.put(NATURA, Natures.ATTIVITA);
           // mappaVuota.put(SEZIONE, value);
            return mappaVuota;
        } else {
            if (obj instanceof Account) {
                Map<String, Object> mappaPiena = new HashMap<>();
                mappaPiena.put(NOME, ((Account) obj).getName());
                return mappaPiena;
            } else {
                throw new IllegalArgumentException("l'oggetto inseito non è un Conto");
            }
        }
    }

    @Override
    public void remove(IDataTableModel elemDaEliminare) throws InstanceNotFoundException { // elimina
                                                                                           // dati
        if (elemDaEliminare == null) {
            throw new IllegalArgumentException("l'elemento da eliminare non si riferisce a nessun oggetto");
        } else {
            if (elemDaEliminare instanceof Account) {
                Account a = (Account) elemDaEliminare;
                for (Account elem : listaaccount) {
                    if (elem.getName().equals(a.getName())) {
                        trovato = true;
                        if (elem.getSaldo() == 0) {
                            listaaccount.remove(elem);
                            db.setAccounts(listaaccount);
                        } else {
                            throw new IllegalArgumentException("non posso eliminare l'elemento perchè ha saldo != 0");
                        }
                    }
                }
                if (trovato == false) {
                    throw new InstanceNotFoundException("elemento da eliminare non trovato");
                }
            } else {
                throw new IllegalArgumentException("l'elemento da eliminare NON è un conto");
            }
        }
    }

    @Override
    public DBDataModel saveDBAndClose() { // salva i dati sul database
        db.setAccounts(listaaccount);
        return db;
    }

    public void updateAccounts(Operation op) { // aggiorna i conti dopo
                                               // l'aggiunta/modifica/eliminazione
                                               // di un movimento
        if (listaaccount.contains(op.getConto())) {
            for (Account elem : listaaccount) {
                if (elem.equals(op.getConto())) {
                    if (elem.getNatura().equals(Natures.COSTO) || elem.getNatura().equals(Natures.ATTIVITA)) {
                        if (op.getDare() > 0)
                            elem.incrSaldo(op.getDare());// Costo e Attività
                                                         // aumentano in dare
                        else if (op.getAvere() > 0)
                            elem.decrSaldo(op.getAvere());// e calano in avere
                    } else {
                        if (op.getAvere() > 0)
                            elem.incrSaldo(op.getAvere()); // Ricavo e
                                                           // Passività
                                                           // aumentano in
                                                           // avere
                        else if (op.getDare() > 0)
                            elem.decrSaldo(op.getDare());// e calano in dare
                    }
                }
            }
        }
    }

    @Override
    public Map<String, Object> getFilterMap() {
        Map<String, Object> mappaFiltro = new HashMap<>();
        mappaFiltro.put(NOME, new String());
        mappaFiltro.put(NATURA, Natures.values());
        mappaFiltro.put(SEZIONE, Sections.values());
        return mappaFiltro;
    }

    @Override
    public LinkedList<Account> load() { // carica tutti i dati
        return new LinkedList<Account>(listaaccount);
    }
    
    @Override
    public LinkedList<? extends IDataTableModel> load(Map<String, Object> mappaFiltro) {// carica dati con filtri
        LinkedList<Account> listaFiltrata = new LinkedList<>();
        if (mappaFiltro.get(NOME) != null) { // controllo il nome
            for (Account a : listaaccount) {
                if (a.getName().contentEquals(NOME))
                    listaFiltrata.add(a);
            }
        }
        // nome = null o nome != null
        if (mappaFiltro.get(NATURA) != null) { // controllo la natura
            if (listaFiltrata.isEmpty()) {
                for (Account a : listaaccount) { // singolo filtro su natura
                    if (a.getNatura() == mappaFiltro.get(NATURA))
                        listaFiltrata.add(a);
                }
            } else { // doppio filtro tra nome e natura
                for (Account a : listaFiltrata) {
                    if (a.getNatura() != mappaFiltro.get(NATURA))
                        listaFiltrata.remove(a);
                }

            }
            if (mappaFiltro.get(SEZIONE) != null) { // controllo se la sezione
                                                    // appartiene alla nature
                if ((checkSection((Natures) mappaFiltro.get(NATURA), (Sections) mappaFiltro.get(SEZIONE))) == mappaFiltro.get(NATURA)) {
                    for (Account a : listaFiltrata) { // doppio filtro sez + nat
                        if (a.getSezione() != mappaFiltro.get(SEZIONE))
                            listaFiltrata.remove(a);
                    }
                } else {
                    throw new IllegalArgumentException("la sezione non appartiene alla natura");
                }
            }
        }
        // natura = null
        if (mappaFiltro.get(SEZIONE) != null) {
            if (listaFiltrata.isEmpty()) {
                for (Account a : listaaccount) { // singolo filtro su sezione
                    if (a.getSezione() == mappaFiltro.get(SEZIONE))
                        listaFiltrata.add(a);
                }
            } else {
                // lista filtrata NON vuota
                for (Account a : listaFiltrata) { // doppio filtro sez + nat
                    if (a.getSezione() != mappaFiltro.get(SEZIONE))
                        listaFiltrata.remove(a);
                }
            }
        }
        if (listaFiltrata.isEmpty()) {
            // throw new InstanceNotFoundException("nella lista non sono
            // presenti elementi che soddisfano i filtri");
        }
        return null;
    }

    private Natures checkSection(Natures nat, Sections sez) {
        if(nat != Natures.COSTO){
            if(nat!=Natures.RICAVO){
                if(nat!=Natures.ATTIVITA){
                    if(Sections.getPassivita().contains(sez))
                        natura = Natures.PASSIVITA;
                }
                else{
                    if(Sections.getAttivita().contains(sez))
                        natura = Natures.ATTIVITA;
                }
            }
            else{
                if(Sections.getRicavi().contains(sez))
                    natura = Natures.RICAVO;
            }
        }
        else{
            if(Sections.getCosti().contains(sez))
                natura = Natures.COSTO;
        }
        return natura;
  }
}
