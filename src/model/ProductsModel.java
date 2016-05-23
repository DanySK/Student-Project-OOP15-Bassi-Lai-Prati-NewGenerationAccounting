package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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
	private int scorta;
	private final static String categoria = "Categoria";
	private final static String descrizione = "Descrizione";
	private final static String prezzo = "Prezzo";
	LinkedList<Product> listaProdotti;

	private DBDataModel db;

	public ProductsModel(DBDataModel db) {
		this.db = db;
	}

	@Override
	protected void addElem(Map<String, Object> elem) throws IllegalArgumentException {
		if (listaProdotti.contains(elem)) {
			throw new IllegalArgumentException("Elemento gia' esistente!");
		} else {//fare meglio
			Product nuovoprodotto = new Product(elem.get(nome).toString(), (Integer) elem.get(codiceA),
					(Integer) elem.get(codiceV), (Integer) elem.get(scorta), 0, elem.get(descrizione).toString(),
					elem.get(categoria).toString(), (Integer) elem.get(prezzo));
			listaProdotti.add(nuovoprodotto);
			db.setProducts(listaProdotti);
		}
	}

	@Override
	protected void editElem(IDataTableModel obj, Map<String, Object> ifoDaModificare) {
		listaProdotti.remove(obj);
		addElem(ifoDaModificare);

	}

	@Override
	public Map<String, Object> getMap() {

		Map<String, Object> mappaProdotti = new HashMap<>();

		mappaProdotti.put(nome, new String());
		mappaProdotti.put(codiceA, new Integer(0));
		mappaProdotti.put(codiceV, new Integer(0));
		mappaProdotti.put(categoria, new String());
		mappaProdotti.put(descrizione, new String());
		mappaProdotti.put(prezzo, new Integer(0));
		

		return mappaProdotti;
	}

	@Override
	public LinkedList<Product> load() {
		return new LinkedList<Product>();
	}

	@Override
	public void remove(IDataTableModel elem) {
		if (listaProdotti.contains(elem)) {
			// controllo scorta = 0 -> Y = ok cancella N = errore
			if((listaProdotti.contains(scorta>0))){
				throw new IllegalArgumentException("Hai ancora rimanenze in magazzino di questo prodotto, non puoi eliminarlo.");	
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
