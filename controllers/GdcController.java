package controllers;

import models.IModel;
import models.IUnit;
import views.GdcGUI.GdcObserver;
import views.IGdcGUI;
import exceptions.NullFieldException;

/** 
 * Class that models the controller for the view GdcGUI.
 * @author MatteoOrzes
 *
 */
public class GdcController implements GdcObserver {

	
	private final IModel model;
	private IGdcGUI gui;
	
	/**
	 * Constructor for controller that takes in input a model to operate with.
	 * @param newModel 
	 */
	public GdcController(final IModel newModel) {
		this.model = newModel;
	}

	@Override
	public void setView(final IGdcGUI v) {
		this.gui = v;
		gui.attachObserver(this);
		gui.updateCheckBox();
	}

	@Override
	public boolean hasStandard() {
		return ((IUnit) this.model.getArmyList().get(gui.getIndex())).hasStendard();
	}

	@Override
	public boolean hasChampion() {
		return ((IUnit) this.model.getArmyList().get(gui.getIndex())).hasChampion();
	}

	@Override
	public boolean hasMusician() {
		return ((IUnit) this.model.getArmyList().get(gui.getIndex())).hasMusician();
	}

	@Override
	public void setStandard() throws NullFieldException {
		((IUnit) this.model.getArmyList().get(gui.getIndex())).setStendard();
	}

	@Override
	public void setChampion() throws NullFieldException {
		((IUnit) this.model.getArmyList().get(gui.getIndex())).setChampion();
	}

	@Override
	public void setMusician() throws NullFieldException {
		((IUnit) this.model.getArmyList().get(gui.getIndex())).setMusician();
	}

}
