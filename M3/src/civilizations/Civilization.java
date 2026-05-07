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
	
	
	
	
	//---------------------------------------------------

	public void newSwordsman(int n) {
	    for (int i = 0; i < n; i++) {
	        int armor = ARMOR_SWORDSMAN + (ARMOR_SWORDSMAN * PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY * technologyDefense) / 100;
	        int baseDamage = BASE_DAMAGE_SWORDSMAN + (BASE_DAMAGE_SWORDSMAN * PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY * technologyAtack) / 100;
	        army[0].add(new Swordsman(armor, baseDamage));
	    }
	}

	
	public void newSpearman(int n) {
	    for (int i = 0; i < n; i++) {
	        int armor = ARMOR_SPEARMAN + (ARMOR_SPEARMAN * PLUS_ARMOR_SPEARMAN_BY_TECHNOLOGY * technologyDefense) / 100;
	        int baseDamage = BASE_DAMAGE_SPEARMAN + (BASE_DAMAGE_SPEARMAN * PLUS_ATTACK_SPEARMAN_BY_TECHNOLOGY * technologyAtack) / 100;
	        army[1].add(new Spearman(armor, baseDamage));
	    }
	}

	public void newCrossbow(int n) {
	    for (int i = 0; i < n; i++) {
	        int armor = ARMOR_CROSSBOW + (ARMOR_CROSSBOW * PLUS_ARMOR_CROSSBOW_BY_TECHNOLOGY * technologyDefense) / 100;
	        int baseDamage = BASE_DAMAGE_CROSSBOW + (BASE_DAMAGE_CROSSBOW * PLUS_ATTACK_CROSSBOW_BY_TECHNOLOGY * technologyAtack) / 100;
	        army[2].add(new Crossbow(armor, baseDamage));
	    }
	}

	public void newCannon(int n) {
	    for (int i = 0; i < n; i++) {
	        int armor = ARMOR_CANNON + (ARMOR_CANNON * PLUS_ARMOR_CANNON_BY_TECHNOLOGY * technologyDefense) / 100;
	        int baseDamage = BASE_DAMAGE_CANNON + (BASE_DAMAGE_CANNON * PLUS_ATTACK_CANNON_BY_TECHNOLOGY * technologyAtack) / 100;
	        army[3].add(new Cannon(armor, baseDamage));
	    }
	}

	public void newArrowTower(int n) {
	    for (int i = 0; i < n; i++) {
	        int armor = ARMOR_ARROWTOWER + (ARMOR_ARROWTOWER * PLUS_ARMOR_ARROWTOWER_BY_TECHNOLOGY * technologyDefense) / 100;
	        int baseDamage = BASE_DAMAGE_ARROWTOWER + (BASE_DAMAGE_ARROWTOWER * PLUS_ATTACK_ARROWTOWER_BY_TECHNOLOGY * technologyAtack) / 100;
	        army[4].add(new ArrowTower(armor, baseDamage));
	    }
	}

	public void newCatapult(int n) {
	    for (int i = 0; i < n; i++) {
	        int armor = ARMOR_CATAPULT + (ARMOR_CATAPULT * PLUS_ARMOR_CATAPULT_BY_TECHNOLOGY * technologyDefense) / 100;
	        int baseDamage = BASE_DAMAGE_CATAPULT + (BASE_DAMAGE_CATAPULT * PLUS_ATTACK_CATAPULT_BY_TECHNOLOGY * technologyAtack) / 100;
	        army[5].add(new Catapult(armor, baseDamage));
	    }
	}

	public void newRocketLauncher(int n) {
	    for (int i = 0; i < n; i++) {
	        int armor = ARMOR_ROCKETLAUNCHERTOWER + (ARMOR_ROCKETLAUNCHERTOWER * PLUS_ARMOR_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY * technologyDefense) / 100;
	        int baseDamage = BASE_DAMAGE_ROCKETLAUNCHERTOWER + (BASE_DAMAGE_ROCKETLAUNCHERTOWER * PLUS_ATTACK_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY * technologyAtack) / 100;
	        army[6].add(new RocketLauncherTower(armor, baseDamage));
	    }
	}

	public void newMagician(int n) throws BuildingException {
	    if (magicTower <= 0) {
	        throw new BuildingException("You need at least one Magic Tower to create Magicians.");
	    }
	    for (int i = 0; i < n; i++) {
	    	int armor = 0;
	        int baseDamage = BASE_DAMAGE_MAGICIAN + (BASE_DAMAGE_MAGICIAN * PLUS_ATTACK_MAGICIAN_BY_TECHNOLOGY * technologyAtack) / 100;
	        army[7].add(new Magician(armor, baseDamage));
	    }
	}

	public void newPriest(int n) throws BuildingException {
	    if (church <= 0) {
	        throw new BuildingException("You need at least one Church to create Priests.");
	    }
	    for (int i = 0; i < n; i++) {
	        int armor = 0;
	        int baseDamage = 0;
	        army[8].add(new Priest(armor, baseDamage));
	    }
	}
	
	private int getFoodGenerated() {
	    return CIVILIZATION_FOOD_GENERATED + (farm * CIVILIZATION_FOOD_GENERATED_PER_FARM);
	}

	private int getWoodGenerated() {
	    return CIVILIZATION_WOOD_GENERATED + (carpentry * CIVILIZATION_WOOD_GENERATED_PER_CARPENTRY);
	}

	private int getIronGenerated() {
	    return CIVILIZATION_IRON_GENERATED + (smithy * CIVILIZATION_IRON_GENERATED_PER_SMITHY);
	}

	private int getManaGenerated() {
	    return magicTower * CIVILIZATION_MANA_GENERATED_PER_MAGIC_TOWER;
	}
	
	public void printStats() {
	    System.out.println("******************* CIVILIZATION STATS *******************");

	    System.out.println("\n--------------------------------------------------TECHNOLOGY----------------------------------------");
	    System.out.printf("%15s %15s%n", "Attack", "Defense");
	    System.out.printf("%15d %15d%n", technologyAtack, technologyDefense);

	    System.out.println("\n---------------------------------------------------BUILDINGS----------------------------------------");
	    System.out.printf("%12s %12s %12s %15s %12s%n",
	            "Farm", "Smithy", "Carpentry", "Magic Tower", "Church");
	    System.out.printf("%12d %12d %12d %15d %12d%n",
	            farm, smithy, carpentry, magicTower, church);

	    System.out.println("\n----------------------------------------------------DEFENSES----------------------------------------");
	    System.out.printf("%15s %15s %20s%n", "Arrow Tower", "Catapult", "Rocket Launcher");
	    System.out.printf("%15d %15d %20d%n",
	            army[4].size(), army[5].size(), army[6].size());

	    System.out.println("\n------------------------------------------------ATTACK UNITS----------------------------------------");
	    System.out.printf("%15s %15s %15s %15s%n",
	            "Swordsman", "Spearman", "Crossbow", "Cannon");
	    System.out.printf("%15d %15d %15d %15d%n",
	            army[0].size(), army[1].size(), army[2].size(), army[3].size());

	    System.out.println("\n----------------------------------------------ESPECIAL UNITS----------------------------------------");
	    System.out.printf("%15s %15s%n", "Magician", "Priest");
	    System.out.printf("%15d %15d%n",
	            army[7].size(), army[8].size());

	    System.out.println("\n---------------------------------------------------RESOURCES----------------------------------------");
	    System.out.printf("%15s %15s %15s %15s%n", "Food", "Wood", "Iron", "Mana");
	    System.out.printf("%15d %15d %15d %15d%n", food, wood, iron, mana);

	    System.out.println("\n----------------------------------------GENERATION RESOURCES----------------------------------------");
	    System.out.printf("%15s %15s %15s %15s%n", "Food", "Wood", "Iron", "Mana");
	    System.out.printf("%15d %15d %15d %15d%n",
	            getFoodGenerated(), getWoodGenerated(), getIronGenerated(), getManaGenerated());
	}
	
	
	
	
	
	
	
}
