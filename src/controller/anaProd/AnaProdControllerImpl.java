/**
 * 
 */
package controller.anaProd;

import java.util.Map;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

import controller.IAnagraficaViewObserver;
import controller.main.MainControllerImpl;
import dataModel.DBDataModel;
import dataModel.IDataTableModel;
import model.ProductsModel;
import view.anaProd.AnaProdView;

/**
 * implementazione controller anagrafica prodotti
 * 
 * @author Pentolo
 *
 */
public class AnaProdControllerImpl implements IAnagraficaViewObserver {
	private final ProductsModel model;
	private final AnaProdView view;

	/**
	 * @param view
	 */
	public AnaProdControllerImpl(final DBDataModel db, final String title) {
		this.model = new ProductsModel(db);
		this.view = new AnaProdView(model.load(), title);
		this.view.setObserver(this);
		view.start();
	}

	@Override
	public void add(Map<String, Object> mappa) throws InstanceAlreadyExistsException, IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.AbstractAnagraficaViewObserver#chiusura()
	 */
	@Override
	public void chiusura() {
		view.close();
		new MainControllerImpl(model.saveDBAndClose());

	}

	@Override
	public void edit(Map<String, Object> mappa) throws InstanceNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, Object> getMap(IDataTableModel obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.AbstractAnagraficaViewObserver#tasto0()
	 */
	@Override
	public void tasto0() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.AbstractAnagraficaViewObserver#tasto1()
	 */
	@Override
	public void tasto1() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.AbstractAnagraficaViewObserver#tasto2()
	 */
	@Override
	public void tasto2() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.AbstractAnagraficaViewObserver#tasto3()
	 */
	@Override
	public void tasto3() {
		// TODO Auto-generated method stub

	}
}
