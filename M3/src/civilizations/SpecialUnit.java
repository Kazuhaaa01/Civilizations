package civilizations;

public abstract class SpecialUnit implements MilitaryUnit, Variables {

	private int armor;
	private int initialArmor;
	private int baseDamage;
	private int experience;
	
	
	 public SpecialUnit(int baseDamage) {
	        this.armor = 0;
	        this.initialArmor = 0;
	        this.baseDamage = baseDamage;
	        this.experience = 0;
	    }
	
}
