package controller;

import java.util.LinkedList;

import model.AbstractModel;
import view.AbstractAnagraficaView;

/**
 * classe astratta per i controller delle view che rispettano il layout di
 * anagrafica, con i 5 tasti nel footer.
 * 
 * @author Pentolo
 *
 */
public abstract class AbstractAnagraficaViewObserver extends AbstractViewObserver {

	protected final AbstractAnagraficaView view;
	protected final AbstractModel model;
	
	public AbstractAnagraficaViewObserver(final AbstractAnagraficaView view, final AbstractModel model) {
		this.view = view;
		this.model = model;
		view.setList(model.load());
	}

	@Override
	public abstract void chiusura();

	public abstract void tasto0();

	public abstract void tasto1();

	public abstract void tasto2();

	public abstract void tasto3();
}
