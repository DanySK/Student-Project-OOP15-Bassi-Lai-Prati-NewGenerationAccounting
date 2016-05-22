import java.io.IOException;

import controller.anaAziende.AnaAziendeControllerImpl;
import controller.dbController.DBLoader;

/**
 * 
 */

/**
 * 
 * classe per l'avvio del programma
 * 
 * @author Pentolo
 */
public class ApplicationStart {

	/**
	 * @param args
	 *            argomento necessario
	 */
	public static void main(final String[] args) {

		try {
			new AnaAziendeControllerImpl(DBLoader.loadCompanys());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		// new AddEditPopupView("modifica", new Dimension(500, 400), new
		// AccountsModel(null).getMap()).start();
	}
}
