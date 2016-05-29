package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.management.InstanceNotFoundException;

import dataModel.DBDataModel;
import dataModel.IDataTableModel;
import dataModel.Product;

/**
 * Classe implementativa per la gestione dei prodotti dell'azienda e la loro
 * modellazione.
 * 
 * @author Diego
 *
 */

public class ProductsModel implements ModelInterface {

	private final static String nome = "NProdotto";
	private final static String codiceA = "CodiceAcquisto";
	private final static String codiceV = "CodiceVendita";
	private final static String categoria = "Categoria";
	private final static String descrizione = "Descrizione";
	private final static String prezzo = "Prezzo";
	private final static String rimanenze = "Rimanenze"; // string di scorta
	private int scorta;
	private boolean trovato = false;

	LinkedList<Product> listaProdotti;

	private DBDataModel db;

	public ProductsModel(DBDataModel db) {
		this.db = db;
	}

	@Override
	public void add(Map<String, Object> elem) throws IllegalArgumentException {

		if (elem.get(nome) == "") {
			throw new IllegalArgumentException("Nome non valido. Riprovare.");
		}

		if (elem.get(codiceA) == "") {
			throw new IllegalArgumentException("Codice Acquisto non valido. Riprovare.");
		}

		if (elem.get(codiceV) == "") {
			throw new IllegalArgumentException("Codice Vendita non valido. Riprovare.");
		}

		if (elem.get(categoria) == "") {
			throw new IllegalArgumentException("Categoria non valida. Riprovare.");
		}

		if (elem.get(descrizione) == "") { // indispensabile?
			throw new IllegalArgumentException("Descrizione non valida. Riprovare.");
		}

		if (elem.get(prezzo) == "") {
			throw new IllegalArgumentException("Prezzo non valido. Riprovare.");
		}

		// if (elem.get(rimanenze) == "" ){
		// throw new IllegalArgumentException("Rimanenze non valide.
		// Riprovare.");
		// }
		//

		if (listaProdotti.contains(elem)) {
			throw new IllegalArgumentException("Elemento gia' esistente!");
		} else {
			Product nuovoprodotto = new Product(elem.get(nome).toString(), (Integer) elem.get(codiceA),
					(Integer) elem.get(codiceV), (Integer) elem.get(scorta), 0, elem.get(descrizione).toString(),
					elem.get(categoria).toString(), (Integer) elem.get(prezzo));
			listaProdotti.add(nuovoprodotto);
			db.setProducts(listaProdotti);
		}
	}

	@Override
	public void edit(IDataTableModel obj, Map<String, Object> infoDaModificare)
			throws InstanceNotFoundException {

		((Product) obj).setNome((String) infoDaModificare.get(nome));
		((Product) obj).setCod_acquisto((Integer) infoDaModificare.get(codiceA));
		((Product) obj).setCod_vendita((Integer) infoDaModificare.get(codiceV));
		((Product) obj).setCategoria((String) infoDaModificare.get(categoria));
		((Product) obj).setDescrizione((String) infoDaModificare.get(descrizione));
		((Product) obj).setPrezzovendita((Integer) infoDaModificare.get(prezzo));
		((Product) obj).setScorta((Integer) infoDaModificare.get(rimanenze));

		if (trovato == false) {
			throw new InstanceNotFoundException("Elemento da modificare non presente.");
		}
	}

	@Override
	public Map<String, Object> getFilterMap() {
		Map<String, Object> mappaFiltro = new HashMap<>();
		mappaFiltro.put(nome, new String(""));
		mappaFiltro.put(codiceA, new String(""));
		mappaFiltro.put(codiceV, new String(""));
		mappaFiltro.put(categoria, new String(""));
		return mappaFiltro;
	}

	@Override
	public Map<String, Object> getMap(IDataTableModel obj) {

		if (obj == null) {
			Map<String, Object> mappaVuota = new HashMap<>();

			mappaVuota.put(nome, new String(""));
			mappaVuota.put(codiceA, new Integer(0));
			mappaVuota.put(codiceV, new Integer(0));
			mappaVuota.put(categoria, new String(""));
			mappaVuota.put(descrizione, new String(""));
			mappaVuota.put(prezzo, new Integer(0));
			mappaVuota.put(rimanenze, new Integer(0));
			return mappaVuota;

		} else {
			if (obj instanceof Product) {
				Map<String, Object> mappaPiena = new HashMap<>();
				mappaPiena.put(nome, ((Product) obj).getNome());
				mappaPiena.put(codiceA, ((Product) obj).getCod_acquisto());
				mappaPiena.put(codiceV, ((Product) obj).getCod_vendita());
				mappaPiena.put(categoria, ((Product) obj).getCategoria());
				mappaPiena.put(descrizione, ((Product) obj).getDescrizione());
				mappaPiena.put(prezzo, ((Product) obj).getPrezzovendita());
				mappaPiena.put(rimanenze, ((Product) obj).getScorta());
				return mappaPiena;
			} else {
				throw new IllegalArgumentException("Valori non validi, riprovare.");
			}
		}

	}

	@Override
	public LinkedList<Product> load() {
		return new LinkedList<Product>(listaProdotti);
	}

	@Override
	public LinkedList<? extends IDataTableModel> load(Map<String, Object> mappaFiltro)
			throws InstanceNotFoundException {
		LinkedList<Product> listaFiltrata = new LinkedList<>();
		if (mappaFiltro.get(nome) != null) {
			for (Product controllofiltro : listaProdotti) {
				if (controllofiltro.getNome().contentEquals(nome)) {
					listaFiltrata.add(controllofiltro);
				}
			}
		}

		if (mappaFiltro.get(codiceA) != null) {
			for (Product controllofiltro : listaProdotti) {
				if (controllofiltro.getCod_acquisto() == Integer.parseInt(mappaFiltro.get(codiceV).toString())) {
					listaFiltrata.add(controllofiltro);
				} else {
					for (Product doppiofiltro : listaFiltrata) {
						if (doppiofiltro.getCod_acquisto() != Integer.parseInt(mappaFiltro.get(codiceV).toString())) {
							listaFiltrata.remove(doppiofiltro);
						}
					}
				}
			}

		}
		if (mappaFiltro.get(codiceV) != null) {
			for (Product controllofiltro : listaProdotti) {
				if (controllofiltro.getCod_vendita() == Integer.parseInt(mappaFiltro.get(codiceV).toString())) {
					listaFiltrata.add(controllofiltro);
				} else {
					for (Product doppiofiltro : listaFiltrata) {
						if (doppiofiltro.getCod_vendita() != Integer.parseInt(mappaFiltro.get(codiceV).toString())) {
							listaFiltrata.remove(doppiofiltro);
						}
					}
				}
			}

		}

		if (mappaFiltro.get(categoria) != null) {
			for (Product controllofiltro : listaProdotti) {
				if (controllofiltro.getCategoria().contentEquals(categoria)) {
					listaFiltrata.add(controllofiltro);
				}
			}
		}

		if (listaFiltrata.isEmpty()) {
			throw new InstanceNotFoundException("Nella lista non sono presenti elementi che soddisfano i filtri.");
		}

		return null;
	}

	@Override
	public void remove(IDataTableModel elem) {
		if (listaProdotti.contains(elem)) {
			// controllo scorta = 0? -> Y = ok cancella N = errore
			if ((listaProdotti.contains(scorta > 0))) {
				throw new IllegalArgumentException(
						"Hai ancora rimanenze in magazzino di questo prodotto, non puoi eliminarlo.");
			}
			listaProdotti.remove(elem);
		} else {
			throw new IllegalArgumentException("Elemento non trovato.");
		}
	}

	@Override
	public DBDataModel saveDBAndClose() {
		db.setProducts(listaProdotti); // Sposto i dati dalla lista interna al
										// DB
		return db;// e restituisco
	}

}
