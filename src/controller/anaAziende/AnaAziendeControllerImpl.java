package controller.anaAziende;

import java.io.IOException;
import java.util.LinkedList;

import javax.management.InstanceNotFoundException;

import controller.IAnagraficaViewObserver;
import controller.dbController.DBLoader;
import controller.dbController.DBSaver;
import controller.main.MainControllerImpl;
import controller.popup.PopupControllerImpl;
import dataEnum.PopupMode;
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

	public void accedi() {
		if (checkPwd(getSelectedCompany())) {
			saveCompanysList();
			view.close();
			new MainControllerImpl(DBLoader.loadDB(getSelectedCompany().getCodice_azienda().toString(), view));
		} else {
			wrongPwd();
		}
	}

	private boolean checkPwd(Company company) {
		return model.isPasswordCorrect(view.getInputPassword(), company);
	}

	private void wrongPwd() {
		view.errorDialog("Password Errata", "Attenzione! La password inserita è errata. riprovare.");
	}

	private Company getSelectedCompany() {
		Company company = null;
		try {
			company = (Company) view.getSelectedItem();
		} catch (InstanceNotFoundException e) {
			view.errorDialog("Errore", e.getMessage());
		}
		return company;
	}

	@Override
	public void chiusura() {
		if (view.confirmDialog("Sei sicuro di voler uscire dal programma?", "Uscire")) {
			saveCompanysList();
			System.exit(0);
		}
	}

	@Override
	public void refresh() {
		view.setList(model.load());
	}

	private void saveCompanysList() {
		try {
			DBSaver.saveCompanys(model.saveCompanysAndClose());
		} catch (IOException e) {
			view.errorDialog("errore", e.getMessage());
		}
	}

	@Override
	public void tasto0() {
		try {
			new PopupControllerImpl(PopupMode.FIND, model, this, view);
		} catch (InstanceNotFoundException | IllegalArgumentException e) {
			view.errorDialog("Errore", e.getMessage());
		}
	}

	@Override
	public void tasto1() {
		try {
			new PopupControllerImpl(PopupMode.ADD, model, this, view) {
				protected void beforeCloseActions() {
					DBSaver.addCompany(null);
				}
			};
		} catch (InstanceNotFoundException | IllegalArgumentException e) {
			view.errorDialog("Errore", e.getMessage());
		}
	}

	@Override
	public void tasto2() {
		if (checkPwd(getSelectedCompany())) {
			try {
				new PopupControllerImpl(PopupMode.EDIT, model, this, view);
			} catch (InstanceNotFoundException | IllegalArgumentException e) {
				view.errorDialog("Errore", e.getMessage());
			}
		} else {
			wrongPwd();
		}
	}

	@Override
	public void tasto3() {
		if (checkPwd(getSelectedCompany())) {
			try {
				Company item = (Company) view.getSelectedItem();
				DBSaver.removeCompany(item.getCodice_azienda().toString());
				model.remove(item);
			} catch (InstanceNotFoundException e) {
				view.errorDialog("Errore", e.getMessage());
			}
			refresh();
		} else {
			wrongPwd();
		}
	}

}