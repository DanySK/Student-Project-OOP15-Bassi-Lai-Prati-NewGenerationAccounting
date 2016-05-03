package dataModel;

public class Azienda {
	
	private int codice_azienda;
	private String password;
	private String ragione_sociale;
	private long partita_iva;
	private String indirizzo;
	private String citta;
	private int cap;
	private String provincia;
	private String tel;
	
	
	private static final String[] intestazione = {"Rag. Soc", "P.iva"};
	
	public Azienda(int codice_azienda, String password, String ragione_sociale, long partita_iva, String indirizzo, String citta, int cap, String provincia,
					String tel) {
		this.codice_azienda = codice_azienda;
		this.password = password;
		this.ragione_sociale = ragione_sociale;
		this.partita_iva = partita_iva;
		this.indirizzo = indirizzo;
		this.citta = citta;
		this.cap = cap;
		this.provincia = provincia;
		this.tel = tel;
		
	}
	
	public int getCodice_azienda() {
		return codice_azienda;
	}

	public void setCodice_azienda(int codice_azienda) {
		this.codice_azienda = codice_azienda;
	}

	public String getRagione_sociale() {
		return ragione_sociale;
	}

	public void setRagione_sociale(String ragione_sociale) {
		this.ragione_sociale = ragione_sociale;
	}

	public long getPartita_iva() {
		return partita_iva;
	}

	public void setPartita_iva(long partita_iva) {
		this.partita_iva = partita_iva;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public int getCap() {
		return cap;
	}

	public void setCap(int cap) {
		this.cap = cap;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static String[] getIntestazione() {
		return intestazione;
	}
}
