package dataModel;

public class Operation implements IEdiTableDataModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 14756423876583L;

	private static final String[] INTESTAZIONE = { "Conto", "Dare", "Avere" };

	public static String[] getIntestazione() {
		return INTESTAZIONE;
	}

	private Account conto;

	private float dare;

	private float avere;

	public Operation(Account conto, float dare, float avere) {
		this.conto = conto;
		this.dare = dare;
		this.avere = avere;
	}

	public float getAvere() {
		return avere;
	}

	@Override
	public Class getColumnClass(int column) {
		return getValueAt(column).getClass();
	}

	public Account getConto() {
		return conto;
	}

	public float getDare() {
		return dare;
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
	public void setValueAt(Object value, Operation operation, int column) throws IllegalArgumentException {
		switch (column) {
		case 0:
			if (value instanceof Account) {
				operation.setConto((Account) value);
			} else {
				throw new IllegalArgumentException("Dato non valido. riprovare");
			}
			break;
		case 1:
			if (value instanceof Float) {
				operation.setDare((float) value);
			} else {
				throw new IllegalArgumentException("Dato non valido. riprovare");
			}
			break;
		case 2:
			if (value instanceof Float) {
				operation.setAvere((float) value);
			} else {
				throw new IllegalArgumentException("Dato non valido. riprovare");
			}
			break;
		}
	}

}
