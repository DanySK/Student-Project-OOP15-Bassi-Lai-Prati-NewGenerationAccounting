package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;

import dataModel.Company;
import dataModel.DBDataModel;
import dataModel.IDataTableModel;

/**
 * Classe implementativa per la gestione dell'anagrafica aziende e la creazione
 * di quest'ultime.
 * 
 * @author Diego
 *
 */

public class CompanyModel extends AbstractModel {

	private final static String ragione_sociale = "Ragione Sociale";
	private final static String cap = "CAP";
	private final static String citta = "Citta'";
	private final static String provincia = "Provincia";
	private final static String indirizzo = "Indirizzo";
	private final static String telefono = "Telefono";
	private final long partitaIVA = 0; // ricordarsi controlli sulla lunghezza
	private final LinkedList<Company> listaAziende;

	public CompanyModel(final LinkedList<Company> linkedList) {
		listaAziende = linkedList;
	}

	@Override
	protected void addElem(Map<String, Object> elem) {
		// SOLO PER TEST, DA CANCELLARE
		char[] password = {};
		Company nuovaazienda = new Company(UUID.randomUUID(), password, ragione_sociale, partitaIVA, indirizzo, citta,
				0, provincia, telefono);
		listaAziende.add(nuovaazienda);
	}

	@Override
	public void editElem(IDataTableModel obj, Map<String, Object> infoDaModificare) {
		listaAziende.remove(obj);

		addElem(infoDaModificare);

	}

	/**
	 * Restituisco la mappa delle aziende
	 * @param iDataTableModel 
	 * 
	 * 
	 * 
	 */
	@Override
	public Map<String, Object> getMap(IDataTableModel iDataTableModel) {

		Map<String, Object> mappaAziende = new HashMap<>();

		mappaAziende.put(ragione_sociale, new String());
		mappaAziende.put(cap, new Integer(0));
		mappaAziende.put(citta, new String());
		mappaAziende.put(provincia, new String());
		mappaAziende.put(telefono, new Integer(0));

		return mappaAziende;
	}

	/**
	 * Controllo che la password sia giusta
	 * 
	 */

	public boolean isPasswordCorrect(final char[] password, final Company company) {
		return Arrays.equals(company.getPassword(), password);
	}

	@Override
	public LinkedList<Company> load() {
		return this.listaAziende;
	}

	@Override
	public void remove(IDataTableModel elem) {
		if (listaAziende.contains(elem)) {
			listaAziende.remove(elem);
		} else {
			throw new IllegalArgumentException("Elemento non trovato.");
		}
	}

	/**
	 * saveCompanyAndClose = saveDBAndClose senza l'utilizzo del DB.
	 */

	public LinkedList<Company> saveCompanysAndClose() {
		return listaAziende;
	}

	/**
	 * metodo inutilizzabile. usare saveCompanysAndClose()
	 */

	@Override
	public DBDataModel saveDBAndClose() {
		return null;
	}

}
