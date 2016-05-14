/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.AbstractViewObserver;
import controller.main.MainControllerImpl;

/**
 * @author Pentolo
 *
 */
public abstract class AbstractWideView extends AbstractFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8661356282182241245L;


	protected AbstractViewObserver observer;
	
	/**
	 * @param title
	 * @param dimension
	 */
	public AbstractWideView(final String title, final Dimension dimension) {
		super(title, dimension);
		JButton chiudi = new JButton("Chiudi");
		JPanel footer = new JPanel(new FlowLayout());
		footer.add(chiudi);
		MyFrame.getContentPane().add(footer, BorderLayout.SOUTH);
		chiudi.addActionListener(e -> {
			chiusura();
		});
	}

	/**
	 * @param observer the observer to set
	 */
	public void setObserver(AbstractViewObserver observer) {
		this.observer=observer;
	}
	
	@Override
	protected void chiusura() {
		observer.chiusura();
	}
}
