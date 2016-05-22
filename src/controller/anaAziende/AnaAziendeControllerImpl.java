package controller.anaAziende;

import java.util.LinkedList;

import controller.IAnagraficaViewObserver;
import controller.dbController.DBLoader;
import controller.main.MainControllerImpl;
import dataModel.Company;
import model.CompanyModel;
import view.anaAziende.AnaAziendeView;

public class AnaAziendeControllerImpl implements IAnagraficaViewObserver {
	private final AnaAziendeView view;
	private final CompanyModel model;

	public AnaAziendeControllerImpl(LinkedList<Company> linkedList) {
		this.model = new CompanyModel(linkedList);
		this.view = new AnaAziendeView(model.load());
		this.view.setObserver(this);
		view.start();
	}

	@Override
	public void chiusura() {
		if (view.confirmDialog("Sei sicuro di voler uscire dal programma?", "Uscire")) {
			System.exit(0);
		}
	}

	@Override
	public void tasto0() {
				
	}

	@Override
	public void tasto1() {
		model.add(null);
		view.setList(model.load());
	}

	@Override
	public void tasto2() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tasto3() {
		model.remove(view.getSelectedItem());
		view.setList(model.load());
	}

	public void accedi(final char[] password) {
		Company item = (Company) view.getSelectedItem();
		if (item != null && model.isPasswordCorrect(password, item)) {
			view.close();
			new MainControllerImpl(DBLoader.loadDB(Integer.toString(item.getCodice_azienda()), view));
		} else {
			view.errorDialog("Password errata", "Password Errata, riprovare.");
		}
	}

}