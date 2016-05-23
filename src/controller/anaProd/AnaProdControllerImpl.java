/**
 * 
 */
package controller.anaProd;

import java.awt.Dimension;
import java.util.Map;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

import controller.IAnagraficaViewObserver;
import controller.main.MainControllerImpl;
import dataModel.DBDataModel;
import dataModel.IDataTableModel;
import model.ProductsModel;
import view.AddEditPopupView;
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
		try {
			model.edit(view.getSelectedItem(), mappa);
		} catch (InstanceAlreadyExistsException | IllegalArgumentException e) {
			view.errorDialog("Errore", e.getMessage());
		}
	}

	@Override
	public Map<String, Object> getMap(IDataTableModel obj) {
		return model.getMap(obj);
	}

	@Override
	public void refresh() {
		view.setList(model.load());
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
		new AddEditPopupView(null, view.getTitle(), new Dimension(300, 400), this, view).start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.AbstractAnagraficaViewObserver#tasto2()
	 */
	@Override
	public void tasto2() {
		try {
			new AddEditPopupView(view.getSelectedItem(), view.getTitle(), new Dimension(300, 400), this, view).start();
		} catch (InstanceNotFoundException e) {
			view.errorDialog("Errore", e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.AbstractAnagraficaViewObserver#tasto3()
	 */
	@Override
	public void tasto3() {
		try {
			model.remove(view.getSelectedItem());
		} catch (InstanceNotFoundException e) {
			view.errorDialog("Errore", e.getMessage());
		}
		refresh();
	}
}
