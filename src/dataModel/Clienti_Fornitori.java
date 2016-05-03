package dataModel;

import dataEnum.Gender;

public class Clienti_Fornitori {
	private String nome;
	private String cognome;
	private String cf;
	private String indirizzo;
	private String citta;
	private int cap;
	private String telefono;
	private Gender sesso;
	
	public Clienti_Fornitori(String nome, String cognome, String cf, String indirizzo, String citta, int cap,
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Gender getSesso() {
		return sesso;
	}

	public void setSesso(Gender sesso) {
		this.sesso = sesso;
	}
}
