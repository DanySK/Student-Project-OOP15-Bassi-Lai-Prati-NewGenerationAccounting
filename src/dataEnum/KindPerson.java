package dataEnum;

/**
 * enumerazione che identifica la persona in base al ruolo nei confronti
 * dell'azienda
 * 
 * @author niky
 *
 */
public enum KindPerson implements IDataEnum {
	CLIENTE, FORNITORE;

	@Override
	public String toString(Enum<? extends IDataEnum> value) {
		KindPerson kp = (KindPerson) value;
		if (value instanceof KindPerson) {
			switch (kp) {
			case CLIENTE:
				return "Cliente";
			case FORNITORE:
				return "Fornitore";
			}
		}
		return "";
	}

	@Override
	public String[] getStrings() {
		String[] stringVet = new String[values().length];
		for (KindPerson value : values()){
			stringVet[value.ordinal()] = value.toString();
		}
		return stringVet;
	}
}
