package civilizations;

public class Spearman extends AtackUnity implements Variables {

    public Spearman(int armor, int baseDamage) {
        super(armor, baseDamage);
        this.initialArmor = armor;
    }

    public Spearman() {
        super(ARMOR_SPEARMAN, BASE_DAMAGE_SPEARMAN);
        this.initialArmor = ARMOR_SPEARMAN;
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
        return FOOD_COST_SPEARMAN;
    }

    public int getWoodCost() {
        return WOOD_COST_SPEARMAN;
    }

    public int getIronCost() {
        return IRON_COST_SPEARMAN;
    }

    public int getManaCost() {
        return MANA_COST_SPEARMAN;
    }

    public int getChanceGeneratinWaste() {
        return CHANCE_GENERATNG_WASTE_SPEARMAN;
    }

    public int getChanceAttackAgain() {
        return CHANCE_ATTACK_AGAIN_SPEARMAN;
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
