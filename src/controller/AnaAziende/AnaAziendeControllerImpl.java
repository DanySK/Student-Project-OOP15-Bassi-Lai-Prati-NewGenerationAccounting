package controller.AnaAziende;

import controller.AbstractAnagraficaViewObserver;
import view.anaAziende.AnaAziendeView;

public class AnaAziendeControllerImpl extends AbstractAnagraficaViewObserver {

	public AnaAziendeControllerImpl() {
		super(new AnaAziendeView());
		view.setObserver(this);
		view.start();
	}

	@Override
	public void Chiusura() {
		System.exit(0);
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

	@Override
	public void tasto4() {
		// TODO Auto-generated method stub

	}

}
