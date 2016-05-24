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

public class ProductsModel extends AbstractModel {

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
	protected void addElem(Map<String, Object> elem) throws IllegalArgumentException {
		if (elem.get(nome) == "" || elem.get(codiceA) == "" || elem.get(codiceV) == "" || elem.get(categoria) == ""
				|| elem.get(descrizione) == "" || elem.get(prezzo) == "") {
			throw new IllegalArgumentException("Uno o pi� valori inseriti risultano non validi. Riprovare.");
		}
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
	protected void editElem(IDataTableModel obj, Map<String, Object> infoDaModificare)
			throws InstanceNotFoundException {
		if (!listaProdotti.contains(obj)) {
			throw new InstanceNotFoundException("Elemento da modificare non presente, riprovare.");
		} else {
			if (obj.getClass().equals(Product.class)) {
				Product cerca = (Product) obj;
				for (Product elem : listaProdotti) {
					if (elem.getNome().equals(cerca.getNome())) {
						elem.setNome(cerca.getNome());
						trovato = true;
					}
				}
				if (trovato == false) {
					throw new InstanceNotFoundException("Elemento da modificare non presente.");
				}
				listaProdotti.remove(obj);
				addElem(infoDaModificare);
			}
		}
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

	/*
	 * public List<? extends IDataTableModel> load(String nome) throws Exception
	 * { // natura LinkedList<Product> filtroNome = new LinkedList<Product>();
	 * if (nome.equals(null)) { throw new Exception("Nome non valido."); } else
	 * for (Product filtra : listaProdotti) { if (filtra.getNome().equals(nome))
	 * { filtroNome.add(filtra); } } return filtroNome; }
	 */
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
