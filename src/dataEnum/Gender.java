package dataEnum;

/**
 * enumerazione che identifica la persona secondo il genere
 * 
 * @author niky
 *
 */

public enum Gender implements IDataEnum {
	F, M;
	
	@Override
	public String toString(Enum<? extends IDataEnum> value) {
		Gender gender = (Gender) value;
		if (value instanceof Gender) {
			switch (gender) {
			case F:
				return "Femmina";
			case M:
				return "Maschio";
			}
		}
		return "";
	}

	@Override
	public String[] getStrings() {
		String[] stringVet = new String[values().length];
		for (Gender value : values()){
			stringVet[value.ordinal()] = value.toString();
		}
		return stringVet;
	}
}
