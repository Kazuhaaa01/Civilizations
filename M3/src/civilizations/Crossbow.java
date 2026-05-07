package civilizations;

public class Crossbow extends AtackUnity implements Variables {
	
    public Crossbow(int armor, int baseDamage) {
        super(armor, baseDamage);
        this.initialArmor = armor;
    }

    public Crossbow() {
        super(ARMOR_CROSSBOW, BASE_DAMAGE_CROSSBOW);
        this.initialArmor = ARMOR_CROSSBOW;
    }

    public int attack() {
        return this.baseDamage + (this.baseDamage * PLUS_ATTACK_UNIT_PER_EXPERIENCE_POINT * this.experience) / 100;
    }

    public void takeDamage(int receivedDamage) {
        this.armor -= receivedDamage;
    }

    public int getActualArmor() {
        return this.armor;
    }

    public int getFoodCost() {
        return FOOD_COST_CROSSBOW;
    }

    public int getWoodCost() {
        return WOOD_COST_CROSSBOW;
    }

    public int getIronCost() {
        return IRON_COST_CROSSBOW;
    }

    public int getManaCost() {
        return MANA_COST_CROSSBOW;
    }

    public int getChanceGeneratinWaste() {
        return CHANCE_GENERATNG_WASTE_CROSSBOW;
    }

    public int getChanceAttackAgain() {
        return CHANCE_ATTACK_AGAIN_CROSSBOW;
    }

    public void resetArmor() {
        this.armor = this.initialArmor;
    }

    public void setExperience(int n) {
        this.experience = n;
    }

    public int getExperience() {
        return this.experience;
    }
}
