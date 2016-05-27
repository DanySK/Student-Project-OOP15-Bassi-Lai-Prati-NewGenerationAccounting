/**
 * 
 */
package view.popup;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;

import controller.movimenti.MovimentiCellEditor;
import controller.movimenti.MovimentiCellRenderer;
import controller.popup.PopupControllerImpl;
import dataEnum.IDataEnum;
import dataEnum.Natures;
import dataEnum.Sections;
import dataModel.Account;
import dataModel.Movement;
import dataModel.Operation;
import view.AbstractFrame;
import view.MyEdiTableModel;

/**
 * @author Pentolo
 *
 */
public class AddEditPopupView extends AbstractFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2412389895309056834L;
	private PopupControllerImpl observer;
	private final HashMap<String, JComponent> compoMap;

	/**
	 * @param title
	 * @param dimension
	 * @param popupControllerImpl
	 */
	public AddEditPopupView(final String title, final Dimension dimension, final Map<String, Object> mappa,
			PopupControllerImpl observer) {
		super(title, dimension);
		this.observer = observer;
		compoMap = new HashMap<String, JComponent>();
		final JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		JPanel itemPanel;
		for (final String campo : mappa.keySet()) {
			Object item = mappa.get(campo);
			if (item != null) {
				itemPanel = new JPanel();
				itemPanel.add(new JLabel(campo + " :  "));
				if (item instanceof char[]) {
					item = new String((char[]) item);
				}
				if (item instanceof String) {
					JTextField jtf = new JTextField(25);
					if (item != null) {
						jtf.setText((String) item);
					}
					compoMap.put(campo, jtf);
					itemPanel.add(jtf);
				} else if (item instanceof Date) {
					final JSpinner js = new JSpinner(new SpinnerDateModel());
					if (item != null) {
						js.setValue(item);
					}
					compoMap.put(campo, js);
					itemPanel.add(js);
				} else if (item instanceof Number) {
					JSpinner js;
					if (item instanceof Float) {
						js = new JSpinner(new SpinnerNumberModel(((Float) item).floatValue(), null, null, 0.01));
					} else if (item instanceof Double) {
						js = new JSpinner(new SpinnerNumberModel(((Double) item).doubleValue(), null, null, 0.01));
					} else if (item instanceof Long) {
						js = new JSpinner(new SpinnerNumberModel(((Long) item).longValue(), null, null, 1));
					} else {
						js = new JSpinner(new SpinnerNumberModel(((Integer) item).intValue(), null, null, 1));
					}
					if (item != null) {
						js.setValue(item);
					}
					js.setPreferredSize(new Dimension(100, js.getPreferredSize().height));
					compoMap.put(campo, js);
					itemPanel.add(js);
				} else if (item instanceof Enum && item instanceof IDataEnum) {
					final JComboBox<Enum<?>> jcb = new JComboBox<Enum<?>>(((IDataEnum) item).getEnumValues());
					if (item != null) {
						jcb.setSelectedItem(item);
					}
					compoMap.put(campo, jcb);
					itemPanel.add(jcb);
				} else if (item instanceof LinkedList) {
					if (item.getClass().isAssignableFrom(LinkedList.class)) {
						final JTable operationTable = getOperationEdiTable((LinkedList<Operation>) item);
						final JScrollPane scrollpane = new JScrollPane(operationTable);
						scrollpane.setPreferredSize(new Dimension(325, 100));
						compoMap.put(campo, operationTable);
						itemPanel.add(scrollpane);
					}
				}
				mainPanel.add(itemPanel);
				itemPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
			}
		}
		final JPanel footer = new JPanel(new FlowLayout());
		final JButton chiudi = new JButton("Chiudi");
		final JButton btnOk = new JButton(title);
		chiudi.addActionListener(e -> {
			observer.chiusura();
		});
		btnOk.addActionListener(e -> {
			observer.go(compoMap);
		});
		footer.add(btnOk);
		footer.add(chiudi);
		getMyFrame().getContentPane().add(footer, BorderLayout.SOUTH);
		getMyFrame().getContentPane().add(mainPanel, BorderLayout.CENTER);
	}

	@Override
	protected void chiusura() {
		observer.chiusura();
	}

	private JTable getOperationEdiTable(final List<Operation> operationsList) {
		final JTable myTable = new JTable();
		myTable.setPreferredSize(new Dimension(350, 100));
		//if (operationsList.size() == 0){
			LinkedList<Operation> lista = new LinkedList<Operation>();
			lista.add(new Operation(observer.getAccountsList().getFirst(), (float)5.12, (float) 12.14));
			lista.add(new Operation(observer.getAccountsList().getFirst(), (float)5.12, (float) 12.14));
			lista.add(new Operation(observer.getAccountsList().getFirst(), (float)5.12, (float) 12.14));
		//}
		final MyEdiTableModel<Operation> tableModel = new MyEdiTableModel<Operation>(Operation.getIntestazione(),
				lista) {
			private static final long serialVersionUID = -7742093275061915171L;
			@Override
			public Class<?> getColumnClass(int column) {
				return Operation.getColumnClass(column);
			}
		};
		myTable.setModel(tableModel);
		myTable.setDefaultRenderer(Account.class, new MovimentiCellRenderer());
		myTable.setDefaultEditor(Account.class, new MovimentiCellEditor(observer.getAccountsList()));
		return myTable;
	}
}
