/**
 * 
 */
package controller.anaConti;

import java.awt.Dimension;
import java.util.LinkedList;
import java.util.Map;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

import controller.IAnagraficaViewObserver;
import controller.dbController.DBSaver;
import controller.main.MainControllerImpl;
import dataEnum.PopupMode;
import dataModel.Account;
import dataModel.DBDataModel;
import dataModel.IDataTableModel;
import model.AccountsModel;
import view.AddEditPopupView;
import view.anaConti.AnaContiView;

/**
 * implementazione controller anagrafica conti
 * 
 * @author Pentolo
 *
 */
public class AnaContiControllerImpl implements IAnagraficaViewObserver {
	private final AccountsModel model;
	private final AnaContiView view;

	/**
	 * @param view
	 */
	public AnaContiControllerImpl(final DBDataModel db, final String title) {
		this.model = new AccountsModel(db);
		this.view = new AnaContiView(model.load(), title);
		this.view.setObserver(this);
		view.start();
	}

	@Override
	public void add(Map<String, Object> mappa) throws InstanceAlreadyExistsException, IllegalArgumentException {
		model.add(mappa);
	}

	@Override
	public void chiusura() {
		DBDataModel db = model.saveDBAndClose();
		new DBSaver(db.getPath(), view, db).start();
		view.close();
		new MainControllerImpl(db);
	}

	@Override
	public void edit(Map<String, Object> mappa) throws InstanceNotFoundException {
		try {
			model.edit(view.getSelectedItem(), mappa);
		} catch (InstanceAlreadyExistsException | IllegalArgumentException e) {
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
		view.setList((LinkedList<Account>) model.load(mappa));
	}
}
