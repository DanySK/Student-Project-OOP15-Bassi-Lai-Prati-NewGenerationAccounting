/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.LinkedList;

import javax.management.InstanceNotFoundException;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import controller.IAnagraficaViewObserver;
import dataModel.IDataTableModel;

/**
 * classe astratta per le view che rispettano il layout di anagrafica, con i 5
 * tasti nel footer.
 * 
 * @author Pentolo
 *
 */
public abstract class AbstractAnagraficaView<E extends IDataTableModel> extends AbstractFrame {

	private final static String DEFAULT_TASTO_0 = "Cerca";
	private final static String DEFAULT_TASTO_1 = "Aggiungi";
	private final static String DEFAULT_TASTO_2 = "Modifica";
	private final static String DEFAULT_TASTO_3 = "Cancella";
	private final static String DEFAULT_TASTO_4 = "Chiudi";

	private static final long serialVersionUID = -1706093338606827050L;

	private MyTableModel<E> dataModel;
	protected IAnagraficaViewObserver observer;
	private final JTable table = new JTable();
	private final JButton tasto0 = new JButton();
	private final JButton tasto1 = new JButton();
	private final JButton tasto2 = new JButton();
	private final JButton tasto3 = new JButton();
	private final JButton tasto4 = new JButton();

	/**
	 * @param title
	 * @param lm
	 * @param dimension
	 */
	public AbstractAnagraficaView(final LinkedList<E> lista, final String intestazione[], final String title) {
		this(lista, intestazione, title, DEFAULT_TASTO_0, DEFAULT_TASTO_1, DEFAULT_TASTO_2, DEFAULT_TASTO_3,
				DEFAULT_TASTO_4);
	}

	public AbstractAnagraficaView(final LinkedList<E> lista, final String intestazione[], final String title,
			final String testo0, final String testo1, final String testo2, final String testo3, final String testo4) {
		super(title, new Dimension(500, 625));
		tasto0.setText(testo0);
		tasto1.setText(testo1);
		tasto2.setText(testo2);
		tasto3.setText(testo3);
		tasto4.setText(testo4);
		final JPanel footer = new JPanel(new FlowLayout());
		footer.add(tasto0);
		footer.add(tasto1);
		footer.add(tasto2);
		footer.add(tasto3);
		footer.add(tasto4);
		this.dataModel = new MyTableModel<E>(intestazione, lista);
		getTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		final JScrollPane scrollPane = new JScrollPane(getTable());
		getTable().setModel(getModel());
		getMyFrame().getContentPane().add(scrollPane, BorderLayout.CENTER);
		getMyFrame().getContentPane().add(footer, BorderLayout.SOUTH);
		addListeners();
	}

	private void addListeners() {
		tasto0.addActionListener(e -> {
			observer.tasto0();
		});
		tasto1.addActionListener(e -> {
			observer.tasto1();
		});
		tasto2.addActionListener(e -> {
			observer.tasto2();
		});
		tasto3.addActionListener(e -> {
			observer.tasto3();
		});
		tasto4.addActionListener(e -> {
			observer.chiusura();
		});
	}

	@Override
	protected void chiusura() {
		observer.chiusura();
	}

	protected MyTableModel<E> getModel() {
		return dataModel;
	}

	public IDataTableModel getSelectedItem() throws InstanceNotFoundException {
		int row = getTable().getSelectedRow();
		if (row != -1) {
			return getModel().getObjectAt(row);
		} else {
			throw new InstanceNotFoundException("Nessuna riga selezionata.");
		}
	}

	protected JTable getTable() {
		return table;
	}

	public void setList(final LinkedList<E> lista) {
		getModel().setList(lista);
	}

	public void setObserver(final IAnagraficaViewObserver observer) {
		this.observer = observer;
	}
}
