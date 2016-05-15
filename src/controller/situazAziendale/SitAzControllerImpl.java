/**
 * 
 */
package controller.situazAziendale;

import java.awt.Dimension;

import controller.IViewObserver;
import controller.main.MainControllerImpl;
import model.FinancialSituationModelImpl;
import view.situazAziendale.SitAzView;

/**
 * @author Pentolo
 *
 */
public class SitAzControllerImpl implements IViewObserver {

	private final FinancialSituationModelImpl model;
	private final SitAzView view;

	/**
	 * 
	 */
	public SitAzControllerImpl(final String title) {
		this.view = new SitAzView(title, new Dimension(400, 500));
		this.model = new FinancialSituationModelImpl();
		this.view.setObserver(this);
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
