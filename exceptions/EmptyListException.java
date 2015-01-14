package exceptions;

/**
 * Unit Generated when a user tries to save an empty list.
 * @author Luca Longobardi
 *
 */
public class EmptyListException extends Exception {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param msg
	 * Sets the msg as the message carried by the exception.
	 */
	public EmptyListException(final String msg) {
		super(msg);
	}

}
