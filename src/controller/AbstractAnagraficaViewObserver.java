package controller;

import dataModel.Company;
import view.AbstractAnagraficaView;
import view.AbstractFrame;

/**
 * classe astratta per i controller delle view che rispettano il layout di
 * anagrafica, con i 5 tasti nel footer.
 * 
 * @author Pentolo
 *
 */
public abstract class AbstractAnagraficaViewObserver extends AbstractViewObserver {

	protected AbstractAnagraficaView view;
	
	public AbstractAnagraficaViewObserver(AbstractAnagraficaView view) {
		this.view = view;
	}

	@Override
	public abstract void chiusura();

	public abstract void tasto0();

	public abstract void tasto1();

	public abstract void tasto2();

	public abstract void tasto3();

	public abstract void tasto4();
}
