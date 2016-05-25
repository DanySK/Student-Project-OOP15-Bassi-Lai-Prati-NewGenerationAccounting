package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

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
	private final static String p_iva = "P.IVA"; 
	private final char[] password = {};
	
	private boolean trovato = false;

	private final LinkedList<Company> listaAziende;

	public CompanyModel(final LinkedList<Company> linkedList) {
		listaAziende = linkedList;
	}

	@Override
	protected void addElem(Map<String, Object> elem) throws InstanceAlreadyExistsException {
		
		if (elem.get(ragione_sociale) == "" ){
			throw new IllegalArgumentException("Ragione sociale non valida. Riprovare.");
		}
		
		if (elem.get(password) == ""){
			throw new IllegalArgumentException("Password non valida. Riprovare.");
		}
		
		if (elem.get(p_iva) == ""){
			throw new IllegalArgumentException("PartitaIVA non valida. Riprovare.");
		}
		
		if (elem.get(cap) == ""){
			throw new IllegalArgumentException("CAP non valido. Riprovare.");
		
		}
		
		if (elem.get(citta) == ""){
			throw new IllegalArgumentException("Citta' non valida. Riprovare.");
		
		}
		if ( elem.get(provincia) == ""){
			throw new IllegalArgumentException("Provincia' non valida. Riprovare.");
		
		}
		
		if (elem.get(indirizzo) == ""){
			throw new IllegalArgumentException("Indirizzo non valido. Riprovare.");
		
		}
		
		if (elem.get(telefono) == ""){
			throw new IllegalArgumentException("Numero di telefono non valido. Riprovare.");
		
		}
		Company nuovaazienda = new Company(UUID.randomUUID(),(char[]) elem.get(password), (String) elem.get(ragione_sociale),(String) elem.get(p_iva),(String) elem.get(indirizzo),(String) elem.get(citta),
			(int) elem.get(cap),(String) elem.get(provincia),(String)elem.get(indirizzo));
		
		if (listaAziende.contains(nuovaazienda)) {
			throw new InstanceAlreadyExistsException("L'elemento e' gia' presente.");
		}
		listaAziende.add(nuovaazienda);
	}

	@Override
	public void editElem(IDataTableModel obj, Map<String, Object> infoDaModificare)
			throws InstanceAlreadyExistsException, InstanceNotFoundException {

		if (!listaAziende.contains(obj)) {
			throw new InstanceNotFoundException("Elemento da modificare non presente, riprovare.");
		} else {
			if (obj instanceof Company) {
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

	/**
	 * Restituisco la mappa delle aziende
	 * 
	 * @param iDataTableModel
	 */
	@Override
	public Map<String, Object> getMap(IDataTableModel obj) {

		if (obj == null) {
			Map<String, Object> mappaVuota = new HashMap<>();

			mappaVuota.put(ragione_sociale, new String());
			mappaVuota.put(cap, new Integer(0));
			mappaVuota.put(citta, new String(""));
			mappaVuota.put(provincia, new String(""));
			mappaVuota.put(telefono, new String(""));
			mappaVuota.put(p_iva, new String(""));
			return mappaVuota;

		} else {
			if (obj instanceof Company) {
				Map<String, Object> mappaPiena = new HashMap<>();
				mappaPiena.put(ragione_sociale, ((Company) obj).getRagione_sociale());
				mappaPiena.put(cap, ((Company) obj).getCap());
				mappaPiena.put(citta, ((Company) obj).getCitta());
				mappaPiena.put(provincia, ((Company) obj).getProvincia());
				mappaPiena.put(telefono, ((Company) obj).getTel());
				mappaPiena.put(p_iva, ((Company) obj).getPartita_iva());
				return mappaPiena;
			} else {
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
		return new LinkedList<Company>(listaAziende);
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

	@Override
	public Map<String, Object> getFilterMap() {
        Map<String, Object> mappaFiltro = new HashMap<>();
        mappaFiltro.put(ragione_sociale, new String(""));
        mappaFiltro.put(p_iva, new String(""));
		return mappaFiltro;
	}

	@Override
	public LinkedList<? extends IDataTableModel> load(Map<String, Object> mappaFiltro) throws InstanceNotFoundException {
        LinkedList<Company> listaFiltrata = new LinkedList<>();
        if (mappaFiltro.get(ragione_sociale) != null) {
            for (Company controllofiltro : listaAziende) {
                if (controllofiltro.getRagione_sociale().contentEquals(ragione_sociale)){
                    listaFiltrata.add(controllofiltro);
                }
            }
        }
     
           if (mappaFiltro.get(p_iva) != null) {
               for (Company controllofiltro : listaAziende) {
              	if(controllofiltro.getPartita_iva() == mappaFiltro.get(p_iva)) {   
                	listaFiltrata.add(controllofiltro);
                  } else {
                      for (Company doppiofiltro : listaFiltrata) { 
                          if (doppiofiltro.getPartita_iva()!= mappaFiltro.get(p_iva)){
                              listaFiltrata.remove(doppiofiltro);
                          }
                      }
              }
           }
            if (listaFiltrata.isEmpty()) {
                throw new InstanceNotFoundException("Nella lista non sono presenti elementi che soddisfano i filtri.");
            }
        
}
           return null;
}
}