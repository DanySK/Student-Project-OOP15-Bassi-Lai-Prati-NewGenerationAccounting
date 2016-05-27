package controller.movimenti;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import dataModel.Account;

public class MovimentiCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 8786624563355722501L;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		//System.out.println(value.toString() + isSelected + hasFocus + row + column);
		if (value instanceof Account) {
			setText(((Account) value).getName());
		}
		if (isSelected) {
			setBackground(table.getSelectionBackground());
		} else {
			setBackground(table.getBackground());
		}
		return this;
	}
}