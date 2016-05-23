package dataEnum;

/**
 * enumerazione che identifica la persona secondo il genere
 * 
 * @author niky
 *
 */

public enum Gender {
	F, M;
	
	public String getString(Gender gender){
		switch (gender){
		case F:
			return "Femmina";
		case M:
			return "Maschio";
		default:
			return "";
		}
	}
}
