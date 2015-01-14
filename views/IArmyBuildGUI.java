package views;

import javax.swing.JList;

import views.ArmyBuildGUI.IArmyBuildObserver;

/**
 * Interface for ArmyBuildGUI.
 * @author MatteoOrzes
 *
 */

public interface IArmyBuildGUI extends IBasicGUI {

	/**
	 * Getter for the unitList in ArmyBuildGUI.
	 * @return the JList UnitList
	 */
	JList<Object> getUnitList();
	/**
	 * Getter for the currentArmyList in ArmyBuildGUI.
	 * @return the JList currentArmyList
	 */
	JList<Object> getCurrentArmyList();
	/**
	 * attach an observer.
	 * @param observer the new observer for the GUI.
	 */
	void attachObserver(IArmyBuildObserver observer);
	/**
	 * Method used to constantly show the proper description related to the last selected unit/hero.
	 */
	void updateDescription();
	/**
	 * Method used to constantly show the proper total cost of the list.
	 */
	void updateTotalCost();
}
