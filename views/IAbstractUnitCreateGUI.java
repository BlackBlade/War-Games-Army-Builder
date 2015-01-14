package views;

/**
 * Interface for the Abstract Class AbstractUnitCreateGUI.
 * @author MatteoOrzes
 *
 */
public interface IAbstractUnitCreateGUI extends IBasicGUI {
	
	/**
	 * Method used in order to get the unit/hero/mount stats from the GUI. 
	 * @return an array that contains all the stats inserted by the User.
	 */
    int[] getStats();
	
    /**
     * Getter for the name of the unit that the user is creating.
     * @return the name of the new unit/hero/mount.
     */
	String getName();
	
	/**
	 * Getter for the description of the unit that the user is creating.
     * @return the description of the new unit/hero/mount.
	 */
	String getDescription();
	
	/**
	 * Getter for the cost of the unit that the user is creating.
	 * @return the cost of the new unit/hero/mount.
	 */
	int getCost();
	/**
	 * Method used to clear all the JTextField in UnitCreateGUI and MountCreateGUI.
	 */
	void clearCommand();
}
