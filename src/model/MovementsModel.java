package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;

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
	LinkedList<Movement> listaMovimenti;
 	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public MovementsModel(DBDataModel db) {
		this.db = db;
	}

	@Override
	 public void remove(IDataTableModel elemDaEliminare){
		 
		if (listaMovimenti.contains(elemDaEliminare)) {
			listaMovimenti.remove(elemDaEliminare);
			// qui si richiamerà la funzione per modificare i conti ->
			// ANNULLANDO gli effetti di questo movimento
		}
	}

	@Override
	public void editElem(IDataTableModel obj, Map<String, Object> elemDaModificare) {
		// TODO Auto-generated method stub

	}

	@Override
	public LinkedList<Movement> load() {
		return new LinkedList<Movement>(db.getMoviments());
	}

	LinkedList<? extends IDataTableModel> load(Date da, Date a) throws Exception {
		LinkedList<Movement> filtroData = new LinkedList<>();
		if (da == null && a == null) {
			throw new Exception("date non valide");
		} else
			for (Movement m : db.getMoviments()) {
				if (m.getData().equals(da) || m.getData().equals(a) || m.getData().after(da) && m.getData().before(a)) {
					filtroData.add(m);
				}
			}
		return filtroData;
	}

	@Override
	protected void addElem(Map<String, Object> elem){
		// TODO Auto-generated method stub
		
	}

}
