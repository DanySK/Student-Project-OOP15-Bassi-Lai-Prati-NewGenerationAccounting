/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.AbstractAnagraficaViewObserver;
import dataModel.Company;

/**
 * classe astratta per le view che rispettano il layout di anagrafica, con i 5
 * tasti nel footer.
 * 
 * @author Pentolo
 *
 */
public abstract class AbstractAnagraficaView extends AbstractFrame {

	private final static String DEFAULT_TASTO_0 = "Cerca";
	private final static String DEFAULT_TASTO_1 = "Aggiungi";
	private final static String DEFAULT_TASTO_2 = "Modifica";
	private final static String DEFAULT_TASTO_3 = "Cancella";
	private final static String DEFAULT_TASTO_4 = "Chiudi";

	private static final long serialVersionUID = -1706093338606827050L;

	private final JButton tasto0 = new JButton();
	private final JButton tasto1 = new JButton();
	private final JButton tasto2 = new JButton();
	private final JButton tasto3 = new JButton();
	private final JButton tasto4 = new JButton();
	
	ArrayList<Company> lista = new ArrayList<Company>();
	private JTable table = new JTable();
	private MyTableModel<Company> model = new MyTableModel<Company>(Company.getIntestazione(), lista);
	
	/**
	 * @param title
	 * @param lm
	 * @param dimension
	 */
	public AbstractAnagraficaView(String title) {
		this(title, DEFAULT_TASTO_0, DEFAULT_TASTO_1, DEFAULT_TASTO_2, DEFAULT_TASTO_3, DEFAULT_TASTO_4);
	}

	public AbstractAnagraficaView(String title, String testo0, String testo1, String testo2, String testo3,
			String testo4) {
		super(title, new Dimension(430, 500));
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
		
		
		lista.add(new Company(1, "a", "b", 123253456, "c", "d", 7777, "e", "f"));
		lista.add(new Company(1, "abrtn", "asd", 122533456, "tnrc", "dntr", 7734577, "e234", "f3444"));
		lista.add(new Company(1, "antr", "tnb", 123434556, "trnnc", "d", 7777, "efsfsee", "ef"));
		
	    JScrollPane scrollPane = new JScrollPane(table);
	    table.setModel(model);
		
		MyFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		MyFrame.getContentPane().add(footer, BorderLayout.SOUTH);
		addListeners();
	}

	private void addListeners() {
		tasto0.addActionListener(e -> {
			lista.remove(model.getObjectAt(table.getSelectedRow()));
			model.fireTableDataChanged();
		});
		tasto1.addActionListener(e -> {
			((AbstractAnagraficaViewObserver) observer).tasto1();
		});
		tasto2.addActionListener(e -> {
			((AbstractAnagraficaViewObserver) observer).tasto2();
		});
		tasto3.addActionListener(e -> {
			((AbstractAnagraficaViewObserver) observer).tasto3();
		});
		tasto4.addActionListener(e -> {
			((AbstractAnagraficaViewObserver) observer).tasto4();
		});
	}
}
