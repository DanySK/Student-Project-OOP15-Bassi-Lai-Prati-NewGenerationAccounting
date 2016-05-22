/**
 * 
 */
package view.anaAziende;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import controller.anaAziende.AnaAziendeControllerImpl;
import dataModel.Company;
import view.AbstractAnagraficaView;

/**
 * view per la anagrafica aziende
 * 
 * @author Pentolo
 *
 */
public class AnaAziendeView extends AbstractAnagraficaView<Company> {

	private static final long serialVersionUID = 5859979634610547926L;

	public AnaAziendeView(final LinkedList<Company> lista) {
		this(lista, "Benvenuto in NGA");
	}

	public AnaAziendeView(final LinkedList<Company> lista, final String title) {
		super(lista, Company.getIntestazione(), title);
		JPasswordField passwordField = new JPasswordField(15);
		JButton accediButton = new JButton("Accedi");
		JPanel topPanel = new JPanel(new FlowLayout());
		topPanel.add(new JLabel("Password: "));
		topPanel.add(passwordField);
		topPanel.add(accediButton);
		MyFrame.getContentPane().add(topPanel, BorderLayout.NORTH);
		accediButton.addActionListener(e -> {
			Company item = (Company) getSelectedItem();
			if (item != null) {
				((AnaAziendeControllerImpl) observer).accedi(passwordField.getPassword());
			} else {
				errorDialog("Attenzione, seleziona una riga per continuare!", "nessuna riga selezionata");
			}
		});
	}
}
