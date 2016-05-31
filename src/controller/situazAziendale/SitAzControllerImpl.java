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
 * implementazione del controller della situazione aziendale con stato
 * patrimoniale, conto economico e commento.
 * 
 * @author Pentolo
 *
 */
public class SitAzControllerImpl implements IViewObserver {

	private final FinancialSituationModelImpl model;
	private final SitAzView view;

	/**
	 * @param db
	 *            il database
	 * @param title
	 *            il titolo della finestra
	 */
	public SitAzControllerImpl(final DBDataModel db, final String title) {
		this.model = new FinancialSituationModelImpl(db);
		this.view = new SitAzView(title, new Dimension(700, 750), model.AnalisiFinanziaria(), model.Attivita(),
				model.Saldi_Attivita(), model.Passivita(), model.Saldi_Attivita(), model.Costi(), model.Saldi_Costi(),
				model.Ricavi(), model.Saldi_Ricavi(), model.getSaldo_Stato_Patr(), model.getSaldo_Conto_Ec());
		this.view.setObserver(this);
		view.start();
	}

	@Override
	public void chiusura() {
		view.close();
		new MainControllerImpl(model.saveDBAndClose());
	}

}
