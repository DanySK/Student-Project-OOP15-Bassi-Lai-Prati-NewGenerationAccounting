package dataEnum;

/**
 * enumerazione per identificare la natura dei conti secondo la posizione in
 * stato patrimoniale e conto economico
 * 
 * @author niky
 *
 */
public enum Natures implements IDataEnum {
	DEFAULT, ATTIVITA, COSTO, PASSIVITA, RICAVO;

	@Override
<<<<<<< local
	public String toString(Enum<? extends IDataEnum> value) {
		Natures natura = (Natures) value;
		if (value instanceof Natures) {
			switch (natura) {
			case ATTIVITA:
				return "Attivit�";
			case COSTO:
				return "Costi";
			case PASSIVITA:
				return "Passivit�";
			case RICAVO:
				return "Ricavi";
			case DEFAULT:
			        return "";
			}
		}
		return "";
=======
	public Enum<?>[] getEnumValues() {
		return values();
>>>>>>> other
	}

}
