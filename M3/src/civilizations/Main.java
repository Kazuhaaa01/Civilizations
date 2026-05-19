package civilizations;

import javax.swing.SwingUtilities;
import java.util.ArrayList;

public class Main extends Civilization implements Variables {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            StartMenuGUI menuInicio = new StartMenuGUI();
            menuInicio.setVisible(true);
        });
    }

    public static ArrayList<MilitaryUnit> createEnemyArmy(int numeroBatalla) {

        ArrayList<MilitaryUnit> enemyArmy = new ArrayList<>();

        int incrementoPorcentaje = numeroBatalla * ENEMY_FLEET_INCREASE;

        int enemyWood = WOOD_BASE_ENEMY_ARMY + (WOOD_BASE_ENEMY_ARMY * incrementoPorcentaje / 100);
        int enemyIron = IRON_BASE_ENEMY_ARMY + (IRON_BASE_ENEMY_ARMY * incrementoPorcentaje / 100);
        int enemyFood = FOOD_BASE_ENEMY_ARMY + (FOOD_BASE_ENEMY_ARMY * incrementoPorcentaje / 100);

        while (enemyWood >= WOOD_COST_SWORDSMAN &&
               enemyIron >= IRON_COST_SWORDSMAN &&
               enemyFood >= FOOD_COST_SWORDSMAN) {

            int random = (int) (Math.random() * 100) + 1;

            if (random <= 35) {

                if (enemyWood >= WOOD_COST_SWORDSMAN &&
                    enemyIron >= IRON_COST_SWORDSMAN &&
                    enemyFood >= FOOD_COST_SWORDSMAN) {

                    enemyArmy.add(new Swordsman());

                    enemyWood -= WOOD_COST_SWORDSMAN;
                    enemyIron -= IRON_COST_SWORDSMAN;
                    enemyFood -= FOOD_COST_SWORDSMAN;
                }

            } else if (random <= 60) {

                if (enemyWood >= WOOD_COST_SPEARMAN &&
                    enemyIron >= IRON_COST_SPEARMAN &&
                    enemyFood >= FOOD_COST_SPEARMAN) {

                    enemyArmy.add(new Spearman());

                    enemyWood -= WOOD_COST_SPEARMAN;
                    enemyIron -= IRON_COST_SPEARMAN;
                    enemyFood -= FOOD_COST_SPEARMAN;
                }

            } else if (random <= 80) {

                if (enemyWood >= WOOD_COST_CROSSBOW &&
                    enemyIron >= IRON_COST_CROSSBOW &&
                    enemyFood >= FOOD_COST_CROSSBOW) {

                    enemyArmy.add(new Crossbow());

                    enemyWood -= WOOD_COST_CROSSBOW;
                    enemyIron -= IRON_COST_CROSSBOW;
                    enemyFood -= FOOD_COST_CROSSBOW;
                }

            } else {

                if (enemyWood >= WOOD_COST_CANNON &&
                    enemyIron >= IRON_COST_CANNON &&
                    enemyFood >= FOOD_COST_CANNON) {

                    enemyArmy.add(new Cannon());

                    enemyWood -= WOOD_COST_CANNON;
                    enemyIron -= IRON_COST_CANNON;
                    enemyFood -= FOOD_COST_CANNON;
                }
            }
        }

        return enemyArmy;
    }
}