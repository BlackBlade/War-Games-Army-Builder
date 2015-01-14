package exceptions;

/**
 * Exception generated when a user tries to do an operation to a non-defined unit.
 * @author Luca Longobardi
 *
 */
public class UnitNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param msg
	 * Sets the msg as the message carried by the exception.
	 */
	public UnitNotFoundException(final String msg) {
		super(msg);
	}
}