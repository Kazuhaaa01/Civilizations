package civilizations;

public interface Variables {

    // =========================
    // RECURSOS GENERADOS
    // =========================

    // Hierro que genera la civilización cada cierto tiempo.
    int CIVILIZATION_IRON_GENERATED = 1500;

    // Madera que genera la civilización cada cierto tiempo.
    int CIVILIZATION_WOOD_GENERATED = 5000;

    // Comida que genera la civilización cada cierto tiempo.
    int CIVILIZATION_FOOD_GENERATED = 8000;

    // Maná generado por cada torre mágica.
    int CIVILIZATION_MANA_GENERATED_PER_MAGIC_TOWER = 10;

    // Recursos extra generados por edificios.
    // Según el PDF, son el 50% de la generación base.
    int CIVILIZATION_IRON_GENERATED_PER_SMITHY = (int) (0.5 * CIVILIZATION_IRON_GENERATED);
    int CIVILIZATION_WOOD_GENERATED_PER_CARPENTRY = (int) (0.5 * CIVILIZATION_WOOD_GENERATED);
    int CIVILIZATION_FOOD_GENERATED_PER_FARM = (int) (0.5 * CIVILIZATION_FOOD_GENERATED);

    // =========================
    // COSTE DE TECNOLOGÍAS
    // =========================

    // Coste base de mejorar defensa.
    int UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST = 2000;

    // Coste base de mejorar ataque.
    int UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST = 2000;

    // Incremento extra del coste de defensa por nivel.
    int UPGRADE_PLUS_DEFENSE_TECHNOLOGY_IRON_COST = 60;

    // Incremento extra del coste de ataque por nivel.
    int UPGRADE_PLUS_ATTACK_TECHNOLOGY_IRON_COST = 60;

    // Coste en madera de mejorar defensa base.
    int UPGRADE_BASE_DEFENSE_TECHNOLOGY_WOOD_COST = 0;

    // Coste en madera de mejorar ataque base.
    int UPGRADE_BASE_ATTACK_TECHNOLOGY_WOOD_COST = 0;

    // Incremento extra en madera de la tecnología de defensa.
    int UPGRADE_PLUS_DEFENSE_TECHNOLOGY_WOOD_COST = 0;

    // Incremento extra en madera de la tecnología de ataque.
    int UPGRADE_PLUS_ATTACK_TECHNOLOGY_WOOD_COST = 0;

    // =========================
    // COSTE DE UNIDADES ATAQUE
    // =========================

    // Swordsman.
    int FOOD_COST_SWORDSMAN = 8000;
    int WOOD_COST_SWORDSMAN = 3000;
    int IRON_COST_SWORDSMAN = 50;
    int MANA_COST_SWORDSMAN = 0;

    // Spearman.
    int FOOD_COST_SPEARMAN = 5000;
    int WOOD_COST_SPEARMAN = 6500;
    int IRON_COST_SPEARMAN = 50;
    int MANA_COST_SPEARMAN = 0;

    // Crossbow.
    int FOOD_COST_CROSSBOW = 0;
    int WOOD_COST_CROSSBOW = 45000;
    int IRON_COST_CROSSBOW = 7000;
    int MANA_COST_CROSSBOW = 0;

    // Cannon.
    int FOOD_COST_CANNON = 0;
    int WOOD_COST_CANNON = 30000;
    int IRON_COST_CANNON = 15000;
    int MANA_COST_CANNON = 0;

    // =========================
    // COSTE DE UNIDADES DEFENSA
    // =========================

    // Arrow Tower.
    int FOOD_COST_ARROWTOWER = 0;
    int WOOD_COST_ARROWTOWER = 2000;
    int IRON_COST_ARROWTOWER = 0;
    int MANA_COST_ARROWTOWER = 0;

    // Catapult.
    int FOOD_COST_CATAPULT = 0;
    int WOOD_COST_CATAPULT = 4000;
    int IRON_COST_CATAPULT = 500;
    int MANA_COST_CATAPULT = 0;

    // Rocket Launcher Tower.
    int FOOD_COST_ROCKETLAUNCHERTOWER = 0;
    int WOOD_COST_ROCKETLAUNCHERTOWER = 50000;
    int IRON_COST_ROCKETLAUNCHERTOWER = 5000;
    int MANA_COST_ROCKETLAUNCHERTOWER = 0;

    // =========================
    // COSTE DE UNIDADES ESPECIALES
    // =========================

    // Magician.
    int FOOD_COST_MAGICIAN = 12000;
    int WOOD_COST_MAGICIAN = 2000;
    int IRON_COST_MAGICIAN = 500;
    int MANA_COST_MAGICIAN = 5000;

    // Priest.
    int FOOD_COST_PRIEST = 15000;
    int WOOD_COST_PRIEST = 0;
    int IRON_COST_PRIEST = 0;
    int MANA_COST_PRIEST = 15000;

    // =========================
    // COSTE DE EDIFICIOS
    // =========================

    // Farm.
    int FOOD_COST_FARM = 5000;
    int WOOD_COST_FARM = 10000;
    int IRON_COST_FARM = 12000;

    // Carpentry.
    int FOOD_COST_CARPENTRY = 5000;
    int WOOD_COST_CARPENTRY = 10000;
    int IRON_COST_CARPENTRY = 12000;

    // Smithy.
    int FOOD_COST_SMITHY = 5000;
    int WOOD_COST_SMITHY = 10000;
    int IRON_COST_SMITHY = 12000;

    // Church.
    int FOOD_COST_CHURCH = 5000;
    int WOOD_COST_CHURCH = 10000;
    int IRON_COST_CHURCH = 12000;

    // Magic Tower.
    int FOOD_COST_MAGICTOWER = 5000;
    int WOOD_COST_MAGICTOWER = 10000;
    int IRON_COST_MAGICTOWER = 12000;

    // =========================
    // DAÑO BASE DE UNIDADES ATAQUE
    // =========================

    // Swordsman.
    int BASE_DAMAGE_SWORDSMAN = 80;

    // Spearman.
    int BASE_DAMAGE_SPEARMAN = 150;

    // Crossbow.
    int BASE_DAMAGE_CROSSBOW = 1000;

    // Cannon.
    int BASE_DAMAGE_CANNON = 700;

    // =========================
    // DAÑO BASE DE UNIDADES DEFENSA
    // =========================

    // Arrow Tower.
    int BASE_DAMAGE_ARROWTOWER = 80;

    // Catapult.
    int BASE_DAMAGE_CATAPULT = 250;

    // Rocket Launcher Tower.
    int BASE_DAMAGE_ROCKETLAUNCHERTOWER = 2000;

    // Magician.
    int BASE_DAMAGE_MAGICIAN = 3000;

    // Priest no tiene ataque.
    int BASE_DAMAGE_PRIEST = 0;

    // =========================
    // ARMADURA BASE DE UNIDADES ATAQUE
    // =========================

    // Swordsman.
    int ARMOR_SWORDSMAN = 400;

    // Spearman.
    int ARMOR_SPEARMAN = 1000;

    // Crossbow.
    int ARMOR_CROSSBOW = 6000;

    // Cannon.
    int ARMOR_CANNON = 8000;

    // =========================
    // ARMADURA BASE DE UNIDADES DEFENSA
    // =========================

    // Arrow Tower.
    int ARMOR_ARROWTOWER = 200;

    // Catapult.
    int ARMOR_CATAPULT = 1200;

    // Rocket Launcher Tower.
    int ARMOR_ROCKETLAUNCHERTOWER = 7000;

    // Magician y Priest no tienen armadura real.
    int ARMOR_MAGICIAN = 0;
    int ARMOR_PRIEST = 0;

    // =========================
    // MEJORA POR TECNOLOGÍA
    // =========================

    // Ataque y armadura extra por nivel tecnológico en unidades de ataque.
    int PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY = 5;
    int PLUS_ARMOR_SPEARMAN_BY_TECHNOLOGY = 5;
    int PLUS_ARMOR_CROSSBOW_BY_TECHNOLOGY = 5;
    int PLUS_ARMOR_CANNON_BY_TECHNOLOGY = 5;

    int PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY = 5;
    int PLUS_ATTACK_SPEARMAN_BY_TECHNOLOGY = 5;
    int PLUS_ATTACK_CROSSBOW_BY_TECHNOLOGY = 5;
    int PLUS_ATTACK_CANNON_BY_TECHNOLOGY = 5;

    // Unidades defensivas.
    int PLUS_ARMOR_ARROWTOWER_BY_TECHNOLOGY = 5;
    int PLUS_ARMOR_CATAPULT_BY_TECHNOLOGY = 5;
    int PLUS_ARMOR_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY = 5;

    int PLUS_ATTACK_ARROWTOWER_BY_TECHNOLOGY = 5;
    int PLUS_ATTACK_CATAPULT_BY_TECHNOLOGY = 5;
    int PLUS_ATTACK_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY = 5;

    // Magician.
    int PLUS_ATTACK_MAGICIAN_BY_TECHNOLOGY = 6;

    // =========================
    // MEJORA POR EXPERIENCIA
    // =========================

    // Bonus por cada punto de experiencia.
    int PLUS_ARMOR_UNIT_PER_EXPERIENCE_POINT = 4;
    int PLUS_ATTACK_UNIT_PER_EXPERIENCE_POINT = 4;

    // =========================
    // UNIDADES SANTIFICADAS
    // =========================

    // Mejora al santificar unidades.
    int PLUS_ARMOR_UNIT_SANCTIFIED = 7;
    int PLUS_ATTACK_UNIT_SANCTIFIED = 7;

    // =========================
    // PROBABILIDADES
    // =========================

    // Probabilidad de resurrección del mago.
    int CHANCE_MAGICIAN_RESSURECT = 2;

    // Probabilidad de generar residuos al morir.
    int CHANCE_GENERATNG_WASTE_SWORDSMAN = 55;
    int CHANCE_GENERATNG_WASTE_SPEARMAN = 65;
    int CHANCE_GENERATNG_WASTE_CROSSBOW = 80;
    int CHANCE_GENERATNG_WASTE_CANNON = 90;

    int CHANCE_GENERATNG_WASTE_ARROWTOWER = 55;
    int CHANCE_GENERATNG_WASTE_CATAPULT = 65;
    int CHANCE_GENERATNG_WASTE_ROCKETLAUNCHERTOWER = 75;

    int CHANCE_GENERATNG_WASTE_PRIEST = 0;
    int CHANCE_GENERATNG_WASTE_MAGICIAN = 0;

    // Probabilidad de atacar otra vez.
    int CHANCE_ATTACK_AGAIN_SWORDSMAN = 3;
    int CHANCE_ATTACK_AGAIN_SPEARMAN = 7;
    int CHANCE_ATTACK_AGAIN_CROSSBOW = 45;
    int CHANCE_ATTACK_AGAIN_CANNON = 70;

    int CHANCE_ATTACK_AGAIN_ARROWTOWER = 5;
    int CHANCE_ATTACK_AGAIN_CATAPULT = 12;
    int CHANCE_ATTACK_AGAIN_ROCKETLAUNCHERTOWER = 30;
    int CHANCE_ATTACK_AGAIN_MAGICIAN = 75;
    int CHANCE_ATTACK_AGAIN_PRIEST = 0;

    // Probabilidad de atacar según el grupo.
    // Civilización: Swordsman, Spearman, Crossbow, Cannon, ArrowTower, Catapult, RocketLauncherTower, Magician, Priest
    int[] CHANCE_ATTACK_CIVILIZATION_UNITS = {4, 9, 13, 37, 4, 9, 14, 10, 0};

    // Enemigo: Swordsman, Spearman, Crossbow, Cannon
    int[] CHANCE_ATTACK_ENEMY_UNITS = {10, 20, 30, 40};

    // =========================
    // ENEMIGO
    // =========================

    // Recursos base del primer ejército enemigo.
    int IRON_BASE_ENEMY_ARMY = 26000;
    int WOOD_BASE_ENEMY_ARMY = 180000;
    int FOOD_BASE_ENEMY_ARMY = 70000;

    // Incremento de recursos del enemigo por batalla.
    int ENEMY_FLEET_INCREASE = 6;

    // =========================
    // WASTE / BOTÍN
    // =========================

    // Porcentaje de coste que se recupera como residuo.
    int PERCENTAGE_WASTE = 70;
}