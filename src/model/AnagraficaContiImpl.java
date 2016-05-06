package model;

import java.util.HashSet;
import java.util.Set;

import dataModel.Conto;

public class AnagraficaContiImpl implements IAnagraficaConti{

    private Set<Conto> listaConti;
    
    public AnagraficaContiImpl() {
        listaConti = new HashSet<Conto>();
    }

    @Override
    public void aggiungi(Conto conto) {
        
        for(Conto elem : listaConti){
            if(elem.getNatura().equals(conto.getNatura())&&
              (elem.getNome().equals(conto.getNome()))){
                System.out.println("conto già inserito, per modificarlo premere su MODIFICA");
            }
        }
        listaConti.add(conto);
    }

    @Override
    public void rimuovi(Conto conto) {
        if (listaConti.contains(conto)){
            listaConti.remove(conto);
            System.out.println("conto" + conto.getNome() + "eliminato");
        }
        else
            System.out.println("conto non trovato");
    }
        
  
    @Override
    public Object cerca(Conto conto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void modifica(Conto conto) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void chiudi() {
        // TODO Auto-generated method stub
        
    }
}
