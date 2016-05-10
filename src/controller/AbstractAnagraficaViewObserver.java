package controller;

import dataModel.Company;
import dataModel.IDataTableModel;
import view.AbstractAnagraficaView;

/**
 * classe astratta per i controller delle view che rispettano il layout di
 * anagrafica, con i 5 tasti nel footer.
 * 
 * @author Pentolo
 *
 */
public abstract class AbstractAnagraficaViewObserver extends AbstractViewObserver {

	public AbstractAnagraficaViewObserver(AbstractAnagraficaView view) {
		super(view);
	}

	@Override
	public abstract void Chiusura();

	public abstract void tasto0();

	public abstract void tasto1();

	public abstract void tasto2();

	public abstract void tasto3();

	public abstract void tasto4();

	public void accedi(Company azienda, char[] password) {
		//if model.ispasswordcorrect(azienda, password)
		
	}
}
