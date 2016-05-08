package dataModel;

import dataEnum.Nature;

public class Conto {
	private Nature natura;
	private String nome;	
	private long avere;
	private long dare;
	
	public Conto(String nome, Nature natura, long dare, long avere) {
		this.nome = nome;
		this.natura = natura;
		this.avere = avere;
		this.dare = dare;
	}

    public Nature getNatura() {
		return natura;
	}

	public String getNome() {
		return nome;
	}

	public void setNatura(Nature natura) {
		this.natura = natura;
	}

	public long getAvere() {
		return avere;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
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