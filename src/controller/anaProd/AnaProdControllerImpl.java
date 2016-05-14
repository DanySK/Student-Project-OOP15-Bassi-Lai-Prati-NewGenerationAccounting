/**
 * 
 */
package controller.anaProd;

import controller.AbstractAnagraficaViewObserver;
import view.AbstractAnagraficaView;
import view.anaProd.AnaProdView;

/**
 * @author Pentolo
 *
 */
public class AnaProdControllerImpl extends AbstractAnagraficaViewObserver {

	/**
	 * @param view
	 */
	public AnaProdControllerImpl(String title) {
		super(new AnaProdView(title));
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see controller.AbstractAnagraficaViewObserver#chiusura()
	 */
	@Override
	public void chiusura() {
		// TODO Auto-generated method stub

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

	/* (non-Javadoc)
	 * @see controller.AbstractAnagraficaViewObserver#tasto4()
	 */
	@Override
	public void tasto4() {
		// TODO Auto-generated method stub

	}

}
