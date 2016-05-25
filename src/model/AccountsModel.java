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
	private final DBDataModel db;
	private final LinkedList<Account> listaaccount;

	public AccountsModel(DBDataModel db) {
		this.db = db;
		this.listaaccount = db.getAccounts();
	}

	public static LinkedList<Account> chartOfAccounts() {
		return new LinkedList<Account>();
	}

	@Override
	protected void addElem(Map<String, Object> elem) throws InstanceAlreadyExistsException {
		if (elem.get(NOME) == "" || elem.get(NATURA) == null || (Sections)elem.get(SEZIONE)==null) {
			throw new IllegalArgumentException("nome, natura o sezione non valide");
		}
		Account a = new Account((String) elem.get(NOME), (Natures) elem.get(NATURA), (Sections) elem.get(SEZIONE), 0);
		if (checkSection((Natures) elem.get(NATURA), (Sections) elem.get(SEZIONE))) {
			if (listaaccount.contains(a)) {
				throw new InstanceAlreadyExistsException("elemento già esistente in lista");
			}
			listaaccount.add(a);
		} else {
			throw new IllegalArgumentException("sezione non appartenente alla natura");
		}
	}

	@Override
	protected void editElem(IDataTableModel obj, Map<String, Object> elemDaModificare) throws InstanceNotFoundException { // modifica elementi
	trovato = false;
	if(obj != null){
	       if ((Float) elemDaModificare.get(SALDO) != 0 || (Natures) elemDaModificare.get(NATURA) != null || (Sections) elemDaModificare.get(SEZIONE) != null) {
			throw new IllegalArgumentException("non posso modificare il saldo, natura o sezione di un conto");
		}
		else
		{
		    if (obj instanceof Account) {
		        Account a = (Account) obj;
	                for (Account elem : listaaccount) {
	                    if (elem.getName().equals(a.getName())) {
	                         elem.setName(elemDaModificare.get(NOME).toString());
	                         trovato = true;
	                    }
	                }
	                if (trovato == false) {
	                    throw new InstanceNotFoundException("elemento da modificare non presente in lista");
	                }
	            } 
		    else throw new IllegalArgumentException("l'oggetto inserito non è un Conto");
	         }
	    }
	throw new IllegalArgumentException("oggeto di riferimento non valido");
}

	@Override
	public Map<String, Object> getMap(IDataTableModel obj) {
		if (obj == null) {
			Map<String, Object> mappaVuota = new HashMap<>();
			mappaVuota.put(NOME, new String(""));
			mappaVuota.put(NATURA, Natures.ATTIVITA);
			mappaVuota.put(SEZIONE, Sections.CREDITI_VS_SOCI);
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
							throw new IllegalArgumentException("non posso eliminare l'elemento perchè ha saldo > 0");
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
		if (listaaccount.contains(op.getConto())) { // l'aggiunta/modifica/eliminazione
			for (Account elem : listaaccount) { // di un movimento
				if (elem.equals(op.getConto())) {
					if (elem.getNatura().equals(Natures.COSTO) || elem.getNatura().equals(Natures.ATTIVITA)) {
						if (op.getDare() > 0)
							elem.incrSaldo(op.getDare());// Costo e Attività
															// aumentano in dare
						else if (op.getAvere() > 0)
							elem.decrSaldo(op.getAvere());// e calano in avere
					} else {
						if (op.getAvere() > 0)
							elem.incrSaldo(op.getAvere()); // Ricavi e
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
		mappaFiltro.put(NATURA, Natures.ATTIVITA);
		mappaFiltro.put(SEZIONE, Sections.ATTIVITA_FINANZIARIE);
		return mappaFiltro;
	}

	@Override
	public LinkedList<Account> load() { // carica tutti i dati
		return new LinkedList<Account>(listaaccount);
	}

	@Override
	public LinkedList<Account> load(Map<String, Object> mappaFiltro) throws InstanceNotFoundException {// carica
																										// dati
																										// con
																										// filtri
		LinkedList<Account> listaFiltrata = new LinkedList<>();
		if (mappaFiltro.get(NOME) != "") { // controllo il nome
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
				if ((checkSection((Natures) mappaFiltro.get(NATURA), (Sections) mappaFiltro.get(SEZIONE)))) {
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
			throw new InstanceNotFoundException("nella lista non sono presenti elementi che soddisfano i filtri");
		}
		return null;
	}

	private boolean checkSection(Natures nat, Sections sez) {											// bool
		switch (nat) {
		case ATTIVITA:
			return Sections.getAttivita().contains(sez);
		case COSTO:
			return Sections.getCosti().contains(sez);
		case PASSIVITA:
			return Sections.getPassivita().contains(sez);
		case RICAVO:
			return Sections.getRicavi().contains(sez);
		default:
			return false;

		}
	}
}
