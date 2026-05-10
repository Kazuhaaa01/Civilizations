package civilizations;

import java.util.ArrayList;

public class Civilization implements Variables{

	
	int technologyDefense ;
	int technologyAtack ;
	int wood;
	int iron;
	static int food;
	int mana;
	int magicTower;
	int church;
	int farm;
	int smithy;
	int carpentry;
	int battles;
	ArrayList<MilitaryUnit>[] army = new ArrayList[9];
	
	int upgradeDefenseTechnologyIronCost;
	int upgradeAttackTechnologyIronCost;
	int upgradeDefenseTechnologyWoodCost;
	int upgradeAttackTechnologyWoodCost;
	
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



	public void newChurch() throws BuildingException{
		if (food >= FOOD_COST_CHURCH && wood >= WOOD_COST_CHURCH && iron >= IRON_COST_CHURCH) {
			food -= FOOD_COST_CHURCH;
			wood -= WOOD_COST_CHURCH;
			iron -= IRON_COST_CHURCH;
			church += 1;
		}else {
			throw new BuildingException("No tienes recursos suficientes para construir una iglesia");
		}
	}
	
	public void newMagicTower() throws BuildingException{
		if (food >= FOOD_COST_MAGICTOWER && wood >= WOOD_COST_MAGICTOWER && iron >= IRON_COST_MAGICTOWER) {
			food -= FOOD_COST_MAGICTOWER;
			wood -= WOOD_COST_MAGICTOWER;
			iron -= IRON_COST_MAGICTOWER;
			magicTower += 1;
		}else {
			throw new BuildingException("No tienes recursos suficientes para construir una torre magica");
		}
	}
	
	public void newFarm() throws BuildingException{
		if (food >= FOOD_COST_FARM && wood >= WOOD_COST_FARM && iron >= IRON_COST_FARM) {
			food -= FOOD_COST_FARM;
			wood -= WOOD_COST_FARM;
			iron -= IRON_COST_FARM;
			farm += 1;
		}else {
			throw new BuildingException("No tienes recursos suficientes para construir una granja");
		}
	}
	
	public void newCarpentry() throws BuildingException{
		if (food >= FOOD_COST_CARPENTRY && wood >= WOOD_COST_CARPENTRY && iron >= IRON_COST_CARPENTRY) {
			food -= FOOD_COST_CARPENTRY;
			wood -= WOOD_COST_CARPENTRY;
			iron -= IRON_COST_CARPENTRY;
			carpentry += 1;
		}else {
			throw new BuildingException("No tienes recursos suficientes para construir una carpinteria");
		}	
	}
	
	public void newSmithy() throws BuildingException{
		if (food >= FOOD_COST_SMITHY && wood >= WOOD_COST_SMITHY && iron >= IRON_COST_SMITHY) {
			food -= FOOD_COST_SMITHY;
			wood -= WOOD_COST_SMITHY;
			iron -= IRON_COST_SMITHY;
			smithy += 1;
		}else {
			throw new BuildingException("No tienes recursos suficientes para construir una herreria");
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

	public void newSwordsman(int n) throws ResourceException {
		if (wood >= WOOD_COST_SWORDSMAN && food >= FOOD_COST_SWORDSMAN && iron >= IRON_COST_SWORDSMAN) {
			for (int i = 0; i < n; i++) {
		        int armor = ARMOR_SWORDSMAN + (ARMOR_SWORDSMAN * PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY * technologyDefense) / 100;
		        int baseDamage = BASE_DAMAGE_SWORDSMAN + (BASE_DAMAGE_SWORDSMAN * PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY * technologyAtack) / 100;
		        wood -= WOOD_COST_SWORDSMAN;
		        food -= FOOD_COST_SWORDSMAN;
		        iron -= IRON_COST_SWORDSMAN;
		        army[0].add(new Swordsman(armor, baseDamage));
			}	
		}else {
			throw new ResourceException("No tienes recursos suficientes para crear Swordman");
	    }
	}

	
	public void newSpearman(int n) throws ResourceException {
		if (wood >= WOOD_COST_SPEARMAN && food >= FOOD_COST_SPEARMAN && iron >= IRON_COST_SPEARMAN) {
		    for (int i = 0; i < n; i++) {
		        int armor = ARMOR_SPEARMAN + (ARMOR_SPEARMAN * PLUS_ARMOR_SPEARMAN_BY_TECHNOLOGY * technologyDefense) / 100;
		        int baseDamage = BASE_DAMAGE_SPEARMAN + (BASE_DAMAGE_SPEARMAN * PLUS_ATTACK_SPEARMAN_BY_TECHNOLOGY * technologyAtack) / 100;
		        wood -= WOOD_COST_SPEARMAN;
		        food -= FOOD_COST_SPEARMAN;
		        iron -= IRON_COST_SPEARMAN;
		        army[1].add(new Spearman(armor, baseDamage));
		    }
		}else {
			throw new ResourceException("No tienes recursos suficientes para crear Spearman");
		}


	}

	public void newCrossbow(int n) throws ResourceException {
		if (wood >= WOOD_COST_CROSSBOW && food >= FOOD_COST_CROSSBOW && iron >= IRON_COST_CROSSBOW) {
		    for (int i = 0; i < n; i++) {
		        int armor = ARMOR_CROSSBOW + (ARMOR_CROSSBOW * PLUS_ARMOR_CROSSBOW_BY_TECHNOLOGY * technologyDefense) / 100;
		        int baseDamage = BASE_DAMAGE_CROSSBOW + (BASE_DAMAGE_CROSSBOW * PLUS_ATTACK_CROSSBOW_BY_TECHNOLOGY * technologyAtack) / 100;
		        wood -= WOOD_COST_CROSSBOW;
		        food -= FOOD_COST_CROSSBOW;
		        iron -= IRON_COST_CROSSBOW;
		        army[2].add(new Crossbow(armor, baseDamage));
		    }
		}else {
			throw new ResourceException("No tienes recursos suficientes para crear Crossbow");
		}


	}

	public void newCannon(int n) throws ResourceException {
		if (wood >= WOOD_COST_CANNON && food >= FOOD_COST_CANNON && iron >= IRON_COST_CANNON) {
		    for (int i = 0; i < n; i++) {
		        int armor = ARMOR_CANNON + (ARMOR_CANNON * PLUS_ARMOR_CANNON_BY_TECHNOLOGY * technologyDefense) / 100;
		        int baseDamage = BASE_DAMAGE_CANNON + (BASE_DAMAGE_CANNON * PLUS_ATTACK_CANNON_BY_TECHNOLOGY * technologyAtack) / 100;
		        wood -= WOOD_COST_CANNON;
		        food -= FOOD_COST_CANNON;
		        iron -= IRON_COST_CANNON;
		        army[3].add(new Cannon(armor, baseDamage));
		    }
		}else {
			throw new ResourceException("No tienes recursos suficientes para crear Cannon");
		}


	}

	public void newArrowTower(int n) throws ResourceException {
		if (wood >= WOOD_COST_ARROWTOWER && food >= FOOD_COST_ARROWTOWER && iron >= IRON_COST_ARROWTOWER) {
			  for (int i = 0; i < n; i++) {
			        int armor = ARMOR_ARROWTOWER + (ARMOR_ARROWTOWER * PLUS_ARMOR_ARROWTOWER_BY_TECHNOLOGY * technologyDefense) / 100;
			        int baseDamage = BASE_DAMAGE_ARROWTOWER + (BASE_DAMAGE_ARROWTOWER * PLUS_ATTACK_ARROWTOWER_BY_TECHNOLOGY * technologyAtack) / 100;
			        wood -= WOOD_COST_ARROWTOWER;
			        food -= FOOD_COST_ARROWTOWER;
			        iron -= IRON_COST_ARROWTOWER;
			        army[4].add(new ArrowTower(armor, baseDamage));
			    }
		}else {
			throw new ResourceException("No tienes recursos suficientes para crear ArrowTower");
		}
	  
	}

	public void newCatapult(int n) throws ResourceException {
		if (wood >= WOOD_COST_CATAPULT && food >= FOOD_COST_CATAPULT && iron >= IRON_COST_CATAPULT) {
		    for (int i = 0; i < n; i++) {
		        int armor = ARMOR_CATAPULT + (ARMOR_CATAPULT * PLUS_ARMOR_CATAPULT_BY_TECHNOLOGY * technologyDefense) / 100;
		        int baseDamage = BASE_DAMAGE_CATAPULT + (BASE_DAMAGE_CATAPULT * PLUS_ATTACK_CATAPULT_BY_TECHNOLOGY * technologyAtack) / 100;
		        wood -= WOOD_COST_CATAPULT;
		        food -= FOOD_COST_CATAPULT;
		        iron -= IRON_COST_CATAPULT;
		        army[5].add(new Catapult(armor, baseDamage));
		    }
		}else{
			throw new ResourceException("No tienes recursos suficientes para crear Catapult");
		}


	}

	public void newRocketLauncher(int n) throws ResourceException {
		if (wood >= WOOD_COST_ROCKETLAUNCHERTOWER && food >= FOOD_COST_ROCKETLAUNCHERTOWER && iron >= IRON_COST_ROCKETLAUNCHERTOWER) {
		    for (int i = 0; i < n; i++) {
		        int armor = ARMOR_ROCKETLAUNCHERTOWER + (ARMOR_ROCKETLAUNCHERTOWER * PLUS_ARMOR_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY * technologyDefense) / 100;
		        int baseDamage = BASE_DAMAGE_ROCKETLAUNCHERTOWER + (BASE_DAMAGE_ROCKETLAUNCHERTOWER * PLUS_ATTACK_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY * technologyAtack) / 100;
		        wood -= WOOD_COST_ROCKETLAUNCHERTOWER;
		        food -= FOOD_COST_ROCKETLAUNCHERTOWER;
		        iron -= IRON_COST_ROCKETLAUNCHERTOWER;
		        army[6].add(new RocketLauncherTower(armor, baseDamage));
		    }
		}else {
			throw new ResourceException("No tienes recursos suficientes para crear RocketLauncher");
		}


	}

	public void newMagician(int n) throws BuildingException, ResourceException {
		if (wood >= WOOD_COST_MAGICIAN && food >= FOOD_COST_MAGICIAN && iron >= IRON_COST_MAGICIAN) {
			if (magicTower <= 0) {
		        throw new BuildingException("You need at least one Magic Tower to create Magicians.");
		    }
		    for (int i = 0; i < n; i++) {
		    	int armor = 0;
		        int baseDamage = BASE_DAMAGE_MAGICIAN + (BASE_DAMAGE_MAGICIAN * PLUS_ATTACK_MAGICIAN_BY_TECHNOLOGY * technologyAtack) / 100;
		        wood -= WOOD_COST_MAGICIAN;
		        food -= FOOD_COST_MAGICIAN;
		        iron -= IRON_COST_MAGICIAN;
		        army[7].add(new Magician(armor, baseDamage));
		    }
		}else {
			throw new ResourceException("No tienes recursos suficientes para crear Magician");
		}

	    
	}

	public void newPriest(int n) throws BuildingException, ResourceException {
		if (wood >= WOOD_COST_PRIEST && food >= FOOD_COST_PRIEST && iron >= IRON_COST_PRIEST) {
		    if (church <= 0) {
		        throw new BuildingException("You need at least one Church to create Priests.");
		    }
		    for (int i = 0; i < n; i++) {
		        int armor = 0;
		        int baseDamage = 0;
		        wood -= WOOD_COST_PRIEST;
		        food -= FOOD_COST_PRIEST;
		        iron -= IRON_COST_PRIEST;
		        army[8].add(new Priest(armor, baseDamage));
		    }
		}else {
			throw new ResourceException("No tienes recursos suficientes para crear Priest");
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
	
	public void generarRecursos() {
	    this.food += CIVILIZATION_FOOD_GENERATED + (this.farm * CIVILIZATION_FOOD_GENERATED_PER_FARM);
	    this.wood += CIVILIZATION_WOOD_GENERATED + (this.carpentry * CIVILIZATION_WOOD_GENERATED_PER_CARPENTRY);
	    this.iron += CIVILIZATION_IRON_GENERATED + (this.smithy * CIVILIZATION_IRON_GENERATED_PER_SMITHY);
	    this.mana += (this.magicTower * CIVILIZATION_MANA_GENERATED_PER_MAGIC_TOWER);
	    //System.out.println("Recursos ampliados");
	}



	// Pon esto en Civilization.java
	public ArrayList<MilitaryUnit> getArmy() {
	    ArrayList<MilitaryUnit> todoMiEjercito = new ArrayList<>();
	    
	    // 1. Recorremos las 9 posiciones de tu array de ejércitos
	    for (int i = 0; i < this.army.length; i++) {
	        
	        // Comprobamos que esa posición no esté vacía (por si acaso)
	        if (this.army[i] != null) {
	            
	            // 2. Recorremos cada tropa que haya dentro de esa lista
	            for (int j = 0; j < this.army[i].size(); j++) {
	                
	                // Extraemos la tropa
	                MilitaryUnit tropa = this.army[i].get(j);
	                
	                // La añadimos a nuestra lista general de una en una
	                todoMiEjercito.add(tropa);
	            }
	        }
	    }
	    
	    return todoMiEjercito;
	}
	
	public void updateArmy(ArrayList<MilitaryUnit> supervivientes) {
	    
	    for (int i = 0; i < this.army.length; i++) {
	        if (this.army[i] != null) {
	            this.army[i].clear();
	        } else {
	            this.army[i] = new ArrayList<>();
	        }
	    }
	    
	    for (int i = 0; i < supervivientes.size(); i++) {
	        MilitaryUnit unit = supervivientes.get(i);
	        
	        if (unit instanceof Swordsman) this.army[0].add(unit);
	        else if (unit instanceof Spearman) this.army[1].add(unit);
	        else if (unit instanceof Crossbow) this.army[2].add(unit);
	        else if (unit instanceof Cannon) this.army[3].add(unit);
	        else if (unit instanceof ArrowTower) this.army[4].add(unit);
	        else if (unit instanceof Catapult) this.army[5].add(unit);
	        else if (unit instanceof RocketLauncherTower) this.army[6].add(unit);
	        else if (unit instanceof Magician) this.army[7].add(unit);
	        else if (unit instanceof Priest) this.army[8].add(unit);
	    }
	}
	
	
	
	
	
	
	
	
	
}
