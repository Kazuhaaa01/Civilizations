# Mòdul 3 (M3)

## Descripcio

Aquest modul conté la versio jugable de Civilizations feta amb Java Swing. Aqui es concentra la pantalla principal, la gestio de recursos, la creacio i millora d'unitats, les estadistiques de la civilitzacio i el sistema de batalles amb animacio i informe final.

## Contingut

- `Main.java`: punt d'entrada del modul. Inicialitza la civilitzacio, genera recursos i activa les batalles periòdiques.
- `MainMenuGUI.java`: finestra principal amb el mapa, els botons de navegacio, els recursos i el report de batalla.
- `Battle.java`: logica del combat entre dos exercits, amb registre de desenvolupament i resum final.
- `BattleAnimPanel.java`: animacio visual de la batalla sobre el mapa.
- `Civilization.java`: estat general de la civilitzacio, exercit, recursos i gestio de supervivents.
- `CreatePanel.java`, `UpgradePanel.java`, `StatsPanel.java`: pantalles per crear unitats, millorar tecnologies i veure estadistiques.
- `MilitaryUnit.java` i les classes derivades: definicio de les unitats d'atac, defensa i especials.
- `Variables.java`: constants de recursos, costos i valors base.
- `ConexionDB.java`: prova de connexio a MySQL.
- `mapa.png`: imatge que es mostra al tauler principal.

## Requisits

- Java 8 o superior.
- Entorn de desenvolupament amb suport per a projectes Java, com Eclipse o IntelliJ IDEA.
- Si es vol executar `ConexionDB.java`, cal tenir el connector JDBC de MySQL configurat.

## Instal·lacio i execucio

1. Obre la carpeta `M3` com a projecte Java.
2. Assegura't que `src` estigui afegit com a carpeta de codi font.
3. Executa la classe `civilizations.Main`.
4. Si el mapa no es carrega, comprova que `mapa.png` continua a `src/civilizations/`.

## Notes

- El joc mostra una batalla cada 3 minuts de temps real.
- El report de batalla es guarda a la interfície i també es mostra per consola.
- `ConexionDB.java` es manté com a prova independent de connexió i no forma part del flux principal del joc.
