package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
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

	private final static String data = "Data Movimento";
	private final static String lista = "Lista Conti Mossi";
	private DBDataModel db;
	LinkedList<Movement> listaMovimenti;

	public MovementsModel(DBDataModel db) {
		this.db = db;
	}

	@Override
	protected void addElem(Map<String, Object> elem) {
		Movement m = new Movement((Date) elem.get(data), (List<Account>) elem.get(lista));
		// chiedere a fede se va bene il cast
		if (listaMovimenti.contains(m) || m.equals(null)) {
			// throw new Exception("elemento già esistente"); ragionarci e
			// correggere
		}
		listaMovimenti.add(m);
		// qui si richiamerà la funzione per modificare i conti ->
		// APPLICANDO gli effetti di questo movimento
		db.setMoviments(listaMovimenti);

	}

	@Override
	public void editElem(IDataTableModel obj, Map<String, Object> elemDaModificare) {
		// TODO Auto-generated method stub

	}

	@Override
	protected Map<String, Object> getMap() {
		Map<String, Object> mappa = new HashMap<>();
		List<String> listaInfo = new ArrayList<>(3);
		mappa.put(data, new Date());
		mappa.put(lista, new LinkedList<>(Arrays.asList(listaInfo)));
		return mappa;
	}

	Map<String, Object> getMap(Movement obj) {
		Map<String, Object> mappa = new HashMap<>();
		mappa.put(data, obj.getData());
		mappa.put(lista, obj.getListaConti()); // pensarci bene e correggere
		return mappa;
	}

	@Override
	public LinkedList<Movement> load() {
		return new LinkedList<Movement>(db.getMoviments());
	}

	LinkedList<Movement> load(Date da, Date a) throws Exception {
		LinkedList<Movement> filtroData = new LinkedList<>();
		if (da == null && a == null) {
			throw new IllegalArgumentException("date non valide");
		} else
			for (Movement m : db.getMoviments()) {
				if (m.getData().equals(da) || m.getData().equals(a) || m.getData().after(da) && m.getData().before(a)) {
					filtroData.add(m);
				}
			}
		return filtroData;
	}

	@Override
	public void remove(IDataTableModel elemDaEliminare) {

		if (listaMovimenti.contains(elemDaEliminare)) {
			listaMovimenti.remove(elemDaEliminare);
			// qui si richiamerà la funzione per modificare i conti ->
			// ANNULLANDO gli effetti di questo movimento
		}
	}

}
