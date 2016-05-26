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
	public abstract Class getColumnClass(int column);

	@Override
	public String getValueAt(int row, int column) {
		return objectsList.get(row).getValueAt(column);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	void setValueAt(Object value, Operation operation, int column) {
		operation.setValueAt(value, column);
	}
}
