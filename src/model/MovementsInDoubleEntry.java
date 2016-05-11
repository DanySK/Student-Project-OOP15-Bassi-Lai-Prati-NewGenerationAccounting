package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dataModel.Account;
import dataModel.Movement;
import javafx.util.Pair;

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
    void comparisonAccount(Pair<String, Object> elemDaAggiungere) {
      //chiedere a fede come far si che questa funzione non venga chiamata
    }

    @Override
    void comparisonMovement(Pair<String, List<ArrayList<String>>> infoMovimento) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date data = dateFormat.parse(infoMovimento.getKey());
        nuovo.setData(data);
        nuovo.setListaConti(infoMovimento.getValue());
        listaMovimenti.add(nuovo);
        
        //updateAccounts(listaConti, infoMovimento);
        
        
    }

    private void updateAccounts(List<Account> listaConti, Pair<String,List<ArrayList<String>>> infoMovimento) {
    }

 }

