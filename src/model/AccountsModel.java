package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

import dataEnum.Natures;
import dataModel.Account;
import dataModel.DBDataModel;
import dataModel.IDataTableModel;

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

	public static LinkedList<Account> chartOfAccounts() {
		return null;

	}

	private boolean trovato = false;

	private DBDataModel db;

	LinkedList<Account> listaaccount;

	public AccountsModel(DBDataModel db) {
		this.db = db;
	}

	@Override
	protected void addElem(Map<String, Object> elem) throws InstanceAlreadyExistsException { // aggiunge
																								// elementi
		if (elem.get(NOME) == "" || elem.get(NATURA) == null || (Long) elem.get(SALDO) != 0) {
			throw new IllegalArgumentException("valori non validi");
		}
		Account a = new Account(elem.get(NOME).toString(), (Natures) elem.get(NATURA), 0);
		if (listaaccount.contains(a)) {
			throw new InstanceAlreadyExistsException("elemento già inserito");
		}
		listaaccount.add(a);
		db.setAccounts(listaaccount);
	}

	@Override
	protected void editElem(IDataTableModel obj, Map<String, Object> elemDaModificare)
			throws InstanceNotFoundException { // modifica elementi
		trovato = false;
		if (!listaaccount.contains(obj)) {
			throw new InstanceNotFoundException("elemento da modificare non presente");
		} else {
			if ((long) elemDaModificare.get(SALDO) != 0 && (Natures) elemDaModificare.get(NATURA) != null) {
				throw new IllegalArgumentException("non posso modificare il saldo o la natura di un conto");
			}
			if (obj.getClass().equals(Account.class)) {
				Account a = (Account) obj;
				for (Account elem : listaaccount) {
					if (elem.getName().equals(a.getName())) {
						elem.setName(a.getName());
						trovato = true;
					}
				}
				if (trovato == false) {
					throw new InstanceNotFoundException("elemento da modificare non trovato");
				}
			} else
				throw new IllegalArgumentException("paramentro non valido");
		}
	}

	@Override
	public Map<String, Object> getMap() {
		Map<String, Object> mappa = new HashMap<>();
		Natures nat = null;
		mappa.put(NOME, new String());
		mappa.put(NATURA, nat);
		mappa.put(SALDO, new Long(0));
		return mappa;
	}

	public Map<String, Object> getMap(Account obj) {
		Map<String, Object> mappa = new HashMap<>();
		mappa.put(NOME, obj.getName());
		mappa.put(NATURA, obj.getNatura());
		mappa.put(SALDO, obj.getSaldo());
		return mappa;
	}

	@Override
	public LinkedList<Account> load() { // carica tutti i dati
		return new LinkedList<Account>(db.getAccounts());
	}

	List<? extends IDataTableModel> load(Natures natura) throws Exception { // carica
																			// dati
																			// secondo
																			// natura
		LinkedList<Account> filtroNatura = new LinkedList<Account>();
		if (natura.equals(null)) {
			throw new Exception("natura non valida");
		} else
			for (Account a : db.getAccounts()) {
				if (a.getNatura().equals(natura)) {
					filtroNatura.add(a);
				}
			}
		return filtroNatura;
	}

	List<? extends IDataTableModel> load(String nome) throws Exception { // carica
																			// dati
																			// secondo
																			// il
																			// nome
		LinkedList<Account> filtroNome = new LinkedList<Account>();
		if (nome.isEmpty()) {
			load();
			throw new Exception("nome non valido");
		} else
			for (Account a : db.getAccounts()) {
				if (a.getName().contains(nome)) {
					filtroNome.add(a);
				}
			}
		return filtroNome;
	}

	@Override
	public void remove(IDataTableModel elemDaEliminare) throws InstanceNotFoundException { // elimina
																							// elementi
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
}
