package model;

import java.util.LinkedList;
import java.util.Map;

import dataModel.DBDataModel;
import dataModel.IDataTableModel;
import dataModel.Product;

/**
 * Classe implementativa per la gestione dei prodotti dell'azienda
 * 
 * @author Diego
 *
 */

public class ProductsModel extends AbstractModel {

	private final static String nome = "NProdotto";
	private final static String codiceA = "CodiceAcquisto";
	private final static String codiceV = "CodiceVendita";
	private final static String scorta = "Scorta";
	private final static String categoria = "Categoria";
	private final static String descrizione = "Descrizione";
	private final static String prezzo = "Prezzo"; // ?

	LinkedList<Product> listaProdotti;

	private DBDataModel db;

	public ProductsModel(DBDataModel db) {
		this.db = db;
	}

	@Override
	protected void addElem(Map<String, Object> elem) throws IllegalArgumentException {
		if (listaProdotti.contains(elem)) {
			throw new IllegalArgumentException("Elemento già esistente!");
		} else {
			Product nuovoprodotto = new Product(elem.get(nome).toString(), (Integer) elem.get(codiceA),
					(Integer) elem.get(codiceV), (Integer) elem.get(scorta), 0, elem.get(descrizione).toString(),
					elem.get(categoria).toString(), 0);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<Product> load() {
		return new LinkedList<Product>();
	}

	@Override
	public void remove(IDataTableModel elem) {
		if (listaProdotti.contains(elem)) {
			// controllo scorta = 0 -> Y = ok cancella N = errore
			listaProdotti.remove(elem);
		} else {
			throw new IllegalArgumentException("Elemento non trovato.");
		}
	}

	@Override
	public DBDataModel saveDBAndClose() { // Sposto i dati dalla lista interna
											// al database e restituisco
											// quest'ultimo
		// TODO Auto-generated method stub
		return null;
	}

}
