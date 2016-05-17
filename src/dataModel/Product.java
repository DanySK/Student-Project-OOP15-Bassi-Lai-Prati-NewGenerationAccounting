package dataModel;

/**
 * Classe per la gestione del singolo prodotto e scorta associata da parte
 * dell'azienda.
 * 
 * @author niky & Diego
 *
 */
public class Product implements IDataTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1618173209813961747L;
	private static final String[] intestazione = { "Codice Prodotto", "Nome", "Categoria", "Tipo Prodotto", "Prezzo" };

	public static String[] getIntestazione() {
		return intestazione;
	}

	private int cod_acquisto;
	private int cod_vendita;
	private int codice_prodotto;
	private String categoria;
	private String descrizione;
	private String nome;
	private int scorta;
	private int prezzovendita;

	public Product(String nome, int codice_prodotto, int cod_acquisto, int cod_vendita, int scorta, String categoria,
			String descrizione, int prezzovendita) {
		this.nome = nome;
		this.codice_prodotto = codice_prodotto;
		this.cod_acquisto = cod_acquisto;
		this.cod_vendita = cod_vendita;
		this.scorta = scorta;
		this.categoria = categoria;
		this.descrizione = descrizione;
		this.prezzovendita = prezzovendita;
	}

	public int getCod_acquisto() {
		return cod_acquisto;
	}

	public int getCod_vendita() {
		return cod_vendita;
	}

	public int getCodice_Product() {
		return codice_prodotto;
	}

	public String getCategoria() {
		return categoria;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public String getNome() {
		return nome;
	}

	public int getScorta() {
		return scorta;
	}

	public int getPrezzovendita() {
		return prezzovendita;
	}

	public void setCod_acquisto(int cod_acquisto) {
		this.cod_acquisto = cod_acquisto;
	}

	public void setCod_vendita(int cod_vendita) {
		this.cod_vendita = cod_vendita;
	}

	public void setCodice_Product(int codice_prodotto) {
		this.codice_prodotto = codice_prodotto;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setScorta(int scorta) {
		this.scorta = scorta;
	}

	public void setPrezzovendita(int prezzovendita) {
		this.prezzovendita = prezzovendita;
	}

	@Override
	public String getValueAt(int column) {

		switch (column) {
		case 0:
			return Integer.toString(codice_prodotto);
		case 1:
			return getNome();
		case 2:
			return getCategoria();
		case 3:
			return getDescrizione();
		case 4:
			return Integer.toString(prezzovendita);
		default:
			return "";
		}

	}

}
