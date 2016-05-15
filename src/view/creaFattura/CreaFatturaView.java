/**
 * 
 */
package view.creaFattura;

import java.util.LinkedList;

import dataModel.Item;
import view.AbstractAnagraficaView;

/**
 * @author Pentolo
 *
 */
public class CreaFatturaView extends AbstractAnagraficaView<Item> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1573273884755541097L;

	/**
	 * 
	 */
	public CreaFatturaView(final LinkedList<Item> lista, final String title) {
		super(lista, Item.getIntestazione(), title);
	}
}
