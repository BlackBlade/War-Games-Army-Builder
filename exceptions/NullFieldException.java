package exceptions;

/**
 * This class models the exception thrown when an input field in a method is null.
 * @author Luca Longobardi
 *
 */
public class NullFieldException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param msg
	 * Sets the msg as the message carried by the exception.
	 */
	public NullFieldException(final String msg) {
		super(msg);
	}
	

}
