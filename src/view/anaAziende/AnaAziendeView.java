/**
 * 
 */
package view.anaAziende;

import dataModel.Company;
import view.AbstractAnagraficaView;

/**
 * @author Pentolo
 *
 */
public class AnaAziendeView extends AbstractAnagraficaView<Company> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5859979634610547926L;

	public AnaAziendeView() {
		super(Company.getIntestazione(), "Anagrafica Aziende");
		
		lista.add(new Company(1, "a", "b", 123253456, "c", "d", 7777, "e", "f"));
		lista.add(new Company(1, "abrtn", "asd", 122533456, "tnrc", "dntr", 7734577, "e234", "f3444"));
		lista.add(new Company(1, "antr", "tnb", 123434556, "trnnc", "d", 7777, "efsfsee", "ef"));
	}
}
