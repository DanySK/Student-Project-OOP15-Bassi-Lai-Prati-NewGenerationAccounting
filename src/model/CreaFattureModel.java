package model;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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

	private DBDataModel db;

	private final static String prodotto = "Prodotto";
	private final static String quantita = "Quantita'";
	private final static String scorta = "Scorta";
	private final static String subtotale = "Subtotale"; //importo da addebitare ai clienti
	private Product product;

	
	
	
	
	private final LinkedList<Item> listaCarrello = new LinkedList<Item>();
	
	public CreaFattureModel(DBDataModel db) {
		this.db = db;
	}
	


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
			Item nuovocarrello = new Item((Product) elem.get(prodotto), (Integer) elem.get(quantita)/*, (Integer)elem.get(subtotale)*/);
			listaCarrello.add(nuovocarrello);
		}
	}

	@Override
	public void edit(IDataTableModel obj, Map<String, Object> infoDaModificare) {
		if ((Product) infoDaModificare.get(scorta) != null) { 
			

			((Item) obj).setProdotto((Product) infoDaModificare.get(prodotto));
			((Item) obj).setQuantita((Integer) infoDaModificare.get(quantita));

		}
	}

	@Override
	public Map<String, Object> getFilterMap() {
		Map<String, Object> mappaFiltro = new HashMap<>();
		mappaFiltro.put(prodotto, new String(""));
		return mappaFiltro;
	}

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

	@Override
	public LinkedList<Item> load() {
		return new LinkedList<Item>(listaCarrello);
	}

	public  LinkedList<Customers_Suppliers> getListaclienti()
	{
		
		final LinkedList<Customers_Suppliers> listaClienti = new LinkedList<Customers_Suppliers>();
		
		for (Customers_Suppliers controlloCliente : db.getCustomersSuppliers()){
			if(controlloCliente.getRuolo() == KindPerson.CLIENTE){
			listaClienti.add(controlloCliente);
			}
		}
		return listaClienti;
	}
	
	
	@Override
	public LinkedList<? extends IDataTableModel> load(Map<String, Object> mappaFiltro) {
		LinkedList<Item> listaFiltrata = new LinkedList<>();
		if (mappaFiltro.get(prodotto) != null) {
			for (Item controllofiltro : listaCarrello) {
				if (controllofiltro.getProdotto().contentEquals(prodotto)) {
					listaFiltrata.add(controllofiltro);
				}
			}
		}
		return null;
	}

	@Override
	public void remove(IDataTableModel elem) {
		if (listaCarrello.contains(elem)) {
			listaCarrello.remove(elem);
		} else {
			throw new IllegalArgumentException("Elemento non trovato.");
		}
	}

	@Override
	public DBDataModel saveDBAndClose() {
		// db.setProducts(listaCarrello);
		return db;
	}

	/**
	 * 
	 * Funzione per l'aggiornamento del DB dopo la creazione della fattura.
	 * 
	 * Crea un movimento, pi� debiti, meno scorte della merce richiesta.
	 * 
	 * 
	 * 
	*/
	public DBDataModel create(Customers_Suppliers item) {
	
		// controlli acquisto valido
		
		if (listaCarrello.isEmpty()) {//sostituire con i controlli singoli
			throw new IllegalArgumentException("Acquisto non valido. Riprovare.");
		}
		for (Item creoSubtotale : listaCarrello) {
			 // il prodotto ha un nome ed un prezzo di vendita , devo utilizzare lo stesso
			//Item  creoSubtotale = new Item((Integer) product.getPrezzovendita() * quantita);
			
	//	 creoSubtotale.getQuantita() ;
		}
		
		// Product.getScorta - listaCarrello(quantita)
		
		
		// nuovo movimento
		
		
		// debiti verso fornitori = debiti verso fornitori + subtotale
		
		item.getDebito();
		//listaCarrello.add(creoSubtotale);
		return db;
	}

}
