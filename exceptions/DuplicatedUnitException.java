package exceptions;

/**
 * Exception generated when an user try to add an element to the codex that already exists.
 * @author Luca Longobardi
 *
 */
public class DuplicatedUnitException extends Exception {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param msg
	 * Sets the msg as the message carried by the exception.
	 */
	public DuplicatedUnitException(final String msg) {
		super(msg);
	}

}
