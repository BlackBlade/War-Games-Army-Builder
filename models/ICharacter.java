package models;


import exceptions.ItemNotFoundException;
import exceptions.NullFieldException;

/**
 * Defines the operation of a generic character.
 * All the operations are necessary to create a character that can be included
 * in an army list.
 * @author Luca Longobardi
 **/



public interface ICharacter extends IBasicCharacter {
	
	/**
	 * @return the mount of the unit, if it has one. Otherwise null.
	 */
	Mount getMount(); 
	
	/**
	 * Removes the mount of the unit, if it has one.
	 * 
	 * @throws NullFieldException 
	 */
	void removeMount() throws NullFieldException;
	
	/**
	 * Adds a mount to the unit. If the unit already has a mount, it overwrites it.
	 * 
	 * @param mount input mount
	 * @throws NullFieldException if the input object is null 
	 */
	void addMount(Mount mount) throws NullFieldException; 
	
	/**
	 * Adds a common item (non magical) to the unit. 
	 * A single unit can bear more than one common item (i.e. Shield, sword) and the cost is applied for each occurrence.
	 * 
	 * @param item input item to add
	 * @throws NullFieldException  if the input object is null
	 */
	void addCommonItem(Item item) throws NullFieldException;
	
	/**
	 * Adds a magic item to the unit/hero. 
	 * A single unit, exactly as an hero, can bear only one magic item.
	 * A magic item assigned to a unit can only be a magic banner.
	 * 
	 * @param item input item
	 * @throws NullFieldException if the input object is null 
	 */
	void addMagicItem(Item item) throws NullFieldException;
	
	/**
	 * Removes a common item from the unit.
	 * 
	 * @param item input item
	 * @throws ItemNotFoundException if the item does not exist
	 * @throws NullFieldException if the input object is null
	 */
	void removeCommonItem(Item item) throws ItemNotFoundException, NullFieldException;
	
	/**
	 * @return the total occurrence for the character.
	 */
	int getCurrentOccurrence();
	
	/**
	 * Adds an occurrence to the character, modifying the cost considering the presence 
	 * of mount and common items.
	 * 
	 * @throws NullFieldException if you call the method on a null character
	 */
	void addOccurrence() throws NullFieldException;

	/**
	 * Removes an occurrence to the character, modifying the cost considering the presence 
	 * of mount and common items.
	 * 
	 * @throws NullFieldException if you call the method on a null character
	 */
	void removeOccurrence() throws NullFieldException;
	
	/**
	 * Method used to print the important characteristic of a Hero or Unit.
	 * this method will be used in the model to print a Text file of the Army list.
	 * @return the string containing the informations
	 */
	String toText();
	
	/**
	 * Method used to print important characteristic of a hero or unit.
	 * Will be used in Army build GUI to show description.
	 * @return the description string
	 */
	String toDescription();
}
