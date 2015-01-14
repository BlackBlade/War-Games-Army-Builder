package tests;

import static org.junit.Assert.*;

import java.util.Iterator;

import models.*;

public class ModelTest {
	
	@org.junit.Test
	public void test() throws Exception{
		Model model = new Model();
		IUnit unit = new Unit(new int[]{1,1,1,1,1,1,1,1,1}, "Cavalieri", "Shazam", 10, 1 ,1 ,1);
		model.addUnitToCodex(unit);
		IHero hero = new Hero(new int[]{1,1,1,1,1,1,1,1,1}, "Eroe", "LEEEROOOY JENKINSSSS", 10);
		model.addHeroToCodex(hero);
		
		//testing contains, add and removals
		assertTrue(model.getCodexlist().contains(hero));
		assertTrue(model.getCodexlist().contains(unit));
		assertFalse(model.getArmyList().contains(hero));
		assertFalse(model.getArmyList().contains(unit));
		
		model.addToArmy(hero);
		model.addToArmy(unit);
		model.addToArmy(hero); // an army list can have more instances of the same character
		
		assertTrue(this.hasInList(model.getArmyList().iterator(), "Cavalieri"));
		assertTrue(this.hasInList(model.getArmyList().iterator(), "Eroe"));
		
		model.removeFromArmy(this.getFromList(model.getArmyList().iterator(), "Cavalieri"));
		model.removeFromArmy(this.getFromList(model.getArmyList().iterator(), "Eroe"));

		assertFalse(this.hasInList(model.getArmyList().iterator(), "Cavalieri"));
		assertTrue(this.hasInList(model.getArmyList().iterator(), "Eroe"));
		assertTrue(this.occurrencesOf(model.getArmyList().iterator(), "Cavalieri") == 0);
		assertTrue(this.occurrencesOf(model.getArmyList().iterator(), "Eroe") == 1);
		assertTrue(this.occurrencesOf(model.getArmyList().iterator(), "Susanoo") == 0);
		
		//testing fails
		
		try{
			model.addToArmy(null);
			fail("cant be null");
		}catch(Exception e){}
		
		try{
			model.addToArmy(null);
			fail("cant be null");
		}catch(Exception e){}
		
		try{
			model.addHeroToCodex(null);;
			fail("cant be null");
		}catch(Exception e){}
		
		try{
			model.addUnitToCodex(null);;
			fail("cant be null");
		}catch(Exception e){}
		
		try{
			model.removeFromArmy(null);;
			fail("cant be null");
		}catch(Exception e){}
		
		try{
			model.removeFromCodex(null);
			fail("cant be null");
		}catch(Exception e){}
		
		//testing add fail to army list. An exception is thrown when
		//the user tries to add an unit or hero that is not in the codex.
		
		IUnit unit2 = new Unit(new int[]{1,1,1,1,1,1,1,1,1}, "Roccasangue", "Shazam", 10, 1 ,1 ,1);
		IHero hero2 = new Hero(new int[]{1,1,1,1,1,1,1,1,1}, "Hercules", "Shazam", 10);
		try{
			model.addToArmy(unit2);
			fail("cant be null");
		}catch(Exception e){}
		
		try{
			model.addToArmy(hero2);
			fail("cant be null");
		}catch(Exception e){}
	}
	
	public boolean hasInList(Iterator<ICharacter> it, String name) {
		while(it.hasNext()){
			if(it.next().getName().equals(name)){
				return true;
			}
		}
		return false;
	}
	
	public ICharacter getFromList(Iterator<ICharacter> it, String name){
		while(it.hasNext()){
			ICharacter character = it.next();
			if(character.getName().equals(name)){
				return character;
			}
		}
		
		return null;
	}
	
	public int occurrencesOf(Iterator<ICharacter> it, String name){
		int occ = 0;
		while(it.hasNext()){
			if(it.next().getName().equals(name)){
				occ++;
			}
		}
		return occ;
	}

}
