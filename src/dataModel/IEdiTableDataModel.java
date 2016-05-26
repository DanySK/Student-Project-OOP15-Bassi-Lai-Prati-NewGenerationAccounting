/**
 * 
 */
package dataModel;

/**
 * @author Pentolo
 *
 */
public interface IEdiTableDataModel extends IDataTableModel {
	Class getColumnClass(int column);

	void setValueAt(Object value, Operation operation, int column) throws IllegalArgumentException;
}
