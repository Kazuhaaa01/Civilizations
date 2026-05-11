package civilizations;

import java.util.ArrayList;

public class Battle implements Variables {

    private ArrayList<MilitaryUnit> civilizationArmy;
    private ArrayList<MilitaryUnit> enemyArmy;

    private String battleDevelopment;
    
    private int initialNumberUnitsCivilization;
    private int initialNumberUnitsEnemy;

    // [wood, iron]
    private int[] wasteWoodIron;

    /*
        initialArmies[0] -> civilization
        initialArmies[1] -> enemy

        positions:
        0 swordsman
        1 spearman
        2 crossbow
        3 cannon
        4 arrow tower
        5 catapult
        6 rocket launcher tower
        7 magician
        8 priest
     */
    private int[][] initialArmies;

    /*
        [food, wood, iron]
     */
    private int[][] initialCostFleet;

    /*
        [foodLoss, woodLoss, ironLoss, weightedLoss]
     */
    private int[][] resourcesLosses;

    public Battle(ArrayList<MilitaryUnit> civilizationArmy,ArrayList<MilitaryUnit> enemyArmy) {

        this.civilizationArmy = civilizationArmy;
        this.enemyArmy = enemyArmy;

        battleDevelopment = "";

        wasteWoodIron = new int[2];

        initialArmies = new int[2][9];

        initialCostFleet = new int[2][3];

        resourcesLosses = new int[2][4];

        initialNumberUnitsCivilization = civilizationArmy.size();
        initialNumberUnitsEnemy = enemyArmy.size();

        initInitialArmies();

        initialCostFleet[0] = fleetResourceCost(civilizationArmy);
        initialCostFleet[1] = fleetResourceCost(enemyArmy);
    }

    /*
        START BATTLE
     */
    public void startBattle() {

        boolean civilizationTurn = Math.random() < 0.5;

        battleDevelopment += "################ BATTLE START ################\n\n";

        while (!battleFinished()) {

            battleDevelopment += "**************** CHANGE ATTACKER ****************\n";

            if (civilizationTurn) {

                attack(civilizationArmy, enemyArmy, true);

            } else {

                attack(enemyArmy, civilizationArmy, false);
            }

            civilizationTurn = !civilizationTurn;
        }

        updateResourcesLosses();

        battleDevelopment += "\n################ BATTLE END ################\n";
    }

    /*
        MAIN ATTACK LOGIC
     */
    /*
    MAIN ATTACK LOGIC
 */
private void attack(ArrayList<MilitaryUnit> attackers, ArrayList<MilitaryUnit> defenders, boolean civilizationAttack) {
	
    if (attackers.isEmpty() || defenders.isEmpty()) {
        return;
    }

    // 1. ELEGIR ATACANTE
    // Seguimos tirando los dados hasta que elija un grupo que realmente tengamos vivo
    MilitaryUnit attacker = null;
    while (attacker == null) {
        int attackerGroup;
        if (civilizationAttack) {
            attackerGroup = getGroupAttacker(CHANCE_ATTACK_CIVILIZATION_UNITS);
        } else {
            attackerGroup = getGroupAttacker(CHANCE_ATTACK_ENEMY_UNITS);
        }
        attacker = getRandomUnitByGroup(attackers, attackerGroup);
    }

    // 2. ELEGIR DEFENSOR
    int defenderGroup = getGroupDefender(defenders);
    MilitaryUnit defender = getRandomUnitByGroup(defenders, defenderGroup);

    if (defender == null) {
        return;
    }

    // 3. DAÑO
    int damage = attacker.attack();
    defender.takeDamage(damage);

    /*
        LOGS CON TEXTOS EXACTOS DEL PDF
     */
    if (civilizationAttack) {
        battleDevelopment += "Attacks Civilization: ";
    } else {
        battleDevelopment += "Attacks army enemy : ";
    }
    
    battleDevelopment += attacker.getClass().getSimpleName() + " attacks " + defender.getClass().getSimpleName() + "\n";
    battleDevelopment += attacker.getClass().getSimpleName() + " generates the damage = " + damage + "\n";
    battleDevelopment += defender.getClass().getSimpleName() + " stays with armor = " + defender.getActualArmor() + "\n";

    /*
        UNIT DEAD
    */
    if (defender.getActualArmor() <= 0) {
        defenders.remove(defender);
        // "we eliminate" en minúscula como pide el PDF
        battleDevelopment += "we eliminate " + defender.getClass().getSimpleName() + "\n";
        generateWaste(defender);
    }

    /*
        ATTACK AGAIN
     */
    if (!defenders.isEmpty()) { // Solo tiramos dado si quedan enemigos
        int random = (int) (Math.random() * 100);

        if (random < attacker.getChanceAttackAgain()) {
            battleDevelopment += "The army gets an extra attack!\n";
            attack(attackers, defenders, civilizationAttack);
        }
    }
}

    /*
    BATTLE FINISHED?
 */
    private boolean battleFinished() {

    // Si algún bando empieza con 0 tropas, la batalla termina al instante
    if (initialNumberUnitsCivilization == 0 || initialNumberUnitsEnemy == 0) {
        return true; 
    }

    int civilizationPercentage = civilizationArmy.size() * 100 / initialNumberUnitsCivilization;

    int enemyPercentage = enemyArmy.size() * 100 / initialNumberUnitsEnemy;

    return civilizationPercentage <= 20 || enemyPercentage <= 20;
    }

    /*
        INITIAL ARMIES
     */
    private void initInitialArmies() {

        for (MilitaryUnit unit : civilizationArmy) {

            int index = getUnitIndex(unit);

            if (index != -1) {
                initialArmies[0][index]++;
            }
        }

        for (MilitaryUnit unit : enemyArmy) {

            int index = getUnitIndex(unit);

            if (index != -1) {
                initialArmies[1][index]++;
            }
        }
    }

    /*
        RESOURCE COST OF ARMY
     */
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

    /*
        UPDATE LOSSES
     */
    private void updateResourcesLosses() {

        int[] civilizationActual =
                fleetResourceCost(civilizationArmy);

        int[] enemyActual =
                fleetResourceCost(enemyArmy);

        /*
            CIVILIZATION LOSSES
         */
        resourcesLosses[0][0] =
                initialCostFleet[0][0]
                        - civilizationActual[0];

        resourcesLosses[0][1] =
                initialCostFleet[0][1]
                        - civilizationActual[1];

        resourcesLosses[0][2] =
                initialCostFleet[0][2]
                        - civilizationActual[2];

        resourcesLosses[0][3] = resourcesLosses[0][2] + resourcesLosses[0][1] / 5 + resourcesLosses[0][0] / 10;

        /*
            ENEMY LOSSES
        */
        resourcesLosses[1][0] = initialCostFleet[1][0] - enemyActual[0];

        resourcesLosses[1][1] = initialCostFleet[1][1] - enemyActual[1];

        resourcesLosses[1][2] = initialCostFleet[1][2] - enemyActual[2];

        resourcesLosses[1][3] = resourcesLosses[1][2] + resourcesLosses[1][1] / 5 + resourcesLosses[1][0] / 10;
    }

    /*
        GENERATE WASTE
     */
    private void generateWaste(MilitaryUnit unit) {

        int random = (int) (Math.random() * 100);

        if (random < unit.getChanceGeneratinWaste()) {

            wasteWoodIron[0] += unit.getWoodCost() * PERCENTAGE_WASTE / 100;

            wasteWoodIron[1] += unit.getIronCost() * PERCENTAGE_WASTE / 100;

            battleDevelopment += "Waste generated!\n";
        }
    }

    /*
        GET GROUP ATTACKER
    */
    
    private int getGroupAttacker(int[] probabilities) {

        int total = 0;

        for (int value : probabilities) {
            total += value;
        }

        int random = (int) (Math.random() * total);

        int accumulated = 0;

        for (int i = 0; i < probabilities.length; i++) {

            accumulated += probabilities[i];

            if (random < accumulated) {
                return i;
            }
        }

        return -1;
    }

    /*
        GET GROUP DEFENDER
    */
    
    private int getGroupDefender(ArrayList<MilitaryUnit> army) {

        int[] groups = countUnitsByType(army);

        int totalUnits = 0;

        for (int amount : groups) {
            totalUnits += amount;
        }

        if (totalUnits == 0) {
            return -1;
        }

        int random = (int) (Math.random() * totalUnits);

        int accumulated = 0;

        for (int i = 0; i < groups.length; i++) {

            accumulated += groups[i];

            if (random < accumulated) {
                return i;
            }
        }

        return -1;
    }

    /*
        COUNT UNITS
     */
    private int[] countUnitsByType(ArrayList<MilitaryUnit> army) {

        int[] counts = new int[9];

        for (MilitaryUnit unit : army) {

            int index = getUnitIndex(unit);

            if (index != -1) {
                counts[index]++;
            }
        }

        return counts;
    }

    /*
        RANDOM UNIT BY GROUP
     */
    private MilitaryUnit getRandomUnitByGroup(
            ArrayList<MilitaryUnit> army,
            int group) {

        ArrayList<MilitaryUnit> units =
                new ArrayList<>();

        for (MilitaryUnit unit : army) {

            if (getUnitIndex(unit) == group) {
                units.add(unit);
            }
        }

        if (units.isEmpty()) {
            return null;
        }

        int random =
                (int) (Math.random() * units.size());

        return units.get(random);
    }

    /*
        UNIT INDEX
     */
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

    /*
        RESET ARMOR
    */
    public void resetArmyArmor() {

        for (MilitaryUnit unit : civilizationArmy) {
            unit.resetArmor();
        }

        for (MilitaryUnit unit : enemyArmy) {
            unit.resetArmor();
        }
    }

    /*
        WINNER
    */
    public String getWinner() {

        if (resourcesLosses[0][3]
                < resourcesLosses[1][3]) {

            return "Civilization";
        }

        return "Enemy";
    }

    /*
        DEVELOPMENT REPORT
     */
    public String getBattleDevelopment() {

        return battleDevelopment;
    }

    /*
        SUMMARY REPORT
     */
    public String getBattleReport(int battleNumber) {

        String report = "";

        report += "BATTLE NUMBER: " + battleNumber + "\n\n";

        report += "################ BATTLE STATISTICS ################\n\n";

        report += "Civilization remaining units: " + civilizationArmy.size() + "\n";

        report += "Enemy remaining units: " + enemyArmy.size() + "\n\n";

        report += "################ LOSSES ################\n\n";

        report += "Civilization Food Losses: " + resourcesLosses[0][0] + "\n";

        report += "Civilization Wood Losses: " + resourcesLosses[0][1] + "\n";

        report += "Civilization Iron Losses: " + resourcesLosses[0][2] + "\n\n";

        report += "Enemy Food Losses: " + resourcesLosses[1][0] + "\n";

        report += "Enemy Wood Losses: " + resourcesLosses[1][1] + "\n";

        report += "Enemy Iron Losses: " + resourcesLosses[1][2] + "\n\n";

        report += "################ WASTE ################\n\n";

        report += "Wood: " + wasteWoodIron[0] + "\n";

        report += "Iron: " + wasteWoodIron[1] + "\n\n";

        report += "Winner: " + getWinner() + "\n";

        return report.toString();
    }
}