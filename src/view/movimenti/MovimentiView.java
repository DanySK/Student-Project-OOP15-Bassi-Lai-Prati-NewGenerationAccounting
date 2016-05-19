/**
 * 
 */
package view.movimenti;

import java.util.LinkedList;

import dataModel.Movement;
import view.AbstractAnagraficaView;

/**
 * la view della finestra di gestione dei movimenti
 * 
 * @author Pentolo
 *
 */
public class MovimentiView extends AbstractAnagraficaView<Movement> {

	private static final long serialVersionUID = -7682380373297678954L;

	/**
	 * 
	 */
	public MovimentiView(final LinkedList<Movement> lista, final String title) {
		super(lista, Movement.getIntestazione(), title);
	}
}
