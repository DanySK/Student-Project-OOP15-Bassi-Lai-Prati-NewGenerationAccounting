/**
 * 
 */
package controller.anaCliFor;

import controller.AbstractAnagraficaViewObserver;
import controller.main.MainControllerImpl;
import view.AbstractAnagraficaView;
import view.anaAziende.AnaAziendeView;
import view.anaCliFor.AnaCliForView;

/**
 * @author Pentolo
 *
 */
public class AnaCliForControllerImpl extends AbstractAnagraficaViewObserver {

	/**
	 * @param view
	 */
	public AnaCliForControllerImpl(String title) {
		super(new AnaCliForView(title));
		view.setObserver(this);
		view.start();
	}

	/* (non-Javadoc)
	 * @see controller.AbstractAnagraficaViewObserver#chiusura()
	 */
	@Override
	public void chiusura() {
		view.close();
		new MainControllerImpl();
	}

	/* (non-Javadoc)
	 * @see controller.AbstractAnagraficaViewObserver#tasto0()
	 */
	@Override
	public void tasto0() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see controller.AbstractAnagraficaViewObserver#tasto1()
	 */
	@Override
	public void tasto1() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see controller.AbstractAnagraficaViewObserver#tasto2()
	 */
	@Override
	public void tasto2() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see controller.AbstractAnagraficaViewObserver#tasto3()
	 */
	@Override
	public void tasto3() {
		// TODO Auto-generated method stub

	}
}
