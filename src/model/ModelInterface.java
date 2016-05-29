package model;

import java.util.LinkedList;
import java.util.Map;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

import dataModel.DBDataModel;
import dataModel.IDataTableModel;

public interface ModelInterface {

	/**
	 * funzione di salvataggio dei dati del database
	 * 
	 * @author niky
	 * @throws InstanceAlreadyExistsException
	 * @throws InstanceNotFoundException
	 */

	public void add(Map<String, Object> elem)
			throws IllegalArgumentException, InstanceAlreadyExistsException, InstanceNotFoundException;

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
			throws InstanceNotFoundException, InstanceAlreadyExistsException, IllegalArgumentException;

	public abstract Map<String, Object> getFilterMap();

	public abstract Map<String, Object> getMap(IDataTableModel obj);

	public abstract LinkedList<? extends IDataTableModel> load();

	/**
	 * operazione di aggiunta di un nuovo oggetto al dataBase del programma
	 * 
	 * @author niky
	 * @param elem
	 *            mappa contenente le informazioni sull'elemento da aggiungere
	 * @throws InstanceNotFoundException
	 */
	public abstract LinkedList<? extends IDataTableModel> load(Map<String, Object> mappaFiltro)
			throws InstanceNotFoundException;

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
}