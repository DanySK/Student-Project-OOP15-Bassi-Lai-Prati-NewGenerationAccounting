package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dataEnum.Natures;
import dataModel.Account;

/**
 * classe implementativa per la gestione dell'anagrafica dei conti
 * 
 * @author niky
 *
 */
public class RegistryAccounts extends AbstractModel {

	public List<Account> listaContiRegistrati;
	private Account nuovo;

	public RegistryAccounts() {
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
	
}
