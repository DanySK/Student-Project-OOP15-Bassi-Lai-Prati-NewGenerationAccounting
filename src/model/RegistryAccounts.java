package model;

import java.util.HashSet;
import java.util.Set;

import dataModel.Account;
import dataModel.DataModelImpl;
/**
 * classe implementativa per la gestione dell'anagrafica dei conti
 * 
 * @author niky
 *
 */
public class RegistryAccounts extends DataModelImpl implements IOperations{

    private Set<Account> listaConti;
    
    public RegistryAccounts() {
        listaConti = new HashSet<Account>();
    }

    @Override
    public void add() {
       
    }

    @Override
    public void remove() {
        // TODO Auto-generated method stub
        
    }


    @Override
    public Object search() {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public Object load() {
        return getContiRegistrati();
    }


    @Override
    public void edit() {
        // TODO Auto-generated method stub
        
    }
    
    private Set<Account> getContiRegistrati(){
        return listaConti;
        
    }

    
}
