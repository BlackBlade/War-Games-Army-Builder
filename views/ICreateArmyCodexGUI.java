package views;

import javax.swing.JList;

import views.CreateArmyCodexGUI.ICreateArmyCodexObserver;
/**
 * Interface for the class CreateArmyCodexGUI.
 * @author MatteoOrzes
 *
 */
public interface ICreateArmyCodexGUI extends IBasicGUI {
	/**
	 * Getter for the currentUnitList in CreateArmyCodexGUI.
	 * @return the currentUnitList of CreateArmyCodexGUI.
	 */
	JList<Object> getCodexList();
	/**
	 * attach an observer.
	 * @param observer the new Observer for the GUI.
	 */
	void attachObserver(ICreateArmyCodexObserver observer);
	/**
	 * Method used to constantly show the proper description related to the last selected unit/hero.
	 */
	void updateDescription();
	/**
	 * 
	 */
	void updateView();
}
