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
	public void chiusura() {
		System.exit(0);
	}

	public void AnaAziendeBtn() {
		view.close();
		new AnaAziendeControllerImpl();
	}
	
	public void btn0 (String title){
		
	}
	
	public void btn1 (String title){
		
	}

	public void btn2 (String title){
	
	}

	public void btn3 (String title){
	
	}
	
	public void btn4 (String title){
		
	}
	
	public void btn5 (String title){
		
	}

	public void btn6 (String title){
	
	}

	public void btn7 (String title){
	
	}
}
