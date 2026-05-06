package civilizations;

public abstract class AtackUnity implements MilitaryUnit, Variables {

    int armor;
    int initialArmor;
    int baseDamage;
    int experience;
    boolean sanctified;

    public AtackUnity(int armor, int baseDamage) {
        this.armor = armor;
        this.initialArmor = armor;
        this.baseDamage = baseDamage;
        this.experience = 0;
        this.sanctified = false;
    }

	public int getInitialArmor() {
		return initialArmor;
	}
    
    
}