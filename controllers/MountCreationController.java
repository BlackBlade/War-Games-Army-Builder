package controllers;

import models.IModel;
import models.Mount;
import views.IMountCreateGUI;
import views.MountCreateGUI.IMountCreateObserver;
import exceptions.DuplicatedUnitException;
import exceptions.NullFieldException;
import exceptions.StatsFaultException;
/** 
 * Class that models the controller for the view MountCreateGUI.
 * @author Luca Longobardi, Orzes Matteo
 *
 */
public class MountCreationController implements IMountCreateObserver {

	private final IModel model;
	private IMountCreateGUI gui;
	
	/**
	 * Constructor for controller that takes in input a model to operate with.
	 * @param newModel 
	 */
	public MountCreationController(final IModel newModel) {
		this.model = newModel;
	}
	
	@Override
	public void setView(final IMountCreateGUI newGui) {
		this.gui = newGui;
		gui.attachObserver(this);
	}
	
	@Override
	public void createMount(final int index) throws DuplicatedUnitException, StatsFaultException, NullFieldException {
		final Mount mount = new Mount(gui.getStats(), gui.getName(), gui.getDescription(), gui.getCost());
		if (gui.isFlying()) {
			mount.setFlying();
		}
		model.getArmyList().get(gui.getIndex()).addMount(mount);
	}
}
