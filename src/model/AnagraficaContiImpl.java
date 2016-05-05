package model;

import java.util.HashSet;
import java.util.Set;

import dataEnum.Nature;
import dataModel.Conto;

public class AnagraficaContiImpl implements IAnagraficaConti{

    private Set<Conto> listaConti;
    
    public AnagraficaContiImpl() {
        listaConti = new HashSet<Conto>();
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
    public Nature getNatura(Conto conto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Conto> getListaConti(Nature natura) {
        // TODO Auto-generated method stub
        return null;
    }

}
