package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.AbstractViewObserver;
import controller.Controller;

public abstract class AbstractFrame extends JFrame {

	
	private static final long serialVersionUID = 638596561545905264L;
	
	protected JFrame MyFrame;
	protected transient final AbstractViewObserver observer;
	
	private static final String QUIT = " Quit ";
	private static final String RESET = " Reset ";
	private static final String GO = "Go";
	
	/*
		14 frame . getContentPane (). add( new JPanel ( new BorderLayout ()));
	15 final JPanel pNorth = new JPanel ( new FlowLayout ());
	16 final JTextField tNumber = new JTextField (10) ;
	17 final JButton bGo = new JButton (GO);
	18 pNorth . add ( tNumber );
	19 pNorth . add ( bGo );
	20 final JPanel pSouth = new JPanel ( new FlowLayout ( FlowLayout . RIGHT ));
	21 final JButton bReset = new JButton ( RESET );
	22 final JButton bQuit = new JButton ( QUIT );
	23 pSouth . add ( bReset );
	24 pSouth . add ( bQuit );
	25 frame . getContentPane (). add( pNorth , BorderLayout . NORTH );
	26 frame . getContentPane (). add( pSouth , BorderLayout . SOUTH );
	x bGo . addActionListener ( new ActionListener (){
		2 public void actionPerformed ( ActionEvent e) {
		3 try {
		4 observer . newAttempt ( Integer . parseInt ( tNumber . getText ()));
		5 } catch ( NumberFormatException exception ){
		6 JOptionPane . showMessageDialog (frame , "An integer please ..");
		7 }
		8 }}) ;
		9 bQuit . addActionListener (new ActionListener (){
		10 public void actionPerformed ( ActionEvent e) {
		11 if ( confirmDialog (" Confirm quitting ?"," Quit ")){
		12 observer . quit ();
		13 }
		14 }}) ;
		15 bReset . addActionListener ( new ActionListener (){
		16 public void actionPerformed ( ActionEvent e) {
		17 if ( confirmDialog (" Confirm resetting ?"," Reset ")){
		18 observer . resetGame ();
		19 }
		20 }}) ;
*/
	
			
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
				//TODO evento chiusura
				System.exit(0);
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
		
	protected boolean confirmDialog (String question , String name){
		return JOptionPane.showConfirmDialog(MyFrame, question, name, JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION;
	}
}
