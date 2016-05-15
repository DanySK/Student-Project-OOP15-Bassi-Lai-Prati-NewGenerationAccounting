/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Pentolo
 *
 */
public class AddEditPopupView extends AbstractFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2412389895309056834L;

	/**
	 * @param title
	 * @param dimension
	 */
	public AddEditPopupView(final String title, final Dimension dimension, final Map<String, Object> mappa) {
		super(title, dimension);
		JPanel Panel = new JPanel(new FlowLayout());
		for (String campo : mappa.keySet()) {
			JTextField field = new JTextField();
			field.setText((String) mappa.get(campo));
			field.setName(campo);
			Panel.add(new JLabel(campo + ": "));
			Panel.add(field);
		}
		MyFrame.getContentPane().add(Panel, BorderLayout.CENTER);
	}

	@Override
	protected void chiusura() {
		// TODO Auto-generated method stub

	}

}
