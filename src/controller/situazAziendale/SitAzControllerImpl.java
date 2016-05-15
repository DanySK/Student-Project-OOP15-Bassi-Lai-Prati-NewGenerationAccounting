/**
 * 
 */
package controller.situazAziendale;

import java.awt.Dimension;

import controller.AbstractViewObserver;
import controller.main.MainControllerImpl;
import view.situazAziendale.SitAzView;

/**
 * @author Pentolo
 *
 */
public class SitAzControllerImpl extends AbstractViewObserver {

	private final SitAzView view; 
	
	/**
	 * 
	 */
	public SitAzControllerImpl(final String title) {
		super();
		view = new SitAzView(title, new Dimension(400,500));
		view.setObserver(this);
		view.start();
	}

	/* (non-Javadoc)
	 * @see controller.AbstractViewObserver#chiusura()
	 */
	@Override
	public void chiusura() {
		view.close();
		new MainControllerImpl();
	}

}