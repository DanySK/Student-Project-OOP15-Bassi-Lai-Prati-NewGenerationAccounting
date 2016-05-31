package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import dataEnum.Gender;
import dataEnum.KindPerson;
import dataModel.Customers_Suppliers;
import dataModel.DBDataModel;
import dataModel.IDataTableModel;
import dataModel.Item;
import dataModel.Product;

/**
 * Classe implementativa per la gestione del carrello che permette l'acquisto di
 * prodotti, coinvolgendo i movimenti dei conti e delle scorte in magazzino.
 * 
 * @author Diego
 *
 */

public class CreaFattureModel implements ModelInterface {

	private final static String prodotto = "Prodotto";
	private final static String quantita = "Quantita'";
	private final static String scorta = "Scorta";
	
	private DBDataModel db;
	

	private final LinkedList<Item> listaCarrello = new LinkedList<Item>();

	public CreaFattureModel(DBDataModel db) {
		this.db = db;
	}

/*
 * Funzione per la creazione di un nuovo carrello , con conseguente possibilit� di acquistare prodotti.
 * 
 * @author Diego
*/
	@Override
	public void add(Map<String, Object> elem) throws IllegalArgumentException {

		
		
		
		if (elem.get(prodotto) == "") {
			throw new IllegalArgumentException("Nome prodotto non valido. Riprovare.");
		}

		if (elem.get(quantita) == null) {
			throw new IllegalArgumentException("Quantita' non valida. Riprovare.");
		}

		if (listaCarrello.contains(elem)) {
			throw new IllegalArgumentException("Elemento gia' esistente!");
		} else {
			Item nuovocarrello = new Item((Product) elem.get(prodotto), (Integer) elem
					.get(quantita));
			listaCarrello.add(nuovocarrello);
		}
	}

	/**
	 * 
	 * Funzione per l'aggiornamento del DB dopo la creazione della fattura.
	 * 
	 * Crea un movimento, pi� debiti verso fornitori, meno scorte della merce
	 * richiesta.
	 * 
	 * 
	 * 
	 */
	
	public DBDataModel create(Customers_Suppliers item) {
		
		int totale=0;
		
		
		if (listaCarrello.isEmpty()) {
			throw new IllegalArgumentException("Acquisto non valido. Riprovare.");
		}else{
			
			
			
		for (Item riga : listaCarrello) {
			totale+=riga.getProdotto().getPrezzovendita() * riga.getQuantita();
			riga.getProdotto().setScorta(riga.getProdotto().getScorta()-riga.getQuantita());
		
			}
		}
		item.setDebito(totale);//Addebito il totale
		
		LinkedList<Customers_Suppliers> cs = db.getCustomersSuppliers();
		
		cs.addLast(item);
		
	
		db.setCustomersSuppliers(cs);//aggiorno prodotti
		
		return db;
	}

	/*
	 * Funzione per la modifica del carrello d'acquisto.
	 * 
	 * @author Diego
	*/	
	
	@Override
	public void edit(IDataTableModel obj, Map<String, Object> infoDaModificare) {
		if ((Product) infoDaModificare.get(scorta) != null) {

			((Item) obj).setProdotto((Product) infoDaModificare.get(prodotto));
			((Item) obj).setQuantita((Integer) infoDaModificare.get(quantita));

		}
	}

	/*
	 * Funzione per la creazione di un filtro per i prodotti.
	 * 
	 * @author Diego
	*/	
	
	@Override
	public Map<String, Object> getFilterMap() {
		Map<String, Object> mappaFiltro = new HashMap<>();
		mappaFiltro.put(prodotto, new String(""));
		return mappaFiltro;
	}

	/*
	 * Funzione per ottenere tra tutte le persone, solo i clienti.
	 * 
	 * @author Diego
	*/
	
	public LinkedList<Customers_Suppliers> getListaclienti() {

		final LinkedList<Customers_Suppliers> listaClienti = new LinkedList<Customers_Suppliers>();

		for (Customers_Suppliers controlloCliente : db.getCustomersSuppliers()) {
			if (controlloCliente.getRuolo() == KindPerson.CLIENTE) {
				listaClienti.add(controlloCliente);
			}
		}
		
		
		listaClienti.add(new Customers_Suppliers("1", "C", "Cognome", "Indirizzo", "Nome", 0, "CAP", Gender.M ,KindPerson.CLIENTE, 0, 0));
		
		return listaClienti;
	}

	/*
	 * Funzione per la creazione di una nuova mappa di classe, sia che sia ricevuta vuota o con valori al suo interno.
	 * 
	 * @author Diego
	*/
	
	@Override
	public Map<String, Object> getMap(IDataTableModel obj) {

		if (obj == null) {
			Map<String, Object> mappaVuota = new HashMap<>();

			mappaVuota.put(prodotto, new String(""));
			mappaVuota.put(quantita, new Integer(0));

			return mappaVuota;

		} else {
			if (obj instanceof Item) {
				Map<String, Object> mappaPiena = new HashMap<>();
				mappaPiena.put(prodotto, ((Item) obj).getProdotto());
				mappaPiena.put(quantita, ((Item) obj).getQuantita());
				return mappaPiena;
			} else {
				throw new IllegalArgumentException("Valori non validi, riprovare.");
			}
		}

	}

	/*
	 * Funzione per la creazione di listaCarrello.
	 * 
	 * @author Diego
	*/	
	
	@Override
	public LinkedList<Item> load() {
		LinkedList<Item> l = new LinkedList<Item>();
		l.add(new Item(new Product("Prodotto Bello", 1, 3, 5, 7, "Categoria",
				"Bellissimo", 1000),0));
		for (Product prodotto : this.db.getProducts()){
			l.add(new Item(prodotto,0));
		}
		return l;
	}

	/*
	 * Funzione per la creazione di un nuovo carrello , con conseguente possibilit� di acquistare prodotti.
	 * 
	 * @author Diego
	*/	
	
	@Override
	public LinkedList<? extends IDataTableModel> load(Map<String, Object> mappaFiltro) {
		LinkedList<Item> listaFiltrata = new LinkedList<>();
		if (mappaFiltro.get(prodotto) != null) {
			for (Item controllofiltro : listaCarrello) {
				if (controllofiltro.getProdotto().getNome().equals(prodotto)) {
					listaFiltrata.add(controllofiltro);
				}
			}
		}
		return null;
	}
	/*
	 * Funzione per rimozione di un elemento dal carrello.
	 * 
	 * @author Diego
	*/
	@Override
	public void remove(IDataTableModel elem) {
		if (listaCarrello.contains(elem)) {
			listaCarrello.remove(elem);
		} else {
			throw new IllegalArgumentException("Elemento non trovato.");
		}
	}

	/*
	 * Funzione per restituzione del DB.
	 * 
	 * @author Diego
	*/
	
	@Override
	public DBDataModel saveDBAndClose() {
		// db.setProducts(listaCarrello);
		return db;
	}

}
