package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dataEnum.Natures;
import dataModel.Account;
import dataModel.IDataTableModel;
/**
 * classe implementativa per la gestione dell'anagrafica dei conti
 * 
 * @author niky
 *
 */
public class RegistryAccounts extends AbstractModel{

    private Set<Account> listaConti;
    private Account nuovoConto;
    private int i = 0;
    
    public RegistryAccounts() {
        listaConti = new HashSet<Account>();
    }

    @Override
    public void add() {
        
    }

    @Override
    public void remove() {
        
        
    }

    @Override
    public Object search() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object load() {
        return listaConti;
    }

    @Override
    public void edit(IDataTableModel oggetto, List<Object> infoContoDaEliminare) {
        // TODO Auto-generated method stub
        
    }

}
