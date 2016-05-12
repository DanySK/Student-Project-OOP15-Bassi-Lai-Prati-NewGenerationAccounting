package model;

import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dataModel.Account;
import dataModel.Movement;

/**
 * classe implementativa per la gestione dell'anagrafica dei movimenti
 * 
 * @author niky
 *
 */
public class MovementsInDoubleEntry extends AbstractModel {

    private Set<Movement> listaMovimenti;
    Movement nuovo;

    public MovementsInDoubleEntry() {
        listaMovimenti = new HashSet<>();
    }
    @Override
    void addElem(Map<String, Object> elem) throws ParseException {
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(elem.get("data").toString());
        nuovo.setData(date);
        nuovo.setListaConti((Set<Account>) elem.get("lista conti usati nel movimento"));
        if(listaMovimenti.contains(nuovo)){
            System.out.println("movimento già registrato");
        }
        else listaMovimenti.add(nuovo);
    }
 }

