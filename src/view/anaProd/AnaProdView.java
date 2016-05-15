package view.anaProd;

import dataModel.Product;
import view.AbstractAnagraficaView;

public class AnaProdView extends AbstractAnagraficaView<Product> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265273023140682274L;

	public AnaProdView(final String title) {
		super(Product.getIntestazione(), title);
	}
}
