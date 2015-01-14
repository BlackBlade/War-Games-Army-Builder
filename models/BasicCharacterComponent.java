package models;

import java.io.Serializable;

import exceptions.NullFieldException;
import exceptions.StatsFaultException;

/**
 * Models the concept of a basic character.
 * A Class to become a character must Extend this class and implement the {@link} Interface
 * @author Luca Longobardi
 *
 */
public class BasicCharacterComponent extends BasicListComponent implements IBasicCharacter, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int STATS_NUMBER = 9;
	private static final int STATS_MAX = 10;
	private int[] statsVector = new int[STATS_NUMBER];
	
    /**
     * Constructor sets params for a basic character.
     * 
     * @param stats stats vector for the character
     * @param newName name of the character
     * @param newDescription description of the character
     * @param newCost cost of the character
     * @throws StatsFaultException thrown if the stats vector has not the required length by default.
     * @throws NullFieldException thrown if an input field is null
     */
	public BasicCharacterComponent(final int[] stats, final String newName, final String newDescription, final int newCost)
			throws StatsFaultException, NullFieldException {
		super(newName, newDescription, newCost);
		this.checkStats(stats);
		this.statsVector = stats;
		
	}
	
	@Override
	public int[] getStats() {
		final int[] arrCopy = new int[STATS_NUMBER];
		System.arraycopy(statsVector, 0, arrCopy, 0, statsVector.length);
		return arrCopy;
	}
	
	@Override
	public void setStats(final int[] stats) throws NullFieldException {
		checkNullPointer(stats);
		statsVector = stats;
		
	}
	
	private void checkStats(final int[] stats) throws StatsFaultException, NullFieldException {
		this.checkNullPointer(stats);
		if (stats.length != STATS_NUMBER) {
			throw new StatsFaultException("Error: stats vector must be long: " + STATS_NUMBER);
		}
		for (int i = 0; i < STATS_NUMBER; i++) {
			if (stats[i] > STATS_MAX) {
				throw new StatsFaultException("Error: a stat value can't be higher than " + STATS_MAX);
			}
		}
		
	}

}
