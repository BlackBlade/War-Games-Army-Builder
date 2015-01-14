package exceptions;

/**
 * Exception generated when you try to use a statistic vector with length different from the default one.
 * @author Luca Longobardi
 *
 */
public class StatsFaultException extends Exception {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param msg
	 * Sets the msg as the message carried by the exception.
	 */
	public StatsFaultException(final String msg) {
		super(msg);
	}

}
