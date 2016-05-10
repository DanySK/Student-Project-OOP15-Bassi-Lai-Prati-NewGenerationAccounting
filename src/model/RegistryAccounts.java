package model;

import java.util.HashSet;
import java.util.Set;

import dataModel.Account;
/**
 * classe implementativa per la gestione dell'anagrafica dei conti
 * 
 * @author niky
 *
 */
public class RegistryAccounts extends AbstractModel{

    private Set<Account> listaConti;
    
    public RegistryAccounts() {
        listaConti = new HashSet<Account>();
    }

}
