package civilizations;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main extends Civilization implements Variables  {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Civilization civilization = new Civilization();

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

                                    System.out.print(
                                            "\nHow many Swordsman -> ");

                                    int amount = sc.nextInt();

                                    try {

                                        civilization.newSwordsman(amount);

                                        System.out.println(
                                                "\nSwordsman created!");

                                    } catch (Exception e) {

                                        System.out.println(
                                                e.getMessage());
                                    }
                                }

                                /*
                                 * SPEARMAN
                                 */
                                else if (attackOption == 2) {

                                    System.out.print(
                                            "\nHow many Spearman -> ");

                                    int amount = sc.nextInt();

                                    try {

                                        civilization.newSpearman(amount);

                                        System.out.println(
                                                "\nSpearman created!");

                                    } catch (Exception e) {

                                        System.out.println(
                                                e.getMessage());
                                    }
                                }

                                /*
                                 * CROSSBOW
                                 */
                                else if (attackOption == 3) {

                                    System.out.print(
                                            "\nHow many Crossbow -> ");

                                    int amount = sc.nextInt();

                                    try {

                                        civilization.newCrossbow(amount);

                                        System.out.println(
                                                "\nCrossbow created!");

                                    } catch (Exception e) {

                                        System.out.println(
                                                e.getMessage());
                                    }
                                }

                                /*
                                 * CANNON
                                 */
                                else if (attackOption == 4) {

                                    System.out.print(
                                            "\nHow many Cannon -> ");

                                    int amount = sc.nextInt();

                                    try {

                                        civilization.newCannon(amount);

                                        System.out.println(
                                                "\nCannon created!");

                                    } catch (Exception e) {

                                        System.out.println(
                                                e.getMessage());
                                    }
                                }

                                else if (attackOption == 0) {

                                    attackFlg = false;
                                }

                                else {

                                    System.out.println(
                                            "\nInvalid option");
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

                                    System.out.print(
                                            "\nHow many Arrow Towers -> ");

                                    int amount = sc.nextInt();

                                    try {

                                        civilization.newArrowTower(amount);

                                        System.out.println(
                                                "\nArrow Towers created!");

                                    } catch (Exception e) {

                                        System.out.println(
                                                e.getMessage());
                                    }
                                }

                                /*
                                 * CATAPULT
                                 */
                                else if (defenseOption == 2) {

                                    System.out.print(
                                            "\nHow many Catapults -> ");

                                    int amount = sc.nextInt();

                                    try {

                                        civilization.newCatapult(amount);

                                        System.out.println(
                                                "\nCatapults created!");

                                    } catch (Exception e) {

                                        System.out.println(
                                                e.getMessage());
                                    }
                                }

                                /*
                                 * ROCKET LAUNCHER
                                 */
                                else if (defenseOption == 3) {

                                    System.out.print(
                                            "\nHow many Rocket Launchers -> ");

                                    int amount = sc.nextInt();

                                    try {

                                        civilization.newRocketLauncher(amount);

                                        System.out.println(
                                                "\nRocket Launchers created!");

                                    } catch (Exception e) {

                                        System.out.println(
                                                e.getMessage());
                                    }
                                }

                                else if (defenseOption == 0) {

                                    defenseFlg = false;
                                }

                                else {

                                    System.out.println(
                                            "\nInvalid option");
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

                                    System.out.print(
                                            "\nHow many Magicians -> ");

                                    int amount = sc.nextInt();

                                    try {

                                        civilization.newMagician(amount);

                                        System.out.println(
                                                "\nMagicians created!");

                                    } catch (Exception e) {

                                        System.out.println(
                                                e.getMessage());
                                    }
                                }

                                /*
                                 * PRIEST
                                 */
                                else if (specialOption == 2) {

                                    System.out.print(
                                            "\nHow many Priests -> ");

                                    int amount = sc.nextInt();

                                    try {

                                        civilization.newPriest(amount);

                                        System.out.println(
                                                "\nPriests created!");

                                    } catch (Exception e) {

                                        System.out.println(
                                                e.getMessage());
                                    }
                                }

                                else if (specialOption == 0) {

                                    specialFlg = false;
                                }

                                else {

                                    System.out.println(
                                            "\nInvalid option");
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

                                int buildingOption =
                                        sc.nextInt();

                                if (buildingOption == 1) {

                                    try {

                                        civilization.newFarm();

                                        System.out.println(
                                                "\nFarm created!");

                                    } catch (Exception e) {

                                        System.out.println(
                                                e.getMessage());
                                    }
                                }

                                else if (buildingOption == 2) {

                                    try {

                                        civilization.newCarpentry();

                                        System.out.println(
                                                "\nCarpentry created!");

                                    } catch (Exception e) {

                                        System.out.println(
                                                e.getMessage());
                                    }
                                }

                                else if (buildingOption == 3) {

                                    try {

                                        civilization.newSmithy();

                                        System.out.println(
                                                "\nSmithy created!");

                                    } catch (Exception e) {

                                        System.out.println(
                                                e.getMessage());
                                    }
                                }

                                else if (buildingOption == 4) {

                                    try {

                                        civilization.newMagicTower();

                                        System.out.println(
                                                "\nMagic Tower created!");

                                    } catch (Exception e) {

                                        System.out.println(
                                                e.getMessage());
                                    }
                                }

                                else if (buildingOption == 5) {

                                    try {

                                        civilization.newChurch();

                                        System.out.println(
                                                "\nChurch created!");

                                    } catch (Exception e) {

                                        System.out.println(
                                                e.getMessage());
                                    }
                                }

                                else if (buildingOption == 0) {

                                    buildingsFlg = false;
                                }

                                else {

                                    System.out.println(
                                            "\nInvalid option");
                                }
                            }
                        }

                        else if (createOption == 0) {

                            createFlg = false;
                        }

                        else {

                            System.out.println(
                                    "\nInvalid option");
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

                                System.out.println(
                                        "\nAttack upgraded!");

                            } catch (Exception e) {

                                System.out.println(
                                        e.getMessage());
                            }
                        }

                        else if (upgradeOption == 2) {

                            try {

                                civilization.upgradeTechnologyDefense();

                                System.out.println(
                                        "\nDefense upgraded!");

                            } catch (Exception e) {

                                System.out.println(
                                        e.getMessage());
                            }
                        }

                        else if (upgradeOption == 0) {

                            upgradeFlg = false;
                        }

                        else {

                            System.out.println(
                                    "\nInvalid option");
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

                    System.out.println(
                            "\nClosing game...");
                }

                else {

                    System.out.println(
                            "\nInvalid option");
                }

            } catch (InputMismatchException e) {

                System.out.println(
                        "\nERROR: introduce numbers");

                sc.nextLine();
            }
        }

        sc.close();
    }
}