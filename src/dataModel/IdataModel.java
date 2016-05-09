package dataModel;

import java.util.Date;
import java.util.List;

import dataEnum.Natures;

/**
 * interfaccia usata per creare un nuovo oggetto di ogni oggetto base
 * 
 * @author niky
 *
 */
public interface IdataModel {
    void newConto(String nome, Natures natura, long dare, long avere);
    void newMovimento(Date data, List<Account> ListaContiUsati);
    void newProdotto();
    void newCl_Fo();
    void newAzienda();
}
