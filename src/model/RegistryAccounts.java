package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dataEnum.Natures;
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
    private Account nuovoConto;
    private int i = 0;
    
    public RegistryAccounts() {
        listaConti = new HashSet<Account>();
    }

    @Override
    public void add(List info) {
        for(i=0; i<info.size();i++){
            
            if(info.get(i).getClass().equals(String.class)){
                String name = info.get(i).toString();
                nuovoConto.setName(name);
            }
            else if(info.get(i).getClass().equals(Long.class)){
                
                long val = (long) info.get(i);
                if(val < 0){
                    nuovoConto.setDare(val);
                }
                else nuovoConto.setAvere(val);
            }
            else if(info.get(i).getClass().equals(Natures.class)){
                Natures nat = (Natures) info.get(i);
                nuovoConto.setNature(nat);
            }
        }  
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
        return listaConti;
    }

    @Override
    public void edit() {
        // TODO Auto-generated method stub
        
    }

}
