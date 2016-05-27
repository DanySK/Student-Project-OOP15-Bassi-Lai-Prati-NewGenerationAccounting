package controller.movimenti;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import dataModel.Account;

public class MovimentiCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

	private static final long serialVersionUID = -118000933929622110L;
	private Account account;
	private LinkedList<Account> listAccount;

	public MovimentiCellEditor(LinkedList<Account> linkedList) {
		this.listAccount = linkedList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent event) {
		JComboBox<Account> comboAccount = (JComboBox<Account>) event.getSource();
		this.account = (Account) comboAccount.getSelectedItem();
	}

	@Override
	public Object getCellEditorValue() {
		return this.account;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		if (value instanceof Account) {
			this.account = (Account) value;
		}
		JComboBox<Account> comboCountry = new JComboBox<Account>();
		for (Account acc : listAccount) {
			comboCountry.addItem(acc);
		}
		comboCountry.setSelectedItem(account);
		comboCountry.addActionListener(this);
		if (isSelected) {
			comboCountry.setBackground(table.getSelectionBackground());
		} else {
			comboCountry.setBackground(table.getSelectionForeground());
		}
		return comboCountry;
	}

}
