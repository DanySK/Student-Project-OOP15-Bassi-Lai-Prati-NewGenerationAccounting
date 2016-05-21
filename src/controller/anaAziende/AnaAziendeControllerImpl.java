package controller.anaAziende;

import java.util.LinkedList;

import controller.IAnagraficaViewObserver;
import controller.main.MainControllerImpl;
import dataModel.Company;
import dataModel.DBDataModel;
import model.CompanyModel;
import view.anaAziende.AnaAziendeView;

public class AnaAziendeControllerImpl implements IAnagraficaViewObserver {
	private final AnaAziendeView view;
	private final CompanyModel model;
	private boolean noCompany = false;

	public AnaAziendeControllerImpl() {
		this.model = new CompanyModel(); // TODO first start
		this.view = new AnaAziendeView(model.load());
		this.view.setObserver(this);
		noCompany = true;
		view.start();
	}

	public AnaAziendeControllerImpl(final DBDataModel db, final String title) {
		this.model = new CompanyModel();
		this.view = new AnaAziendeView((LinkedList<Company>) model.load(), title);
		this.view.setObserver(this);
		noCompany = true;
		view.start();
	}

	@Override
	public void chiusura() {
		if (noCompany) {
			if (view.confirmDialog("Sei sicuro di voler uscire dal programma?", "Uscire")) {
				System.exit(0);
			}
		} else {
			view.close();
			new MainControllerImpl();
		}
	}

	@Override
	public void tasto0() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tasto1() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tasto2() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tasto3() {
		// TODO Auto-generated method stub

	}

	public void accedi(final Company objectAt, final char[] password) {
		if (model.isPasswordCorrect(password, objectAt)) {
			view.close();
			new MainControllerImpl();
		} else {
			view.errorDialog("Password errata", "Password Errata, riprovare.");
		}
	}

}
