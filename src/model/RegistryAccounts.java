package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import dataEnum.Natures;
import dataModel.Account;
import javafx.util.Pair;

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
	void comparisonAccount(Pair<String, Object> elemDaAggiungere) {
		nuovo.setName(elemDaAggiungere.getKey());
		nuovo.setNatura((Natures) elemDaAggiungere.getValue());
		nuovo.setAvere(0);
		nuovo.setDare(0);
		if (listaContiRegistrati.contains(nuovo)) {
			System.out.println("conto già inserito");
		} else
			listaContiRegistrati.add(nuovo);
	}

    @Override
    void comparisonMovement(Pair<String, List<ArrayList<String>>> infoMovimento) {
        //chiedere a fede come far si che questa funzione non venga chiamata
    }
    
}
