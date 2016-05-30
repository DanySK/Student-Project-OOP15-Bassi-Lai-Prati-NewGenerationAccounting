/**
 * 
 */
package view.situazAziendale;

import java.awt.Dimension;

import view.AbstractWideView;

/**
 * view per visualizzare la situazione aziendale con stato patrimoniale, conto
 * economico e commento.
 * 
 * @author Pentolo
 *
 */
public class SitAzView extends AbstractWideView {

	private static final long serialVersionUID = -8573556973965470550L;

	/**
	 * @param title
	 *            titolo della finestra
	 * @param Dimension
	 *            dimensione della finestra
	 */
	public SitAzView(final String title, final Dimension dimension) {
		super(title, dimension);
	}
}
