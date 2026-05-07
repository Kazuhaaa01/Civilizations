package civilizations;

public abstract class DefenseUnit implements MilitaryUnit, Variables {

	int armor;
	int initialArmor;
	int baseDamage;
	int experience;
	boolean sanctified;
	
	
	 public DefenseUnit(int armor, int baseDamage) {
	        this.armor = armor;
	        this.initialArmor = armor;
	        this.baseDamage = baseDamage;
	        this.experience = 0;
	        this.sanctified = false;
	    }
	
}
