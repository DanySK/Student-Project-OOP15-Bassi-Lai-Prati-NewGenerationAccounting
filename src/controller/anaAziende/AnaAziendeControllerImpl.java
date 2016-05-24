package controller.anaAziende;

import java.awt.Dimension;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

import controller.IAnagraficaViewObserver;
import controller.dbController.DBLoader;
import controller.dbController.DBSaver;
import controller.main.MainControllerImpl;
import dataEnum.PopupMode;
import dataModel.Company;
import dataModel.IDataTableModel;
import model.CompanyModel;
import view.AddEditPopupView;
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
	public void add(Map<String, Object> mappa) throws InstanceAlreadyExistsException, IllegalArgumentException {
		model.add(mappa);
	}

	@Override
	public void chiusura() {
		if (view.confirmDialog("Sei sicuro di voler uscire dal programma?", "Uscire")) {
			saveCompanysList();
			System.exit(0);
		}
	}

	@Override
	public void edit(Map<String, Object> mappa) throws InstanceNotFoundException {
		try {
			model.edit(view.getSelectedItem(), mappa);
		} catch (InstanceAlreadyExistsException e) {
			view.errorDialog("Errore", e.getMessage());
		} catch (IllegalArgumentException e) {
			view.errorDialog("Errore", e.getMessage());
		}
	}

	@Override
	public Map<String, Object> getMap(IDataTableModel obj) {
		return model.getMap(obj);
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
		new AddEditPopupView(PopupMode.FIND, null, view.getTitle(), new Dimension(300, 400), this, view).start();
	}

	@Override
	public void tasto1() {
		new AddEditPopupView(PopupMode.ADD, null, view.getTitle(), new Dimension(300, 400), this, view).start();
	}

	@Override
	public void tasto2() {
		try {
			new AddEditPopupView(PopupMode.FIND, view.getSelectedItem(), view.getTitle(), new Dimension(300, 400), this,
					view).start();
		} catch (InstanceNotFoundException e) {
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

	@Override
	public void filterList(Map<String, Object> mappa) {
		view.setList((LinkedList<Company>) model.load(mappa));
	}

}