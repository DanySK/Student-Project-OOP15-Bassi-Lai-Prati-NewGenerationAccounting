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

import controller.AbstractAnagraficaViewObserver;
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

	public AnaAziendeView() {
		super(Company.getIntestazione(), "Anagrafica Aziende");
		JPasswordField passwordField = new JPasswordField(15);
		JButton accediButton = new JButton("Accedi");
		JPanel topPanel = new JPanel(new FlowLayout());
		topPanel.add(new JLabel("Password: "));
		topPanel.add(passwordField);
		topPanel.add(accediButton);
		MyFrame.getContentPane().add(topPanel, BorderLayout.NORTH);
        //if (isPasswordCorrect(passwordField.getPassword())) {
		
		/*lista.add(new Company(1, "a", "bbbbbbbbbb", 253456, "c", "d", 7777, "e", "f"));
		lista.add(new Company(1, "abghgrtn", "asd", 122533456, "tnrc", "dntr", 7734577, "e234", "f3444"));
		lista.add(new Company(1, "mmmntr", "tnt", 1234567890, "trnnc", "d", 7777, "efsfsee", "ef"));*/
		
		accediButton.addActionListener(e -> {
			((AbstractAnagraficaViewObserver) observer).accedi(getModel().getObjectAt(getTable().getSelectedRow()), passwordField.getPassword());
		});
	}
}
