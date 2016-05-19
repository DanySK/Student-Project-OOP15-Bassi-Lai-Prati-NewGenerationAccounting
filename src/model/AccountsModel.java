package model;

import java.util.HashMap;
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
	private final static String nome = "Nome Conto";
	private final static String natura = "Natura Conto";
	private final static String saldo = "Saldo Conto";
	
	private DBDataModel db;
	public AccountsModel(DBDataModel db) {
		this.db = db;
	}

	protected void addElem(Map<String,Object> elem) {
	    Account a = new Account(elem.get(nome).toString(), (Natures) elem.get(natura), 0);
	    if (listaaccount.contains(a)){
	        //throw new Exception("elemento già inserito"); ragionarci bene con fede
	    }
	        listaaccount.add(a);
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

    @Override
    protected Map<String, Object> getMap() {
        Map<String,Object> mappa = new HashMap<>();
        Natures nat = null;
        mappa.put(nome, new String());
        mappa.put(natura, nat);
        return null;
    }
    
    Map<String, Object> getMap(Account obj) {
        Map<String,Object> mappa = new HashMap<>();
        mappa.put(nome, obj.getName());
        mappa.put(natura, obj.getNatura());
        mappa.put(saldo, obj.getSaldo());
        return mappa;
    }
    
    
    
    
    
    
    
}
