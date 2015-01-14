package views;

import views.UnitCreateGUI.UnitCreateObserver;

/**
 * Interface for the class UnitCreateGUI.
 * @author MatteoOrzes
 *
 */
public interface IUnitCreateGUI extends IAbstractUnitCreateGUI {
	/**
	 * attach an observer to the GUI.
	 * @param newObserver the new observer for the GUI.
	 */
	void attachObserver(UnitCreateObserver newObserver);
		
	/**
	 * Getter for the numeric cost inserted in the JTextField of the "Standard Cost". 
	 * @return the cost for a standard in the new unit.
	 */
	int getStandardCost();
	
	/**
	 * Getter for the numeric cost inserted in the JTextField of the "Champion Cost". 
	 * @return the cost for a champion in the new unit.
	 */
	int getChampionCost();
	
	/**
	 * Getter for the numeric cost inserted in the JTextField of the "Musician Cost". 
	 * @return the cost for a musician in the new unit.
	 */
	int getMusicianCost();
}
