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

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
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

import dataEnum.IDataEnum;
import dataModel.IDataTableModel;
import model.AbstractModel;

/**
 * @author Pentolo
 *
 */
public class AddEditPopupView extends AbstractWideView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2412389895309056834L;
	private final AbstractModel model;
	private final IDataTableModel obj;
	private final HashMap<String, JComponent> compoMap;

	/**
	 * @param title
	 * @param dimension
	 */
	public AddEditPopupView(final IDataTableModel obj, final String title, final Dimension dimension,
			final AbstractModel model) {
		super(title, dimension);
		this.obj = obj;
		this.model = model;
		Map<String, Object> mappa = model.getMap();
		compoMap = new HashMap<String, JComponent>();
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		JPanel itemPanel;
		for (String campo : mappa.keySet()) {
			itemPanel = new JPanel(new FlowLayout());
			itemPanel.add(new JLabel(campo + ":"));
			Object item = mappa.get(campo);

			if (item instanceof String) {
				JTextField jtf = new JTextField(50);
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
				if (item instanceof Float || item instanceof Double) {
					js = new javax.swing.JSpinner(
							new SpinnerNumberModel(((Number) item).doubleValue(), null, null, 0.01));
				} else {
					js = new javax.swing.JSpinner(new SpinnerNumberModel(((Number) item).longValue(), null, null, 1));
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

			mainPanel.add(Box.createRigidArea(new Dimension(0, 8)));
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

	private void add() {
		Map<String, Object> mappa = new HashMap<String, Object>();
		for (String key : compoMap.keySet()) {
			JComponent field = compoMap.get(key);
			if (field instanceof JComboBox) {
				mappa.put(key, ((JComboBox<?>) field).getSelectedItem());
			}
			if (field instanceof JTextField) {
				mappa.put(key, ((JTextField) field).getText());
			}
			if (field instanceof JSpinner) {
				mappa.put(key, ((JSpinner) field).getValue());
			}
			System.out.println(mappa.get(key).getClass());
			System.out.println(mappa.get(key));
		}
		System.out.println(mappa.toString());
		try {
			model.add(mappa);
		} catch (InstanceAlreadyExistsException e) {
			errorDialog("errore", e.getMessage());
		} catch (IllegalArgumentException e) {
			errorDialog("errore", e.getMessage());
		}

	}

	@Override
	protected void chiusura() {
		this.close();
	}

	private void edit() {
		try {
			model.edit(obj, null);
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
