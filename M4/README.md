# Mòdul 4 (M4)

## Descripció

Aquest mòdul representa la interfície web del projecte Civilizations. Conté un dashboard SPA (Single Page Application) en HTML, CSS i JavaScript per mostrar:

- Navegació entre seccions: Inicio, Juego, Batallas, Civilización i Equipo.
- Historial de batalles amb dades fictícies.
- Estat de la civilització amb recursos carregats des d'un fitxer JSON simulant una API o backend.
- Disseny visual responsiu amb estil modern.

## Contingut

El mòdul inclou els següents fitxers:

- `index.html` : estructura de la pàgina i seccions del dashboard.
- `style.css` : estilos responsius i efectes glassmorphism.
- `main.js` : lògica de navegació SPA i càrrega dinàmica de dades des de `datos_civilizacion.json`.
- `datos_civilizacion.json` : dades de recursos de la civilització.
- `assets/` : carpeta per a recursos addicionals (imatges, icones, etc.).

## Instal·lació i execució

Per executar el mòdul localment, segueix aquests passos:

1. Obre la carpeta `M4` en un navegador web o en un servidor local.
2. Assegura't que `index.html`, `main.js`, `style.css` i `datos_civilizacion.json` estan a la mateixa carpeta.
3. Obre `index.html` amb un navegador modern.

### Executar amb un servidor local (recomanat)

Si vols evitar problemes amb les peticions `fetch` a fitxers locals, utilitza un servidor HTTP senzill:

- Amb Python 3:
  ```bash
  cd c:\Users\User\Documents\GitHub\Civilizations\M4
  python -m http.server 8000
  ```
- Després, obre `http://localhost:8000` al navegador.

Això carregarà la interfície i permetrà la secció de Civilització que llegeix `datos_civilizacion.json` correctament.
