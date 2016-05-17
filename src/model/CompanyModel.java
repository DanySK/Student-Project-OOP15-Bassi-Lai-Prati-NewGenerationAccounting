package model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;

import dataModel.Company;
import dataModel.DBDataModel;
import dataModel.IDataTableModel;

/**
 * Classe implementativa per la gestione dell'anagrafica aziende
 * 
 * @author Diego
 *
 */

public class CompanyModel extends AbstractModel {

	LinkedList<Company> listaAziende;
	private DBDataModel db;

	public CompanyModel(DBDataModel db) {
		this.db = db;
		listaAziende = db.getCompanys();
	}

	@Override
	public LinkedList<Company> load() {
		return new LinkedList<>(Arrays.asList(new Company(1, "password", "societ� 1", 123456789, "via dalle palle, 3",
				"cittadimmerda", 11111, "Levati (dal cazzo)", "1100110011")));
	}

	@Override
	protected void addElem(Map<String, Object> mappa){
		Company azienda = null; // TODO
		listaAziende.add(azienda);
		db.setCompanys(listaAziende);
	}

	@Override
	public void remove(IDataTableModel elem) {
		if (listaAziende.contains(elem)) {
			listaAziende.remove(elem);
			db.setCompanys(listaAziende);
		} else {
			throw new IllegalArgumentException("sto elemento non esiste.");
		}
	}

	public boolean isPasswordCorrect(final char[] password, Company company) {
		return company.getPassword().equals(password);
	}

	@Override
	public void editElem(IDataTableModel obj, Map<String, Object> infoDaModificare) {
		listaAziende.remove(obj);
		
		addElem(infoDaModificare);
		
	}

	
}
