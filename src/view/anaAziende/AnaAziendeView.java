/**
 * 
 */
package view.anaAziende;

import controller.AnaAziende.AnaAziendeControllerImpl;
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

	
	public AnaAziendeView(AnaAziendeControllerImpl observer) {
		super("Anagrafica Aziende", observer);
	}
}
