package dataModel;

import dataEnum.NaturaConti;

public class Conto {
	private NaturaConti natura;
	private String nome;
	private long saldo;

	public Conto(String nome, NaturaConti natura, long saldo) {
		this.nome = nome;
		this.natura = natura;
		this.saldo = saldo;
	}

	public NaturaConti getNatura() {
		return natura;
	}

	public String getNome() {
		return nome;
	}

	public long getSaldo() {
		return saldo;
	}

	public void setNatura(NaturaConti natura) {
		this.natura = natura;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSaldo(long saldo) {
		this.saldo = saldo;
	}

}
