package views;

import views.ItemGUI.ItemObserver;
/**
 * Interface for the class ItemGUI.
 * @author MatteoOrzes
 *
 */
public interface IItemGUI extends IBasicGUI {
	/**
	 * Attach a new ItemObserver to the GUI.
	 * @param newObserver the new observer for the GUI.
	 */
	void attachObserver(ItemObserver newObserver);
	/**
	 * Getter for the Combobx Choice.
	 * @return the selected item in comboBox Choice.
	 */
	Object getComboBoxValue();
}
