/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

import controller.AbstractAnagraficaViewObserver;

/**
 * @author Pentolo
 *
 */
public abstract class AbstractAnagraficaView extends AbstractFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1706093338606827050L;

	/**
	 * @param title
	 * @param lm
	 * @param dimension
	 */
	public AbstractAnagraficaView(String title, AbstractAnagraficaViewObserver observer) {
		super(title, new Dimension(430, 500), observer);
		final JPanel footer = new JPanel(new FlowLayout());
		footer.add ( new JButton ("Cerca"));
		footer.add ( new JButton ("Aggiungi"));
		footer.add ( new JButton ("Modifica"));
		footer.add ( new JButton ("Cancella"));
		footer.add ( new JButton ("Chiudi"));
		this.getContentPane().add(new JList<String>(), BorderLayout.CENTER);
		this.getContentPane().add(footer, BorderLayout.SOUTH);
		this.setVisible(true);
	}

}
