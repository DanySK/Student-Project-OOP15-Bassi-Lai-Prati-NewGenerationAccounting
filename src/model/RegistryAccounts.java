package model;

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

	List<Account> lista = new LinkedList(datiDB);
	Account nuovo;

	public RegistryAccounts() {
	}

	@Override
	void comparisonAccount(Pair<String, Object> elemDaAggiungere) {
		nuovo.setName(elemDaAggiungere.getKey());
		nuovo.setNatura((Natures) elemDaAggiungere.getValue());
		nuovo.setAvere(0);
		nuovo.setDare(0);
		if (lista.contains(nuovo)) {
			System.out.println("conto già inserito");
		} else
			lista.add(nuovo);
	}

}
