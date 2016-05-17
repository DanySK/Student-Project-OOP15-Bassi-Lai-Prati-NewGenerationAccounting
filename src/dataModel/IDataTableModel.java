/**
 * 
 */
package dataModel;

import java.io.Serializable;

/**
 * @author Pentolo
 *
 */
public interface IDataTableModel extends Serializable {
	String getValueAt(int column);
}
