package dataModel;

import java.util.ArrayList;
/**
 * classe per gestire il singolo movimento in partita doppia
 * 
 * @author niky
 */
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Movement implements IDataTableModel {

	private static final String[] intestazione = { "Data", "Nome" };
	public static String[] getIntestazione() {
		return intestazione;
	}

	private Date data;

	private List<ArrayList<String>> listaConti;

	public Movement(Date data, List<ArrayList<String>> lista) {
		this.data = data;
		this.listaConti = new LinkedList<ArrayList<String>>(lista);
	}

	public Date getData() {
		return data;
	}

	public List<ArrayList<String>> getListaConti() {
		return listaConti;
	}

	@Override
	public String getValueAt(int column) {
		switch (column) {
		case 0:
			return getData().toString();
		case 1:
			for (ArrayList<String> a : listaConti) {
				for(String s : a){
				    if(!s.equals("0")){
				        return s;
				    }
				}
			}
		default:
			return "";
		}
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setListaConti(List<ArrayList<String>> listaConti) {
		this.listaConti = listaConti;
	}

}
