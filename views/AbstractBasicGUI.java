package views;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Abstract class that implements the method showErrorDialog used in all the GUIs.
 * @author MatteoOrzes
 *
 */
public abstract class AbstractBasicGUI extends JFrame implements IBasicGUI {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String ERROR_STR = "Error";

	@Override
	public void showErrorDialog(final String msg) {
		JOptionPane.showMessageDialog(this, msg, ERROR_STR, JOptionPane.ERROR_MESSAGE);
	}
}
