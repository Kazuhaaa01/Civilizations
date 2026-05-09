package civilizations;

import java.util.ArrayList;

public class Battle implements Variables {

    private ArrayList<MilitaryUnit> civilizationArmy;
    private ArrayList<MilitaryUnit> enemyArmy;

    private StringBuilder battleDevelopment;

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

    public Battle(ArrayList<MilitaryUnit> civilizationArmy,
                  ArrayList<MilitaryUnit> enemyArmy) {

        this.civilizationArmy = civilizationArmy;
        this.enemyArmy = enemyArmy;

        battleDevelopment = new StringBuilder();

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

        battleDevelopment.append(
                "################ BATTLE START ################\n\n"
        );

        while (!battleFinished()) {

            battleDevelopment.append(
                    "**************** CHANGE ATTACKER ****************\n"
            );

            if (civilizationTurn) {

                attack(civilizationArmy, enemyArmy, true);

            } else {

                attack(enemyArmy, civilizationArmy, false);
            }

            civilizationTurn = !civilizationTurn;
        }

        updateResourcesLosses();

        battleDevelopment.append(
                "\n################ BATTLE END ################\n"
        );
    }

    /*
        MAIN ATTACK LOGIC
     */
    private void attack(ArrayList<MilitaryUnit> attackers,
                        ArrayList<MilitaryUnit> defenders,
                        boolean civilizationAttack) {

        if (attackers.isEmpty() || defenders.isEmpty()) {
            return;
        }

        int attackerGroup;

        if (civilizationAttack) {

            attackerGroup =
                    getGroupAttacker(CHANCE_ATTACK_CIVILIZATION_UNITS);

        } else {

            attackerGroup =
                    getGroupAttacker(CHANCE_ATTACK_ENEMY_UNITS);
        }

        MilitaryUnit attacker =
                getRandomUnitByGroup(attackers, attackerGroup);

        if (attacker == null) {
            return;
        }

        int defenderGroup = getGroupDefender(defenders);

        MilitaryUnit defender =
                getRandomUnitByGroup(defenders, defenderGroup);

        if (defender == null) {
            return;
        }

        int damage = attacker.attack();

        defender.takeDamage(damage);

        /*
            LOGS
         */
        if (civilizationAttack) {

            battleDevelopment.append(
                    "Attacks Civilization Army: "
            );

        } else {

            battleDevelopment.append(
                    "Attacks Enemy Army: "
            );
        }

        battleDevelopment.append(
                attacker.getClass().getSimpleName()
                        + " attacks "
                        + defender.getClass().getSimpleName()
                        + "\n"
        );

        battleDevelopment.append(
                attacker.getClass().getSimpleName()
                        + " generates damage = "
                        + damage
                        + "\n"
        );

        battleDevelopment.append(
                defender.getClass().getSimpleName()
                        + " stays with armor = "
                        + defender.getActualArmor()
                        + "\n"
        );

        /*
            UNIT DEAD
         */
        if (defender.getActualArmor() <= 0) {

            defenders.remove(defender);

            battleDevelopment.append(
                    "We eliminate "
                            + defender.getClass().getSimpleName()
                            + "\n"
            );

            generateWaste(defender);
        }

        /*
            ATTACK AGAIN
         */
        int random = (int) (Math.random() * 100);

        if (random < attacker.getChanceAttackAgain()) {

            battleDevelopment.append(
                    attacker.getClass().getSimpleName()
                            + " attacks again!\n"
            );

            attack(attackers, defenders, civilizationAttack);
        }
    }

    /*
        BATTLE FINISHED?
     */
    private boolean battleFinished() {

        int civilizationPercentage =
                civilizationArmy.size() * 100
                        / initialNumberUnitsCivilization;

        int enemyPercentage =
                enemyArmy.size() * 100
                        / initialNumberUnitsEnemy;

        return civilizationPercentage <= 20
                || enemyPercentage <= 20;
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

        resourcesLosses[0][3] =
                resourcesLosses[0][2]
                        + resourcesLosses[0][1] * 5
                        + resourcesLosses[0][0] * 10;

        /*
            ENEMY LOSSES
         */
        resourcesLosses[1][0] =
                initialCostFleet[1][0]
                        - enemyActual[0];

        resourcesLosses[1][1] =
                initialCostFleet[1][1]
                        - enemyActual[1];

        resourcesLosses[1][2] =
                initialCostFleet[1][2]
                        - enemyActual[2];

        resourcesLosses[1][3] =
                resourcesLosses[1][2]
                        + resourcesLosses[1][1] * 5
                        + resourcesLosses[1][0] * 10;
    }

    /*
        GENERATE WASTE
     */
    private void generateWaste(MilitaryUnit unit) {

        int random = (int) (Math.random() * 100);

        if (random < unit.getChanceGeneratinWaste()) {

            wasteWoodIron[0] +=
                    unit.getWoodCost()
                            * PERCENTAGE_WASTE / 100;

            wasteWoodIron[1] +=
                    unit.getIronCost()
                            * PERCENTAGE_WASTE / 100;

            battleDevelopment.append(
                    "Waste generated!\n"
            );
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

        return battleDevelopment.toString();
    }

    /*
        SUMMARY REPORT
     */
    public String getBattleReport(int battleNumber) {

        StringBuilder report = new StringBuilder();

        report.append(
                "BATTLE NUMBER: "
                        + battleNumber
                        + "\n\n"
        );

        report.append(
                "################ BATTLE STATISTICS ################\n\n"
        );

        report.append(
                "Civilization remaining units: "
                        + civilizationArmy.size()
                        + "\n"
        );

        report.append(
                "Enemy remaining units: "
                        + enemyArmy.size()
                        + "\n\n"
        );

        report.append(
                "################ LOSSES ################\n\n"
        );

        report.append(
                "Civilization Food Losses: "
                        + resourcesLosses[0][0]
                        + "\n"
        );

        report.append(
                "Civilization Wood Losses: "
                        + resourcesLosses[0][1]
                        + "\n"
        );

        report.append(
                "Civilization Iron Losses: "
                        + resourcesLosses[0][2]
                        + "\n\n"
        );

        report.append(
                "Enemy Food Losses: "
                        + resourcesLosses[1][0]
                        + "\n"
        );

        report.append(
                "Enemy Wood Losses: "
                        + resourcesLosses[1][1]
                        + "\n"
        );

        report.append(
                "Enemy Iron Losses: "
                        + resourcesLosses[1][2]
                        + "\n\n"
        );

        report.append(
                "################ WASTE ################\n\n"
        );

        report.append(
                "Wood: "
                        + wasteWoodIron[0]
                        + "\n"
        );

        report.append(
                "Iron: "
                        + wasteWoodIron[1]
                        + "\n\n"
        );

        report.append(
                "Winner: "
                        + getWinner()
                        + "\n"
        );

        return report.toString();
    }
}