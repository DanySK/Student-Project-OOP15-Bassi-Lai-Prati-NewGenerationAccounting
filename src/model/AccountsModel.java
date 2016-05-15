package model;

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

	public DBDataModel db;
	private Account nuovo;
	private Account elem;
	

	public AccountsModel() {
	    db.getAccounts();
	}

    @Override
    public void addElem(Map<String, Object> elem) {
        nuovo.setName(elem.get("Nome Conto").toString());
        nuovo.setNatura((Natures) elem.get("Natura Conto"));
        nuovo.setDare(0);
        nuovo.setAvere(0);
        if(db.getAccounts().contains(nuovo)){
            System.out.println("conto già registrato");
        }
        else db.getAccounts().add(nuovo);
    }

    @Override
    public void removeElem(Map<String, Object> elemDaEliminare) {
        elem.setName(elemDaEliminare.get("Nome Conto").toString());
        elem.setNatura((Natures) elemDaEliminare.get("Natura Conto"));
        for(Account a : db.getAccounts()){
            if(a.getNatura().equals(elem.getNatura())&&a.getName().equals(elem.getName())){
                db.getAccounts().remove(a);
            }
        }
    }

    @Override
    protected void editElem(IDataTableModel obj, Map<String, Object> elemDaModificare) {
        
    }

    @Override
    public List<Object> load() {
        // TODO Auto-generated method stub
        return null;
    }
    
    List<Object> load(String nome){
        return null;
        
    }
    
    List<Object> load(Natures natura){
        return null;
        
    }
    
    
	
}
