/**
 * 
 */
package dataEnum;

/**
 * @author Pentolo
 *
 */
public interface IDataEnum {
	String toString(Enum<? extends IDataEnum> value);
	String[] getStrings();
}
