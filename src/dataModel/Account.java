package dataModel;

import java.io.Serializable;

import dataEnum.Natures;

/**
 * classe per la gestione del singolo conto
 * 
 * @author niky
 *
 */
public class Account implements IDataTableModel, Serializable {

	@Override
	public String toString() {
		return "Account [natura=" + natura + ", nome=" + nome + ", saldo=" + saldo + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (natura != other.natura)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 14756423876583L;
	private static final String[] intestazione = { "Natura", "Nome" };

	public static String[] getIntestazione() {
		return intestazione;
	}

	private Natures natura;
	private String nome;
	private long saldo;

	public Account(String nome, Natures natura, long saldo) {
		this.nome = nome;
		this.natura = natura;
		this.saldo = saldo;
	}

	public long getSaldo() {
		return saldo;
	}

	public String getName() {
		return nome;
	}

	public Natures getNatura() {
		return natura;
	}

	@Override
	public String getValueAt(int column) {
		switch (column) {
		case 0:
			return getNatura().toString();
		case 1:
			return getName();
		default:
			return "";
		}
	}

	public void setSaldo(long saldo) {
		this.saldo = saldo;
	}

	public void setName(String nome) {
		this.nome = nome;
	}

	public void setNatura(Natures natura) {
		this.natura = natura;
	}
}