package dataModel;

import java.util.Date;

public class Movimento {
	private Date data;
	private int codice;
	private String conto; //(il conto attraverso cui si svolge il movimento)
	private long saldo;
	
	public Movimento(Date data, int codice, String conto, long saldo) {
		this.data = data;
		this.codice = codice;
		this.conto = conto;
		this.saldo = saldo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public String getConto() {
		return conto;
	}

	public void setConto(String conto) {
		this.conto = conto;
	}

	public long getSaldo() {
		return saldo;
	}

	public void setSaldo(long saldo) {
		this.saldo = saldo;
	}
	
	
}
