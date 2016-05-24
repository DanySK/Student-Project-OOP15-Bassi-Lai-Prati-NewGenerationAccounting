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

import controller.PopupControllerImpl;
import dataEnum.IDataEnum;

/**
 * @author Pentolo
 *
 */
public class AddEditPopupView extends AbstractWideView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2412389895309056834L;
	private PopupControllerImpl controller;
	private final HashMap<String, JComponent> compoMap;

	/**
	 * @param title
	 * @param dimension
	 */
	public AddEditPopupView(final String title, final Dimension dimension, final Map<String, Object> mappa) {
		super(title, dimension);
		compoMap = new HashMap<String, JComponent>();
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		JPanel itemPanel;
		for (String campo : mappa.keySet()) {
			itemPanel = new JPanel();
			itemPanel.add(new JLabel(campo + ": "));
			Object item = mappa.get(campo);
			if (item instanceof char[]) {
				item = new String((char[]) item);
			}
			if (item instanceof String) {
				JTextField jtf = new JTextField(25);
				if (item != null) {
					jtf.setText((String) item);
				}
				compoMap.put(campo, jtf);
				itemPanel.add(jtf);
			} else if (item instanceof Date) {
				JSpinner js = new JSpinner(new SpinnerDateModel());
				if (item != null) {
					js.setValue(item);
				}
				compoMap.put(campo, js);
				itemPanel.add(js);
			} else if (item instanceof Number) {
				JSpinner js;
				if (item instanceof Float) {
					js = new JSpinner(new SpinnerNumberModel(((Float) item).floatValue(), null, null, 0.01));
				} else if (item instanceof Double) {
					js = new JSpinner(new SpinnerNumberModel(((Double) item).doubleValue(), null, null, 0.01));
				} else if (item instanceof Long) {
					js = new JSpinner(new SpinnerNumberModel(((Long) item).longValue(), null, null, 1));
				} else {
					js = new JSpinner(new SpinnerNumberModel(((Integer) item).intValue(), null, null, 1));
				}
				if (item != null) {
					js.setValue(item);
				}
				js.setPreferredSize(new Dimension(100, js.getPreferredSize().height));
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
		btn.setText(title);
		btn.addActionListener(e -> {
			controller.go(compoMap);
		});
		footer.add(btn);
		footer.add(chiudi);
		MyFrame.getContentPane().add(footer, BorderLayout.SOUTH);
		MyFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);
	}

	@Override
	protected void chiusura() {
		controller.chiusura();
	}
}
