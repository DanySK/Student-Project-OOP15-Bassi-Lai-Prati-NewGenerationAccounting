/**
 * 
 */
package controller.main;

import java.io.IOException;

import controller.IViewObserver;
import controller.anaAziende.AnaAziendeControllerImpl;
import controller.anaCliFor.AnaCliForControllerImpl;
import controller.anaConti.AnaContiControllerImpl;
import controller.anaProd.AnaProdControllerImpl;
import controller.creaFattura.CreaFatturaControllerImpl;
import controller.dbController.DBLoader;
import controller.dbController.DBSaver;
import controller.movimenti.MovimentiControllerImpl;
import controller.situazAziendale.SitAzControllerImpl;
import dataModel.DBDataModel;
import view.main.MainView;

/**
 * implementazione del controller del menu principale
 * 
 * @author Pentolo
 *
 */
public class MainControllerImpl implements IViewObserver {

	private final DBDataModel db;
	private final MainView view;

	/**
	 * @param db
	 *            il database
	 */
	public MainControllerImpl(final DBDataModel db) {
		this.view = new MainView();
		this.db = db;
		this.view.setObserver(this);
		view.start();
	}

	public void btn0(final String title) {
		view.close();
		new CreaFatturaControllerImpl(db, title);
	}

	public void btn1(final String title) {
		new DBSaver(db.getPath(), view, db).start();
		view.close();
		try {
			new AnaAziendeControllerImpl(DBLoader.loadCompanys());
		} catch (IOException e) {
			view.errorDialog("errore", e.getMessage());
		}
	}

	public void btn2(final String title) {
		view.close();
		new AnaCliForControllerImpl(db, title);
	}

	public void btn3(final String title) {
		view.close();
		new AnaContiControllerImpl(db, title);
	}

	public void btn4(final String title) {
		view.close();
		new MovimentiControllerImpl(db, title);
	}

	public void btn5(final String title) {
		view.close();
		new AnaProdControllerImpl(db, title);
	}

	public void btn6(final String title) {
		view.errorDialog("Funzione presto disponibile", "Questa funzione non � ancora disponibile.");
		// view.close();
		// new SitCredDebControllerImpl(db, title);
	}

	public void btn7(final String title) {
		view.close();
		new SitAzControllerImpl(db, title);
	}

	@Override
	public void chiusura() {
		new DBSaver(db.getPath(), view, db).start();
		if (view.confirmDialog("Sei sicuro di voler uscire dal programma?", "Uscire")) {
			System.exit(0);
		}
	}
}
