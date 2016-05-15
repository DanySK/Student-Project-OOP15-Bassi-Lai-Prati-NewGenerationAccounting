package dataModel;

/**
 * Classe per la gestione di un singolo tipo oggetto,
 * verrà usata per il carrello dell'utente
 * 
 * @author Diego
 *
 */

public class Item implements IDataTableModel {

	private static final String[] intestazione = { "Prodotto", "Prezzo", "Quantità" };

	public static String[] getIntestazione() {
		return intestazione;
	}

	private int iditem;

	private String nomeitem;
	private int prezzo;
	private int quantita;

	
	public Item(int iditem , String nomeitem, int prezzo, int quantita) {
		
		this.iditem=iditem;
		this.nomeitem=nomeitem;
		this.prezzo=prezzo;
		this.quantita=quantita;

	}

	public int getIditem() {
		return iditem;
	}

	public String getNome() {
		return nomeitem;
	}

	public int getPrezzo() {
		return prezzo;
	}

	public int getQuantita() {
		return quantita;
	}

	
	


	public void setIditem(int iditem) {
		this.iditem = iditem;
	}

	public void setNomeitem(String nomeitem) {
		this.nomeitem = nomeitem;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	@Override
	public String getValueAt(int column) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
