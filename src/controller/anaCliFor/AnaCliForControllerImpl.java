/**
 * 
 */
package controller.anaCliFor;

import javax.management.InstanceNotFoundException;

import controller.IAnagraficaViewObserver;
import controller.PopupControllerImpl;
import controller.main.MainControllerImpl;
import dataEnum.PopupMode;
import dataModel.DBDataModel;
import model.CustomersSuppliersModel;
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
	public void chiusura() {
		view.close();
		new MainControllerImpl(model.saveDBAndClose());
	}

	@Override
	public void refresh() {
		view.setList(model.load());
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
