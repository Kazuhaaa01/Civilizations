package civilizations;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.SwingUtilities;

public class Main extends Civilization implements Variables {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Civilization civilization = new Civilization();
        civilization.generarRecursos();

        final MainMenuGUI[] menuRef = new MainMenuGUI[1];

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int minutosTranscurridos = 0;
            int numeroBatalla = 1;

            public void run() {
                minutosTranscurridos++;

                civilization.generarRecursos();

                if (minutosTranscurridos % 3 == 0) {
                    System.out.println("\n¡ATENCIÓN! Un ejército enemigo se aproxima...");

                    ArrayList<MilitaryUnit> enemyArmy = createEnemyArmy(numeroBatalla);
                    ArrayList<MilitaryUnit> myArmy = civilization.getArmy();

                    int aliadosInicio = myArmy.size();
                    int enemigosInicio = enemyArmy.size();

                    Battle battle = new Battle(myArmy, enemyArmy);
                    battle.startBattle();

                    int aliadosFin = myArmy.size();
                    int enemigosFin = enemyArmy.size();

                    // Juntamos la historia del combate con el resumen final.
                    String development = battle.getBattleDevelopment();
                    String summary = battle.getBattleReport(numeroBatalla);

                    // Se imprime tambien en consola para seguir el combate paso a paso.
                    System.out.println(development);
                    System.out.println(summary);

                    // Este texto es el que luego se muestra en la interfaz.
                    String report = development + "\n" + summary;

                    civilization.updateArmy(myArmy);
                    battle.resetArmyArmor();

                    if (menuRef[0] != null) {
                        SwingUtilities.invokeLater(() -> {
                            menuRef[0].showBattleAnim(
                                    aliadosInicio, enemigosInicio,
                                    aliadosFin, enemigosFin,
                                    report);
                        });
                    }

                    numeroBatalla++;
                }
            }
        };

        timer.schedule(task, 60000, 60000);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                menuRef[0] = new MainMenuGUI(civilization, timer);
                menuRef[0].setVisible(true);
            }
        });

        sc.close();
    }

    public static ArrayList<MilitaryUnit> createEnemyArmy(int numeroBatalla) {
        ArrayList<MilitaryUnit> enemyArmy = new ArrayList<>();

        int incrementoPorcentaje = numeroBatalla * ENEMY_FLEET_INCREASE;

        int enemyWood = WOOD_BASE_ENEMY_ARMY + (WOOD_BASE_ENEMY_ARMY * incrementoPorcentaje / 100);
        int enemyIron = IRON_BASE_ENEMY_ARMY + (IRON_BASE_ENEMY_ARMY * incrementoPorcentaje / 100);
        int enemyFood = FOOD_BASE_ENEMY_ARMY + (FOOD_BASE_ENEMY_ARMY * incrementoPorcentaje / 100);

        while (enemyWood >= WOOD_COST_SWORDSMAN && enemyIron >= IRON_COST_SWORDSMAN
                && enemyFood >= FOOD_COST_SWORDSMAN) {

            int random = (int) (Math.random() * 100) + 1;

            if (random <= 35) {
                if (enemyWood >= WOOD_COST_SWORDSMAN && enemyIron >= IRON_COST_SWORDSMAN
                        && enemyFood >= FOOD_COST_SWORDSMAN) {
                    enemyArmy.add(new Swordsman());
                    enemyWood -= WOOD_COST_SWORDSMAN;
                    enemyIron -= IRON_COST_SWORDSMAN;
                    enemyFood -= FOOD_COST_SWORDSMAN;
                }
            } else if (random <= 60) {
                if (enemyWood >= WOOD_COST_SPEARMAN && enemyIron >= IRON_COST_SPEARMAN
                        && enemyFood >= FOOD_COST_SPEARMAN) {
                    enemyArmy.add(new Spearman());
                    enemyWood -= WOOD_COST_SPEARMAN;
                    enemyIron -= IRON_COST_SPEARMAN;
                    enemyFood -= FOOD_COST_SPEARMAN;
                }
            } else if (random <= 80) {
                if (enemyWood >= WOOD_COST_CROSSBOW && enemyIron >= IRON_COST_CROSSBOW
                        && enemyFood >= FOOD_COST_CROSSBOW) {
                    enemyArmy.add(new Crossbow());
                    enemyWood -= WOOD_COST_CROSSBOW;
                    enemyIron -= IRON_COST_CROSSBOW;
                    enemyFood -= FOOD_COST_CROSSBOW;
                }
            } else {
                if (enemyWood >= WOOD_COST_CANNON && enemyIron >= IRON_COST_CANNON && enemyFood >= FOOD_COST_CANNON) {
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