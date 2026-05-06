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
	private ArrayList<MilitaryUnit>[] army = new ArrayList[9];
	
	private int upgradeDefenseTechnologyIronCost;
	private int upgradeAttackTechnologyIronCost;
	private int upgradeDefenseTechnologyWoodCost;
	private int upgradeAttackTechnologyWoodCost;
	
	public Civilization() {
	    upgradeDefenseTechnologyIronCost = UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST;
	    upgradeAttackTechnologyIronCost = UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST;
	    upgradeDefenseTechnologyWoodCost = UPGRADE_BASE_DEFENSE_TECHNOLOGY_WOOD_COST;
	    upgradeAttackTechnologyWoodCost = UPGRADE_BASE_ATTACK_TECHNOLOGY_WOOD_COST;

	    army = new ArrayList[9];
	    for (int i = 0; i < army.length; i++) {
	        army[i] = new ArrayList<>();
	    }
	}
	
	
	
	public int getTechnologyDefense() {
		return technologyDefense;
	}



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
			throw new ResourceException("No tienes recursos suficientes para construir una herreria");
		}
	}
	
	public void upgradeTechnologyDefense() throws ResourceException {
	    if (iron >= upgradeDefenseTechnologyIronCost && wood >= upgradeDefenseTechnologyWoodCost) {
	        iron -= upgradeDefenseTechnologyIronCost;
	        wood -= upgradeDefenseTechnologyWoodCost;
	        technologyDefense++;
	        upgradeDefenseTechnologyIronCost += (upgradeDefenseTechnologyIronCost * UPGRADE_PLUS_DEFENSE_TECHNOLOGY_IRON_COST) / 100;
	        upgradeDefenseTechnologyWoodCost += (upgradeDefenseTechnologyWoodCost * UPGRADE_PLUS_DEFENSE_TECHNOLOGY_WOOD_COST) / 100;
	    } else {
	        throw new ResourceException("No tienes recursos suficientes para mejorar la tecnología de defensa");
	    }
	}
	
	public void upgradeTechnologyAttack() throws ResourceException {
	    if (iron >= upgradeAttackTechnologyIronCost && wood >= upgradeAttackTechnologyWoodCost) {
	        iron -= upgradeAttackTechnologyIronCost;
	        wood -= upgradeAttackTechnologyWoodCost;
	        technologyAtack++;
	        upgradeAttackTechnologyIronCost += (upgradeAttackTechnologyIronCost * UPGRADE_PLUS_ATTACK_TECHNOLOGY_IRON_COST) / 100;
	        upgradeAttackTechnologyWoodCost += (upgradeAttackTechnologyWoodCost * UPGRADE_PLUS_ATTACK_TECHNOLOGY_WOOD_COST) / 100;
	    } else {
	        throw new ResourceException("No tienes recursos suficientes para mejorar la tecnología de ataque");
	    }
	}
	
	public void newSwordsman(int n) {
	    for (int i = 0; i < n; i++) {
	        int armor = ARMOR_SWORDSMAN + (ARMOR_SWORDSMAN * PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY * technologyDefense) / 100;
	        int baseDamage = BASE_DAMAGE_SWORDSMAN + (BASE_DAMAGE_SWORDSMAN * PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY * technologyAtack) / 100;
	        army[0].add(new Swordsman(armor, baseDamage));
	    }
	}
	
	
	
	
	
	
	
}
