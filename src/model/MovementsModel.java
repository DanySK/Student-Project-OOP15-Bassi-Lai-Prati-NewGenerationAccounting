package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import dataModel.Account;
import dataModel.IDataTableModel;
import dataModel.Movement;

/**
 * classe implementativa per la gestione dell'anagrafica dei movimenti
 * 
 * @author niky
 *
 */
public class MovementsModel extends AbstractModel {

    private Set<Movement> listaMovimenti;
    Movement nuovo;
    Movement elem;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public MovementsModel() {
        listaMovimenti = new HashSet<>();
    }
    @Override
    void addElem(Map<String, Object> elem) throws ParseException {
        
        Date date = dateFormat.parse(elem.get("Data Movimento").toString());
        nuovo.setData(date);
        nuovo.setListaConti((Set<Account>) elem.get("lista conti usati nel movimento"));
        if(listaMovimenti.contains(nuovo)){
            System.out.println("movimento già registrato");
        }
        else listaMovimenti.add(nuovo);
      //qui si richiamerà la funzione per modificare i conti -> APPLICANDO gli effetti di questo movimento
    }
    @Override
    void removeElem(Map<String, Object> elemDaEliminare) throws ParseException {
        elem.setData(dateFormat.parse(elemDaEliminare.get("Data Movimento").toString()));
        elem.setListaConti((Set<Account>) elemDaEliminare.get("lista conti usati nel movimento"));
        if(listaMovimenti.contains(elem)){
            listaMovimenti.remove(elem);
            //qui si richiamerà la funzione per modificare i conti -> ANNULLANDO gli effetti di questo movimento
        }
    }
    @Override
    void editElem(IDataTableModel obj, Map<String, Object> elemDaModificare) {
        // TODO Auto-generated method stub
        
    }
 }

