package dataModel;

/**
 * classe per la gestione del singolo cliente/fornitore
 * 
 * @author niky
 */
import dataEnum.Gender;
import dataEnum.KindPerson;

public class Customers_Suppliers implements IDataTableModel {
	private int cap;
	private String cf;
	private String citta;
	private String cognome;
	private String indirizzo;
	private String nome;
	private Gender sesso;
	private String telefono;
	private KindPerson ruolo;

	public Customers_Suppliers(String nome, String cognome, String cf, String indirizzo, String citta, int cap,
			String telefono, Gender sesso) {

		this.nome = nome;
		this.cognome = cognome;
		this.cf = cf;
		this.indirizzo = indirizzo;
		this.citta = citta;
		this.cap = cap;
		this.telefono = telefono;
		this.sesso = sesso;
	}

	public int getCap() {
		return cap;
	}

	public String getCf() {
		return cf;
	}

	public String getCitta() {
		return citta;
	}

	public String getCognome() {
		return cognome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public String getNome() {
		return nome;
	}

	public KindPerson getRuolo() {
		return ruolo;
	}

	public Gender getSesso() {
		return sesso;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setCap(int cap) {
		this.cap = cap;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setRuolo(KindPerson ruolo) {
		this.ruolo = ruolo;
	}

	public void setSesso(Gender sesso) {
		this.sesso = sesso;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String getValueAt(int column) {
		// TODO Auto-generated method stub
		return null;
	}
}
