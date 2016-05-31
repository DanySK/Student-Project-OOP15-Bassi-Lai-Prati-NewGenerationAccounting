package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

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

public class CustomersSuppliersModel implements ModelInterface {

	private final String CF = "CF";
	private final String Citta = "Citta'";
	private final String Cognome = "Cognome";
	private final String Nome = "Nome";
	private final String Indirizzo = "Indirizzo";
	private final String CAP = "Cap";
	private final String Credito = "Credito";
	private final String Debito = "Debito";
	private final String Telefono = "Telefono";
	private final String Ruolostring = "RuoloString";
	private final String Sessostring = "SessoString";
	private KindPerson ruolo;
	private Gender sesso;
	private boolean trovato = false;

	private DBDataModel db;

	/* listaRapportiC = lista rapporti commerciali */
	LinkedList<Customers_Suppliers> listaRapportiC;

	public CustomersSuppliersModel(DBDataModel db) {
		this.db = db;
		this.listaRapportiC = db.getCustomersSuppliers();
	}

	/**
	 * Metodo per la creazione di un nuovo cliente o fornitore .
	 * 
	 * 
	 */

	@Override
	public void add(Map<String, Object> elem) throws IllegalArgumentException, InstanceAlreadyExistsException { // controllare

		if (elem.get(CF) == "") {
			throw new IllegalArgumentException("CF non valido. Riprovare.");
		}

		if (elem.get(Citta) == "") {
			throw new IllegalArgumentException("Citta' non valida. Riprovare.");
		}

		if (elem.get(Cognome) == "") {
			throw new IllegalArgumentException("Cognome non valido. Riprovare.");
		}

		if (elem.get(Nome) == "") {
			throw new IllegalArgumentException("Nome non valido. Riprovare.");

		}

		if (elem.get(Indirizzo) == "") {
			throw new IllegalArgumentException("Indirizzo non valido. Riprovare.");

		}
		if (elem.get(CAP) == null) {
			throw new IllegalArgumentException("CAP non valido. Riprovare.");

		}

		if (elem.get(Telefono) == "") {
			throw new IllegalArgumentException("Numero di telefono non valido. Riprovare.");
		}

		if (elem.get(Ruolostring) == KindPerson.NESSUNO) {
			throw new IllegalArgumentException("Ruolo non valido. Riprovare.");

		}

		if (elem.get(Sessostring) == Gender.NESSUNO) {
			throw new IllegalArgumentException("Gender non valido. Riprovare.");

		}

		Customers_Suppliers rapportoC = new Customers_Suppliers((String) elem.get(Nome), (String) elem.get(Cognome),
				(String) elem.get(CF), (String) elem.get(Indirizzo), (String) elem.get(Citta), (int) elem.get(CAP),
				(String) elem.get(Telefono), (Gender) elem.get(sesso), (KindPerson) elem.get(ruolo),
				(int) elem.get(Debito), (int) elem.get(Credito));

		if (listaRapportiC.contains(rapportoC)) {
			throw new InstanceAlreadyExistsException("L'elemento e' gia' presente.");
		}
		listaRapportiC.add(rapportoC);
	}

	/**
	 * Metodo che permette di modificare i dati di un cliente o di un fornitore.
	 * 
	 * 
	 */

	@Override
	public void edit(IDataTableModel obj, Map<String, Object> infoDaModificare)
			throws InstanceNotFoundException, InstanceAlreadyExistsException, IllegalArgumentException {

		if (!listaRapportiC.contains(obj)) {
			throw new InstanceNotFoundException("Elemento da modificare non presente, riprovare.");
		} else {

			((Customers_Suppliers) obj).setCf((String) infoDaModificare.get(CF));
			((Customers_Suppliers) obj).setCitta((String) infoDaModificare.get(Citta));
			((Customers_Suppliers) obj).setCognome((String) infoDaModificare.get(Cognome));
			((Customers_Suppliers) obj).setNome((String) infoDaModificare.get(Nome));
			((Customers_Suppliers) obj).setIndirizzo((String) infoDaModificare.get(Indirizzo));
			((Customers_Suppliers) obj).setCap((Integer) infoDaModificare.get(CAP));
			((Customers_Suppliers) obj).setRuolo((KindPerson) infoDaModificare.get(ruolo));
			((Customers_Suppliers) obj).setSesso((Gender) infoDaModificare.get(sesso));
			((Customers_Suppliers) obj).setTelefono((String) infoDaModificare.get(Telefono));
		}
		if (trovato == false) {
			throw new InstanceNotFoundException("Elemento da modificare non presente.");
		}

	}

	/**
	 * Metodo per la creazione di filtri per i clienti o fornitori, seconda il
	 * Codice Fiscale o la citt�.
	 * 
	 * 
	 */

	@Override
	public Map<String, Object> getFilterMap() {
		Map<String, Object> mappaFiltro = new HashMap<>();
		mappaFiltro.put(CF, new String(""));
		// mappaFiltro.put(Citta, new String(""));
		return mappaFiltro;
	}

	/**
	 * Metodo per la creazione delle mappe, sia con che senza valori.
	 * 
	 * 
	 */

	@Override
	public Map<String, Object> getMap(IDataTableModel obj) {

		if (obj == null) {
			Map<String, Object> mappaVuota = new HashMap<>();

			mappaVuota.put(CF, new String(""));
			mappaVuota.put(Citta, new String(""));
			mappaVuota.put(Cognome, new String(""));
			mappaVuota.put(Nome, new String(""));
			mappaVuota.put(Indirizzo, new String(""));
			mappaVuota.put(CAP, new Integer(0));
			mappaVuota.put(Telefono, new String(""));
			mappaVuota.put(Ruolostring, KindPerson.NESSUNO);
			mappaVuota.put(Sessostring, Gender.NESSUNO);

			return mappaVuota;

		} else {
			if (obj instanceof Customers_Suppliers) {

				Map<String, Object> mappaPiena = new HashMap<>();
				mappaPiena.put(CF, ((Customers_Suppliers) obj).getCf());
				mappaPiena.put(Citta, ((Customers_Suppliers) obj).getCitta());
				mappaPiena.put(Cognome, ((Customers_Suppliers) obj).getCognome());
				mappaPiena.put(Nome, ((Customers_Suppliers) obj).getNome());
				mappaPiena.put(Indirizzo, ((Customers_Suppliers) obj).getIndirizzo());
				mappaPiena.put(CAP, ((Customers_Suppliers) obj).getCap());
				mappaPiena.put(Telefono, ((Customers_Suppliers) obj).getTelefono());
				mappaPiena.put(Ruolostring, ((Customers_Suppliers) obj).getRuolo());
				mappaPiena.put(Sessostring, ((Customers_Suppliers) obj).getSesso());

				return mappaPiena;
			} else {
				throw new IllegalArgumentException("Valori non validi, riprovare.");
			}
		}

	}

	/**
	 * Metodo per la restituzione di una LinkedList di Customers_Suppliers.
	 * 
	 * 
	 */

	@Override
	public LinkedList<Customers_Suppliers> load() {

		return new LinkedList<Customers_Suppliers>(listaRapportiC);
	}

	@Override
	public LinkedList<Customers_Suppliers> load(Map<String, Object> mappaFiltro) throws InstanceNotFoundException {

		LinkedList<Customers_Suppliers> listaFiltrata = new LinkedList<>();

		if (mappaFiltro.get(CF) != null) {
			for (Customers_Suppliers controllofiltro : listaRapportiC) {
				if (controllofiltro.getCf().contentEquals(CF)) {
					listaFiltrata.add(controllofiltro);
				}
			}
		}

		// if (mappaFiltro.get(Citta) != null) {
		// for (Customers_Suppliers controllofiltro : listaRapportiC) {
		// if (controllofiltro.getCitta().contentEquals(Citta)) {
		// listaFiltrata.add(controllofiltro);
		// }
		// }
		//
		// }
		if (listaFiltrata.isEmpty()) {
			throw new InstanceNotFoundException("Nella lista non sono presenti elementi che soddisfano i filtri.");
		}
		return listaFiltrata;
	}

	@Override
	public void remove(IDataTableModel elemDaEliminare) throws InstanceNotFoundException { // dati
		if (elemDaEliminare.getClass().equals(Customers_Suppliers.class)) {
			Customers_Suppliers rimuovi = (Customers_Suppliers) elemDaEliminare;
			for (Customers_Suppliers elem : listaRapportiC) {
				if (elem.getCf().equals(rimuovi.getCf())) {
					trovato = true;
					if (elem.getCredito() == 0 || elem.getDebito() == 0) {
						listaRapportiC.remove(elem);
						db.setCustomersSuppliers(listaRapportiC);
					} else {
						throw new IllegalArgumentException(
								"Esiste ancora un credito o un debito verso questa persona, dunque rimozione non effettuabile.");
					}
				}
			}
			if (trovato == false) {
				throw new InstanceNotFoundException("Elemento da eliminare non trovato, riprovare.");
			}
		} else {
			throw new IllegalArgumentException("Elemento non valido, riprovare.");
		}
	}

	/**
	 * Metodo per spostare i dati dalla lista interna al database e restituire
	 * quest'ultimo.
	 * 
	 * 
	 */

	@Override
	public DBDataModel saveDBAndClose() {
		db.setCustomersSuppliers(listaRapportiC);

		return db;
	}
}
