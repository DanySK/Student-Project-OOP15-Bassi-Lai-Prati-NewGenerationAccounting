package model;

import java.util.LinkedList;
import java.util.Map;

import dataModel.DBDataModel;
import dataModel.IDataTableModel;
import dataModel.Item;

/**
 * Classe implementativa per la gestione del carrello che permette l'acquisto di
 * prodotti, coinvolgendo i movimenti dei conti e delle scorte in magazzino.
 * 
 * @author Diego
 *
 */

public class CreaFattureModel extends AbstractModel {

	DBDataModel db;

	// private final Product oggetto;
	// private final int quantita;

	private int spesa; // Spesa = subtotale

	LinkedList<Item> listaCarrello;

	public CreaFattureModel(DBDataModel db) {
		this.db = db;
	}

	@Override
	protected void addElem(Map<String, Object> elem) throws IllegalArgumentException {
		// if (oggetto.getScorta()<=0){
		// throw new IllegalArgumentException("Questo prodotto non � pi�
		// disponibile in magazzino, mi dispiace.");
		// }else{

		// Item nuovoCarrello = new Item (oggetto, quantita);
		// }

		// listaCarrello.add(nuovoCarrello);

	}

	@Override
	protected void editElem(IDataTableModel obj, Map<String, Object> ifoDaModificare) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, Object> getFilterMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getMap(IDataTableModel obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<Item> load() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<? extends IDataTableModel> load(Map<String, Object> mappaFiltro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(IDataTableModel elem) {
		// TODO Auto-generated method stub

	}

	@Override
	public DBDataModel saveDBAndClose() {
		// TODO Auto-generated method stub
		return null;
	}

}
