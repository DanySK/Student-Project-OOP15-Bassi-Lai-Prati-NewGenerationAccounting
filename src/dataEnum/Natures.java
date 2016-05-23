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
	public Enum<?>[] getEnumValues() {
		return values();
	}

}
