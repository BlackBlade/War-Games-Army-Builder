package views;

import views.GdcGUI.GdcObserver;
/**
 * Interface for GdcGUI.
 * @author MatteoOrzes
 *
 */
public interface IGdcGUI extends IBasicGUI {
	/**
	 * attach a new observer.
	 * @param newObserver the new Observer for the GUI.
	 */
	void attachObserver(GdcObserver newObserver);
	/**
	 * Method that sets the standardCheckBox according to the input parameter.
	 * @param b boolean to set the checkbox.
	 */
	void setStandardCheckbox(boolean b);
	/**
	 * Method that sets the championCheckBox according to the input parameter.
	 * @param b boolean to set the checkbox.
	 */
	void setChampionCheckbox(boolean b);
	/**
	 * Method that sets the musicianCheckBox according to the input parameter.
	 * @param b boolean to set the checkbox.
	 */
	void setMusicianCheckbox(boolean b);
	/**
	 * Method invoked as soon as a GdcGUI is created to set the starting checkBoxs to match with the unit field.
	 */
	void updateCheckBox();
	/**
	 * Getter for the field index.
	 * @return the value of field 'index'.
	 */
	int getIndex();
}
