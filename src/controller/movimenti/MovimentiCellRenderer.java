package controller.movimenti;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import dataModel.Operation;

public class MovimentiCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 8786624563355722501L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (value instanceof Operation) {
			Operation country = (Operation) value;
			setText(country.getConto().getName());
		}

		if (isSelected) {
			setBackground(table.getSelectionBackground());
		} else {
			setBackground(table.getSelectionForeground());
		}

		return this;
	}
}