/**
 * 
 */
package controller.situazCreditiDebiti;

import java.awt.Dimension;

import controller.IViewObserver;
import controller.main.MainControllerImpl;
import model.AbstractModel;
import view.situazCreditiDebiti.SitCredDebView;

/**
 * @author Pentolo
 *
 */
public class SitCredDebControllerImpl implements IViewObserver {

	private final AbstractModel model;
	private final SitCredDebView view;

	/**
	 * 
	 */
	public SitCredDebControllerImpl(final String title) {
		this.model = null; // TODO!!
		view = new SitCredDebView(title, new Dimension(400, 500));
		view.setObserver(this);
		view.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.AbstractViewObserver#chiusura()
	 */
	@Override
	public void chiusura() {
		view.close();
		new MainControllerImpl();
	}

}
