package model;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.Map;

import dataModel.IDataTableModel;

/**
 * Interfaccia delle operazioni comuni delle classi di anagrafica
 * 
 * @author niky
 * @throws ParseException
 */
public abstract class AbstractModel {

	/**
	 * operazione di aggiunta di un nuovo oggetto al dataBase del programma
	 * 
	 * @author niky
	 * @throws ParseException
	 * @param elem
	 *            mappa contenente le informazioni sull'elemento da aggiungere
	 */
	public void add(Map<String, Object> elem) throws IllegalArgumentException {
		addElem(elem);
	}

	protected abstract void addElem(Map<String, Object> elem) throws IllegalArgumentException;

	/**
	 * operazione per cercare e modificare un oggetto all'interno del dataBase
	 * 
	 * @author niky
	 * 
	 * @param obj,
	 *            infoDaModificare l'oggetto da modificare e le nuove
	 *            informazioni
	 */
	public void edit(IDataTableModel obj, Map<String, Object> infoDaModificare) {
		editElem(obj, infoDaModificare);
	}

	protected abstract void editElem(IDataTableModel obj, Map<String, Object> ifoDaModificare);

	protected abstract Map<String, Object> getMap();

	/**
	 * operazione per restituire alla view i dati del dataBase da mostrare
	 * all'utente
	 * 
	 * @return ritorna i dati richiesti
	 * 
	 * @author niky
	 */
	public abstract LinkedList<? extends IDataTableModel> load();

	/**
	 * operazione per rimuovere un oggetto dal dataBase
	 * 
	 * @author niky
	 * 
	 * @param elem
	 *            elemento da modificare
	 */
	public abstract void remove(IDataTableModel elem);

}
