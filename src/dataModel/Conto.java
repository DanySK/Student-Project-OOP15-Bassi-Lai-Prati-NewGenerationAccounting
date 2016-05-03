package dataModel;

import dataEnum.NaturaConti;

public class Conto {
	private String nome;
	private NaturaConti natura;
	private long saldo;
	
	public Conto(String nome, NaturaConti natura, long saldo) {
		this.nome = nome;
		this.natura = natura;
		this.saldo = saldo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public NaturaConti getNatura() {
		return natura;
	}

	public void setNatura(NaturaConti natura) {
		this.natura = natura;
	}

	public long getSaldo() {
		return saldo;
	}

	public void setSaldo(long saldo) {
		this.saldo = saldo;
	}
	
	
}
