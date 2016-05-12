package dataModel;

/**
 * classe per gestire il singolo movimento in partita doppia
 * 
 * @author niky
 */
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Movement implements IDataTableModel {

	private static final String[] intestazione = { "Data", "Nome" };
	public static String[] getIntestazione() {
		return intestazione;
	}

	private Date data;

	private Set<Account> listaConti;

	public Movement(Date data, List<Account> lista) {
		this.data = data;
		this.listaConti = new HashSet<Account>(lista);
	}

	public Date getData() {
		return data;
	}

	public Set<Account> getListaConti() {
		return listaConti;
	}

	@Override
	public String getValueAt(int column) {
		switch (column) {
		case 0:
			return getData().toString();
		case 1:
			for (Account a : listaConti) {
			        return a.getName() + a.getAvere() + a.getDare();
				}
		default:
			return "";
		}
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setListaConti(Set<Account> listaContiUsati) {
		this.listaConti = listaContiUsati;
	}

}
