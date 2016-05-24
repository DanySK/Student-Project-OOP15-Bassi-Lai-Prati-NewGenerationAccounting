/**
 * 
 */
package controller.anaCliFor;

import java.awt.Dimension;
import java.util.LinkedList;
import java.util.Map;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

import controller.IAnagraficaViewObserver;
import controller.main.MainControllerImpl;
import dataEnum.PopupMode;
import dataModel.Customers_Suppliers;
import dataModel.DBDataModel;
import dataModel.IDataTableModel;
import model.CustomersSuppliersModel;
import view.AddEditPopupView;
import view.anaCliFor.AnaCliForView;

/**
 * implementazione controller anagrafica clienti e fornitori
 * 
 * @author Pentolo
 *
 */
public class AnaCliForControllerImpl implements IAnagraficaViewObserver {

	private final CustomersSuppliersModel model;
	private final AnaCliForView view;

	public AnaCliForControllerImpl(final DBDataModel db, final String title) {
		this.model = new CustomersSuppliersModel(db);
		this.view = new AnaCliForView(model.load(), title);
		this.view.setObserver(this);
		view.start();
	}

	@Override
	public void add(Map<String, Object> mappa) throws InstanceAlreadyExistsException, IllegalArgumentException {
		model.add(mappa);
	}

	@Override
	public void chiusura() {
		view.close();
		new MainControllerImpl(model.saveDBAndClose());
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
		view.setList((LinkedList<Customers_Suppliers>) model.load(mappa));
	}
}
