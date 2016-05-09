package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dataModel.DataModelImpl;
import dataModel.Movement;
/**
 * classe implementativa per la gestione dell'anagrafica dei movimenti
 * 
 * @author niky
 *
 */
public class MovementsInDoubleEntry extends DataModelImpl implements IOperations{

    private Set<Movement> listaMovimenti;
    
    public MovementsInDoubleEntry() {
        listaMovimenti = new HashSet<>();
    }

    @Override
    public void add(List info) {
        // TODO Auto-generated method stub
        
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void edit() {
        // TODO Auto-generated method stub
        
    } 
}
