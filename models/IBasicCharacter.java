package models;

import exceptions.NullFieldException;

/**
 * Models the basic operations that a simple character component must have.
 * @author Luca Longobardi
 *
 */
public interface IBasicCharacter extends IBasicComponent {
	
	/**
	 *  @return the stats vector of the character
	 */
	int[] getStats();


	/**
	 * @param stats
	 * Sets the stats of the character.
	 * @throws NullFieldException 
	 */
	void setStats(int[] stats) throws NullFieldException;
}
