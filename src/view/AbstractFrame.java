package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.AbstractViewObserver;

public abstract class AbstractFrame extends JFrame {

	private static final long serialVersionUID = 638596561545905264L;

	protected JFrame MyFrame;

	/**
	 * frame generico del progetto NGA
	 * 
	 * @param title
	 *            titolo della finestra
	 * @param lm
	 *            LayoutManager
	 * @param dimension
	 *            dimensione finestra
	 */
	public AbstractFrame(final String title, final Dimension dimension) {
		MyFrame = new JFrame("NGA - " + title);
		MyFrame.setSize(dimension);
		MyFrame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		MyFrame.getContentPane().add(new JPanel(new BorderLayout()));
		MyFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing (WindowEvent e) {
				chiusura();
			}
		});
	}

	protected abstract void chiusura();

	public boolean confirmDialog(final String question, final String title) {
		return JOptionPane.showConfirmDialog(MyFrame, question, title,
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
	}
	
	public void errorDialog(final String title, final String message){
		JOptionPane.showMessageDialog(MyFrame, message, title, JOptionPane.ERROR_MESSAGE );
	}

	public void start() {
		this.MyFrame.setVisible(true);
	}

	public void close() {
		this.MyFrame.setVisible(false);
	}
}
