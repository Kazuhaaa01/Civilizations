package civilizations;

public class Cannon extends AtackUnity implements Variables {

    public Cannon(int armor, int baseDamage) {
        super(armor, baseDamage);
        this.initialArmor = armor;
    }

    public Cannon() {
        super(ARMOR_CANNON, BASE_DAMAGE_CANNON);
        this.initialArmor = ARMOR_CANNON;
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
        return FOOD_COST_CANNON;
    }

    public int getWoodCost() {
        return WOOD_COST_CANNON;
    }

    public int getIronCost() {
        return IRON_COST_CANNON;
    }

    public int getManaCost() {
        return MANA_COST_CANNON;
    }

    public int getChanceGeneratinWaste() {
        return CHANCE_GENERATNG_WASTE_CANNON;
    }

    public int getChanceAttackAgain() {
        return CHANCE_ATTACK_AGAIN_CANNON;
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
