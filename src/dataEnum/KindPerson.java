package dataEnum;

/**
 * enumerazione che identifica la persona in base al ruolo nei confronti
 * dell'azienda
 * 
 * @author niky
 *
 */
public enum KindPerson {
	CLIENTE, FORNITORE;
	
	public String getString(KindPerson kp){
		switch (kp){
		case CLIENTE:
			return "Cliente";
		case FORNITORE:
			return "Fornitore";
		default:
			return "";
		}
	}
}
