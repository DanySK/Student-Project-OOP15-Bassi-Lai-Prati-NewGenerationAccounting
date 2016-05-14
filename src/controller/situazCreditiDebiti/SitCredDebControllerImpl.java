/**
 * 
 */
package controller.situazCreditiDebiti;

import java.awt.Dimension;

import controller.AbstractViewObserver;
import view.situazAziendale.SitAzView;

/**
 * @author Pentolo
 *
 */
public class SitCredDebControllerImpl extends AbstractViewObserver {

private final SitAzView view; 
		
	/**
	 * 
	 */
	public SitCredDebControllerImpl(String title) {
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
		// TODO Auto-generated method stub

	}

}
