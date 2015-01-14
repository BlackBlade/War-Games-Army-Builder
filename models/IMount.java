package models;

/**
 * It models the mount that a character or a unit can have.
 * 
 * @author Luca Longobardi
 *
 */

public interface IMount {

	/**
	 * @return if the mount can fly or not
	 */
	boolean isFlying();
	
	/**
	 * sets/unsets the boolean "Flying", which determines if the unit can fly or not.
	 */
	void setFlying();

}
