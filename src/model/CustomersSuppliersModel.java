package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

public class CustomersSuppliersModel extends AbstractModel {

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
	}

	@Override
	protected void addElem(Map<String, Object> elem) throws IllegalArgumentException { // controllare
																						// il
																						// CF?
		Customers_Suppliers rapportoC = new Customers_Suppliers(elem.get(Nome).toString(), elem.get(Cognome).toString(),
				elem.get(CF).toString(), elem.get(Indirizzo).toString(), elem.get(Citta).toString(),
				(Integer) elem.get(CAP), elem.get(Telefono).toString(), (Gender) elem.get(sesso),
				(KindPerson) elem.get(ruolo), (Integer) elem.get(Credito), (Integer) elem.get(Debito));
		listaRapportiC.add(rapportoC);
	}

	@Override
	protected void editElem(IDataTableModel obj, Map<String, Object> ifoDaModificare) {
		listaRapportiC.remove(obj);

		addElem(ifoDaModificare);

	}

	@Override
	public Map<String, Object> getMap(IDataTableModel obj) {

		if (obj == null) {
			Map<String, Object> mappaVuota = new HashMap<>();

			mappaVuota.put(CF, new String(""));
			mappaVuota.put(Citta, new String(""));
			mappaVuota.put(Cognome, new String(""));
			mappaVuota.put(Nome, new String(""));
			mappaVuota.put(Indirizzo, new String(""));
			mappaVuota.put(CAP, new String(""));
			mappaVuota.put(Credito, new Integer(0));
			mappaVuota.put(Debito, new Integer(0));
			mappaVuota.put(Telefono, new String(""));
			mappaVuota.put(Ruolostring, KindPerson.CLIENTE);
			mappaVuota.put(Ruolostring, KindPerson.FORNITORE);
			mappaVuota.put(Sessostring, Gender.F);
			mappaVuota.put(Sessostring, Gender.M);

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
				mappaPiena.put(Credito, ((Customers_Suppliers) obj).getCredito());
				mappaPiena.put(Debito, ((Customers_Suppliers) obj).getDebito());
				mappaPiena.put(Telefono, ((Customers_Suppliers) obj).getTelefono());
				mappaPiena.put(Ruolostring, ((Customers_Suppliers) obj).getRuolo());
				mappaPiena.put(Sessostring, ((Customers_Suppliers) obj).getSesso());

				return mappaPiena;
			} else {
				throw new IllegalArgumentException("Valori non validi, riprovare.");
			}
		}

	}

	/* @Override
	public LinkedList<Customers_Suppliers> load() {

		return new LinkedList<Customers_Suppliers>();
	}

	public List<? extends IDataTableModel> load(String Cf) throws Exception { // natura
		LinkedList<Customers_Suppliers> filtroCF = new LinkedList<Customers_Suppliers>();
		if (Cf.equals(null)) {
			throw new Exception("Cf non valido.");
		} else
			for (Customers_Suppliers filtra : listaRapportiC) {
				if (filtra.getCf().equals(Cf)) {
					filtroCF.add(filtra);
				}
			}
		return filtroCF;
	}
*/
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

	@Override
	public DBDataModel saveDBAndClose() {
		db.setCustomersSuppliers(listaRapportiC); // Sposto i dati dalla lista
													// interna al DB
		return db;// e restituisco
	}

    @Override
    public Map<String, Object> getFilterMap() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LinkedList<? extends IDataTableModel> load(Map<String, Object> mappaFiltro) {
        // TODO Auto-generated method stub
        return null;
    }
}
