package dataEnum;

/**
 * enumerazione per identificare la natura dei conti secondo la posizione in
 * stato patrimoniale e conto economico
 * 
 * @author niky
 *
 */
public enum Natures {
	ATTIVITA, COSTO, PASSIVITA, RICAVO;
	
	public String getString(Natures natura){
		switch (natura){
		case ATTIVITA:
			return "Attività";
		case COSTO:
			return "Costi";
		case PASSIVITA:
			return "Passività";
		case RICAVO:
			return "Ricavi";
		default:
			return "";
		}
	}
}
