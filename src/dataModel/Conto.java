package dataModel;

import dataEnum.Nature;

public class Conto {
	private Nature natura;
	private String nome;
<<<<<<< local
	private Nature natura;
	private long avere;
	private long dare;
	
	public Conto(String nome, Nature natura, long dare, long avere) {
=======
	private long saldo;

	public Conto(String nome, Nature natura, long saldo) {
>>>>>>> other
		this.nome = nome;
		this.natura = natura;
		this.dare = dare;
		this.avere = avere;
	}

	public Nature getNatura() {
		return natura;
	}

	public String getNome() {
		return nome;
	}

	public long getSaldo() {
		return saldo;
	}

<<<<<<< local
	public Nature getNatura() {
		return natura;
	}

	public void setNatura(Nature natura) {
=======
	public void setNatura(Nature natura) {
>>>>>>> other
		this.natura = natura;
	}

<<<<<<< local
	public long getAvere() {
		return avere;
=======
	public void setNome(String nome) {
		this.nome = nome;
>>>>>>> other
	}

	public void setAvere(long avere) {
		this.avere = avere;
	}
<<<<<<< local

	public long getDare() {
		return dare;
	}

	public void setDare(long dare) {
		this.dare = dare;
	}
=======

>>>>>>> other
}
