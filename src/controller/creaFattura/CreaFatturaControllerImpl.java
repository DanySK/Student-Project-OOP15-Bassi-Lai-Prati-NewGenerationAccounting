/**
 * 
 */
package controller.creaFattura;

import javax.management.InstanceNotFoundException;

import controller.IAnagraficaViewObserver;
import controller.PopupControllerImpl;
import controller.main.MainControllerImpl;
import dataEnum.PopupMode;
import dataModel.DBDataModel;
import model.CreaFattureModel;
import view.creaFattura.CreaFatturaView;

/**
 * implementazione controller finestra creazione fattura di acquisto
 * 
 * @author Pentolo
 *
 */
public class CreaFatturaControllerImpl implements IAnagraficaViewObserver {

	private final CreaFattureModel model;
	private final CreaFatturaView view;

	/**
	 * @param view
	 */
	public CreaFatturaControllerImpl(final DBDataModel db, final String title) {
		this.model = new CreaFattureModel(db);
		this.view = new CreaFatturaView(model.load(), title);
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
