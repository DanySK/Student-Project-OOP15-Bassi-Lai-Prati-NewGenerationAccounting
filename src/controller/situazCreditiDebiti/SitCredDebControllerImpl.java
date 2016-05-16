/**
 * 
 */
package controller.situazCreditiDebiti;

import java.awt.Dimension;

import controller.IViewObserver;
import controller.main.MainControllerImpl;
import dataModel.DBDataModel;
import model.AbstractModel;
import model.ReceivablesPayablesModel;
import view.situazCreditiDebiti.SitCredDebView;

/**
 * @author Pentolo
 *
 */
public class SitCredDebControllerImpl implements IViewObserver {

	private final ReceivablesPayablesModel model;
	private final SitCredDebView view;

	/**
	 * 
	 */
	public SitCredDebControllerImpl(final DBDataModel db, final String title) {
		this.model = new ReceivablesPayablesModel(db);
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
