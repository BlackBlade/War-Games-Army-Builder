package exceptions;

/**
 * Exception generated when an user wants to do an operation with a selected item which is not defined for some reasons.
 * @author Luca Longobardi
 *
 */
public class ItemNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param msg
	 * Sets the msg as the message carried by the exception.
	 */
	public ItemNotFoundException(final String msg) {
		super(msg);
	}

}
