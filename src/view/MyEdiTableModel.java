/**
 * 
 */
package view;

import java.util.List;

import dataModel.IEdiTableDataModel;
import dataModel.Operation;

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

	@Override
	public abstract Class<?> getColumnClass(final int column);

	@Override
	public boolean isCellEditable(final int rowIndex, final int columnIndex) {
		return true;
	}

	void setValueAt(final Object value, final Operation operation, final int column) {
		System.out.println("set" + value + "  " + column);
		if (operation != null) {
			operation.setValueAt(value, column);
		}
	}
}
