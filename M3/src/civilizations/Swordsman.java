package civilizations;

public class Swordsman extends AtackUnity implements Variables {

    public Swordsman(int armor, int baseDamage) {
        super(armor, baseDamage);
        this.initialArmor = armor;
    }

    public Swordsman() {
        super(ARMOR_SWORDSMAN, BASE_DAMAGE_SWORDSMAN);
        this.initialArmor = ARMOR_SWORDSMAN;
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
        return FOOD_COST_SWORDSMAN;
    }

    public int getWoodCost() {
        return WOOD_COST_SWORDSMAN;
    }

    public int getIronCost() {
        return IRON_COST_SWORDSMAN;
    }

    public int getManaCost() {
        return MANA_COST_SWORDSMAN;
    }

    public int getChanceGeneratinWaste() {
        return CHANCE_GENERATNG_WASTE_SWORDSMAN;
    }

    public int getChanceAttackAgain() {
        return CHANCE_ATTACK_AGAIN_SWORDSMAN;
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