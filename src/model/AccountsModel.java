package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

	LinkedList<Account> listaaccount;
	private DBDataModel db;
	private Account nuovo;
	private Account elem;

	public AccountsModel(DBDataModel db) {
		this.db = db;
	}

	protected void addElem(Map<String, Object> mappa) {
		Account account = null; // TODO
		listaaccount.add(account);
		db.setAccounts(listaaccount);
	}

	@Override
	public void remove(IDataTableModel elemDaEliminare) {
		if (listaaccount.contains(elemDaEliminare)) {
			listaaccount.remove(elemDaEliminare);
			db.setAccounts(listaaccount);
		} else {
			throw new IllegalArgumentException("sto elemento non esiste.");
		}

	}

	@Override
	protected void editElem(IDataTableModel obj, Map<String, Object> elemDaModificare) {

	}

	@Override
	public LinkedList<Account> load() {
		return new LinkedList<Account>(db.getAccounts());
	}

	List<? extends IDataTableModel> load(String nome) throws Exception {
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

	List<? extends IDataTableModel> load(Natures natura) throws Exception {
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

}
