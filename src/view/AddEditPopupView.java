/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Date;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;

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

	/**
	 * @param title
	 * @param dimension
	 */
	public AddEditPopupView(final IDataTableModel obj, final String title, final Dimension dimension,
			final AbstractModel model) {
		super(title, dimension);
		this.model = model;
		Map<String, Object> mappa = model.g;
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JComponent field;
		for (String campo : mappa.keySet()) {
			panel.add(Box.createRigidArea(new Dimension(0, 5)));
			Object item = mappa.get(campo);
			if (item instanceof String) {
				field = new JTextField(25);
				((JTextField) field).setText((String) item);
			} else if (item instanceof Date) {
				field = new javax.swing.JSpinner(new SpinnerDateModel());
			} else if (item instanceof Long) {
				SpinnerNumberModel sm = new SpinnerNumberModel();
				field = new javax.swing.JSpinner(sm);
			} else {
				field = new JTextField();
				((JTextField) field).setText((String) item);
			}
			field.setName(campo);
			field.setAlignmentX(Component.CENTER_ALIGNMENT);
			panel.add(new JLabel(campo + ": "));
			panel.add(field);
		}

		JPanel footer = new JPanel(new FlowLayout());
		JButton chiudi = new JButton("Chiudi");
		JButton btn = new JButton();
		chiudi.addActionListener(e -> {
			chiusura();
		});
		if (obj.equals(null)) {
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
		MyFrame.getContentPane().add(panel, BorderLayout.CENTER);
	}

	@Override
	protected void chiusura() {
		this.close();
	}

	private void add() {
	}

	private void edit() {
	}

}
