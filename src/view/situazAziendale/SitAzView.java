/**
 * 
 */
package view.situazAziendale;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JEditorPane;
import javax.swing.JPanel;

import view.AbstractWideView;

/**
 * view per visualizzare la situazione aziendale con stato patrimoniale, conto
 * economico e commento.
 * 
 * @author Pentolo
 *
 */
public class SitAzView extends AbstractWideView {

	private static final Dimension DEFAULT = new Dimension(200, 300);
	private static final Dimension DEFAULTSALDO = new Dimension(100, 300);
	private static final long serialVersionUID = -8573556973965470550L;
	private final String AnalisiFinanziaria;
	private final String Attivita;
	private final String Saldi_Attivita;
	private final String Passivita;
	private final String Saldi_Passivita;
	private final String Costi;
	private final String Saldi_Costi;
	private final String Ricavi;
	private final String Saldi_Ricavi;
	private final Float Saldo_Stato_Patr;
	private final Float Saldo_Conto_Ec;

	/**
	 * @param title
	 *            titolo della finestra
	 * @param Dimension
	 *            dimensione della finestra
	 */
	public SitAzView(String title, Dimension dimension, String analisiFinanziaria, String attivita,
			String saldi_Attivita, String passivita, String saldi_Passivita, String costi, String saldi_Costi,
			String ricavi, String saldi_Ricavi, Float saldo_Stato_Patr, Float saldo_Conto_Ec) {
		super(title, dimension);
		AnalisiFinanziaria = analisiFinanziaria;
		Attivita = attivita;
		Saldi_Attivita = saldi_Attivita;
		Passivita = passivita;
		Saldi_Passivita = saldi_Passivita;
		Costi = costi;
		Saldi_Costi = saldi_Costi;
		Ricavi = ricavi;
		Saldi_Ricavi = saldi_Ricavi;
		Saldo_Stato_Patr = saldo_Stato_Patr;
		Saldo_Conto_Ec = saldo_Conto_Ec;
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		addToPanel(panel, getSP());
		addToPanel(panel, getSE());
		addToPanel(panel, getComment());
		getMyFrame().getContentPane().add(panel, BorderLayout.CENTER);
	}

	private void addToPanel(JPanel panel, JPanel comp) {
		comp.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(comp);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
	}

	private JPanel getComment() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(getPane(AnalisiFinanziaria, new Dimension(600, 100)), BorderLayout.CENTER);
		return panel;
	}

	private JEditorPane getPane(final String text, final Dimension dim) {
		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setContentType("text/html");
		editorPane.setText(text);
		editorPane.setPreferredSize(dim);
		editorPane.setFont(new Font("Calibri", Font.PLAIN, 8));;
		return editorPane;
	}
	
	private JEditorPane getPane(final String text, final boolean primaColonna) {
		if (primaColonna)
			return getPane(text, DEFAULT);
		else
			return getPane(text, DEFAULTSALDO);
	}

	private JPanel getSE() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.add(getPane(Ricavi, true));
		panel.add(getPane(Saldi_Ricavi, false));
		panel.add(getPane(Costi, true));
		panel.add(getPane(Saldi_Costi, false));
		return panel;
	}

	private JPanel getSP() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.add(getPane(Attivita, true));
		panel.add(getPane(Saldi_Attivita, false));
		panel.add(getPane(Passivita, true));
		panel.add(getPane(Saldi_Passivita, false));
		return panel;
	}
}
