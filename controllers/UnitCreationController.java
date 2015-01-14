package controllers;

import exceptions.DuplicatedUnitException;
import exceptions.NullFieldException;
import exceptions.StatsFaultException;
import models.Hero;
import models.IModel;
import models.Unit;
import views.IUnitCreateGUI;
import views.UnitCreateGUI;

/** 
 * Class that models the controller for the view UnitCreateGUI.
 * @author Luca Longobardi, Orzes Matteo
 *
 */
public class UnitCreationController implements UnitCreateGUI.UnitCreateObserver {
	
	private final IModel model;
	private IUnitCreateGUI gui;
	
	/**
	 * Constructor for controller that takes in input a model to operate with.
	 * @param newModel 
	 */
	public UnitCreationController(final IModel newModel) {
		this.model = newModel;
	}
	
	@Override
	public void setView(final IUnitCreateGUI v) {
		this.gui = v;
		this.gui.attachObserver(this);
	}
	
	@Override
	public void createHero() 
			throws DuplicatedUnitException, StatsFaultException, NullFieldException {
		model.addHeroToCodex(new Hero(gui.getStats(), gui.getName(), gui.getDescription(), gui.getCost()));
	}
	
	@Override
	public void createUnit() throws DuplicatedUnitException, StatsFaultException, NullFieldException {
		model.addUnitToCodex(new Unit(gui.getStats(), gui.getName(), gui.getDescription(), gui.getCost(),
				gui.getStandardCost(), gui.getChampionCost(), gui.getMusicianCost()));
	}
}
