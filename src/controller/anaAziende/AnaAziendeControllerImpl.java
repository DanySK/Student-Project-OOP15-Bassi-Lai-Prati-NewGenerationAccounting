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

	public void accedi(final char[] password) {
		Company item = null;
		try {
			item = (Company) view.getSelectedItem();
		} catch (InstanceNotFoundException e) {
			view.errorDialog("Errore", e.getMessage());
			return;
		}
		if (item != null && model.isPasswordCorrect(password, item)) {
			saveCompanysList();
			view.close();
			new MainControllerImpl(DBLoader.loadDB(item.getCodice_azienda().toString(), view));
		} else {
			view.errorDialog("Password errata", "Password Errata, riprovare.");
		}
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
			new PopupControllerImpl(PopupMode.ADD, model, this, view);
		} catch (InstanceNotFoundException | IllegalArgumentException e) {
			view.errorDialog("Errore", e.getMessage());
		}
	}

	@Override
	public void tasto2() {
		try {
			new PopupControllerImpl(PopupMode.EDIT, model, this, view);
		} catch (InstanceNotFoundException | IllegalArgumentException e) {
			view.errorDialog("Errore", e.getMessage());
		}
	}

	@Override
	public void tasto3() {
		try {
			model.remove(view.getSelectedItem());
		} catch (InstanceNotFoundException e) {
			view.errorDialog("Errore", e.getMessage());
		}
		refresh();
	}

}