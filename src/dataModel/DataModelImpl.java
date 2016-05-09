package dataModel;

import java.util.Date;
import java.util.List;

import dataEnum.Natures;
/**
 * classe implementativa per la creazione di oggetti
 * 
 * @author niky
 *
 */
public class DataModelImpl implements IdataModel {

    Bill newConto;
    Movement newMovimento;
    Product newProdotto;
    Customers_Suppliers newCL_FO;
    Company newAzienda;
    
    @Override
    public void newConto(String nome, Natures natura, long dare, long avere) {
        newConto.setName(nome);
        newConto.setNature(natura);
        newConto.setDare(dare);
        newConto.setAvere(avere);
    }

    @Override
    public void newMovimento(Date data, List<Bill> ListaContiUsati) {
       newMovimento.setData(data);
       newMovimento.setListaConti(ListaContiUsati);
    }

    @Override
    public void newProdotto() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void newCl_Fo() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void newAzienda() {
        // TODO Auto-generated method stub
        
    }
}
