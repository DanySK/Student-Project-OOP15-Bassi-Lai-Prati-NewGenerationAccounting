/**
 * 
 */
package controller.movimenti;

import java.awt.Dimension;
import java.util.Map;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

import controller.IAnagraficaViewObserver;
import controller.main.MainControllerImpl;
import dataModel.DBDataModel;
import dataModel.IDataTableModel;
import model.MovementsModel;
import view.AddEditPopupView;
import view.movimenti.MovimentiView;

/**
 * implementazione del controller della sezione movimenti
 * 
 * @author Pentolo
 *
 */
public class MovimentiControllerImpl implements IAnagraficaViewObserver {

	private final MovementsModel model;
	private final MovimentiView view;

	/**
	 * @param view
	 */
	public MovimentiControllerImpl(final DBDataModel db, final String title) {
		this.model = new MovementsModel(db);
		this.view = new MovimentiView(model.load(), title);
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
		// TODO Auto-generated method stub

	}

	@Override
	public void tasto1() {
		new AddEditPopupView(null, view.getTitle(), new Dimension(300, 400), this, view).start();
	}

	@Override
	public void tasto2() {
		try {
			new AddEditPopupView(view.getSelectedItem(), view.getTitle(), new Dimension(300, 400), this, view).start();
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
}
