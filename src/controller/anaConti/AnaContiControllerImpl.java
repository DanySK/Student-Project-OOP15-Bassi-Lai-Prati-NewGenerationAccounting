/**
 * 
 */
package controller.anaConti;

import java.awt.Dimension;
import java.util.Map;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

import controller.IAnagraficaViewObserver;
import controller.dbController.DBSaver;
import controller.main.MainControllerImpl;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.AbstractAnagraficaViewObserver#chiusura()
	 */
	@Override
	public void chiusura() {
		DBDataModel db = model.saveDBAndClose();
		new DBSaver(db.getPath(), view, db).start();
		view.close();
		new MainControllerImpl(db);
	}

	@Override
	public void edit(Map<String, Object> mappa) throws InstanceNotFoundException {
		model.edit(view.getSelectedItem(), mappa);
	}

	@Override
	public Map<String, Object> getMap(IDataTableModel obj) {
		return model.getMap(obj);
	}

	@Override
	public void refresh() {
		view.setList(model.load());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.AbstractAnagraficaViewObserver#tasto0()
	 */
	@Override
	public void tasto0() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.AbstractAnagraficaViewObserver#tasto1()
	 */
	@Override
	public void tasto1() {
		new AddEditPopupView(null, view.getTitle(), new Dimension(300, 400), this, view).start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.AbstractAnagraficaViewObserver#tasto2()
	 */
	@Override
	public void tasto2() {
		new AddEditPopupView(view.getSelectedItem(), view.getTitle(), new Dimension(300, 400), this, view).start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.AbstractAnagraficaViewObserver#tasto3()
	 */
	@Override
	public void tasto3() {
		// TODO Auto-generated method stub

	}
}
