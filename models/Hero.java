package models;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import exceptions.ItemNotFoundException;
import exceptions.NullFieldException;
import exceptions.StatsFaultException;

/**
 * It models the concept of Hero.
 * @author Luca Longobardi
 *
 */

public class Hero extends AbstractCharacter implements IHero, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_VALUE_FOR_BSB = 25;
	private static final String DEFAULT_SPACE_LINE = "     ";
	private boolean hasBsb;
	private final List<Item> magicItemList = new LinkedList<>();

	/**
	 * Calls the constructor of the superclass.
	 * 
	 * @param stats stats vector for the character
	 * @param name name of the hero
	 * @param description description of the hero
	 * @param cost integer cost of the hero
	 * @throws StatsFaultException thrown when the stats vector does not fit the standard length defined
	 * @throws NullFieldException thrown when an input parameter is null
	 */
	public Hero(final int[] stats, final String name, final String description, final int cost) throws StatsFaultException, NullFieldException {
		super(stats, name, description, cost);
		this.hasBsb = false;
	}
	

	@Override
	public void addOccurrence() {
		throw new UnsupportedOperationException("ERROR: cannot modify occurrence of a Hero");
		
	}
	
	@Override
	public boolean isBsb() {
		return this.hasBsb;
	}
	
	@Override
	public void removeOccurrence() {
		throw new UnsupportedOperationException("ERROR: cannot modify occurrence of a Hero");
		
	}
	
	@Override
	public void setBsb() throws NullFieldException {
		if (this.hasBsb) {
			this.hasBsb = false;
			this.subtractFromCost(DEFAULT_VALUE_FOR_BSB);
		} else {
			this.hasBsb = true;
			this.addToCost(DEFAULT_VALUE_FOR_BSB);
		}


	}

	@Override
	public void addMagicItem(final Item item) throws NullFieldException {
		this.checkNullPointer(item);
		magicItemList.add(item);
		this.addToCost(item.getCost());
		
	}

	@Override
	public void removeMagicItem(final Item item) throws ItemNotFoundException, NullFieldException {
		this.checkNullPointer(item);
		if (magicItemList.contains(item)) {
			magicItemList.remove(item);
			this.subtractFromCost(item.getCost());
		} else {
			throw new ItemNotFoundException("Item not found");
		}
		
	}
	
	 @Override
	public String toDescription() {
		String toDescription = "";
		
		toDescription = "\n" + this.getCurrentOccurrence() + " " + this.getName() + " @ " + this.getCost();
		toDescription += "\n     ";
		
		 Iterator<Item> it = this.getCommonItemListIterator();
		 
		 boolean needsSpace = false;
		 
		while (it.hasNext()) {
			toDescription += it.next().getName() + "; ";
			needsSpace = true;
		}
		
		if (needsSpace) {
		    toDescription += "\n\n     ";
		    needsSpace = false;
		}
		
		it = this.magicItemList.iterator();
		Item temp = null;
		while (it.hasNext()) {
			temp = it.next();
			toDescription += temp.getName() + "[" + temp.getCost() + "]\n       ";
			toDescription += temp.getDescription() + "\n     ";
			needsSpace = true;
			
		}
		
		if (needsSpace) {
		    toDescription += "\n     ";
		    needsSpace = false;
		}
		
		if (this.getMount() != null) {
			toDescription += this.getMount().getName() + "[" + (this.getMount().getCost() * this.getCurrentOccurrence()) + "]";
			toDescription += "\n       " + this.getMount().getDescription();
		}
	
	
	return toDescription;

	}

	@Override
	public String toText() {
		final String newline = System.getProperty("line.separator");
		String toDescription;
		toDescription = newline + this.getCurrentOccurrence() + " " + this.getName() + " @ " + this.getCost();
		toDescription += newline + DEFAULT_SPACE_LINE;
			
		boolean needsSpace = false;
			
		Iterator<Item> it = this.getCommonItemListIterator();
		while (it.hasNext()) {
			toDescription += it.next().getName() + "; ";
			needsSpace = true;
		}
			
		if (needsSpace) {
		    toDescription += newline + DEFAULT_SPACE_LINE;
		    needsSpace = false;
		}
			
		it = this.magicItemList.iterator();
		Item temp = null;
		while (it.hasNext()) {
			temp = it.next();
			toDescription += temp.getName() + "[" + temp.getCost() + "]" + newline + "     ";
			needsSpace = true;
				
		}
			
		if (needsSpace) {
		    toDescription += newline + DEFAULT_SPACE_LINE;
		    needsSpace = false;
		}
			
		if (this.getMount() != null) {
			toDescription += this.getMount().getName() + "[" + this.getMount().getCost() + "]";
		}
			
		
		return toDescription;
	}
	
	
}
