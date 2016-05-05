package dataModel;

import dataEnum.Nature;

public class Conto {
	private Nature natura;
	private String nome;
	private long saldo;

	public Conto(String nome, Nature natura, long saldo) {
		this.nome = nome;
		this.natura = natura;
		this.saldo = saldo;
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

	public void setNatura(Nature natura) {
		this.natura = natura;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSaldo(long saldo) {
		this.saldo = saldo;
	}

}
