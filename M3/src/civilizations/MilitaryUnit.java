package civilizations;

public interface MilitaryUnit {

	//Nos devolverá el poder de ataque que tenga la unidad.
    int attack();

    
    //Restará a nuestro blindaje el daño recibido por parámetro.
    void takeDamage(int receivedDamage);

    
    //Nos devolverá el blindaje que tengamos actualmente, después de haber recibido un ataque.
    int getActualArmor();

    
    //Nos devolverá el coste de Comida que tiene crear una nueva unidad.
    int getFoodCost();

    
    //Nos devolverá el coste de Madera que tiene crear una nueva unidad.
    int getWoodCost();

    
    //Nos devolverá el coste de Hierro que tiene crear una nueva unidad.
    int getIronCost();

    
    //Nos devolverá el coste de Mana que tiene crear una nueva unidad.
    int getManaCost();

    
    //Nos la probabilidad de generar residuos al ser totalmente eliminada (blindaje 0 o inferior).
    int getChanceGeneratinWaste();

    
    //Nos la probabilidad de volver a atacar.
    int getChanceAttackAgain();

    
    //Nos restablecerá nuestro blindaje a su valor original.
    void resetArmor();

    
    //Establecerá la experiencia a un nuevo valor.
    void setExperience(int n);

    
    //Nos devolverá la experiencia actual de la unidad.
    int getExperience();
}