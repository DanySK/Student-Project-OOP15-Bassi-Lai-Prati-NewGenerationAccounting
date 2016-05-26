/**
 * 
 */
package view;

import java.util.List;

import dataModel.IEdiTableDataModel;

/**
 * @author Pentolo
 *
 */
public abstract class MyEdiTableModel<E extends IEdiTableDataModel> extends MyTableModel<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 423854890085035434L;

	/**
	 * 
	 */
	public MyEdiTableModel(final String headerList[], final List<E> list) {
		super(headerList, list);
	}

}
