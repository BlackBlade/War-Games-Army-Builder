package tests;

import org.junit.Test;


import models.*;
import static org.junit.Assert.*;

public class HeroTest {
	
	@Test
	public void test() throws Exception {
		Hero hero = new Hero(new int[]{1 , 1, 1, 1, 1, 1, 1, 1, 1}, "Hero", "Tons of damage", 10);
		Item kaeleth = new Item("Kaeleth", "Molto forte", 35);
		Item armor = new Item("Armatura nera", "Molto forte", 25);
		hero.addMagicItem(kaeleth);
		hero.addMagicItem(armor);
		
		Mount dragon = new Mount(new int[]{1,1,1,1,1,1,1,1,1}, "Dragon", "Tons of damage", 10);
		hero.addMount(dragon);
		
		hero.addCommonItem(new Item("armatura pesante", "", 1));
		hero.addCommonItem(new Item("scudo", "", 1));
		
		//Testing hero cost after adding items and mount
		assertTrue(hero.getCost()==82);
		
		
		hero.removeMagicItem(kaeleth);
		hero.removeMount();
		
		//Testing hero cost after removals
		assertTrue(hero.getCost() == 37);
		
		hero.setBsb();
		
		//Testing hero bsb setting
		assertTrue(hero.getCost() == 62);
		
		//Testing fail of occurrence modifiers functions
		try{
		    hero.removeOccurrence();
		    fail("Cannot be incremented");
		}catch(Exception e){}
		
		try{
		    hero.addOccurrence();
		    fail("Cannot be incremented");
		}catch(Exception e){}
	}

}
