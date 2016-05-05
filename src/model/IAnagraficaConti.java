package model;

import java.util.Set;

import dataEnum.Nature;
import dataModel.Conto;

public interface IAnagraficaConti {
    
    abstract void aggiungi();
    abstract void rimuovi();
    abstract Object cerca();
    abstract void modifica();
    abstract void chiudi();
    
    public Nature getNatura(Conto conto);
    
    public Set<Conto> getListaConti(Nature natura);

}
