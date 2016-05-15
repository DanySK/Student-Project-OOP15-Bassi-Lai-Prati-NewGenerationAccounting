/**
 * 
 */
package controller.creaFattura;

import controller.IAnagraficaViewObserver;
import controller.main.MainControllerImpl;
import model.CreaFattureModel;
import view.creaFattura.CreaFatturaView;

/**
 * @author Pentolo
 *
 */
public class CreaFatturaControllerImpl implements IAnagraficaViewObserver {

	private final CreaFatturaView view;
	private final CreaFattureModel model;

	/**
	 * @param view
	 */
	public CreaFatturaControllerImpl(final String title) {
		this.model = new CreaFattureModel();
		this.view = new CreaFatturaView(model.load(), title);
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
