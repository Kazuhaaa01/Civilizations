const express = require('express');
const fs = require('fs');
const path = require('path');
const hbs = require('hbs');
const MySQL = require('./utilsMySQL');
const app = express();
const port = process.env.PORT || 3000;

// Detectar si estem al Proxmox (si és pm2)
const isProxmox = !!process.env.PM2_HOME;

// Iniciar connexió MySQL amb la teva base de dades real
const db = new MySQL();
db.init({
  host: '192.168.152.207',
  port: 3306,
  user: 'root',
  password: '1234',
  database: 'civilizations'  // ← Connectat al teu esquema real
});

// Static files
app.use(express.static(path.join(__dirname, '../public')));
app.use(express.urlencoded({ extended: true }));

// Disable cache
app.use((req, res, next) => {
  res.setHeader('Cache-Control', 'no-store, no-cache, must-revalidate, proxy-revalidate');
  res.setHeader('Pragma', 'no-cache');
  res.setHeader('Expires', '0');
  res.setHeader('Surrogate-Control', 'no-store');
  next();
});

// Handlebars
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'hbs');

// Registrar Helpers HBS comuns
hbs.registerHelper('eq', (a, b) => a == b);
hbs.registerHelper('gt', (a, b) => parseInt(a) > parseInt(b));
hbs.registerHelper('lt', (a, b) => parseInt(a) < parseInt(b));

// Helper auxiliar per gestionar el menú actiu de la teva plantilla
function getMenuPage(activePage) {
  const pages = { inicio: false, batalles: false, civilitzacio: false, programadors: false };
  if (activePage) pages[activePage] = true;
  return pages;
}

// ==================== 1. PÀGINA PRINCIPAL (INICI) ====================
app.get('/', async (req, res) => {
  try {
    // Queries apuntant a les teves taules: 'battle_report' i 'Civilization_stats'
    const [countBatalles, dadesCiv, ultimes2] = await Promise.all([
      db.query(`SELECT COUNT(*) as total FROM battle_report`),
      db.query(`SELECT attack_tech, defense_tech FROM Civilization_stats LIMIT 1`), 
      db.query(`SELECT id_batalla, nom_enemic, guanyada, baixes_enemigues, residus FROM battle_report ORDER BY id_batalla DESC LIMIT 2`)
    ]);

    const total = countBatalles[0]?.total || 0;
    const civ = dadesCiv[0] || { attack_tech: 0, defense_tech: 0 };

    res.render('index', {
      page: getMenuPage('inicio'),
      total_batalles: total,
      tecnologia_atac: civ.attack_tech,      // Revisa si a la taula es diu attack_tech
      tecnologia_defensa: civ.defense_tech,  // Revisa si a la taula es diu defense_tech
      ultimes_batalles: db.table_to_json(ultimes2, {
        id_batalla: 'number', nom_enemic: 'string', guanyada: 'boolean', baixes_enemigues: 'number', residus: 'string'
      })
    });
  } catch (err) {
    console.error("🔥 Error a l'Inici:", err);
    res.status(500).send('Error al Dashboard Principal');
  }
});

// ==================== 2. HISTORIAL DE BATALLES ====================
app.get('/batalles', async (req, res) => {
  try {
    const [countBatalles, llistat] = await Promise.all([
      db.query(`SELECT COUNT(*) as total FROM battle_report`),
      db.query(`SELECT id_batalla, DATE_FORMAT(data_batalla, '%d/%m/%Y %H:%i') as data_batalla, nom_enemic, guanyada, baixes_propies, baixes_enemigues FROM battle_report ORDER BY id_batalla DESC`)
    ]);

    res.render('batalles', {
      page: getMenuPage('batalles'),
      total_batalles: countBatalles[0]?.total || 0,
      llistat_batalles: db.table_to_json(llistat, {
        id_batalla: 'number', data_batalla: 'string', nom_enemic: 'string', guanyada: 'boolean', baixes_propies: 'number', baixes_enemigues: 'number'
      })
    });
  } catch (err) {
    console.error("🔥 Error a /batalles:", err);
    res.status(500).send('Error carregant llistat de batalles');
  }
});

// ==================== 3. INFORMES DE BATALLA (?informe=id) ====================
app.get('/informe', async (req, res) => {
  try {
    const idBatalla = parseInt(req.query.informe, 10);
    if (!idBatalla || Number.isNaN(idBatalla)) {
      return res.status(400).send('ID de batalla requerit.');
    }

    // Consulta directa a la teva taula 'battle_report'
    const resultat = await db.query(`SELECT id_batalla, DATE_FORMAT(data_batalla, '%d/%m/%Y %H:%i') as data_batalla, nom_enemic, guanyada, baixes_propies, baixes_enemigues, acer_recuperat, fusta_recuperada, or_recuperat, desenvolupament_text FROM battle_report WHERE id_batalla = ${idBatalla} LIMIT 1`);
    
    if (!resultat.length) {
      return res.status(404).send('L’informe sol·licitat no existeix.');
    }

    res.render('informe', {
      page: getMenuPage('batalles'), 
      informe: db.table_to_json(resultat, {
        id_batalla: 'number', data_batalla: 'string', nom_enemic: 'string', guanyada: 'boolean', baixes_propies: 'number', baixes_enemigues: 'number', acer_recuperat: 'number', fusta_recuperada: 'number', or_recuperat: 'number', desenvolupament_text: 'string'
      })[0]
    });
  } catch (err) {
    console.error("🔥 Error a /informe:", err);
    res.status(500).send('Error carregant el detall de l’informe');
  }
});

// ==================== 4. ESTAT DE LA CIVILITZACIÓ ====================
app.get('/civilitzacio', async (req, res) => {
  try {
    // Consulta directa a la teva taula 'Civilization_stats'
    const dadesCiv = await db.query(`SELECT acer, fusta, or FROM Civilization_stats LIMIT 1`);
    const c = dadesCiv[0] || { acer: 0, fusta: 0, or: 0 };

    const recursosMapejats = {
      "Acer": c.acer,
      "Fusta": c.fusta,
      "Or": c.or
    };

    res.render('civilitzacio', {
      page: getMenuPage('civilitzacio'),
      recursos: recursosMapejats
    });
  } catch (err) {
    console.error("🔥 Error a /civilitzacio:", err);
    res.status(500).send('Error a l’estat de la civilització');
  }
});

// ==================== 5. PROGRAMADORS ====================
app.get('/programadors', (req, res) => {
  res.render('programadors', {
    page: getMenuPage('programadors')
  });
});

// Inicialització del Servidor HTTP
const httpServer = app.listen(port, () => {
  console.log(`🚀 Servidor de Civilizations actiu a: http://localhost:${port}`);
});

// Tancament net de connexions (Graceful shutdown)
process.on('SIGINT', async () => {
  await db.end();
  httpServer.close();
  process.exit(0);
});