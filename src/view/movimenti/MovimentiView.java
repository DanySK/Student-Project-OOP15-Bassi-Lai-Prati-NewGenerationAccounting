/**
 * 
 */
package view.movimenti;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import dataModel.Movement;
import view.AbstractAnagraficaView;

/**
 * la view della finestra di gestione dei movimenti
 * 
 * @author Pentolo
 *
 */
public class MovimentiView extends AbstractAnagraficaView<Movement> {

	private static final long serialVersionUID = -7682380373297678954L;

	/**
	 * 
	 */
	public MovimentiView(final LinkedList<Movement> lista, final String title) {
		super(lista, Movement.getIntestazione(), title);
		getTable().getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {

			@Override
			public void setValue(Object value) {
				setText(new SimpleDateFormat("dd/MM/yyyy").format((Date) value));
			}
		});
		getTable().getColumnModel().getColumn(1).setCellRenderer(new MultiLineCellRenderer());
		getTable().getColumnModel().getColumn(2).setCellRenderer(new MultiLineCellRenderer());
		getTable().getColumnModel().getColumn(3).setCellRenderer(new MultiLineCellRenderer());
	}
}

class MultiLineCellRenderer extends JTextArea implements TableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		table.setRowHeight(row, 115);
		this.setText((String) value);
		this.setWrapStyleWord(true);
		this.setLineWrap(true);
		if (isSelected) {
			setBackground(table.getSelectionBackground());
		} else {
			setBackground(table.getBackground());
		}
		return this;
	}
}
