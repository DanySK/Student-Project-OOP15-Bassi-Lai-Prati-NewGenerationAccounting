package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

import dataEnum.Natures;
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
	public LinkedList<Account> load() { // carica tutti i dati
		return new LinkedList<Account>(listaaccount);
	}
	/*
	public List<? extends IDataTableModel> load(Natures natura) throws Exception { // carica
																					// //
																					// natura
		LinkedList<Account> filtroNatura = new LinkedList<Account>();
		if (natura.equals(null)) {
			throw new Exception("natura non valida");
		} else
			for (Account a : listaaccount) {
				if (a.getNatura().equals(natura)) {
					filtroNatura.add(a);
				}
			}
		return filtroNatura;
	}

	public List<? extends IDataTableModel> load(String nome) throws Exception { // carica																		// nome
		LinkedList<Account> filtroNome = new LinkedList<Account>();
		if (nome.isEmpty()) {
			throw new Exception("nome non valido");
		} else
			for (Account a : listaaccount) {
				if (a.getName().contains(nome)) {
					filtroNome.add(a);
				}
			}
		return filtroNome;
	}
*/
	@Override
	public void remove(IDataTableModel elemDaEliminare) throws InstanceNotFoundException { // elimina
																							// i
																							// dati
																							// //
																							// dati
		if (elemDaEliminare.getClass().equals(Account.class)) {
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
			throw new IllegalArgumentException("elemento non valido");
		}
	}

	@Override
	public DBDataModel saveDBAndClose() { // salva i dati sul database
		db.setAccounts(listaaccount);
		return db;
	}

	public void updateAccounts(Operation op) { // aggiorna i conti dopo l'aggiunta/modifica/eliminazione di un movimento
		if (listaaccount.contains(op.getConto())) {
			for (Account elem : listaaccount) {
				if (elem.equals(op.getConto())) {
					if (elem.getNatura().equals(Natures.COSTO) || elem.getNatura().equals(Natures.ATTIVITA)) {
						if (op.getDare() > 0)
							elem.incrSaldo(op.getDare());// Costo e Attività aumentano in dare
						else if (op.getAvere() > 0)
							elem.decrSaldo(op.getAvere());// e calano in avere
					} else {
						if (op.getAvere() > 0)
							elem.incrSaldo(op.getAvere()); // Ricavo e Passività aumentano in avere
						else if (op.getDare() > 0)
							elem.decrSaldo(op.getDare());// e calano in dare
					}
				}
			}
		}
	}

    @Override
    public Map<String, Object> getFilterMap() {
        Map<String,Object> mappaFiltro = new HashMap<>();
        mappaFiltro.put(NOME, new String());
        mappaFiltro.put(NATURA, Natures.values());
        //mappaFiltro.put(SEZIONE, value);
        return mappaFiltro;
    }

    @Override
    public LinkedList<? extends IDataTableModel> load(Map<String, Object> mappaFiltro) {
        // TODO Auto-generated method stub
        return null;
    }


}
