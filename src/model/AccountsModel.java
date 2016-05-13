package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dataEnum.Natures;
import dataModel.Account;
import dataModel.IDataTableModel;

/**
 * classe implementativa per la gestione dell'anagrafica dei conti
 * 
 * @author niky
 *
 */
public class AccountsModel extends AbstractModel {

	public List<Account> listaContiRegistrati;
	private Account nuovo;
	private Account elem;

	public AccountsModel() {
	    listaContiRegistrati = new LinkedList<Account>();
	}

    @Override
    void addElem(Map<String, Object> elem) {
        nuovo.setName(elem.get("Nome Conto").toString());
        nuovo.setNatura((Natures) elem.get("Natura Conto"));
        nuovo.setDare(0);
        nuovo.setAvere(0);
        if(listaContiRegistrati.contains(nuovo)){
            System.out.println("conto già registrato");
        }
        else listaContiRegistrati.add(nuovo);
    }

    @Override
    void removeElem(Map<String, Object> elemDaEliminare) {
        elem.setName(elemDaEliminare.get("Nome Conto").toString());
        elem.setNatura((Natures) elemDaEliminare.get("Natura Conto"));
        for(Account a : listaContiRegistrati){
            if(a.getNatura().equals(elem.getNatura())&&a.getName().equals(elem.getName())){
                listaContiRegistrati.remove(a);
            }
        }
    }

    @Override
    void editElem(IDataTableModel obj, Map<String, Object> elemDaModificare) {
        
    }
	
}
