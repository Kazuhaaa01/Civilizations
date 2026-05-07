package civilizations;

public class Magician extends SpecialUnit implements Variables{

	public Magician(int armor, int baseDamage) {
		super(armor, baseDamage);
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
        return FOOD_COST_MAGICIAN;
    }

    public int getWoodCost() {
        return WOOD_COST_MAGICIAN;
    }

    public int getIronCost() {
        return IRON_COST_MAGICIAN;
    }

    public int getManaCost() {
        return MANA_COST_MAGICIAN;
    }

    public int getChanceGeneratinWaste() {
        return CHANCE_GENERATNG_WASTE_MAGICIAN;
    }

    public int getChanceAttackAgain() {
        return CHANCE_ATTACK_AGAIN_MAGICIAN;
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
