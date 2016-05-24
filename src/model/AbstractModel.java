package model;

import java.util.LinkedList;
import java.util.Map;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

import dataModel.DBDataModel;
import dataModel.IDataTableModel;

public abstract class AbstractModel {

	/**
	 * funzione di salvataggio dei dati del database
	 * 
	 * @author niky
	 * @throws InstanceAlreadyExistsException
	 */

	public void add(Map<String, Object> elem) throws IllegalArgumentException, InstanceAlreadyExistsException {
		addElem(elem);
	}

	/**
	 * operazione per cercare e modificare un oggetto all'interno del dataBase
	 * 
	 * @author niky
	 * 
	 * @param obj,
	 *            infoDaModificare l'oggetto da modificare e le nuove
	 *            informazioni
	 * @throws InstanceAlreadyExistsException
	 * @throws InstanceNotFoundException
	 */

	protected abstract void addElem(Map<String, Object> elem)
			throws IllegalArgumentException, InstanceAlreadyExistsException;

	/**
	 * operazione per rimuovere un oggetto dal dataBase
	 * 
	 * @author niky
	 * 
	 * @param elem
	 *            elemento da modificare
	 * @throws InstanceNotFoundException
	 * @throws IllegalArgumentException
	 * @throws InstanceAlreadyExistsException
	 */

	public void edit(IDataTableModel obj, Map<String, Object> infoDaModificare)
			throws InstanceNotFoundException, InstanceAlreadyExistsException, IllegalArgumentException {
		editElem(obj, infoDaModificare);
	}

	protected abstract void editElem(IDataTableModel obj, Map<String, Object> infoDaModificare)
			throws InstanceNotFoundException, InstanceAlreadyExistsException, IllegalArgumentException;

	public abstract Map<String, Object> getMap(IDataTableModel obj);

	/**
	 * operazione di aggiunta di un nuovo oggetto al dataBase del programma
	 * 
	 * @author niky
	 * @param elem
	 *            mappa contenente le informazioni sull'elemento da aggiungere
	 */
	public abstract LinkedList<? extends IDataTableModel> load(Map<String,Object>mappaFiltro);

	/**
	 * operazione per restituire alla view i dati del dataBase da mostrare
	 * all'utente
	 * 
	 * @return ritorna i dati richiesti
	 * 
	 * @author niky
	 */

	public abstract void remove(IDataTableModel elem) throws InstanceNotFoundException;

	public abstract DBDataModel saveDBAndClose();
	public abstract Map<String,Object> getFilterMap();
}
