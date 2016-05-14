/**
 * 
 */
package view.anaConti;

import dataModel.Account;
import view.AbstractAnagraficaView;

/**
 * @author Pentolo
 *
 */
public class AnaContiView extends AbstractAnagraficaView<Account> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5072597009355844451L;

	/**
	 * 
	 */
	public AnaContiView(String title) {
		super(Account.getIntestazione(), title);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void chiusura() {
		// TODO Auto-generated method stub
		
	}

}
