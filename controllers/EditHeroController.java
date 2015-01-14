package controllers;

import models.IHero;
import models.IModel;
import views.EditHeroGUI.IEditHeroObserver;
import views.IEditHeroGUI;
import exceptions.NullFieldException;

/** 
 * Class that models the controller for the view EditHeroGUI.
 * @author MatteoOrzes
 *
 */
public class EditHeroController implements IEditHeroObserver {

	private final  IModel model;
	private IEditHeroGUI gui;
	
	/**
	 * Constructor for controller that takes in input a model to operate with.
	 * @param newModel 
	 */
	public EditHeroController(final IModel newModel) {
		this.model = newModel;
	}
	
	@Override
	public void setView(final IEditHeroGUI newGui) {
		this.gui = newGui;
		gui.attachObserver(this);
		gui.updateCheckBox();
	}

	@Override
	public IModel getModel() {
		return this.model;
	}

	@Override
	public boolean isBsb() {
		return ((IHero) this.model.getArmyList().get(gui.getIndex())).isBsb();
	}

	@Override
	public void setBsb() throws NullFieldException {
		((IHero) this.model.getArmyList().get(gui.getIndex())).setBsb();
	}

}
