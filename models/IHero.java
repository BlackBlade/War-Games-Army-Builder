package models;

import exceptions.ItemNotFoundException;
import exceptions.NullFieldException;

/**
 * It models the concept of hero. A hero is a particular kind of unit that can bear a battle stendard, becoming the
 * battle standard bearer (BSB) of the army. A hero can also have a list of magical items, which improve his stats in some way.
 * @author Luca Longobardi
 *
 */

public interface IHero extends ICharacter {
	
	/**
	 * Sets the BSB of the army. If the hero is not bsb, he acquires the status of bsb. Vice Versa if the hero is bsb,
	 * this method will remove his status.
	 * @throws NullFieldException 
	 */
	void setBsb() throws NullFieldException;
	
	/**
	 * Adds a magic item to the hero.
	 * 
	 * @param item input item
	 * @throws NullFieldException if the input object is null
	 */
	void addMagicItem(Item item) throws NullFieldException;
	
	/**
	 * Removes a magic item from the hero.
	 * 
	 * @param item input item
	 * @throws ItemNotFoundException if the item no longer exists
	 * @throws NullFieldException if the input item is null
	 */
	void removeMagicItem(Item item) throws ItemNotFoundException, NullFieldException;

	/**
	 * @return a boolean showing if the hero is a bsb.
	 */
	boolean isBsb();

}
