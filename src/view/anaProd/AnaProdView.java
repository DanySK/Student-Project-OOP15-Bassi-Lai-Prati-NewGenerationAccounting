package view.anaProd;

import java.util.LinkedList;

import dataModel.Product;
import view.AbstractAnagraficaView;

public class AnaProdView extends AbstractAnagraficaView<Product> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265273023140682274L;

	public AnaProdView(final LinkedList<Product> lista, final String title) {
		super(lista, Product.getIntestazione(), title);
	}
}
