package controllers;
import models.IModel;
import models.Item;
import views.IItemGUI;
import views.ItemGUI;
import exceptions.NullFieldException;

/**
 * Class that models the controller for the view ItemGUI.
 * @author MatteoOrzes
 *
 */
public class ItemController implements ItemGUI.ItemObserver {
	
	private final IModel model;
	private IItemGUI gui;
	
	/**
	 * Constructor for controller that takes in input a model to operate with.
	 * @param newModel 
	 */
	public ItemController(final IModel newModel) {
		this.model = newModel;
	}
	
	@Override
	public void setView(final IItemGUI newGui) {
		this.gui = newGui;
		this.gui.attachObserver(this);
	}
	
	@Override
	public IModel getModel() {
		return this.model;
	}
	
	@Override
	public void confirmItem(final int index, final Item item) throws NullFieldException {
		if (this.gui.getComboBoxValue().equals("Magic Item")) {
			
				this.model.getArmyList().get(index).addMagicItem(item);
			
		} else if (this.gui.getComboBoxValue().equals("Common Item")) {
		
				this.model.getArmyList().get(index).addCommonItem(item);
			
		} 
	}
	
}
