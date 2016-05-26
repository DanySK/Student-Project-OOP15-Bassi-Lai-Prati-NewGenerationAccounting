package controller.anaAziende;

import java.io.IOException;
import java.util.LinkedList;
import java.util.UUID;

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
		Company company = getSelectedCompany();
		if (company != null) {
			if (checkPwd(company)) {
				saveCompanysList();
				view.close();
				new MainControllerImpl(DBLoader.loadDB(company.getCodice_azienda().toString(), view));
			} else {
				wrongPwd();
			}
		}
	}

	private boolean checkPwd(Company company) {
		return model.isPasswordCorrect(view.getInputPassword(), company);
	}

	@Override
	public void chiusura() {
		if (view.confirmDialog("Sei sicuro di voler uscire dal programma?", "Uscire")) {
			saveCompanysList();
			System.exit(0);
		}
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
				@Override
				protected void beforeCloseActions() {
					UUID codice = model.getLastAddedItemCode();
					if (codice != null) {
						DBSaver.addCompany(codice);
					}
				}
			};
		} catch (InstanceNotFoundException | IllegalArgumentException e) {
			view.errorDialog("Errore", e.getMessage());
		}
	}

	@Override
	public void tasto2() {
		Company company = getSelectedCompany();
		if (company != null) {
			if (checkPwd(company)) {
				try {
					new PopupControllerImpl(PopupMode.EDIT, model, this, view);
				} catch (InstanceNotFoundException | IllegalArgumentException e) {
					view.errorDialog("Errore", e.getMessage());
				}
			} else {
				wrongPwd();
			}
		}
	}

	@Override
	public void tasto3() {
		Company company = getSelectedCompany();
		if (company != null) {
			if (checkPwd(company)) {
				DBSaver.removeCompany(company.getCodice_azienda().toString());
				model.remove(company);
				refresh();
			} else {
				wrongPwd();
			}
		}
	}

	private void wrongPwd() {
		view.errorDialog("Password Errata", "Attenzione! La password inserita è errata. riprovare.");
	}

}