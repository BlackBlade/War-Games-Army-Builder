package models;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import exceptions.ItemNotFoundException;
import exceptions.NullFieldException;
import exceptions.StatsFaultException;;

/**
 * It models the basic structure of a character.
 * By extending this class we create the more specific classes that can compose a single army list. 
 * 
 * @author Luca Longobardi
 *
 */


public abstract class AbstractCharacter extends BasicCharacterComponent implements ICharacter, Serializable {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_STARTING_OCCURRENCE = 1;
	private static final int DEFAULT_NAME_LENGTH = 15;
	private static final int SPACE_FACTOR = 2;
	private static final int SPACE_LETTERS_FACTOR = 3;
	private final Occurrence currentOccurrence;
	private final List<Item> commonItemList = new LinkedList<>();
	private int commonItemsTotalCost;
	private Mount mount;
	
	/**
	 * Constructor for a new Abstract Character. Sets occurrence to 1 by default.
	 * 
	 * @param stats stats vector of the character
	 * @param newName name of the character
	 * @param newDescription description of the character
	 * @param newCost cost of the character
	 * @throws NullFieldException exception thrown when an input field is null
	 * @throws StatsFaultException thrown if the stats vector length does not fit the standard length defined
	 */
	public AbstractCharacter(final int[] stats, final String newName, final String newDescription, final int newCost) 
			throws StatsFaultException, NullFieldException {
		super(stats, newName, newDescription, newCost);
		this.currentOccurrence = new Occurrence(DEFAULT_STARTING_OCCURRENCE);
		this.commonItemsTotalCost = 0;
	}
	
	
	/**
	 * @return the Object representing the occurrence of a character and his operations.
	 */
	protected Occurrence getOccurrence() {
		return currentOccurrence;
	}
	
	/**
	 * @return The total cost of the common item list.
	 */
	protected int getCommonItemTotalCost() {
		return this.commonItemsTotalCost;
	}
	
	@Override
	public int getCurrentOccurrence() {
		return this.currentOccurrence.getOccurrence();
	}
	
	/**
	 * Adds an occurrence to the character.
	 * @throws NullFieldException 
	 */
	public abstract void addOccurrence() throws NullFieldException;
	
	/**
	 * Removes an occurrence from the character.
	 * @throws NullFieldException 
	 */
	public abstract void removeOccurrence() throws NullFieldException;
	
	/** 
	 * 
	 * Adds a cost to the total.
	 * @param newCost the cost to add
	 * @throws NullFieldException thrown if the input field is null
	 */
	protected void addToCost(final int newCost) throws NullFieldException {
		this.setCost(this.getCost() + newCost);
		
	}

	/**
	 * @param newCost
	 * 
	 * Subtracts a certain cost to the total.
	 * @throws NullFieldException 
	 */
	protected void subtractFromCost(final int newCost) throws NullFieldException {
		this.setCost(this.getCost() - newCost);
		
	}
	
	@Override
	public void addCommonItem(final Item item) throws NullFieldException {
		checkNullPointer(item);
		commonItemList.add(item);
		this.addToCost(item.getCost() * getCurrentOccurrence());
		this.commonItemsTotalCost += item.getCost();
	}
	
	@Override
	public void removeCommonItem(final Item item) throws ItemNotFoundException, NullFieldException {
		checkNullPointer(item);
		if (commonItemList.contains(item)) {
			this.commonItemsTotalCost -= item.getCost();
			this.subtractFromCost(item.getCost() * this.getCurrentOccurrence());
			commonItemList.remove(item);
		} else {
			throw new ItemNotFoundException("Item not found");
		}
	}
	
	@Override
	public Mount getMount() {
		return mount;
	}
	
	@Override
	public void addMount(final Mount newMount) throws NullFieldException {
		checkNullPointer(newMount);
		if (this.mount != null) {
			this.removeMount();
		}
		this.addToCost(newMount.getCost() * this.getCurrentOccurrence());
		this.commonItemsTotalCost += newMount.getCost();
		this.mount = newMount;
		
		
	}
	
	@Override
	public void removeMount() throws NullFieldException {
		checkNullPointer(this.mount);
		this.subtractFromCost(this.getMount().getCost() * this.getCurrentOccurrence());
		this.commonItemsTotalCost -= this.getMount().getCost();
		mount = null;
		
	}
	
	/**
	 * @return The iterator for the common item list
	 */
	protected Iterator<Item> getCommonItemListIterator() {
		return this.commonItemList.iterator();
	}
	
	/**
	 * Empirical alignment formula to ensure the string alignment while printing the army or codex
	 * list in the principal view.
	 * @param name the name to fill
	 * @return a fill string of spaces
	 */
	private String getAlignmentString(final String name) {
		//Based on the fact that string alignment formulas(created with space filling)
		//follow a certain curve, and his used values are a certain multiple of a space.
		//More the function is approximated to a straight line parallel to the 
		//axis of X, more the string alignment is precise.
		
		//NOTE: these consideration are made just by empirical experiments with strings
		//and space filling alignment. The idea is mine.
		
		String fill = "";
		int space = SPACE_FACTOR;
		int k = 0;
		for (int i = name.length(); i < DEFAULT_NAME_LENGTH; i++) {
			fill += "  ";
		}

		while (name.length() > space) {
			space = space * SPACE_FACTOR;
			fill = fill.substring(0, fill.length() - 1);
		}
		for (int i = 0; i < name.length(); i++) {
			//check for some letters that are less than 2 spaces in pixel length.
			if (name.charAt(i) == 'i' || name.charAt(i) == 'j' || name.charAt(i) == 'l' 
					|| name.charAt(i) == 'f' || name.charAt(i) == 't') {
				k++;
				fill += " ";
				//every three letters that are less than 2 spaces one more space is added.
				if (k == SPACE_LETTERS_FACTOR) {
					k = 0;
					fill += " ";
				}
			}
		}
		return fill;
	}
	
	@Override
	public String toString() {
		String toString = "";
		String zeroSeparator = "";
		if (this.getCurrentOccurrence() < 10) {
			zeroSeparator = "0";
		}
		
		toString += zeroSeparator + this.getCurrentOccurrence() + "     " + this.getName() + this.getAlignmentString(this.getName()) + "  | ";
		
		for (final int i : this.getStats()) {
			if (i < 10) {
				toString += "0";
			}
			toString += i + " | ";
		}
		
		toString +=   "     " +  this.getCost();
		
		return toString;
		
	}
	
	/**
	 * @author Luca Longobardi
	 * Utility nested class used to manipulate the occurrence of a character.
	 */
	public class Occurrence implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int currentOccurrence;
		
		/**
		 * @param i
		 * Constructor that sets the current occurrence to i.
		 */
		public Occurrence(final int i) {
			currentOccurrence = i;
		}
		
		/**
		 * Increments the occurrence.
		 */
		public void increment() {
			currentOccurrence++;
		}
		
		/**
		 * Decrement the occurrence.
		 */
		public void decrement() {
			currentOccurrence--;
		}
		
		/**
		 * @return the current occurrence
		 */
		public int getOccurrence() {
			return currentOccurrence;
		}
	}

	

}
