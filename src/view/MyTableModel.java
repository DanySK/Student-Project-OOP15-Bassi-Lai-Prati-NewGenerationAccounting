/**
 * 
 */
package view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import dataModel.IDataTableModel;

/**
 * @author Pentolo
 *
 */
public class MyTableModel<dataModel extends IDataTableModel> extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9056625553908580890L;
	private String headerList[];
	private final List<dataModel> objectsList;

	/**
	 * 
	 */
	public MyTableModel(final String headerList[], final List<dataModel> list) {
		this.objectsList = list;
		this.headerList = headerList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return this.headerList.length;
	}

	@Override
	public String getColumnName(final int col) {
		return headerList[col];
	}

	public dataModel getObjectAt(final int row) {
		return objectsList.get(row);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return this.objectsList.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public String getValueAt(int row, int column) {
		return objectsList.get(row).getValueAt(column);
	}
}