package models;

import java.io.Serializable;

import exceptions.NullFieldException;
import exceptions.StatsFaultException;

/**
 * Models generic concept of a list component.
 * A list component can be an hero, unit, item, mount or any object that extends this class
 * and can be placed in an army list.
 * @author Luca Longobardi
 *
 */
public class BasicListComponent implements IBasicComponent, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String description;
	private int cost;
	private static final int DEFAULT_NAME_LENGTH = 15;
	private static final int DEFAULT_TRUNCATION_DELTA = 3;
	
	/**
	 * Constructor for a generic list component.
	 * 
	 * @param newName name of the component
	 * @param newDescription description of the component
	 * @param newCost cost of the component
	 * @throws NullFieldException  thrown if an input field is null
	 * @throws StatsFaultException if cost is less than one
	 */
	public BasicListComponent(final String newName, final String newDescription, final int newCost) throws NullFieldException, StatsFaultException {
		checkNullPointer(newName);
		checkNullPointer(newDescription);
		checkNullPointer(newCost);
		
		checkCost(newCost);
		
		this.name = this.truncateName(newName);
		this.description = newDescription;
		this.cost = newCost;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getCost() {
		return cost;
	}

    @Override
	public void setDescription(final String newDescription) throws NullFieldException {
    	checkNullPointer(newDescription);
		this.description = newDescription;
		
	}

	@Override
	public void setName(final String newName) throws NullFieldException {
		checkNullPointer(newName);
		this.name = newName;
		
	}

	@Override
	public void setCost(final int newCost) throws NullFieldException {
		checkNullPointer(newCost);
		this.cost = newCost;
		
	}
	
	/**
	 * @param o
	 * Utility method to check the null pointer exception.
	 * @throws NullFieldException 
	 */
	protected void checkNullPointer(final Object o) throws NullFieldException {
		if (o == null) {
			throw new NullFieldException("Field cannot be empty");
		}
	}
	
	/**
	 * @param toCheck integer to check
	 * @throws StatsFaultException if the integer is less than one
	 */
	protected void checkCost(final int toCheck) throws StatsFaultException {
		if (toCheck < 1) {
			throw new StatsFaultException("Cost can't be less than one");
		}
	}
	
	private String truncateName(String newName) {
		if (newName.length() > DEFAULT_NAME_LENGTH - DEFAULT_TRUNCATION_DELTA) {
			// DEFAULT_TRUNCATION_DELTA(3) is a parameter obtained by empirical experiments which ensures
			//the string alignment in the toString implemented in AbstractCharacter.
			newName = newName.substring(0, DEFAULT_NAME_LENGTH - DEFAULT_TRUNCATION_DELTA);
		}
		return newName;
	}

}
