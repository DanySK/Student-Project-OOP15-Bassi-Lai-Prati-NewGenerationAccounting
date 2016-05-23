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
	public Enum<?>[] getEnumValues() {
		return values();
	}

}
