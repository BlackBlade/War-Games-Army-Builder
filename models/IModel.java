package models;

import java.util.List;

import exceptions.DuplicatedUnitException;
import exceptions.NullFieldException;
import exceptions.StatsFaultException;
import exceptions.UnitNotFoundException;


/**
 * It defines the operations of a single Army builder model.
 * @author Luca Longobardi
 *
 */

public interface IModel {
	
	/**
	 * Adds an unit to the codex. Two or more occurrences of a single element are not allowed in the codex list.
	 * An element added to the codex is created at the moment by passing parameters.
	 *  
	 * @param unit input unit object
	 * @throws DuplicatedUnitException when the unit is already contained in the codex list
	 * @throws StatsFaultException if the stats vector does not fit the default length
	 * @throws NullFieldException if the input field is null
	 */
     void addUnitToCodex(IUnit unit) throws DuplicatedUnitException, StatsFaultException, NullFieldException;
	
	/**
	 * Adds a hero to the codex. Two or more occurrences of a single element are not allowed in the codex list.
	 * An element added to the codex is created at the moment by passing parameters.
	 * 
	 * @param hero input hero object
	 * @throws DuplicatedUnitException when the unit is already contained in the codex list
	 * @throws StatsFaultException if the stats vector does not fit the default length
	 * @throws NullFieldException if the input field is null
	 */
	void addHeroToCodex(IHero hero) throws DuplicatedUnitException, StatsFaultException, NullFieldException;
	
	/**
	 * Removes an element from the codex. If the element does not exist, it throws an exception.
	 * 
	 * @param character input character to remove
	 * @throws UnitNotFoundException if the character does not exist 
	 * @throws NullFieldException if the input object is null
	 */
	void removeFromCodex(ICharacter character) throws UnitNotFoundException, NullFieldException;
	
	/**
	 * Adds an ICharacter to the army list.
	 * 
	 * @param character input character
	 * @throws UnitNotFoundException if the unit does not exist in the codex
	 * @throws StatsFaultException if the stats vector does not fit the standard length defined
	 * @throws NullFieldException if the input object is null
	 */
	void addToArmy(ICharacter character) throws UnitNotFoundException, StatsFaultException, NullFieldException;
	
	/**
	 * Removes an element from the army.
	 * 
	 * @param character input character
	 * @throws UnitNotFoundException if the unit does not exist in the codex 
	 * @throws NullFieldException  if the input parameter is null
	 */
	void removeFromArmy(ICharacter character) throws UnitNotFoundException, NullFieldException;
	
	/**
	 * @return the codex list.
	 */
	List<ICharacter> getCodexlist();
	
	/**
	 * @return the army list.
 	 */
	List<ICharacter> getArmyList();
	
	/**
	 * @param list
	 * Sets the Codex list. This method is used to load/save a list from file.
	 */
	void setCodexList(List<ICharacter> list);
	
	/**
	 * @param list
	 * Sets the army list. This method is used to load/save a list from file.
	 */
	void setArmylist(List<ICharacter> list);
	

	/**
	 * Switches two elements in the army list. 
	 * Nothing happens if the input indexes are out of lower or upper bound.
	 * 
	 * @param indexOne first index
	 * @param indexTwo second index
	 */
	void switchTwoElements(int indexOne, int indexTwo);
	
	/**
	 * Utility method to print the list in txt format.
	 * @return the composite string of every single toString for each character.
	 */
	String toText();
}
