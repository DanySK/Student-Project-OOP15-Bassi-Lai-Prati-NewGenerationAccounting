package controller.anaAziende;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

import controller.IAnagraficaViewObserver;
import controller.dbController.DBLoader;
import controller.dbController.DBSaver;
import controller.main.MainControllerImpl;
import dataModel.Company;
import dataModel.IDataTableModel;
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
		Company item = (Company) view.getSelectedItem();
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

	private void saveCompanysList() {
		try {
			DBSaver.saveCompanys(model.saveCompanysAndClose());
		} catch (IOException e) {
			view.errorDialog("errore", e.getMessage());
		}
	}

	@Override
	public void tasto0() {

	}

	@Override
	public void tasto1() {
		try {
			model.add(null);
		} catch (InstanceAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void tasto2() {

	}

	@Override
	public void tasto3() {
		model.remove(view.getSelectedItem());
		view.setList(model.load());
	}

	@Override
	public Map<String, Object> getMap(IDataTableModel obj) {
		return model.getMap(obj);
	}

	@Override
	public void add(Map<String, Object> mappa) throws InstanceAlreadyExistsException, IllegalArgumentException {
		model.add(mappa);
	}

	@Override
	public void edit(Map<String, Object> mappa) throws InstanceNotFoundException {
		model.edit(view.getSelectedItem(), mappa);
	}

	@Override
	public void refresh() {
		view.setList(model.load());
	}

}