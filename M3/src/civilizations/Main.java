package civilizations;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Civilization implements Variables  {
	
	
	// Añade este método en tu clase Main o Civilization
	// Añade este método en tu clase Main (o Civilization)
	// Le pasamos el número de batalla para saber cuánto debemos aumentar sus recursos

    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);

        Civilization civilization = new Civilization();
        
     // ... dentro de tu main ...
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int minutosTranscurridos = 0;
            int numeroBatalla = 1; // Para llevar la cuenta de las batallas
            
            public void run() {
                minutosTranscurridos++;
                
                // 1. Generar recursos cada minuto
                civilization.generarRecursos(); 
                
                // 2. Cada 3 minutos: Crear ejército enemigo y BATALLA
                if (minutosTranscurridos % 3 == 0) {
                    System.out.println("\n¡ATENCIÓN! Un ejército enemigo se aproxima...");
                    

                    ArrayList<MilitaryUnit> enemyArmy = createEnemyArmy(numeroBatalla); 
                    ArrayList<MilitaryUnit> myArmy = civilization.getArmy(); // Tu ejército actual
                    
                    // Instanciar la batalla con tu ejército y el enemigo
                    Battle battle = new Battle(myArmy, enemyArmy);
                    
                    // Iniciar el combate
                    battle.startBattle();
                    
                    // Mostrar los resultados por pantalla
                    System.out.println(battle.getBattleDevelopment());
                    System.out.println(battle.getBattleReport(numeroBatalla));
                    
                    civilization.updateArmy(myArmy);
                    
                    // Aquí deberías añadir una función en tu Civilization que recoja 
                    // la "chatarra" (waste) generada en la batalla y te la sume a tus recursos.
                    // Ejemplo: civilization.addWaste(battle.getWasteWoodIron());
                    
                    // Restaurar la armadura de las tropas que han sobrevivido
                    battle.resetArmyArmor(); 
                    
                    numeroBatalla++;
                }
            }
        };
        
        // Recuerda que para pruebas pusimos 10000 (10 segundos), 
        // pero en versión final debe ser 60000 (1 minuto).
        timer.schedule(task, 0, 10000);
                

        boolean flg = true;

        while (flg) {

            System.out.println("\n=============== MAIN MENU ===============");

            System.out.println("1. Create");

            System.out.println("2. Upgrade Technologies");

            System.out.println("3. View Stats");

            System.out.println("0. Exit");

            System.out.print("\nOption -> ");

            try {

                int option = sc.nextInt();

                /*
                 * CREATE MENU
                 */
                if (option == 1) {

                    boolean createFlg = true;

                    while (createFlg) {

                        System.out.println("\n=============== CREATE MENU ===============");

                        System.out.println("1. Attack Units");

                        System.out.println("2. Defense Units");

                        System.out.println("3. Special Units");

                        System.out.println("4. Buildings");

                        System.out.println("0. Back");

                        System.out.print("\nOption -> ");

                        int createOption = sc.nextInt();

                        /*
                         * ATTACK UNITS
                         */
                        if (createOption == 1) {

                            boolean attackFlg = true;

                            while (attackFlg) {

                                System.out.println("\n=============== ATTACK UNITS ===============");

                                System.out.println("1. Swordsman");

                                System.out.println("2. Spearman");

                                System.out.println("3. Crossbow");

                                System.out.println("4. Cannon");

                                System.out.println("0. Back");

                                System.out.print("\nOption -> ");

                                int attackOption = sc.nextInt();

                                /*
                                 * SWORDSMAN
                                 */
                                if (attackOption == 1) {

                                    System.out.print("\nHow many Swordsman -> ");

                                    int amount = sc.nextInt();

                                    try {

                                        civilization.newSwordsman(amount);

                                        System.out.println("\nSwordsman created!");

                                    } catch (Exception e) {

                                        System.out.println(e.getMessage());
                                    }
                                }

                                /*
                                 * SPEARMAN
                                 */
                                else if (attackOption == 2) {

                                    System.out.print("\nHow many Spearman -> ");

                                    int amount = sc.nextInt();

                                    try {

                                        civilization.newSpearman(amount);

                                        System.out.println("\nSpearman created!");

                                    } catch (Exception e) {

                                        System.out.println( e.getMessage());
                                    }
                                }

                                /*
                                 * CROSSBOW
                                 */
                                else if (attackOption == 3) {

                                    System.out.print("\nHow many Crossbow -> ");

                                    int amount = sc.nextInt();

                                    try {

                                        civilization.newCrossbow(amount);

                                        System.out.println("\nCrossbow created!");

                                    } catch (Exception e) {

                                        System.out.println(e.getMessage());
                                    }
                                }

                                /*
                                 * CANNON
                                 */
                                else if (attackOption == 4) {

                                    System.out.print("\nHow many Cannon -> ");

                                    int amount = sc.nextInt();

                                    try {

                                        civilization.newCannon(amount);

                                        System.out.println("\nCannon created!");

                                    } catch (Exception e) {

                                        System.out.println(e.getMessage());
                                    }
                                }

                                else if (attackOption == 0) {

                                    attackFlg = false;
                                }

                                else {

                                    System.out.println("\nInvalid option");
                                }
                            }
                        }

                        /*
                         * DEFENSE UNITS
                         */
                        else if (createOption == 2) {

                            boolean defenseFlg = true;

                            while (defenseFlg) {

                                System.out.println("\n=============== DEFENSE UNITS ===============");

                                System.out.println("1. Arrow Tower");

                                System.out.println("2. Catapult");

                                System.out.println("3. Rocket Launcher");

                                System.out.println("0. Back");

                                System.out.print("\nOption -> ");

                                int defenseOption = sc.nextInt();

                                /*
                                 * ARROW TOWER
                                 */
                                if (defenseOption == 1) {

                                    System.out.print("\nHow many Arrow Towers -> ");

                                    int amount = sc.nextInt();

                                    try {

                                        civilization.newArrowTower(amount);

                                        System.out.println("\nArrow Towers created!");

                                    } catch (Exception e) {

                                        System.out.println(e.getMessage());
                                    }
                                }

                                /*
                                 * CATAPULT
                                 */
                                else if (defenseOption == 2) {

                                    System.out.print("\nHow many Catapults -> ");

                                    int amount = sc.nextInt();

                                    try {

                                        civilization.newCatapult(amount);

                                        System.out.println("\nCatapults created!");

                                    } catch (Exception e) {

                                        System.out.println(e.getMessage());
                                    }
                                }

                                /*
                                 * ROCKET LAUNCHER
                                 */
                                else if (defenseOption == 3) {

                                    System.out.print("\nHow many Rocket Launchers -> ");

                                    int amount = sc.nextInt();

                                    try {

                                        civilization.newRocketLauncher(amount);

                                        System.out.println("\nRocket Launchers created!");

                                    } catch (Exception e) {

                                        System.out.println(e.getMessage());
                                    }
                                }

                                else if (defenseOption == 0) {

                                    defenseFlg = false;
                                }

                                else {

                                    System.out.println("\nInvalid option");
                                }
                            }
                        }

                        /*
                         * SPECIAL UNITS
                         */
                        else if (createOption == 3) {

                            boolean specialFlg = true;

                            while (specialFlg) {

                                System.out.println("\n=============== SPECIAL UNITS ===============");

                                System.out.println("1. Magician");

                                System.out.println("2. Priest");

                                System.out.println("0. Back");

                                System.out.print("\nOption -> ");

                                int specialOption = sc.nextInt();

                                /*
                                 * MAGICIAN
                                 */
                                if (specialOption == 1) {

                                    System.out.print("\nHow many Magicians -> ");

                                    int amount = sc.nextInt();

                                    try {

                                        civilization.newMagician(amount);

                                        System.out.println("\nMagicians created!");

                                    } catch (Exception e) {

                                        System.out.println(e.getMessage());
                                    }
                                }

                                /*
                                 * PRIEST
                                 */
                                else if (specialOption == 2) {

                                    System.out.print("\nHow many Priests -> ");

                                    int amount = sc.nextInt();

                                    try {

                                        civilization.newPriest(amount);

                                        System.out.println("\nPriests created!");

                                    } catch (Exception e) {

                                        System.out.println(e.getMessage());
                                    }
                                }

                                else if (specialOption == 0) {

                                    specialFlg = false;
                                }

                                else {

                                    System.out.println("\nInvalid option");
                                }
                            }
                        }

                        /*
                         * BUILDINGS
                         */
                        else if (createOption == 4) {

                            boolean buildingsFlg = true;

                            while (buildingsFlg) {

                                System.out.println("\n=============== BUILDINGS ===============");

                                System.out.println("1. Farm");

                                System.out.println("2. Carpentry");

                                System.out.println("3. Smithy");

                                System.out.println("4. Magic Tower");

                                System.out.println("5. Church");

                                System.out.println("0. Back");

                                System.out.print("\nOption -> ");

                                int buildingOption = sc.nextInt();

                                if (buildingOption == 1) {

                                    try {

                                        civilization.newFarm();

                                        System.out.println("\nFarm created!");

                                    } catch (Exception e) {

                                        System.out.println(e.getMessage());
                                    }
                                }

                                else if (buildingOption == 2) {

                                    try {

                                        civilization.newCarpentry();

                                        System.out.println("\nCarpentry created!");

                                    } catch (Exception e) {

                                        System.out.println(e.getMessage());
                                    }
                                }

                                else if (buildingOption == 3) {

                                    try {

                                        civilization.newSmithy();

                                        System.out.println("\nSmithy created!");

                                    } catch (Exception e) {

                                        System.out.println(e.getMessage());
                                    }
                                }

                                else if (buildingOption == 4) {

                                    try {

                                        civilization.newMagicTower();

                                        System.out.println("\nMagic Tower created!");

                                    } catch (Exception e) {

                                        System.out.println(e.getMessage());
                                    }
                                }

                                else if (buildingOption == 5) {

                                    try {

                                        civilization.newChurch();

                                        System.out.println("\nChurch created!");

                                    } catch (Exception e) {

                                        System.out.println(e.getMessage());
                                    }
                                }

                                else if (buildingOption == 0) {

                                    buildingsFlg = false;
                                }

                                else {

                                    System.out.println("\nInvalid option");
                                }
                            }
                        }

                        else if (createOption == 0) {

                            createFlg = false;
                        }

                        else {

                            System.out.println("\nInvalid option");
                        }
                    }
                }

                /*
                 * UPGRADE
                 */
                else if (option == 2) {

                    boolean upgradeFlg = true;

                    while (upgradeFlg) {

                        System.out.println("\n=============== UPGRADE MENU ===============");

                        System.out.println("1. Upgrade Attack");

                        System.out.println("2. Upgrade Defense");

                        System.out.println("0. Back");

                        System.out.print("\nOption -> ");

                        int upgradeOption = sc.nextInt();

                        if (upgradeOption == 1) {

                            try {

                                civilization.upgradeTechnologyAttack();

                                System.out.println("\nAttack upgraded!");

                            } catch (Exception e) {

                                System.out.println(e.getMessage());
                            }
                        }

                        else if (upgradeOption == 2) {

                            try {

                                civilization.upgradeTechnologyDefense();

                                System.out.println("\nDefense upgraded!");

                            } catch (Exception e) {

                                System.out.println(e.getMessage());
                            }
                        }

                        else if (upgradeOption == 0) {

                            upgradeFlg = false;
                        }

                        else {

                            System.out.println("\nInvalid option");
                        }
                    }
                }

                /*
                 * STATS
                 */
                else if (option == 3) {

                    civilization.printStats();
                }

                /*
                 * EXIT
                 */
                else if (option == 0) {

                    flg = false;

                    System.out.println("\nClosing game...");
                }

                else {

                    System.out.println("\nInvalid option");
                }

            } catch (InputMismatchException e) {

                System.out.println("\nERROR: introduce numbers");

                sc.nextLine();
            }
        }

        sc.close();
           
        
    }
    
	public static ArrayList<MilitaryUnit> createEnemyArmy(int numeroBatalla) {
	    ArrayList<MilitaryUnit> enemyArmy = new ArrayList<>();
	    
	    int incrementoPorcentaje = numeroBatalla * ENEMY_FLEET_INCREASE;
	    
	    int enemyWood = WOOD_BASE_ENEMY_ARMY + (WOOD_BASE_ENEMY_ARMY * incrementoPorcentaje / 100);
	    int enemyIron = IRON_BASE_ENEMY_ARMY + (IRON_BASE_ENEMY_ARMY * incrementoPorcentaje / 100);
	    int enemyFood = FOOD_BASE_ENEMY_ARMY + (FOOD_BASE_ENEMY_ARMY * incrementoPorcentaje / 100);

	    while (enemyWood >= WOOD_COST_SWORDSMAN && enemyIron >= IRON_COST_SWORDSMAN && enemyFood >= FOOD_COST_SWORDSMAN) {
	        
	        int random = (int) (Math.random() * 100) + 1;
	        
	        if (random <= 35) {
	            // 35% de probabilidad: Intentar crear Swordsman
	            if (enemyWood >= WOOD_COST_SWORDSMAN && enemyIron >= IRON_COST_SWORDSMAN && enemyFood >= FOOD_COST_SWORDSMAN) {
	                enemyArmy.add(new Swordsman()); 
	                enemyWood -= WOOD_COST_SWORDSMAN;
	                enemyIron -= IRON_COST_SWORDSMAN;
	                enemyFood -= FOOD_COST_SWORDSMAN;
	            }
	        } else if (random <= 60) {
	            // 25% de probabilidad (35 + 25 = 60): Intentar crear Spearman
	            if (enemyWood >= WOOD_COST_SPEARMAN && enemyIron >= IRON_COST_SPEARMAN && enemyFood >= FOOD_COST_SPEARMAN) {
	                enemyArmy.add(new Spearman());
	                enemyWood -= WOOD_COST_SPEARMAN;
	                enemyIron -= IRON_COST_SPEARMAN;
	                enemyFood -= FOOD_COST_SPEARMAN;
	            }
	        } else if (random <= 80) {
	            // 20% de probabilidad (60 + 20 = 80): Intentar crear Crossbow
	            if (enemyWood >= WOOD_COST_CROSSBOW && enemyIron >= IRON_COST_CROSSBOW && enemyFood >= FOOD_COST_CROSSBOW) {
	                enemyArmy.add(new Crossbow());
	                enemyWood -= WOOD_COST_CROSSBOW;
	                enemyIron -= IRON_COST_CROSSBOW;
	                enemyFood -= FOOD_COST_CROSSBOW;
	            }
	        } else {
	            // 20% de probabilidad restante: Intentar crear Cannon
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