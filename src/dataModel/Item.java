package dataModel;

/**
 * Classe per la gestione di un singolo tipo oggetto, verrà usata per il
 * carrello dell'utente
 * 
 * @author Diego
 *
 */

public class Item implements IDataTableModel {

	private static final String[] intestazione = { "Prodotto", "Quantità", "Subtotale" };

	public static String[] getIntestazione() {
		return intestazione;
	}

	private final Product prodotto;
	private int quantita;

	public Item(Product prodotto, int quantita) {

		this.prodotto = prodotto;
		this.quantita = quantita;

	}

	public String getProdotto() {
		return prodotto.getNome();
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	@Override
	public String getValueAt(int column) {
		switch (column) {
		case 0:
			return prodotto.getNome();
		case 1:
			return Integer.toString(quantita);
		case 2:
			return Integer.toString(prodotto.getPrezzovendita() * quantita);
		default:
			return "";
		}
	}

}
