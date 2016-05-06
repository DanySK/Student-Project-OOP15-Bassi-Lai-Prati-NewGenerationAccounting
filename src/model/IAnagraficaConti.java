package model;

import dataModel.Conto;

public interface IAnagraficaConti {
    
    abstract void aggiungi(Conto conto);
    abstract void rimuovi(Conto conto);
    abstract Object cerca(Conto conto);
    abstract void modifica(Conto conto);
    abstract void chiudi();

}
