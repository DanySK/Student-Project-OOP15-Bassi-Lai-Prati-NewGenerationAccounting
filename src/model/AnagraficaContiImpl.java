package model;

import java.util.HashSet;
import java.util.Set;

import dataModel.Conto;
import dataModel.DataModelImpl;

public class AnagraficaContiImpl extends DataModelImpl implements IOperations{

    private Set<Conto> listaConti;
    private Conto nuovoConto;
    
    public AnagraficaContiImpl() {
        listaConti = new HashSet<Conto>();
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
    
    private Set<Conto> getContiRegistrati(){
        return listaConti;
        
    }

    
}
