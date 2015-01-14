package tests;

import static org.junit.Assert.*;
import models.*;

public class UnitTest {
	
	@org.junit.Test
	public void test() throws Exception{
		
		Unit unit = new Unit( new int[]{1,1,1,1,1,1,1,1,1}, "Cavalieri", "Shazam", 10, 1 ,1 ,1);
		Item scudo = new Item("scudo", "", 1);
		unit.addCommonItem(scudo);
	    Mount cavallo = new Mount(new int[]{1,1,1,1,1,1,1,1,1}, "cavallo", "", 2);
		unit.addMount(cavallo);
		unit.addMagicItem(new Item("Stendardo", "", 1));
		
		//testing cost for unit and occurrence
		assertTrue(unit.getCost() == 14);
		assertTrue(unit.getCurrentOccurrence() == 1);
		assertTrue(unit.getChampionCost() == 1);
		assertTrue(unit.getMusicianCost() == 1);
		assertTrue(unit.getStendardCost() == 1);
		
		for(int i = 0; i<4; i++){
			unit.addOccurrence();
		}
		
		//testing add occurrence for cost calculus
		assertTrue(unit.getCost() == 66);
		
		//testing removals
		unit.removeCommonItem(scudo);
		assertTrue(unit.getCost() == 61);
		
		unit.removeMagicStendard();
		assertTrue(unit.getCost() == 60);
		
		unit.removeMount();
		assertTrue(unit.getCost() == 50);
		
		//testing remove occurrence
		unit.removeOccurrence();
		assertTrue(unit.getCost() == 40);
	}

}
