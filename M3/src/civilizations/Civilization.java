package civilizations;

import java.util.ArrayList;

public class Civilization implements Variables{

	
	private int technologyDefense ;
	private int technologyAtack ;
	private int wood;
	private int iron;
	private int food;
	private int mana;
	private int magicTower;
	private int church;
	private int farm;
	private int smithy;
	//private Projecte Civilizations
	private int carpentry;
	private int battles;
	private ArrayList<MilitaryUnit> army = new ArrayList[9];
	
	
	public void newChurch() throws ResourceException{
		if (food >= FOOD_COST_CHURCH && wood >= WOOD_COST_CHURCH && iron >= IRON_COST_CHURCH) {
			food -= FOOD_COST_CHURCH;
			wood -= WOOD_COST_CHURCH;
			iron -= IRON_COST_CHURCH;
			church += 1;
		}else {
			throw new ResourceException("No tienes recursos suficientes para construir una iglesia");
		}
	}
	
	public void newMagicTower() throws ResourceException{
		if (food >= FOOD_COST_MAGICTOWER && wood >= WOOD_COST_MAGICTOWER && iron >= IRON_COST_MAGICTOWER) {
			food -= FOOD_COST_MAGICTOWER;
			wood -= WOOD_COST_MAGICTOWER;
			iron -= IRON_COST_MAGICTOWER;
			magicTower += 1;
		}else {
			throw new ResourceException("No tienes recursos suficientes para construir una torre magica");
		}
	}
	
	public void newFarm() throws ResourceException{
		if (food >= FOOD_COST_FARM && wood >= WOOD_COST_FARM && iron >= IRON_COST_FARM) {
			food -= FOOD_COST_FARM;
			wood -= WOOD_COST_FARM;
			iron -= IRON_COST_FARM;
			farm += 1;
		}else {
			throw new ResourceException("No tienes recursos suficientes para construir una granja");
		}
	}
	
	public void newCarpentry() throws ResourceException{
		if (food >= FOOD_COST_CARPENTRY && wood >= WOOD_COST_CARPENTRY && iron >= IRON_COST_CARPENTRY) {
			food -= FOOD_COST_CARPENTRY;
			wood -= WOOD_COST_CARPENTRY;
			iron -= IRON_COST_CARPENTRY;
			carpentry += 1;
		}else {
			throw new ResourceException("No tienes recursos suficientes para construir una carpinteria");
		}	
	}
	
	public void newSmithy() throws ResourceException{
		if (food >= FOOD_COST_SMITHY && wood >= WOOD_COST_SMITHY && iron >= IRON_COST_SMITHY) {
			food -= FOOD_COST_SMITHY;
			wood -= WOOD_COST_SMITHY;
			iron -= IRON_COST_SMITHY;
			smithy += 1;
		}else {
			throw new ResourceException("No tienes recursos suficientes para construir una carpinteria");
		}
	}
	
}
