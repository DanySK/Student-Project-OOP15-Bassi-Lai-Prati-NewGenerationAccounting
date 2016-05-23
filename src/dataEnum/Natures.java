package dataEnum;

/**
 * enumerazione per identificare la natura dei conti secondo la posizione in
 * stato patrimoniale e conto economico
 * 
 * @author niky
 *
 */
public enum Natures implements IDataEnum {
	ATTIVITA, COSTO, PASSIVITA, RICAVO;

	@Override
	public String toString(Enum<? extends IDataEnum> value) {
		Natures natura = (Natures) value;
		if (value instanceof Natures) {
			switch (natura) {
			case ATTIVITA:
				return "Attività";
			case COSTO:
				return "Costi";
			case PASSIVITA:
				return "Passività";
			case RICAVO:
				return "Ricavi";
			}
		}
		return "";
	}

	@Override
	public String[] getStrings() {
		String[] stringVet = new String[values().length];
		for (Natures value : values()){
			stringVet[value.ordinal()] = value.toString();
		}
		return stringVet;
	}
}
