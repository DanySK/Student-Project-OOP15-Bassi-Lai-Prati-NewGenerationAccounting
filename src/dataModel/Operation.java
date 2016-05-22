package dataModel;

public class Operation {
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

}
