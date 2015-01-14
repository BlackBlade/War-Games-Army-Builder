package main;
import models.Model;

import views.ArmyBuildGUI;
import views.IArmyBuildGUI;
import controllers.ArmyBuildController;

/**
 * Main class containing main method.
 * @author MatteoOrzes
 */
public final class Main {	
	
	private Main() { };
	/**
	 * Main method.
	 * @param args arguments of main class
	 */
    public static void main(final String[] args) {
		final Model model = new Model();
		final ArmyBuildController controller = new ArmyBuildController(model);
		final IArmyBuildGUI gui = new ArmyBuildGUI();
		controller.setView(gui);
	}
}
