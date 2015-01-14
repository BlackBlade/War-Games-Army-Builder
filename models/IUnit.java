package models;

import exceptions.NullFieldException;

/**
 * It models the structure of the unit and its operations. 
 * Armies are made by one or more unit.
 * 
 * @author Luca Longobardi
 *
 */

public interface IUnit extends ICharacter {
	

	
	/**
	 * 
	 * @return a bool that shows if the unit bears a stendard;
	 */
	boolean hasStendard();
	
	/**
	 * 
	 * @return a bool that shows if the unit has a champion;
	 */
	boolean hasChampion();
	
	/**
	 * 
	 * @return a bool that shows if the unit has a musician;
	 */
	boolean hasMusician();
	
	/**
	 * Sets/unsets the boolean for the stendard.
	 * @throws NullFieldException 
	 */
	void setStendard() throws NullFieldException;
	
	/**
	 * Sets/unsets the boolean for the musician.
	 * @throws NullFieldException 
	 */
	void setMusician() throws NullFieldException;
	
	/**
	 * Sets/unsets the boolean for the champion.
	 * @throws NullFieldException 
	 */
	void setChampion() throws NullFieldException;
	
	/**
	 * @return the stendard cost
	 */
	int getStendardCost();
	
	/**
	 * @return the champion cost
	 */
	int getChampionCost();
	
	/**
	 * @return the musician cost
	 */
	int getMusicianCost();
	
	/**
	 * @param stendard
	 * Adds a magic stendard for the unit. If the unit already bears a stendard, it overwrites it.
	 * @throws NullFieldException 
	 */
	void addMagicItem(Item stendard) throws NullFieldException;
	
	/**
	 * Removes a magic stendard, if any.
	 * @throws NullFieldException 
	 */
	void removeMagicStendard() throws NullFieldException;

}
