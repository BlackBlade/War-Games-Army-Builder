package views;

import views.MountCreateGUI.IMountCreateObserver;


/**
 * Interface for MountCreateGUI.
 * @author MatteoOrzes
 *
 */

public interface IMountCreateGUI extends IAbstractUnitCreateGUI {
	
	/**
	 * Getter for the state of the CheckBox flyingCheckBox.
	 * @return the boolean that states if the mount is flying or not.
	 */
	boolean isFlying();
	
	/**
	 * attach an observer.
	 * @param newObserver the new observer for the GUI.
	 */
	void attachObserver(IMountCreateObserver newObserver);

	/**
	 * Getter for the field index.
	 * @return the index of the last selected unit before the invocation of MountCreationGUI.
	 */
	int getIndex();

}
