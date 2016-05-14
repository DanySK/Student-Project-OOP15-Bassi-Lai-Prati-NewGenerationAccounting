/**
 * 
 */
package view.movimenti;

import dataModel.Movement;
import view.AbstractAnagraficaView;

/**
 * @author Pentolo
 *
 */
public class MovimentiView extends AbstractAnagraficaView<Movement> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7682380373297678954L;

	/**
	 * 
	 */
	public MovimentiView(final String title) {
		super(Movement.getIntestazione(),title);
	}
}
