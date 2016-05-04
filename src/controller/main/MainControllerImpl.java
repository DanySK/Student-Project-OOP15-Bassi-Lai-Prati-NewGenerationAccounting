/**
 * 
 */
package controller.main;

import controller.AbstractViewObserver;
import view.main.MainView;

public class MainControllerImpl extends AbstractViewObserver {

	public MainControllerImpl() {
		super(new MainView());
	}

	@Override
	public void Chiusura() {
		System.exit(0);
	}

}
