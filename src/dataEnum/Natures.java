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

	public Enum<?>[] getEnumValues() {
		return values();
	}

}
