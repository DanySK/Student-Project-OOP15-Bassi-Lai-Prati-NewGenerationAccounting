package model;

import java.util.LinkedList;
import java.util.Map;

import dataModel.Customers_Suppliers;
import dataModel.DBDataModel;
import dataModel.IDataTableModel;

/**
 * Classe implementativa per la gestione dell'anagrafica clienti\fornitori.
 * 
 * @author Diego
 *
 */

public class CustomersSuppliersModel extends AbstractModel {

	private final String CF = "CF";

	private final String CITTA = "Citt�";
	private final String COGNOME = "Cognome";
	DBDataModel db;
	private final String INDIRIZZO = "Indirizzo";
	LinkedList<Customers_Suppliers> listaRapportiC;
	private final String NOME = "Nome";

	/* listaRapportiC = lista rapporti commerciali */

	private final String TELEFONO = "Telefono";

	public CustomersSuppliersModel(DBDataModel db) {
		this.db = db;
	}

	@Override
	protected void addElem(Map<String, Object> elem) throws IllegalArgumentException {
		Customers_Suppliers rapporto = null; // TODO
		listaRapportiC.add(rapporto);
	}

	@Override
	protected void editElem(IDataTableModel obj, Map<String, Object> ifoDaModificare) {
		listaRapportiC.remove(obj);

		addElem(ifoDaModificare);

	}

	@Override
	public Map<String, Object> getMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<Customers_Suppliers> load() {

		return new LinkedList<Customers_Suppliers>();
		// new Customers_Suppliers(01, "lol1lolaa" ,"Faenza", "X", "casatua",
		// "boh", "?", "0546 660546", "CLIENTE", 0, 1000)));
	}

	@Override
	public void remove(IDataTableModel elem) {
		if (listaRapportiC.contains(elem)) {
			listaRapportiC.remove(elem);
		} else {
			throw new IllegalArgumentException("Elemento non trovato.");
		}
	}

	@Override
	public DBDataModel saveDBAndClose() {
		// TODO Auto-generated method stub
		return null;
	}
}
