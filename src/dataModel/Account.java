package dataModel;

import dataEnum.Natures;

/**
 * classe per la gestione del singolo conto
 * 
 * @author niky
 *
 */
public class Account implements IDataTableModel {
	private static final String[] intestazione = { "Natura", "Nome" };

	public static String[] getIntestazione() {
		return intestazione;
	}

	private Natures natura;
	private String nome;
	private long avere;
	private long dare;

	public Account(String nome, Natures natura, long dare, long avere) {
		this.nome = nome;
		this.natura = natura;
		this.avere = avere;
		this.dare = dare;
	}

	public long getAvere() {
		return avere;
	}

	public long getDare() {
		return dare;
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

	public void setAvere(long avere) {
		this.avere = avere;
	}

	public void setDare(long dare) {
		this.dare = dare;
	}

	public void setName(String nome) {
		this.nome = nome;
	}

	public void setNatura(Natures natura) {
		this.natura = natura;
	}
}