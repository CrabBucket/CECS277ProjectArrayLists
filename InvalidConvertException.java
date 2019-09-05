/**
 * 
 * Exception thrown for invalid converts when going from integers to face or suit values for cards.
 * 
 * @see Deck
 * @author Thomas McSwain
 * @since 2019-09-03
 * @version 1.0
 */
public class InvalidConvertException extends Exception {
	String message;
	/**
	 * Creates the exception with an error, message.
	 * @param message The error message that will be displayed
	 */
	public InvalidConvertException(String message) {
		super(message);
		this.message = message;
	}
	/**
	 * Returns the error message if needed.
	 */
	public String toString() {
		return message;
	}
}
