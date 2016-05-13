package view.main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.main.MainControllerImpl;
import view.AbstractWideView;

public class MainView extends AbstractWideView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8496684753244767160L;
	private String[] listaBtn;
	private MainControllerImpl observer;

	/**
	 * @param observer the observer to set
	 */
	public void setObserver(MainControllerImpl observer) {
		this.observer = observer;
	}

	public MainView() {
		super(Messages.getString("MainView.8"), new Dimension(250, 350));
		int maxBtn = Integer.parseInt(Messages.getString("MainView.500"));
		listaBtn = new String[maxBtn];
		JPanel panel = new JPanel();
		myAction actionEvent = new myAction();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		for (int i=0; i<maxBtn; i++){
			panel.add(Box.createRigidArea(new Dimension(0,5)));
			String tmpStr = Messages.getString("MainView."+i);
			listaBtn[i] = tmpStr; 
			final JButton tmpBtn = new JButton(tmpStr);
			tmpBtn.setActionCommand(tmpStr);
			tmpBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
			tmpBtn.addActionListener(actionEvent);
			panel.add(tmpBtn);
		}
		MyFrame.getContentPane().add(panel, BorderLayout.CENTER);
	}

	class myAction implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String action = e.getActionCommand();
			if (action.equals(listaBtn[0])) {
				observer.btn0(action);
			}
			else if (action.equals(listaBtn[1])) {
				observer.btn1(action);
			}
			else if (action.equals(listaBtn[2])) {
				observer.btn2(action);
			}
			else if (action.equals(listaBtn[3])) {
				observer.btn3(action);
			}
			else if (action.equals(listaBtn[4])) {
				observer.btn4(action);
			}
			else if (action.equals(listaBtn[5])) {
				observer.btn5(action);
			}
			else if (action.equals(listaBtn[6])) {
				observer.btn6(action);
			}
			else if (action.equals(listaBtn[7])) {
				observer.btn7(action);
			}
			else {
				chiusura();
			}
		}
	}

	@Override
	protected void chiusura() {
		observer.chiusura();
	}
}
