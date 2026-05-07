package civilizations;

public abstract class SpecialUnit implements MilitaryUnit, Variables {

	int armor;
	int initialArmor;
	int baseDamage;
	int experience;
	
	
	 public SpecialUnit(int armor, int baseDamage) {
	        this.armor = 0;
	        this.initialArmor = 0;
	        this.baseDamage = baseDamage;
	        this.experience = 0;
	    }
	
}
