/**
 * 
 */
package view.anaAziende;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import controller.AnaAziende.AnaAziendeControllerImpl;
import dataModel.Company;
import view.AbstractAnagraficaView;

/**
 * @author Pentolo
 *
 */
public class AnaAziendeView extends AbstractAnagraficaView<Company> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5859979634610547926L;
	
	public AnaAziendeView(){
		this("Benvenuto in NGA");
	}
	
	public AnaAziendeView(final String title) {
		super(Company.getIntestazione(), title);
		JPasswordField passwordField = new JPasswordField(15);
		JButton accediButton = new JButton("Accedi");
		JPanel topPanel = new JPanel(new FlowLayout());
		topPanel.add(new JLabel("Password: "));
		topPanel.add(passwordField);
		topPanel.add(accediButton);
		MyFrame.getContentPane().add(topPanel, BorderLayout.NORTH);
		accediButton.addActionListener(e -> {
			int row = getTable().getSelectedRow();
			if (row != -1) {
				((AnaAziendeControllerImpl) observer).accedi(getModel().getObjectAt(row),passwordField.getPassword());
			} else {
				errorDialog("Attenzione, seleziona una riga per continuare!", "nessuna riga selezionata");
			}
		});
	}
}
