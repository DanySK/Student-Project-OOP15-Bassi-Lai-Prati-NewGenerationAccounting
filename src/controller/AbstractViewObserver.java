package controller;

import view.AbstractFrame;

/**
 * classe astratta per tutti i controller di NGA.
 * 
 * @author Pentolo
 *
 */
public abstract class AbstractViewObserver {

	protected AbstractFrame view;

	public AbstractViewObserver(AbstractFrame view) {
		this.view = view;
	}

	public abstract void Chiusura();
}
