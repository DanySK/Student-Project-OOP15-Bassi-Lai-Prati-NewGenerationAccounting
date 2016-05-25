/**
 * 
 */
package controller.popup;

import java.awt.Dimension;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.management.InstanceNotFoundException;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import controller.IAnagraficaViewObserver;
import controller.IViewObserver;
import dataEnum.IDataEnum;
import dataEnum.PopupMode;
import model.AbstractModel;
import view.AbstractAnagraficaView;
import view.popup.AddEditPopupView;

/**
 * implementazione del controller di AddEditPopupView
 * 
 * @author Pentolo
 *
 */
public class PopupControllerImpl implements IViewObserver {

	private final AbstractModel model;
	private final IAnagraficaViewObserver parentController;
	private final AbstractAnagraficaView parentView;
	private final AddEditPopupView view;
	private final PopupMode mode;
	private final Map<String, Object> mappa;

	public PopupControllerImpl(final PopupMode mode, final AbstractModel model,
			final IAnagraficaViewObserver parentController, final AbstractAnagraficaView parentView)
			throws InstanceNotFoundException, IllegalArgumentException {
		this.mode = mode;
		this.model = model;
		this.parentController = parentController;
		this.parentView = parentView;
		parentView.setEnabled(false);
		String titolo;
		switch (mode) {
		case ADD:
			titolo = "Aggiungi";
			mappa = model.getMap(null);
			break;
		case EDIT:
			titolo = "Modifica";
			mappa = model.getMap(parentView.getSelectedItem());
			break;
		case FIND:
			titolo = "Filtra/Cerca";
			mappa = model.getFilterMap();
			break;
		default:
			throw new IllegalArgumentException("Modalità non consentita.");
		}
		Dimension dim = new Dimension(350, 200 + 40 * mappa.size());
		this.view = new AddEditPopupView(titolo, dim, mappa);
		view.setObserver(this);
		view.start();
	}

	@Override
	public void chiusura() {
		view.close();
	}

	public void go(final HashMap<String, JComponent> compoMap) {
		try {
			switch (mode) {
			case ADD:
				model.add(populateMap(compoMap));
				parentController.refresh();
				break;
			case EDIT:
				model.edit(parentView.getSelectedItem(), populateMap(compoMap));
				parentController.refresh();
				break;
			case FIND:
				filterList(populateMap(compoMap));
				break;
			}
			chiusura();
		} catch (Exception e) {
			view.errorDialog("errore", e.getMessage());
		}
	}

	public void filterList(final Map<String, Object> mappa) {
		try {
			parentView.setList(model.load(mappa));
		} catch (InstanceNotFoundException e) {
			view.errorDialog("errore", e.getMessage());
		}
	}

	public Map<String, Object> populateMap(final HashMap<String, JComponent> compoMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (String key : mappa.keySet()) {
			JComponent field = compoMap.get(key);
			Object defaultValue = mappa.get(key);

			if (defaultValue instanceof String && field instanceof JTextField) {
				map.put(key, ((JTextField) field).getText());
			} else if (defaultValue instanceof char[] && field instanceof JTextField) {
				map.put(key, ((JPasswordField) field).getPassword());
			} else if (defaultValue instanceof Date && field instanceof JSpinner) {
				map.put(key, ((JSpinner) field).getValue());
			} else if (defaultValue instanceof Number && field instanceof JSpinner) {
				Number numero = (Number) ((JSpinner) field).getValue();
				if (defaultValue instanceof Float) {
					map.put(key, numero.floatValue());
				} else if (defaultValue instanceof Double) {
					map.put(key, numero.doubleValue());
				} else if (defaultValue instanceof Long) {
					map.put(key, numero.longValue());
				} else {
					map.put(key, numero.intValue());
				}
			} else if (defaultValue instanceof Enum && defaultValue instanceof IDataEnum
					&& field instanceof JComboBox) {
				map.put(key, ((JComboBox<?>) field).getSelectedItem());
			} else {
				throw new IllegalArgumentException(
						"Errore di conversione del dato " + key + " correggere e riprovare.");
			}
		}
		System.out.println(map);
		return map;
	}
}
