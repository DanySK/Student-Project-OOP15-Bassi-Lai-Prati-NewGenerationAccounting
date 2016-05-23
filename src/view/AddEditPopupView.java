/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;

import controller.IAnagraficaViewObserver;
import dataEnum.IDataEnum;
import dataModel.IDataTableModel;

/**
 * @author Pentolo
 *
 */
public class AddEditPopupView extends AbstractWideView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2412389895309056834L;
	private final IAnagraficaViewObserver controller;
	private final HashMap<String, JComponent> compoMap;
	private final AbstractAnagraficaView view;
	private final Map<String, Object> mappa;

	/**
	 * @param title
	 * @param dimension
	 */
	public AddEditPopupView(final IDataTableModel obj, final String title, final Dimension dimension,
			final IAnagraficaViewObserver controller, final AbstractAnagraficaView view) {
		super(title, dimension);
		this.view = view;
		this.controller = controller;
		mappa = controller.getMap(obj);
		compoMap = new HashMap<String, JComponent>();
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		JPanel itemPanel;
		for (String campo : mappa.keySet()) {
			itemPanel = new JPanel(new FlowLayout());
			itemPanel.add(new JLabel(campo + ":"));
			Object item = mappa.get(campo);

			if (item instanceof String) {
				JTextField jtf = new JTextField(25);
				if (item != null) {
					jtf.setText((String) item);
				}
				compoMap.put(campo, jtf);
				itemPanel.add(jtf);
			} else if (item instanceof Date) {
				JSpinner js = new javax.swing.JSpinner(new SpinnerDateModel());
				if (item != null) {
					js.setValue(item);
				}
				compoMap.put(campo, js);
				itemPanel.add(js);
			} else if (item instanceof Number) {
				JSpinner js;
				if (item instanceof Float) {
					js = new javax.swing.JSpinner(
							new SpinnerNumberModel(((Float) item).floatValue(), null, null, 0.01));
				} else if (item instanceof Double) {
					js = new javax.swing.JSpinner(
							new SpinnerNumberModel(((Double) item).doubleValue(), null, null, 0.01));
				} else if (item instanceof Long) {
					js = new javax.swing.JSpinner(new SpinnerNumberModel(((Long) item).longValue(), null, null, 1));
				} else {
					js = new javax.swing.JSpinner(new SpinnerNumberModel(((Integer) item).intValue(), null, null, 1));
				}
				if (item != null) {
					js.setValue(item);
				}
				compoMap.put(campo, js);
				itemPanel.add(js);
			} else if (item instanceof Enum && item instanceof IDataEnum) {
				JComboBox<Enum<?>> jcb = new JComboBox<Enum<?>>(((IDataEnum) item).getEnumValues());
				if (item != null) {
					jcb.setSelectedItem(item);
				}
				compoMap.put(campo, jcb);
				itemPanel.add(jcb);
			}
			mainPanel.add(Box.createRigidArea(new Dimension(0, 7)));
			mainPanel.add(itemPanel);
			itemPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		JPanel footer = new JPanel(new FlowLayout());
		JButton chiudi = new JButton("Chiudi");
		JButton btn = new JButton();
		chiudi.addActionListener(e -> {
			chiusura();
		});
		if (obj == null) {
			btn.setText("Aggiungi");
			btn.addActionListener(e -> {
				add();
			});
		} else {
			btn.setText("Modifica");
			btn.addActionListener(e -> {
				edit();
			});
		}
		footer.add(btn);
		footer.add(chiudi);
		MyFrame.getContentPane().add(footer, BorderLayout.SOUTH);
		MyFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);
	}

	private Map<String, Object> populateMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		for (String key : mappa.keySet()) {
			JComponent field = compoMap.get(key);
			Object defaultValue = mappa.get(key);

			if (defaultValue instanceof String && field instanceof JTextField) {
				map.put(key, ((JTextField) field).getText());
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

	private void add() {
		try {
			controller.add(populateMap());
		} catch (Exception e) {
			errorDialog("errore", e.getMessage());
		}
		controller.refresh();
		chiusura();
	}

	@Override
	protected void chiusura() {
		this.close();
	}

	private void edit() {
		try {
			controller.edit(populateMap());
		} catch (Exception e) {
			errorDialog("errore", e.getMessage());
		}
		controller.refresh();
		chiusura();
	}

}
