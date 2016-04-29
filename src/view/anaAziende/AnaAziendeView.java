/**
 * 
 */
package view.anaAziende;

import controller.AnaAziende.AnaAziendeViewControllerImpl;
import view.AbstractAnagraficaView;

/**
 * @author Pentolo
 *
 */
public class AnaAziendeView extends AbstractAnagraficaView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5859979634610547926L;

	
	public AnaAziendeView() {
		super("Anagrafica Aziende", new AnaAziendeViewControllerImpl());
		
	}
}
