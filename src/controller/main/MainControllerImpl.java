/**
 * 
 */
package controller.main;

import controller.AbstractViewObserver;
import controller.AnaAziende.AnaAziendeControllerImpl;
import view.main.MainView;

public class MainControllerImpl extends AbstractViewObserver {

	private final MainView view = new MainView();
	
	public MainControllerImpl() {
		super();
		view.setObserver(this);
		view.start();
	}

	@Override
	public void chiusura() {
		if (view.confirmDialog("Sei sicuro di voler uscire dal programma?", "Uscire")) {
			System.exit(0);
		}
	}

	
	public void btn0 (final String title){
		//view.close();
		//new ();
	}
	
	public void btn1 (){
		view.close();
		new AnaAziendeControllerImpl();
	}

	public void btn2 (final String title){
	
	}

	public void btn3 (final String title){
	
	}
	
	public void btn4 (final String title){
		
	}
	
	public void btn5 (final String title){
		
	}

	public void btn6 (final String title){
	
	}

	public void btn7 (final String title){
	
	}
}
