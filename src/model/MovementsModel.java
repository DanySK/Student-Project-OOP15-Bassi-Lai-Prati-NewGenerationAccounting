package model;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

import dataModel.DBDataModel;
import dataModel.IDataTableModel;
import dataModel.Movement;
import dataModel.Operation;

/**
 * classe implementativa per la gestione dell'anagrafica dei movimenti
 * 
 * @author niky
 *
 */
public class MovementsModel extends AbstractModel {

	private final static String DATA = "Data Movimento";
	private final static String LISTA = "Lista Conti Mossi";
	private DBDataModel db;
	private LinkedList<Movement> listaMovimenti;
	private float temp;

	public MovementsModel(DBDataModel db) {
		this.db = db;
	}

	@Override
	protected void addElem(Map<String, Object> elem) throws InstanceAlreadyExistsException {
		@SuppressWarnings("unchecked")
        Movement m = new Movement((Date) elem.get(DATA), (LinkedList<Operation>) elem.get(LISTA));
		// chiedere a fede se va bene il cast
		if (listaMovimenti.contains(m)) {
			throw new InstanceAlreadyExistsException("elemento già esistente");
		}
		if (m.getData().equals(null) || m.getListaConti().isEmpty()) {
			throw new IllegalArgumentException("elemento da inserire non valido");
		}
		listaMovimenti.add(m);
		AccountsModel a = new AccountsModel(db);
		for (Operation op : m.getListaConti()) {
                    a.updateAccounts(op);
            }
		

	}

	@SuppressWarnings("unchecked")
    @Override
	public void editElem(IDataTableModel obj, Map<String, Object> elemDaModificare) {
		if (obj.getClass().equals(Movement.class)){
		    Movement m = new Movement(null,null);
		    m.setData((Date) elemDaModificare.get(DATA));
		    m.setListaConti((List<Operation>) elemDaModificare.get(LISTA));
		    for(Movement mov:listaMovimenti){
		        if (mov.equals(obj)){
		           // remove(mov);
		            //add(elemDaModificare);
		        }
		    }
		}
	}

	@Override
	public Map<String, Object> getMap() {
		Map<String, Object> mappa = new HashMap<>();
		mappa.put(DATA, new Date());
		mappa.put(LISTA, new LinkedList<Operation>());
		return mappa;
	}

	public Map<String, Object> getMap(Movement obj) {
		Map<String, Object> mappa = new HashMap<>();
		mappa.put(DATA, obj.getData());
		mappa.put(LISTA, obj.getListaConti());
		return mappa;
	}

	@Override
	public LinkedList<Movement> load() {
		return new LinkedList<Movement>(db.getMoviments());
	}

	LinkedList<Movement> load(Date da, Date a) throws IllegalArgumentException {
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
	public void remove(IDataTableModel elemDaEliminare) throws InstanceNotFoundException {
		if (elemDaEliminare.getClass().equals(Movement.class)) {
			Movement m = (Movement) elemDaEliminare;
			if (m.getData() == null || m.getListaConti().isEmpty()) {
				throw new IllegalArgumentException("elemento non valido");
			} else {
				if (listaMovimenti.contains(m)) {
					listaMovimenti.remove(m);
					AccountsModel a = new AccountsModel(db);
					for (Operation op : m.getListaConti()) {
						temp = op.getAvere();
						op.setAvere(op.getDare());
						op.setDare(temp);
						a.updateAccounts(op);
					}
				} else {
					throw new InstanceNotFoundException("elemento da eliminare non trovato");
				}
			}
		}
	}

	@Override
	public DBDataModel saveDBAndClose() {
		db.setMoviments(listaMovimenti);
		return db;
	}
}
