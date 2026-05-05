package civilizations;

public abstract class DefenseUnit implements MilitaryUnit, Variables {

	private int armor;
	private int initialArmor;
	private int baseDamage;
	private int experience;
	private boolean sanctified;
	
	
	 public DefenseUnit(int armor, int baseDamage) {
	        this.armor = armor;
	        this.initialArmor = armor;
	        this.baseDamage = baseDamage;
	        this.experience = 0;
	        this.sanctified = false;
	    }
	
}
