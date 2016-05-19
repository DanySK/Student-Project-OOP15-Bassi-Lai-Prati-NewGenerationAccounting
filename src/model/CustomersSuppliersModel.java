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

	DBDataModel db;
	
	private final String CF = "CF";
	private final String CITTA = "Città";
	private final String COGNOME = "Cognome";
	private final String NOME = "Nome";
	private final String INDIRIZZO = "Indirizzo";
	private final String TELEFONO = "Telefono";
	
	/*listaRapportiC = lista rapporti commerciali*/
	
	LinkedList<Customers_Suppliers> listaRapportiC; 
	
	public CustomersSuppliersModel(DBDataModel db) {
		this.db = db;
	}

	@Override
	public LinkedList<Customers_Suppliers> load() {

		return new LinkedList<Customers_Suppliers>(); 
		//	new Customers_Suppliers(01, "lol1lolaa" ,"Faenza", "X", "casatua", "boh", "?", "0546 660546", "CLIENTE", 0, 1000)));
	}

	public void remove(IDataTableModel elem) {
		if (listaRapportiC.contains(elem)) {
			listaRapportiC.remove(elem);
		} else {
			throw new IllegalArgumentException("Elemento non trovato.");
		}
	}


	@Override
	protected void editElem(IDataTableModel obj, Map<String, Object> ifoDaModificare) {
		listaRapportiC.remove(obj);

		addElem(ifoDaModificare);

	}

    @Override
    protected void addElem(Map<String, Object> elem) throws IllegalArgumentException {
        Customers_Suppliers rapporto=null; //TODO
    	listaRapportiC.add(rapporto);
    }

    @Override
    protected Map<String, Object> getMap() {
        // TODO Auto-generated method stub
        return null;
    }
}
