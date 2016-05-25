package dataModel;

public class Operation implements IDataTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 14756423876583L;

	private Account conto;
	private float dare;
	private float avere;

	private static final String[] INTESTAZIONE = { "Conto", "Dare", "Avere" };

	public static String[] getIntestazione() {
		return INTESTAZIONE;
	}

	public Operation(Account conto, float dare, float avere) {
		this.conto = conto;
		this.dare = dare;
		this.avere = avere;
	}

	public float getAvere() {
		return avere;
	}

	public Account getConto() {
		return conto;
	}

	public float getDare() {
		return dare;
	}

	public void setAvere(float avere) {
		this.avere = avere;
	}

	public void setConto(Account conto) {
		this.conto = conto;
	}

	public void setDare(float dare) {
		this.dare = dare;
	}

	@Override
	public String getValueAt(int column) {

		switch (column) {

		case 0:
			return getConto().getName();
		case 1:
			return Float.toString(getDare());
		case 2:
			return Float.toString(getAvere());
		default:
			return "";
		}
	}

}
