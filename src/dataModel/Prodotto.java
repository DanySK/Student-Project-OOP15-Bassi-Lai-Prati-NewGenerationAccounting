package dataModel;

public class Prodotto {
	private String nome;
	private int codice_prodotto;
	private int cod_acquisto;
	private int cod_vendita;
	private int scorta;
	private String descrizione;
	
	public Prodotto(String nome, int codice_prodotto, int cod_acquisto, int cod_vendita, int scorta,
					String descrizione) {
		super();
		this.nome = nome;
		this.codice_prodotto = codice_prodotto;
		this.cod_acquisto = cod_acquisto;
		this.cod_vendita = cod_vendita;
		this.scorta = scorta;
		this.descrizione = descrizione;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCodice_prodotto() {
		return codice_prodotto;
	}

	public void setCodice_prodotto(int codice_prodotto) {
		this.codice_prodotto = codice_prodotto;
	}

	public int getCod_acquisto() {
		return cod_acquisto;
	}

	public void setCod_acquisto(int cod_acquisto) {
		this.cod_acquisto = cod_acquisto;
	}

	public int getCod_vendita() {
		return cod_vendita;
	}

	public void setCod_vendita(int cod_vendita) {
		this.cod_vendita = cod_vendita;
	}

	public int getScorta() {
		return scorta;
	}

	public void setScorta(int scorta) {
		this.scorta = scorta;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	
}
