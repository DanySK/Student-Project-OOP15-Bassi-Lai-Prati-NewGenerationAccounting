/**
 * 
 */
package controller.situazAziendale;

import java.awt.Dimension;

import controller.IViewObserver;
import controller.main.MainControllerImpl;
import dataModel.DBDataModel;
import model.FinancialSituationModelImpl;
import view.situazAziendale.SitAzView;

/**
 * implementazione del controller della situazione aziendale con stato patrimoniale, conto economico e commento.
 * 
 * @author Pentolo
 *
 */
public class SitAzControllerImpl implements IViewObserver {

	private final FinancialSituationModelImpl model;
	private final SitAzView view;

	/**
	 * 
	 */
	public SitAzControllerImpl(final DBDataModel db, final String title) {
		this.view = new SitAzView(title, new Dimension(400, 500));
		this.model = new FinancialSituationModelImpl(db);
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
