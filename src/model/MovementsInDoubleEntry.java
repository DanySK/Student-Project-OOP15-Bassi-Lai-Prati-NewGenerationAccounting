package model;

import java.util.HashSet;
import java.util.Set;

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

	public MovementsInDoubleEntry() {
		listaMovimenti = new HashSet<>();
	}

	@Override
	void comparisonAccount(Pair<String, Object> elemDaAggiungere) {
		// TODO Auto-generated method stub

	}

}
