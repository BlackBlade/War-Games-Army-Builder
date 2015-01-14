package models;

import java.io.Serializable;

import exceptions.NullFieldException;
import exceptions.StatsFaultException;

/**
 * It models the mount that a unit or character can have.
 * A mount is a character, so this class extends AbstractCharacter
 * 
 * @author Luca Longobardi
 *
 */

public class Mount extends BasicCharacterComponent implements IMount, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean flying;

	/**
	 * Constructor for a mount. Sets the fields with the input parameter.
	 * 
	 * @param stats stats vector for the mount
	 * @param name name of the mount
	 * @param description description of the mount
	 * @param cost cost of the mount
	 * @throws StatsFaultException thrown if the stats vector does not fit the defined default length
	 * @throws NullFieldException thrown if an input field is null
	 */
	public Mount(final int[] stats, final String name, final String description, final int cost) throws StatsFaultException, NullFieldException {
		super(stats, name, description, cost);
		this.flying = false;
	}

	
	
	
	@Override
	public boolean isFlying() {
		return this.flying;
	}

	@Override
	public void setFlying() {
		if (this.isFlying()) {
			this.flying = false;
		} else {
			this.flying = true;
		}
		
	}
	

}
