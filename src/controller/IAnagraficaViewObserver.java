package controller;

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
}
