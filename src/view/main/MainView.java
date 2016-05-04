package view.main;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;

import view.AbstractFrame;

public class MainView extends AbstractFrame {

	private static final String ACQUISTO = "Crea fattura";
	private static final String ANA_AZIENDE = "Cambia/Modifica Azienda";
	private static final String ANA_CLI_FOR = "Anagrafica Clienti e Fornitori";
	private static final String ANA_CONTI = "Gestione Conti";
	private static final String ANA_MOVIMENTI = "Visualizza Movimenti";
	private static final String ANA_PROD_SCORTE = "Anagrafica Prod. e Scorte attuali";
	private static final String CHIUDI = "Esci";
	private static final String DEBITI_CREDITI = "Dettaglio Debiti e Crediti";
	private static final String SITUAZIONE_AZIENDALE = "Visualizza Situazione Aziendale";

	public MainView() {
		super("Menù principale", new Dimension(250, 400));
		this.getContentPane().add(new JButton("Apri anaAziende"), BorderLayout.CENTER);
	}

}
