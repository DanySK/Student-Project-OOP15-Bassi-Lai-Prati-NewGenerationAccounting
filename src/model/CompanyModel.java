package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

import dataModel.Account;
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
	private final static String p_iva = "P.IVA"; //stringa che uso unicamente per il return nelle mappe.
	private final long partitaIVA = 0; // ricordarsi controlli sulla lunghezza
	//private final int length = String.valueOf(11).length();
	private boolean trovato = false;
	
	private final LinkedList<Company> listaAziende;
	
	
	
	public CompanyModel(final LinkedList<Company> linkedList) {
		listaAziende = linkedList;
	}

	@Override
	protected void addElem(Map<String, Object> elem) throws InstanceAlreadyExistsException {
		if (elem.get(ragione_sociale) == "" ||(Long) elem.get(partitaIVA) == null || elem.get(cap) == "" || elem.get(citta) == "" || elem.get(provincia) == "" || elem.get(indirizzo) == "" || elem.get(telefono) == "") {
			throw new IllegalArgumentException("Uno o più valori inseriti risultano non validi. Riprovare.");
		}
		// SOLO PER TEST, DA CANCELLARE
		char[] password = {};
		Company nuovaazienda = new Company(UUID.randomUUID(), password, ragione_sociale, partitaIVA, indirizzo, citta,
				0, provincia, telefono);
		if (listaAziende.contains(nuovaazienda)) {
			throw new InstanceAlreadyExistsException("L'elemento e' gia' presente.");
		}
		listaAziende.add(nuovaazienda);
	}

	@Override
	public void editElem(IDataTableModel obj, Map<String, Object> infoDaModificare) throws InstanceAlreadyExistsException, InstanceNotFoundException {
		
		if (!listaAziende.contains(obj)) {
			throw new InstanceNotFoundException("Elemento da modificare non presente, riprovare.");
		}else {
			if (obj.getClass().equals(Account.class)) {
				Company cerca = (Company) obj;
				for (Company elem : listaAziende) {
					if (elem.getCodice_azienda().equals(cerca.getCodice_azienda())) {
						elem.setCodice(cerca.getCodice_azienda());
						trovato = true;
					}
				}
				if (trovato == false) {
					throw new InstanceNotFoundException("Elemento da modificare non presente.");
				}
		listaAziende.remove(obj);

		addElem(infoDaModificare);
		}
	}
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
	 * Restituisco la mappa delle aziende
	 * 
	 * @param iDataTableModel
	 * 
	 * 
	 * 
	 */
	@Override
	public Map<String, Object> getMap(IDataTableModel obj) {

		if (obj == null) {
			Map<String, Object> mappaVuota= new HashMap<>();	
			
			mappaVuota.put(ragione_sociale, new String());
			mappaVuota.put(cap, new Integer(0));
			mappaVuota.put(citta, new String(""));
			mappaVuota.put(provincia, new String(""));
			mappaVuota.put(telefono, new Integer(0));
			mappaVuota.put(p_iva, new String(""));
			return mappaVuota;
			
		} else {
			if (obj instanceof Company){
				Map<String, Object> mappaPiena = new HashMap<>();
				mappaPiena.put(ragione_sociale, ((Company) obj).getRagione_sociale());
				mappaPiena.put(cap, ((Company) obj).getCap());
				mappaPiena.put(citta, ((Company) obj).getCitta());
				mappaPiena.put(provincia, ((Company) obj).getProvincia());
				mappaPiena.put(telefono, ((Company) obj).getTel());
				mappaPiena.put(p_iva, ((Company) obj).getPartita_iva());
				return mappaPiena;
			}else {
				throw new IllegalArgumentException("Valori non validi, riprovare.");
			}
		}
			
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
		return new LinkedList <Company>(listaAziende);
	}
	
	public List<? extends IDataTableModel> load(String Ragione_sociale) throws Exception { 																			// natura
		LinkedList<Company> filtroRagioneSociale = new LinkedList<Company>();
		if (Ragione_sociale.equals(null)) {
			throw new Exception("Ragione Sociale non valida.");
		} else
			for (Company filtra : listaAziende) {
				if (filtra.getRagione_sociale().equals(Ragione_sociale)) {
					filtroRagioneSociale.add(filtra);
				}
			}
		return filtroRagioneSociale;
	}
	
	public List<? extends IDataTableModel> load(Long p_iva) throws Exception { 																			
		LinkedList<Company> filtroPIVA = new LinkedList<Company>();
		if (p_iva.equals(null)) {
			throw new Exception("P.IVA non valida!");
		} else
			for (Company filtra : listaAziende) {
				if (filtra.getPartita_iva()==(long)p_iva) {
					filtroPIVA.add(filtra);
				}
			}
		return filtroPIVA;
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
