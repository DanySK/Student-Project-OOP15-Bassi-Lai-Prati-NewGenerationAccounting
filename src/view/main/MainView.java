package view.main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.main.MainControllerImpl;
import view.AbstractFrame;
import view.AbstractWideView;

public class MainView extends AbstractWideView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8496684753244767160L;
	private static final String CHIUDI = "Esci"; 
	private final ArrayList<String> listaBtnStrings;
	private final ArrayList<JButton> listaBtn;
	private MainControllerImpl observer;
	
	/**
	 * @param observer the observer to set
	 */
	public void setObserver(MainControllerImpl observer) {
		this.observer = observer;
	}

	public MainView() {
		super(Messages.getString("MainView.8"), new Dimension(250, 350)); //$NON-NLS-1$
		int maxBtn = Integer.parseInt(Messages.getString("MainView.500"));
		listaBtnStrings = new ArrayList<String>(maxBtn);
		listaBtn = new ArrayList<JButton>(maxBtn);
		JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		for (int i=0; i<maxBtn; i++){
			panel.add(Box.createRigidArea(new Dimension(0,5)));
			String tmpStr = Messages.getString("MainView."+i);
			listaBtnStrings.add(tmpStr);
			JButton tmpBtn = new JButton(tmpStr);
			tmpBtn.setName(tmpStr);
			tmpBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
			panel.add(tmpBtn);
		}
		MyFrame.getContentPane().add(panel, BorderLayout.CENTER);
	}
	

	@Override
	protected void Chiusura() {
		confirmDialog("a", "a");
	}
}
