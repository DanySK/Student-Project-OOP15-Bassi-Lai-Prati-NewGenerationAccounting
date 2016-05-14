package dataModel;

/**
 * Classe per la gestione del singolo prodotto e scorta associata da parte dell'azienda.
 * 
 * @author niky & Diego
 *
 */
public class Product implements IDataTableModel {
	
	private static final String[] intestazione = { "Codice Prodotto", "Nome" , "Categoria", "Tipo Prodotto" };
	
	public static String[] getIntestazione() {
		return intestazione;
	}
	
	private int cod_acquisto;
	private int cod_vendita;
	private int codice_prodotto;
	private String descrizione;
	private String nome;
	private int scorta;

	public Product(String nome, int codice_prodotto, int cod_acquisto, int cod_vendita, int scorta,
			String descrizione) {
		this.nome = nome;
		this.codice_prodotto = codice_prodotto;
		this.cod_acquisto = cod_acquisto;
		this.cod_vendita = cod_vendita;
		this.scorta = scorta;
		this.descrizione = descrizione;
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

	public String getDescrizione() {
		return descrizione;
	}

	public String getNome() {
		return nome;
	}

	public int getScorta() {
		return scorta;
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

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setScorta(int scorta) {
		this.scorta = scorta;
	}

	@Override
	public String getValueAt(int column) {
		// TODO Auto-generated method stub
		return null;
	}

}
