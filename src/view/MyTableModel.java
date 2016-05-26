/**
 * 
 */
package view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import dataModel.IDataTableModel;

/**
 * classe che estende AbstractTableModel e che permette di gestire una tabella
 * popolata tramite lista di oggetti. CONSIGLIATO uso tramite
 * AbstractAnagraficaView.
 * 
 * @author Pentolo
 *
 */
public class MyTableModel<E extends IDataTableModel> extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9056625553908580890L;
	private String headerList[];
	private List<E> objectsList;

	/**
	 * 
	 */
	public MyTableModel(final String headerList[], final List<E> list) {
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

	public E getObjectAt(final int row) {
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

	public void setList(List<E> objectsList) {
		this.objectsList = objectsList;
		fireTableDataChanged();
	}
}