package model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public interface IMovimentiPartitaDoppia {
    abstract void aggiungi();
    abstract void rimuovi();
    abstract Object cerca();
    abstract void modifica();
    abstract void chiudi();
    
    public List<Arrays> getListaMovimenti(Date data);
}
