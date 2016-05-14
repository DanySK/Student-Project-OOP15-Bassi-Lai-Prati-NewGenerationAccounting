package controller.AnaAziende;

import java.util.Optional;

import controller.AbstractAnagraficaViewObserver;
import controller.main.MainControllerImpl;
import dataModel.Company;
import view.anaAziende.AnaAziendeView;

public class AnaAziendeControllerImpl extends AbstractAnagraficaViewObserver {
	
	private boolean noCompany = false;
	
	public AnaAziendeControllerImpl(){
		this(new AnaAziendeView());
		noCompany = true;
	}
	
	public AnaAziendeControllerImpl(String title) {
		this(new AnaAziendeView(title));
	}
	
	private AnaAziendeControllerImpl(AnaAziendeView view){
		super(view);
		view.setObserver(this);
		view.start();
	}

	@Override
	public void chiusura() {
		if(noCompany){
			if (view.confirmDialog("Sei sicuro di voler uscire dal programma?", "Uscire")) {
				System.exit(0);
			}
		} else {
			view.close();
			new MainControllerImpl();
		}
	}

	@Override
	public void tasto0() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tasto1() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tasto2() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tasto3() {
		// TODO Auto-generated method stub

	}
	
	public void accedi(final Company objectAt, final char[] password) {
		view.close();
		new MainControllerImpl();
	}

}
