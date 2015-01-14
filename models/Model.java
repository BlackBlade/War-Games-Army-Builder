package models;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import exceptions.DuplicatedUnitException;
import exceptions.NullFieldException;
import exceptions.StatsFaultException;
import exceptions.UnitNotFoundException;



/**
 * Class representing the whole data managed by the Army Builder application.
 * @author Luca Longobardi
 *
 */

public class Model implements IModel, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ICharacter> codexList = new LinkedList<>();
	private List<ICharacter> armyList = new LinkedList<>();
	private transient String toText;
	private String codexName = "New Codex";
	private String listName = "New List";
	
	@Override
	public void addUnitToCodex(final IUnit unit) throws DuplicatedUnitException, StatsFaultException, NullFieldException {
		this.checkNullPointer(unit);
		if (codexList.contains(unit)) {
			throw new DuplicatedUnitException("Unit cannot be duplicated in codex list");
		} else {
			this.codexList.add(unit);
		}
		this.toText = null;
		
	}
	
	@Override
	public void addHeroToCodex(final IHero hero) throws DuplicatedUnitException, StatsFaultException, NullFieldException {
		this.checkNullPointer(hero);
		if (codexList.contains(hero)) {
			throw new DuplicatedUnitException("Hero cannot be duplicated in codex list");
		} else {
			this.codexList.add(hero);
		}
		
	}
	
	@Override
	public void removeFromCodex(final ICharacter character) throws UnitNotFoundException, NullFieldException {
		this.checkNullPointer(character);
		if (this.codexList.contains(character)) {
		    this.codexList.remove(character);
		} else {
			throw new UnitNotFoundException("Character not found");
		}
	}
	
    @Override
	public void addToArmy(final ICharacter character) throws UnitNotFoundException, StatsFaultException, NullFieldException {
		if (character instanceof IUnit) {
			this.addUnitToArmy((IUnit) character);
		} else {
			this.addHeroToArmy((IHero) character);
		}
	}
	
	/**
	 * @param unit
	 * @throws UnitNotFoundException
	 * @throws StatsFaultException
	 * Utility method used to add an unit to the army list.
	 * @throws NullFieldException 
	 */
	private void addUnitToArmy(final IUnit unit) throws UnitNotFoundException, StatsFaultException, NullFieldException {
		this.checkNullPointer(unit);
		if (this.codexList.contains(unit)) {
			final IUnit newUnit = new Unit(unit.getStats(), unit.getName(), unit.getDescription(), unit.getCost(),
					unit.getStendardCost(), unit.getChampionCost(), unit.getMusicianCost());
			this.armyList.add(newUnit);
		} else {
			throw new UnitNotFoundException("Character not found");
		}
	}
	
	/**
	 * @param hero
	 * @throws UnitNotFoundException
	 * @throws StatsFaultException
	 * Utility method used to add an hero to the army list.
	 * @throws NullFieldException 
	 */
	private void addHeroToArmy(final IHero hero) throws UnitNotFoundException, StatsFaultException, NullFieldException {
		this.checkNullPointer(hero);
		if (this.codexList.contains(hero)) {
			final IHero newHero = new Hero(hero.getStats(), hero.getName(), hero.getDescription(), hero.getCost());
			this.armyList.add(newHero);
		} else {
			throw new UnitNotFoundException("Cannot add a hero not defined in codex list");
			
		}
	}
	
	@Override
	public void removeFromArmy(final ICharacter character) throws UnitNotFoundException, NullFieldException {
		this.checkNullPointer(character);
		if (this.armyList.contains(character)) {
			this.armyList.remove(character);
		} else {
			throw new UnitNotFoundException("");
		}
	}
	
	@Override
	public List<ICharacter> getCodexlist() {
		return this.codexList;
	}
	
	@Override
	public List<ICharacter> getArmyList() {
		return this.armyList;
	}
	
	@Override
	public void setCodexList(final List<ICharacter> list) {
		this.codexList = list;
	}
	
	@Override
	public void setArmylist(final List<ICharacter> list) {
		this.armyList = list;
	}
	
	/**
	 * @return the codex name
	 */
	public String getCodexName() {
		return this.codexName;
	}
	
	/**
	 * @return the list name
	 */
	public String getListName() {
		return this.listName;
	}
	
	/**
	 * @param newName
	 * Sets the codex name.
	 */
	public void setCodexName(final String newName) {
		this.codexName = newName;
	}
	
	/**
	 * @param newName
	 * Sets the list name.
	 */
	public void setListName(final String newName) {
		this.listName = newName;
	}
	
	@Override
	public String toText() {
		final String newline = System.getProperty("line.separator");
		int totalCost = 0;
		int numberOfModels = 0;
			
		toText = this.getCodexName() + " - " + this.getListName();
		toText += newline + newline;
		for (final ICharacter character : this.armyList) {
			toText += character.toText();
			toText += newline;
			totalCost += character.getCost();
			numberOfModels += character.getCurrentOccurrence();
		}
		toText += newline + "Total Cost: " + totalCost + "    Models: " + numberOfModels + newline;
		
		return toText;
		
	}
	
	@Override
	public void switchTwoElements(final int indexOne, final int indexTwo) {
		if (indexOne >= 0 && indexOne < armyList.size() && indexTwo >= 0 && indexTwo < armyList.size()) {
			final ICharacter temp = armyList.get(indexTwo);
			armyList.set(indexTwo, armyList.get(indexOne));
			armyList.set(indexOne, temp);
		}
		
	}
	
	/**
	 * Utility method used to check if a param is null, and in this case throw an exception
	 * @param o
	 * @throws NullFieldException 
	 */
	private void checkNullPointer(final Object o) throws NullFieldException {
		if (o == null) {
			throw new NullFieldException("Field can't be empty");
		}
	}
}
