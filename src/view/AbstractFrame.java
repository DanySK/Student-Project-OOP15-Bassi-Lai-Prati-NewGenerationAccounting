package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.AbstractViewObserver;

public abstract class AbstractFrame extends JFrame {

	
	private static final long serialVersionUID = 638596561545905264L;
	
	private JFrame MyFrame;
	protected AbstractViewObserver observer;
	
	/**
	 * frame generico del progetto NGA
	 * @param title titolo della finestra
	 * @param lm LayoutManager
	 * @param dimension	dimensione finestra
	 */
	public AbstractFrame(String title, Dimension dimension, AbstractViewObserver observer){
		MyFrame = new JFrame("NGA - " + title);
		MyFrame.setSize(dimension);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		getContentPane().add(new JPanel(new BorderLayout()));
		this.observer = observer;
		MyFrame.addWindowListener(new WindowListener() {
			@Override
			public void windowActivated(final WindowEvent arg0) {}
			@Override
			public void windowClosed(final WindowEvent arg0) {}
			@Override
			public void windowClosing(final WindowEvent arg0) {
				observer.Chiusura();				
			}
			@Override
			public void windowDeactivated(final WindowEvent arg0) {}
			@Override
			public void windowDeiconified(final WindowEvent arg0) {}
			@Override
			public void windowIconified(final WindowEvent arg0) {}
			@Override
			public void windowOpened(final WindowEvent arg0) {}
		});
	}
	
	public void start(){
		this.MyFrame.setVisible(true);
	}
		
	public boolean confirmDialog (String question , String name){
		return JOptionPane.showConfirmDialog(MyFrame, question, name, JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION;
	}
}
