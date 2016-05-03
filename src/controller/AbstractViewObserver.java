package controller;

import view.AbstractFrame;

/**
 * classe astratta per tutti i controller di NGA.
 * @author Pentolo
 *
 */
public abstract class AbstractViewObserver {

	protected AbstractFrame View;
	
	public AbstractViewObserver() {}
	
	public abstract void Chiusura();

}
