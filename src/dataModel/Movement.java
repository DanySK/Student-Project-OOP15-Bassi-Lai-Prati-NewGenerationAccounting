package dataModel;

/**
 * classe per gestire il singolo movimento in partita doppia
 * 
 * @author niky
 */
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Movement implements IDataTableModel {

	private static final String[] INTESTAZIONE = { "Data", "Nome Conto", "Dare", "Avere" };

	/**
	 * 
	 */
	private static final long serialVersionUID = -65774517331356915L;

	public static String[] getIntestazione() {
		return INTESTAZIONE;
	}

	private Date data;
	private LinkedList<Operation> listaConti;

	public Movement(Date data, LinkedList<Operation> object) {
		this.data = data;
		this.listaConti = new LinkedList<>(object);
	}

	public Date getData() {
		return data;
	}

	public List<Operation> getListaConti() {
		return listaConti;
	}

	@Override
	public String getValueAt(int column) {
		switch (column) {
		case 0:
			return getData().toString();
		case 1:
			for (Operation o : listaConti) {
				return o.getConto().getName();
			}
		case 2:
			for (Operation o : listaConti) {
				return Float.toString(o.getDare());
			}
		case 3:
			for (Operation o : listaConti) {
				return Float.toString(o.getAvere());
			}
		default:
			return "";
		}
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setListaConti(LinkedList<Operation> listaContiUsati) {
		this.listaConti = listaContiUsati;
	}

}
