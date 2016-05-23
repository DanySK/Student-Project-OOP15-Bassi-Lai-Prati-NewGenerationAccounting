package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import dataEnum.Gender;
import dataEnum.KindPerson;
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
	private final String Citta = "Citta'";
	private final String Cognome = "Cognome";
	private final String Nome = "Nome";
	private final String Indirizzo = "Indirizzo";
	private final String CAP="Cap";
	private final String Credito="Credito";
	private final String Debito="Debito";
	private final String Telefono="Telefono";
	private KindPerson ruolo;
	private Gender sesso;
	
	private DBDataModel db;
	
	
	/* listaRapportiC = lista rapporti commerciali */
	LinkedList<Customers_Suppliers> listaRapportiC;
	

	public CustomersSuppliersModel(DBDataModel db) {
		this.db = db;
	}
	
	@Override
	protected void addElem(Map<String, Object> elem) throws IllegalArgumentException { //controllare il CF?
		Customers_Suppliers rapportoC = new Customers_Suppliers(elem.get(Nome).toString(),elem.get(Cognome).toString(),elem.get(CF).toString(),elem.get(Indirizzo).toString(),elem.get(Citta).toString(),(Integer)elem.get(CAP) ,elem.get(Telefono).toString(),(Gender)elem.get(sesso),(KindPerson)elem.get(ruolo),(Integer)elem.get(Credito),(Integer)elem.get(Debito));
		listaRapportiC.add(rapportoC);
	}

	@Override
	protected void editElem(IDataTableModel obj, Map<String, Object> ifoDaModificare) {
		listaRapportiC.remove(obj);

		addElem(ifoDaModificare);

	}

public Map<String, Object> getMap() {
		
		Map<String, Object> mappaRapportiC = new HashMap<>();
		
		
		mappaRapportiC.put(CF, new Integer(0));
		mappaRapportiC.put(Nome, new String());
		mappaRapportiC.put(Cognome, new String());
		mappaRapportiC.put(Indirizzo, new String());
		mappaRapportiC.put(CAP, new Integer(0));
		mappaRapportiC.put(Credito, new Integer(0));
		mappaRapportiC.put(Debito, new Integer(0));
		mappaRapportiC.put(Telefono, new String());
		return mappaRapportiC;
	}

	@Override
	public LinkedList<Customers_Suppliers> load() {

		return new LinkedList<Customers_Suppliers>();
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
		db.setCustomersSuppliers(listaRapportiC); // Sposto i dati dalla lista interna al DB
		return db;// e restituisco			
	}
}
