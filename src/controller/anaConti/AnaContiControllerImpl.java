/**
 * 
 */
package controller.anaConti;

import controller.IAnagraficaViewObserver;
import controller.main.MainControllerImpl;
import dataModel.DBDataModel;
import model.AccountsModel;
import view.anaConti.AnaContiView;

/**
 * @author Pentolo
 *
 */
public class AnaContiControllerImpl implements IAnagraficaViewObserver {

	private final AnaContiView view;
	private final AccountsModel model;

	/**
	 * @param view
	 */
	public AnaContiControllerImpl(final DBDataModel db, final String title) {
		this.model = new AccountsModel(db);
		this.view = new AnaContiView(model.load(), title);
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
