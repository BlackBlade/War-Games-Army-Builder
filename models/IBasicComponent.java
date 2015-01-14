package models;

import exceptions.NullFieldException;

/**
 * Generic interface for a Basic list component.
 * @author Luca Longobardi
 */
public interface IBasicComponent {
	
	/**
	 * @return the string description for the item
	 */
	String getDescription();
	
	/** 
	 * @return the string name for the item
	 */
	String getName();
	
	/**
	 * 
	 * @return the cost for the item
	 */
	int getCost();
	
	/**
	 * @param description
	 * Sets the description of the item
	 * @throws NullFieldException 
	 */
	void setDescription(String description) throws NullFieldException;
	
	/**
	 * @param name
	 * Sets the name for the item
	 * @throws NullFieldException 
	 */
	void setName(String name) throws NullFieldException;
	
	/**
	 * @param cost
	 * Sets the cost for the item
	 * @throws NullFieldException 
	 */
	void setCost(int cost) throws NullFieldException;

}
