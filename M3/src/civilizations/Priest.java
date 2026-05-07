package civilizations;

public class Priest extends SpecialUnit implements Variables{

	public Priest(int armor, int baseDamage) {
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
        return FOOD_COST_PRIEST;
    }

    public int getWoodCost() {
        return WOOD_COST_PRIEST;
    }

    public int getIronCost() {
        return IRON_COST_PRIEST;
    }

    public int getManaCost() {
        return MANA_COST_PRIEST;
    }

    public int getChanceGeneratinWaste() {
        return CHANCE_GENERATNG_WASTE_PRIEST;
    }

    public int getChanceAttackAgain() {
        return CHANCE_ATTACK_AGAIN_PRIEST;
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
