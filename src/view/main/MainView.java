package view.main;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;

import view.AbstractFrame;

public class MainView extends AbstractFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8496684753244767160L;
	
	private static final String ACQUISTO = Messages.getString("MainView.0"); //$NON-NLS-1$
	private static final String ANA_AZIENDE = Messages.getString("MainView.1"); //$NON-NLS-1$
	private static final String ANA_CLI_FOR = Messages.getString("MainView.2"); //$NON-NLS-1$
	private static final String ANA_CONTI = Messages.getString("MainView.3"); //$NON-NLS-1$
	private static final String ANA_MOVIMENTI = Messages.getString("MainView.4"); //$NON-NLS-1$
	private static final String ANA_PROD_SCORTE = Messages.getString("MainView.5"); //$NON-NLS-1$
	private static final String CHIUDI = Messages.getString("MainView.6"); //$NON-NLS-1$
	private static final String DEBITI_CREDITI = Messages.getString("MainView.7"); //$NON-NLS-1$
	private static final String SITUAZIONE_AZIENDALE = Messages.getString("MainView.8"); //$NON-NLS-1$

	public MainView() {
		super(Messages.getString("MainView.9"), new Dimension(250, 400)); //$NON-NLS-1$
		this.getContentPane().add(new JButton(Messages.getString("MainView.10")), BorderLayout.CENTER); //$NON-NLS-1$
	}

}
