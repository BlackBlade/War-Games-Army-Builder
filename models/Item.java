package models;

import java.io.Serializable;

import exceptions.NullFieldException;
import exceptions.StatsFaultException;

/**
 * It models the generic concept of item.
 * @author Luca Longobardi
 *
 */

public class Item extends BasicListComponent implements IItem, Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Standard constructor for an item. Sets his fields with the input parameters.
	 * 
	 * @param newName name of the item
	 * @param newDescription description of the item
	 * @param newCost cost of the item
	 * @throws NullFieldException thrown if an input field is null
	 * @throws StatsFaultException if the cost is less than one
	 */
	public Item(final String newName, final String newDescription, final int newCost) throws NullFieldException, StatsFaultException {
		super(newName, newDescription, newCost);
	}



}
