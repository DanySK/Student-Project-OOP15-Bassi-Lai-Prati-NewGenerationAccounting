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

	private static final String[] INTESTAZIONE = { "Data", "Nome" };

	/**
	 * 
	 */
	private static final long serialVersionUID = -65774517331356915L;

	public static String[] getIntestazione() {
		return INTESTAZIONE;
	}

	private Date data;

	private List<Account> listaConti;

	public Movement(Date data, List<Account> lista) {
		this.data = data;
		this.listaConti = new LinkedList<>(lista);
	}

	public Date getData() {
		return data;
	}

	public List<Account> getListaConti() {
		return listaConti;
	}

	@Override
	public String getValueAt(int column) {
		switch (column) {
		case 0:
			return getData().toString();
		case 1:
			for (Account a : listaConti) {
				return a.getName() + a.getSaldo();
			}
		default:
			return "";
		}
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setListaConti(List<Account> listaContiUsati) {
		this.listaConti = listaContiUsati;
	}

}
