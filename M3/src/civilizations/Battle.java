package civilizations;

import java.util.ArrayList;

public class Battle {

	ArrayList<MilitaryUnit> civilizationArmy; // para almacenar nuestro ejército
	ArrayList<MilitaryUnit> enemyArmy; // para almacenar el ejército enemigo
	ArrayList<ArrayList<MilitaryUnit>> armies; // array de ArrayLists de dos filas y nueve columnas, donde almacenaremos nuestro ejército en la primera fila, y el ejército enemigo en la segunda fila
	String battleDevelopment; // donde guardamos todo el desarrollo de la batalla paso a paso
	int initialCostFleet; // coste de comida, madera y hierro de los ejércitos iniciales
	int initialNumberUnitsCivilization; // cantidad de unidades iniciales de nuestra civilización
	int initialNumberUnitsEnemy; // cantidad de unidades iniciales del ejército enemigo
	int wasteWoodIron; // residuos generados en la batalla, madera e hierro
	int enemyDrops; // necesarios para generar reporte de batalla y para calcular las pérdidas materiales del ejército enemigo
	int civilizationDrops; // necesarios para generar reporte de batalla y para calcular las pérdidas materiales de nuestra civilización
	int[][] resourcesLooses; // array de dos filas y cuatro columnas, pérdidas de comida, madera y hierro de cada ejército
	int[][] initialArmies; // array de dos filas y nueve columnas para cuantificar cada tipo de unidad de los ejércitos iniciales
	int actualNumberUnitsCivilization; // arrays que cuantifican las unidades actuales de cada grupo, tanto para nuestra civilización
	int actualNumberUnitsEnemy; // arrays que cuantifican las unidades actuales de cada grupo, tanto para el enemigo
	
}
