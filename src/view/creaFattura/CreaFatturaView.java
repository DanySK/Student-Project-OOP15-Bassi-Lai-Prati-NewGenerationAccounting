/**
 * 
 */
package view.creaFattura;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.creaFattura.CreaFatturaControllerImpl;
import dataModel.Customers_Suppliers;
import dataModel.Item;
import view.AnagraficaView;

/**
 * la schermata di acquisto prodotti per la creazione di fattura
 * 
 * @author Pentolo
 *
 */
public class CreaFatturaView extends AnagraficaView<Item> {
	private final JComboBox<Customers_Suppliers> customerField;
	private static final long serialVersionUID = 1573273884755541097L;

	/**
	 * @param list lista dei valori da inserire nella JTable
	 * @param title titolo della finestra
	 */
	public CreaFatturaView(final LinkedList<Item> list, final String title) {
		super(list, Item.getIntestazione(), title);
		customerField = new JComboBox<Customers_Suppliers>(
				((CreaFatturaControllerImpl) getObserver()).getCustomersList());
		JButton creaButton = new JButton("Accedi");
		JPanel topPanel = new JPanel(new FlowLayout());
		topPanel.add(new JLabel("Seleziona il cliente: "));
		topPanel.add(customerField);
		topPanel.add(creaButton);
		getMyFrame().getContentPane().add(topPanel, BorderLayout.NORTH);
		SwingUtilities.getRootPane(getMyFrame()).setDefaultButton(creaButton);
		creaButton.addActionListener(e -> {
			Customers_Suppliers item = null;
			try {
				item = (Customers_Suppliers) getSelectedItem();
			} catch (Exception ex) {
				errorDialog("Errore", ex.getMessage());
				return;
			}
			if (item != null) {
				((CreaFatturaControllerImpl) getObserver()).create(item);
			} else {
				errorDialog("Attenzione, seleziona una riga per continuare!", "nessuna riga selezionata");
			}
		});
	}

	public Customers_Suppliers getSelectedCustomer() {
		Customers_Suppliers item = null;
		try {
			item = (Customers_Suppliers) getSelectedItem();
		} catch (Exception ex) {
			errorDialog("Errore", ex.getMessage());
			return null;
		}
		if (item != null) {
			return (Customers_Suppliers) item;
		} else {
			errorDialog("Attenzione, seleziona una riga per continuare!", "nessuna riga selezionata");
			return null;
		}
	}
}
