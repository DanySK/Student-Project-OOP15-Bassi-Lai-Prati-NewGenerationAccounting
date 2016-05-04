package dataModel;

import java.util.Date;

public class Movimento {
	private int codice;
	private String conto; // (il conto attraverso cui si svolge il movimento)
	private Date data;
	private long saldo;

	public Movimento(Date data, int codice, String conto, long saldo) {
		this.data = data;
		this.codice = codice;
		this.conto = conto;
		this.saldo = saldo;
	}

	public int getCodice() {
		return codice;
	}

	public String getConto() {
		return conto;
	}

	public Date getData() {
		return data;
	}

	public long getSaldo() {
		return saldo;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public void setConto(String conto) {
		this.conto = conto;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setSaldo(long saldo) {
		this.saldo = saldo;
	}

}
