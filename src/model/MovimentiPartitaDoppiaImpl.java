package model;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dataModel.Movimento;

public class MovimentiPartitaDoppiaImpl implements IMovimentiPartitaDoppia{

    private Map<Date,Set<Movimento>> mappaMovimenti;
    
    public MovimentiPartitaDoppiaImpl() {
        mappaMovimenti = new HashMap<>();
    }

    @Override
    public void aggiungi() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void rimuovi() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Object cerca() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void modifica() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void chiudi() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Arrays> getListaMovimenti(Date data) {
        // TODO Auto-generated method stub
        return null;
    }

}
