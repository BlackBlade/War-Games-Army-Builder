package models;

import java.io.Serializable;
import java.util.Iterator;

import exceptions.NullFieldException;
import exceptions.StatsFaultException;



/**
 * This class models the concept of unit. An unit can bear a stendard (only one), which can be magical or not, can have a champion and a musician and can bear
 * multiple common items. Also inherits the possibility to add a mount for each model. The costs are applied by the single functions to the total of the unit.
 * @author Luca Longobardi
 *
 */

public class Unit extends AbstractCharacter implements IUnit, Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean hasStendard;
	private boolean hasChampion;
	private boolean hasMusician;
	private final int stendardCost;
	private final int championCost;
	private final int musicianCost;
	private final int costForOccurrence;
	private Item magicStendard;
	

	/**
	 * Calls the super class constructor, adding some parameters in input.
	 * 
	 * @param stats stats vector for the unit
	 * @param name name of the unit
	 * @param description description of the unit
	 * @param cost cost of the unit
	 * @param newStendardCost stendard cost of the unit
	 * @param newChampionCost champion cost of the unit
	 * @param newMusicianCost musician cost of the unit
	 * @throws NullFieldException thrown if an input field is null
	 * @throws StatsFaultException thrown if the stats vector length does not fit the standard length defined
	 */
	public Unit(final int[] stats, final String name, final String description, final int cost, final int newStendardCost,
			final int newChampionCost, final int newMusicianCost) throws StatsFaultException, NullFieldException {
		super(stats, name, description, cost);
		this.costForOccurrence = cost;
		this.checkCost(newStendardCost);
		this.stendardCost = newStendardCost;
		this.checkCost(newChampionCost);
		this.championCost = newChampionCost;
		this.checkCost(newMusicianCost);
		this.musicianCost = newMusicianCost;
		this.hasStendard = false;
		this.hasMusician = false;
		this.hasChampion = false;
	}
	

	@Override
	public boolean hasStendard() {
		return hasStendard;
	}

	@Override
	public boolean hasChampion() {
		return hasChampion;
	}

	@Override
	public boolean hasMusician() {
		return hasMusician;
	}

	@Override
	public void setStendard() throws NullFieldException {
		if (this.hasStendard()) {
			hasStendard = false;
			this.subtractFromCost(stendardCost);
		} else {
			hasStendard = true;
			this.addToCost(stendardCost);
		}
		
	}

	@Override
	public void setMusician() throws NullFieldException {
		if (this.hasMusician()) {
			this.hasMusician = false;
			this.subtractFromCost(musicianCost);
		} else {
			this.hasMusician = true;
			this.addToCost(musicianCost);
		}
		
	}

	@Override
	public void setChampion() throws NullFieldException {
		if (this.hasChampion()) {
			this.hasChampion = false;
			this.subtractFromCost(championCost);
		} else {
			this.hasChampion = true;
			this.addToCost(championCost);
		}
		
	}
	
	@Override
	public int getStendardCost() {
		return this.stendardCost;
	}
	
	@Override
	public int getChampionCost() {
		return this.championCost;
	}
	
	@Override
	public int getMusicianCost() {
		return this.musicianCost;
	}

	@Override
	public void addMagicItem(final Item stendard) throws NullFieldException {
		this.checkNullPointer(stendard);
		if (this.hasStendard()) {
			this.subtractFromCost(stendardCost);
		}
		this.magicStendard = stendard;
		this.addToCost(stendard.getCost());
		
	}
	
	@Override
	public void removeMagicStendard() throws NullFieldException {
        this.checkNullPointer(this.magicStendard);
		this.subtractFromCost(magicStendard.getCost());
		magicStendard = null;

	}
	

	@Override
	public void addOccurrence() throws NullFieldException {
		this.addToCost(costForOccurrence + this.getCommonItemTotalCost());
		this.getOccurrence().increment();
		
	}

	@Override
	public void removeOccurrence() throws NullFieldException {
		if (this.getOccurrence().getOccurrence() > 1) {
			this.subtractFromCost(costForOccurrence + this.getCommonItemTotalCost());
			this.getOccurrence().decrement();
		}	
	}
	

	@Override
	public String toText() {
		String toText = "";
		final String newline = System.getProperty("line.separator");
		
			toText = newline + this.getCurrentOccurrence() + " " + this.getName() + " @ " + this.getCost();
			toText += newline + "     ";
			
			 final Iterator<Item> it = this.getCommonItemListIterator();
			 
			 boolean needsSpace = false;
			 
			while (it.hasNext()) {
				toText += it.next().getName() + "; ";
				needsSpace = true;
			}
			if (this.hasStendard()) {
				toText += "Stendard; ";
				needsSpace = true;
			}
			if (this.hasChampion()) {
				toText += "Champion; ";
				needsSpace = true;
			}
			if (this.hasMusician()) {
				toText += "Musician; ";
				needsSpace = true;
			}
			
			if (needsSpace) {
			    toText += newline + newline + "     ";
			    needsSpace = false;
			}
			if (this.magicStendard != null) {
				toText += this.magicStendard.getName() + "[" + this.magicStendard.getCost() + "]";
				needsSpace = true;
			}
			
			if (needsSpace) {
			    toText += newline + newline + "     ";
			    needsSpace = false;
			}
			
			if (this.getMount() != null) {
				toText += this.getMount().getName() + "[" + (this.getMount().getCost() * this.getCurrentOccurrence()) + "]";
			}
		
		
		return toText;
		
	}
	
	@Override
	public String toDescription() {
		String toDescription;
		
		toDescription = "\n" + this.getCurrentOccurrence() + " " + this.getName() + " @ " + this.getCost();
		toDescription += "\n     ";
		
		 final Iterator<Item> it = this.getCommonItemListIterator();
		 
		 boolean needsSpace = false;
		 
		while (it.hasNext()) {
			toDescription += it.next().getName() + "; ";
			needsSpace = true;
		}
		if (this.hasStendard()) {
			toDescription += "Stendard; ";
			needsSpace = true;
		}
		if (this.hasChampion()) {
			toDescription += "Champion; ";
			needsSpace = true;
		}
		if (this.hasMusician()) {
			toDescription += "Musician; ";
			needsSpace = true;
		}
		
		if (needsSpace) {
		    toDescription += "\n\n     ";
		    needsSpace = false;
		}
		if (this.magicStendard != null) {
			toDescription += this.magicStendard.getName() + "[" + this.magicStendard.getCost() + "]";
			toDescription += "\n       " + this.magicStendard.getDescription();
			needsSpace = true;
		}
		
		if (needsSpace) {
		    toDescription += "\n\n     ";
		    needsSpace = false;
		}
		
		if (this.getMount() != null) {
			toDescription += this.getMount().getName() + "[" + (this.getMount().getCost() * this.getCurrentOccurrence()) + "]";
			toDescription += "\n       " + this.getMount().getDescription();
		}
	
	
	return toDescription;

	}
}
