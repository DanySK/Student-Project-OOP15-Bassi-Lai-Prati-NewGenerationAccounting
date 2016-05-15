package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import dataModel.Account;
import dataModel.DBDataModel;
import dataModel.IDataTableModel;
import dataModel.Movement;

/**
 * classe implementativa per la gestione dell'anagrafica dei movimenti
 * 
 * @author niky
 *
 */
public class MovementsModel extends AbstractModel {

    private DBDataModel db;
    Movement nuovo;
    Movement elem;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public MovementsModel() {
       db.getMoviments();
    }
    @Override
    public void addElem(Map<String, Object> elem) throws ParseException {
        
        Date date = dateFormat.parse(elem.get("Data Movimento").toString());
        nuovo.setData(date);
        nuovo.setListaConti((List<Account>) elem.get("lista conti usati nel movimento"));
        if(db.getMoviments().contains(nuovo)){
            System.out.println("movimento già registrato");
        }
        else db.getMoviments().add(nuovo);
      //qui si richiamerà la funzione per modificare i conti -> APPLICANDO gli effetti di questo movimento
    }
    @Override
    public void removeElem(Map<String, Object> elemDaEliminare) throws ParseException {
        elem.setData(dateFormat.parse(elemDaEliminare.get("Data Movimento").toString()));
        elem.setListaConti((List<Account>) elemDaEliminare.get("lista conti usati nel movimento"));
        if(db.getMoviments().contains(elem)){
            db.getMoviments().remove(elem);
            //qui si richiamerà la funzione per modificare i conti -> ANNULLANDO gli effetti di questo movimento
        }
    }
    @Override
    public void editElem(IDataTableModel obj, Map<String, Object> elemDaModificare) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public List<Object> load() {
        // TODO Auto-generated method stub
        return null;
    }
    
    List<Object>load(Date da, Date a){
        return null;
    }

 }

