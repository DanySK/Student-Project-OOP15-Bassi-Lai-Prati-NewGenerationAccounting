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

	public CompanyModel(LinkedList<Company> linkedList) {
		listaAziende = linkedList;
	}

	@Override
	protected void addElem(Map<String, Object> mappa) {
		Company azienda = null; // TODO
		listaAziende.add(azienda);
	}

	@Override
	public void editElem(IDataTableModel obj, Map<String, Object> infoDaModificare) {
		listaAziende.remove(obj);

		addElem(infoDaModificare);

	}

	@Override
	protected Map<String, Object> getMap() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isPasswordCorrect(final char[] password, final Company company) {
		return Arrays.equals(company.getPassword(), password);
	}

	@Override
	public LinkedList<Company> load() {
		char[] password = { 'p', 'w', 'd' };
		return new LinkedList<>(Arrays.asList(
				new Company(1, password, "societa 1", 123456789, "via di qua, 3", "citta", 1111, "Levati", "1100")));
	}

	@Override
	public void remove(IDataTableModel elem) {
		if (listaAziende.contains(elem)) {
			listaAziende.remove(elem);
		} else {
			throw new IllegalArgumentException("Elemento non trovato.");
		}
	}

	@Override
	public DBDataModel saveDBAndClose() {
		// TODO Auto-generated method stub
		return null;
	}

	public LinkedList<Company> saveCompanysAndClose() {
		// TODO Auto-generated method stub
		return null;
	}

}
