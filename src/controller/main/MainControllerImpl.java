/**
 * 
 */
package controller.main;

import controller.AbstractViewObserver;
import controller.AnaAziende.AnaAziendeControllerImpl;
import view.AbstractFrame;
import view.main.MainView;

public class MainControllerImpl extends AbstractViewObserver {

	private final MainView view = new MainView();
	
	public MainControllerImpl() {
		super();
		view.setObserver(this);
		view.start();
	}

	@Override
	public void Chiusura() {
		System.exit(0);
	}

	public void AnaAziendeBtn() {
		view.close();
		new AnaAziendeControllerImpl();
	}
	
}
