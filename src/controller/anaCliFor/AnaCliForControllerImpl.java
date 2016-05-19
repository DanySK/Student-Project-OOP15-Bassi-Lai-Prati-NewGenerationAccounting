/**
 * 
 */
package controller.anaCliFor;

import controller.IAnagraficaViewObserver;
import controller.main.MainControllerImpl;
import dataModel.DBDataModel;
import model.CustomersSuppliersModel;
import view.anaCliFor.AnaCliForView;

/**
 * @author Pentolo
 *
 */
public class AnaCliForControllerImpl implements IAnagraficaViewObserver {

	private final CustomersSuppliersModel model;
	private final AnaCliForView view;

	/**
	 * @param view
	 */
	public AnaCliForControllerImpl(final DBDataModel db, final String title) {
		this.model = new CustomersSuppliersModel(db);
		this.view = new AnaCliForView(model.load(), title);
		this.view.setObserver(this);
		view.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.AbstractAnagraficaViewObserver#chiusura()
	 */
	@Override
	public void chiusura() {
		view.close();
		new MainControllerImpl();
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
