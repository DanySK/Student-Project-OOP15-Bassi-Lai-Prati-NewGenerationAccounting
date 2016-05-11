package model;

import java.util.LinkedList;
import java.util.List;

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
}
