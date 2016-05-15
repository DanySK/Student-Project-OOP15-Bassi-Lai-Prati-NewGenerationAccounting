/**
 * 
 */
package view.creaFattura;

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
	public CreaFatturaView(final String title) {
		super(Item.getIntestazione(), title);
	}
}
