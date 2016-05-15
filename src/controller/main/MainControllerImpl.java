/**
 * 
 */
package controller.main;

import controller.IViewObserver;
import controller.AnaAziende.AnaAziendeControllerImpl;
import controller.anaCliFor.AnaCliForControllerImpl;
import controller.anaConti.AnaContiControllerImpl;
import controller.anaProd.AnaProdControllerImpl;
import controller.creaFattura.CreaFatturaControllerImpl;
import controller.movimenti.MovimentiControllerImpl;
import controller.situazAziendale.SitAzControllerImpl;
import controller.situazCreditiDebiti.SitCredDebControllerImpl;
import view.main.MainView;

public class MainControllerImpl implements IViewObserver {

	private final MainView view;

	public MainControllerImpl() {
		this.view = new MainView();
		this.view.setObserver(this);
		view.start();
	}

	@Override
	public void chiusura() {
		if (view.confirmDialog("Sei sicuro di voler uscire dal programma?", "Uscire")) {
			System.exit(0);
		}
	}

	public void btn0(final String title) {
		view.close();
		new CreaFatturaControllerImpl(title);
	}

	public void btn1(final String title) {
		view.close();
		new AnaAziendeControllerImpl(title);
	}

	public void btn2(final String title) {
		view.close();
		new AnaCliForControllerImpl(title);
	}

	public void btn3(final String title) {
		view.close();
		new AnaContiControllerImpl(title);
	}

	public void btn4(final String title) {
		view.close();
		new MovimentiControllerImpl(title);
	}

	public void btn5(final String title) {
		view.close();
		new AnaProdControllerImpl(title);
	}

	public void btn6(final String title) {
		view.close();
		new SitCredDebControllerImpl(title);
	}

	public void btn7(final String title) {
		view.close();
		new SitAzControllerImpl(title);
	}
}
