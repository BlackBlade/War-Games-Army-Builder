package views;

import views.EditHeroGUI.IEditHeroObserver;

/**
 * Interface for the class EditHeroGUI.
 * @author MatteoOrzes
 *
 */
public interface IEditHeroGUI extends IBasicGUI {
     /**
	 * Attach a new observer.
	 * @param newObserver the new Observer for the GUI.
	 */
	void attachObserver(IEditHeroObserver newObserver);

	/**
	 * Getter for the field index.
	 * @return the value of field 'index'.
	 */
	int getIndex();

	/**
	 * Method invoked as soon as a EditHeroGUI is created to set the starting checkBox to match with the hero field.
	 */
	void updateCheckBox();

}
