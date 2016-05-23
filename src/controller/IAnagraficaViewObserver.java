package controller;

import java.util.Map;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

import dataModel.IDataTableModel;

/**
 * classe astratta per i controller delle view che rispettano il layout di
 * anagrafica, con i 5 tasti nel footer.
 * 
 * @author Pentolo
 *
 */
public interface IAnagraficaViewObserver extends IViewObserver {
	void tasto0();

	void tasto1();

	void tasto2();

	void tasto3();

	Map<String, Object> getMap(IDataTableModel obj);

	void add(Map<String, Object> mappa) throws InstanceAlreadyExistsException, IllegalArgumentException;

	void edit(Map<String, Object> mappa) throws InstanceNotFoundException;

	void refresh();
}
