package dataModel;

import dataEnum.Nature;

public class Conto {
	private String nome;
	private Nature natura;
	private long avere;
	private long dare;
	
	public Conto(String nome, Nature natura, long dare, long avere) {
		this.nome = nome;
		this.natura = natura;
		this.dare = dare;
		this.avere = avere;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Nature getNatura() {
		return natura;
	}

	public void setNatura(Nature natura) {
		this.natura = natura;
	}

	public long getAvere() {
		return avere;
	}

	public void setAvere(long avere) {
		this.avere = avere;
	}

	public long getDare() {
		return dare;
	}

	public void setDare(long dare) {
		this.dare = dare;
	}
}
