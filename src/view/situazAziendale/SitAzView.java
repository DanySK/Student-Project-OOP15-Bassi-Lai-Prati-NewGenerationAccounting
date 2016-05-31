/**
 * 
 */
package view.situazAziendale;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import view.AbstractWideView;

/**
 * view per visualizzare la situazione aziendale con stato patrimoniale, conto
 * economico e commento.
 * 
 * @author Pentolo
 *
 */
public class SitAzView extends AbstractWideView {

	private static final Dimension DEFAULT = new Dimension(245, 300);
	private static final Dimension DEFAULTSALDO = new Dimension(70, 300);
	private static final String ATTIVITA = "ATTIVITA";
	private static final String PASSIVITA = "PASSIVITA";
	private static final String COSTI = "COSTI";
	private static final String RICAVI = "RICAVI";
	private static final String COLSALDO = "€";
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
		addToPanel(panel, new JScrollPane(getSP()));
		addToPanel(panel, new JScrollPane(getSE()));
		addToPanel(panel, new JScrollPane(getComment()));
		getMyFrame().getContentPane().add(panel, BorderLayout.CENTER);
	}

	private void addToPanel(JPanel panel, JComponent comp) {
		comp.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(comp);
		// panel.add(Box.createRigidArea(new Dimension(0, 5)));
	}

	private JComponent getComment() {
		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setContentType("text/html");
		String htmlText = "<font size=3>" + AnalisiFinanziaria.replaceAll("(\r\n|\n)", "<br/>") + "</font>";
		editorPane.setText(htmlText);
		editorPane.setPreferredSize(new Dimension(650, 75));
		return editorPane;
	}

	private JPanel getPane(final String text, final Dimension dim, final String fontSize, final String intestaz,
			final boolean isSaldo, final Float saldo) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		addToPanel(panel, new JLabel(intestaz));
		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setContentType("text/html");
		String htmlText = "<font size=" + fontSize + ">" + text.replaceAll("(\r\n|\n)", "<br/>") + "</font>";
		editorPane.setText(htmlText);
		editorPane.setPreferredSize(dim);
		addToPanel(panel, editorPane);
		if (isSaldo) {
			addToPanel(panel, new JLabel(String.valueOf(saldo)));
		} else {
			addToPanel(panel, new JLabel("TOTALE " + intestaz + ":"));
		}
		return panel;
	}

	private JPanel getPane(final String text, final String intestaz) {
		if (intestaz.equals(COLSALDO)) {
			if (intestaz.equals(ATTIVITA) || intestaz.equals(PASSIVITA)) {
				return getPane(text, DEFAULTSALDO, "2", intestaz, true, Saldo_Stato_Patr);
			} else {
				return getPane(text, DEFAULTSALDO, "2", intestaz, true, Saldo_Conto_Ec);
			}
		} else {
			return getPane(text, DEFAULT, "2", intestaz, false, new Float(0));
		}
	}

	private JPanel getSE() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.add(getPane(Costi, COSTI));
		panel.add(getPane(Saldi_Costi, COLSALDO));
		panel.add(getPane(Ricavi, RICAVI));
		panel.add(getPane(Saldi_Ricavi, COLSALDO));
		return panel;
	}

	private JPanel getSP() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.add(getPane(Attivita, ATTIVITA));
		panel.add(getPane(Saldi_Attivita, COLSALDO));
		panel.add(getPane(Passivita, PASSIVITA));
		panel.add(getPane(Saldi_Passivita, COLSALDO));
		return panel;
	}
}
