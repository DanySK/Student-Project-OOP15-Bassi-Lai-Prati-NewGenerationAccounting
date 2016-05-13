/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Pentolo
 *
 */
public abstract class AbstractWideView extends AbstractFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8661356282182241245L;

	/**
	 * @param title
	 * @param dimension
	 */
	public AbstractWideView(String title, Dimension dimension) {
		super(title, dimension);
		JButton chiudi = new JButton("Chiudi");
		JPanel footer = new JPanel(new FlowLayout());
		footer.add(chiudi);
		MyFrame.getContentPane().add(footer, BorderLayout.SOUTH);
		chiudi.addActionListener(e -> {
			Chiusura();
		});
	}
}
