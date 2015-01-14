package views;
/**
 * Basic Interface for every GUI. This will contain the common method show error dialog
 * avoiding code duplication.
 * @author MatteoOrzes
 *
 */
public interface IBasicGUI {
	/**
	 * Shows a dialog with the input message.
	 * @param msg the message shown as error.
	 */
	void showErrorDialog(String msg);
}
