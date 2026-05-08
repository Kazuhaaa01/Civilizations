package civilizations;

import java.util.ArrayList;

public class Battle implements Variables {

    private ArrayList<MilitaryUnit> civilizationArmy;
    private ArrayList<MilitaryUnit> enemyArmy;
    private ArrayList<ArrayList<MilitaryUnit>> armies;
    private String battleDevelopment;
    private int initialNumberUnitsCivilization;
    private int initialNumberUnitsEnemy;
    private int[] wasteWoodIron;
    private int enemyDrops;
    private int civilizationDrops;
    
    private int[][] initialCostFleet;
    private int[][] resourcesLooses;
    private int[][] initialArmies;
    private int[] actualNumberUnitsCivilization;
    private int[] actualNumberUnitsEnemy;

    public Battle(ArrayList<MilitaryUnit> civilizationArmy, ArrayList<MilitaryUnit> enemyArmy) {
        this.civilizationArmy = civilizationArmy;
        this.enemyArmy = enemyArmy;
        this.armies = new ArrayList<>();
        this.battleDevelopment = "";
        this.initialCostFleet = new int[2][3];
        this.wasteWoodIron = new int[2];
        this.resourcesLooses = new int[2][4];
        this.initialArmies = new int[2][9];
        this.actualNumberUnitsCivilization = new int[9];
        this.actualNumberUnitsEnemy = new int[9];
        initInitialArmies();
        this.initialNumberUnitsCivilization = civilizationArmy.size();
        this.initialNumberUnitsEnemy = enemyArmy.size();
        this.actualNumberUnitsCivilization = initialFleetNumber(civilizationArmy);
        this.actualNumberUnitsEnemy = initialFleetNumber(enemyArmy);
        this.initialCostFleet[0] = fleetResourceCost(civilizationArmy);
        this.initialCostFleet[1] = fleetResourceCost(enemyArmy);
        updateResourcesLooses();
    }
    
    
    private void initInitialArmies() {
        for (MilitaryUnit unit : civilizationArmy) {
            int idx = getUnitIndex(unit);
            if (idx != -1) initialArmies[0][idx]++;
        }
        for (MilitaryUnit unit : enemyArmy) {
            int idx = getUnitIndex(unit);
            if (idx != -1) initialArmies[1][idx]++;
        }
    }

    private int[] initialFleetNumber(ArrayList<MilitaryUnit> army) {
        int[] counts = new int[9];
        for (MilitaryUnit unit : army) {
            int idx = getUnitIndex(unit);
            if (idx != -1) counts[idx]++;
        }
        return counts;
    }
    
    
    private int getUnitIndex(MilitaryUnit unit) {
        if (unit instanceof Swordsman) return 0;
        if (unit instanceof Spearman) return 1;
        if (unit instanceof Crossbow) return 2;
        if (unit instanceof Cannon) return 3;
        if (unit instanceof ArrowTower) return 4;
        if (unit instanceof Catapult) return 5;
        if (unit instanceof RocketLauncherTower) return 6;
        if (unit instanceof Magician) return 7;
        if (unit instanceof Priest) return 8;
        return -1;
    }

    private int[] fleetResourceCost(ArrayList<MilitaryUnit> army) {
        int food = 0;
        int wood = 0;
        int iron = 0;
        for (MilitaryUnit unit : army) {
            food += unit.getFoodCost();
            wood += unit.getWoodCost();
            iron += unit.getIronCost();
        }
        return new int[]{food, wood, iron};
    }

    private void updateResourcesLooses() {

        int[] civActual = fleetResourceCost(civilizationArmy);
        int[] eneActual = fleetResourceCost(enemyArmy);

        // Civilization losses
        resourcesLooses[0][0] = initialCostFleet[0][0] - civActual[0];
        resourcesLooses[0][1] = initialCostFleet[0][1] - civActual[1];
        resourcesLooses[0][2] = initialCostFleet[0][2] - civActual[2];

        resourcesLooses[0][3] =
                resourcesLooses[0][2]
                + 5 * resourcesLooses[0][1]
                + 10 * resourcesLooses[0][0];

        // Enemy losses
        resourcesLooses[1][0] = initialCostFleet[1][0] - eneActual[0];
        resourcesLooses[1][1] = initialCostFleet[1][1] - eneActual[1];
        resourcesLooses[1][2] = initialCostFleet[1][2] - eneActual[2];

        resourcesLooses[1][3] =
                resourcesLooses[1][2]
                + 5 * resourcesLooses[1][1]
                + 10 * resourcesLooses[1][0];
    }
    
    private int getGroupDefender(ArrayList<MilitaryUnit> army) {

        int[] groups = initialFleetNumber(army);

        int totalUnits = 0;

        for(int amount : groups) {
            totalUnits += amount;
        }

        int random = (int)(Math.random() * totalUnits);

        int accumulated = 0;

        for(int i = 0; i < groups.length; i++) {

            accumulated += groups[i];

            if(random < accumulated) {
                return i;
            }
        }

        return -1;
    }
    
    private MilitaryUnit getRandomUnitByGroup(
            ArrayList<MilitaryUnit> army,
            int group) {

        ArrayList<MilitaryUnit> units = new ArrayList<>();

        for(MilitaryUnit unit : army) {

            if(getUnitIndex(unit) == group) {
                units.add(unit);
            }
        }

        if(units.isEmpty()) {
            return null;
        }

        int random = (int)(Math.random() * units.size());

        return units.get(random);
    }
}