package civilizations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GameDAO {

	private static final String DB_URL = "jdbc:mysql://localhost:3307/civilizations?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true&connectTimeout=3000";
	private static final String DB_USER = "civilization123";
	private static final String DB_PASSWORD = "123";

    private Connection getConnection() throws SQLException {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL no encontrado", e);
        }
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    private void close(Connection conn, PreparedStatement ps) {
        if (ps != null) { try { ps.close(); } catch (SQLException e) { e.printStackTrace(); } }
        if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
    }

    private void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        if (rs != null) { try { rs.close(); } catch (SQLException e) { e.printStackTrace(); } }
        close(conn, ps);
    }

    /**
     * CREAR PARTIDA (M03)
     * Inicializa una nueva civilización con los recursos base y la guarda en el slot.
     */
    public Civilization crearPartida(int saveSlot) {
        // 1. Instanciar la nueva civilización
        Civilization nuevaCiv = new Civilization();
        
        // 2. Configurar los recursos iniciales por defecto
        nuevaCiv.setFood(10000); // Comida inicial
        nuevaCiv.setWood(10000); // Madera inicial
        nuevaCiv.setIron(5000);  // Hierro inicial
        nuevaCiv.setMana(0);     // El maná empieza en 0 por defecto
        
        // 3. Configurar tecnologías y edificios a nivel cero
        nuevaCiv.setTechnologyDefense(0);
        nuevaCiv.setTechnologyAtack(0);
        nuevaCiv.setMagicTower(0);
        nuevaCiv.setChurch(0);
        nuevaCiv.setFarm(0);
        nuevaCiv.setSmithy(0);
        nuevaCiv.setCarpentry(0);
        nuevaCiv.setBattles(0); 
        
        // 4. Inicializar de forma limpia el ejército plano
        ArrayList<MilitaryUnit> army = nuevaCiv.getArmy();
        if (army == null) {
            nuevaCiv.updateArmy(new ArrayList<MilitaryUnit>());
        } else {
            army.clear(); 
        }
        
        // 5. Persistir la nueva partida en la base de datos de forma directa
        boolean exito = this.saveGame(saveSlot, nuevaCiv);
        if (exito) {
            System.out.println(">> ¡Nueva civilización creada con éxito en el Slot " + saveSlot + "!");
            return nuevaCiv;
        } else {
            System.out.println(">> Error: No se pudo registrar la partida en la base de datos.");
            return null;
        }
    }
    
    /**
     * GUARDA LA PARTIDA COMPLETA (M02 - Base de datos)
     * Salva los stats de la civilización y todas las unidades de su ejército plano de forma atómica.
     */
    public boolean saveGame(int saveSlot, Civilization civ) {
        Connection conn = null;
        PreparedStatement psDeleteUnits = null;
        PreparedStatement psCheck = null;
        PreparedStatement psCiv = null;
        PreparedStatement psAttack = null;
        PreparedStatement psDefense = null;
        PreparedStatement psSpecial = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false); // Iniciamos transacción segura

            // 1. Limpiar las unidades previas en este slot para evitar duplicados históricos
            String[] deleteQueries = {
                "DELETE FROM attack_units_stats WHERE civilization_id = ?",
                "DELETE FROM defense_units_stats WHERE civilization_id = ?",
                "DELETE FROM special_units_stats WHERE civilization_id = ?"
            };
            for (String deleteQuery : deleteQueries) {
                psDeleteUnits = conn.prepareStatement(deleteQuery);
                psDeleteUnits.setInt(1, saveSlot);
                psDeleteUnits.executeUpdate();
                psDeleteUnits.close();
            }

            // 2. Insertar o actualizar los stats principales de la civilización (Civilization_stats)
            String sqlCheck = "SELECT civilization_id FROM Civilization_stats WHERE civilization_id = ?";
            psCheck = conn.prepareStatement(sqlCheck);
            psCheck.setInt(1, saveSlot);
            ResultSet rsCheck = psCheck.executeQuery();

            if (rsCheck.next()) {
                // Si ya existe, hacemos UPDATE
                String sqlUpdate = "UPDATE Civilization_stats SET wood_amount = ?, iron_amount = ?, food_amount = ?, "
                        + "mana_amount = ?, magicTower_counter = ?, church_counter = ?, farm_counter = ?, "
                        + "smithy_counter = ?, carpentry_counter = ?, technology_defense_level = ?, "
                        + "technology_attack_level = ?, battles_counter = ? WHERE civilization_id = ?";
                psCiv = conn.prepareStatement(sqlUpdate);
                psCiv.setInt(1, civ.getWood());
                psCiv.setInt(2, civ.getIron());
                psCiv.setInt(3, civ.getFood());
                psCiv.setInt(4, civ.getMana());
                psCiv.setInt(5, civ.getMagicTower());
                psCiv.setInt(6, civ.getChurch());
                psCiv.setInt(7, civ.getFarm());
                psCiv.setInt(8, civ.getSmithy());
                psCiv.setInt(9, civ.getCarpentry());
                psCiv.setInt(10, civ.getTechnologyDefense());
                psCiv.setInt(11, civ.getTechnologyAtack());
                psCiv.setInt(12, civ.getBattles());
                psCiv.setInt(13, saveSlot);
            } else {
                // Si no existe, hacemos INSERT
                String sqlInsert = "INSERT INTO Civilization_stats (civilization_id, wood_amount, iron_amount, food_amount, "
                        + "mana_amount, magicTower_counter, church_counter, farm_counter, smithy_counter, carpentry_counter, "
                        + "technology_defense_level, technology_attack_level, battles_counter) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                psCiv = conn.prepareStatement(sqlInsert);
                psCiv.setInt(1, saveSlot);
                psCiv.setInt(2, civ.getWood());
                psCiv.setInt(3, civ.getIron());
                psCiv.setInt(4, civ.getFood());
                psCiv.setInt(5, civ.getMana());
                psCiv.setInt(6, civ.getMagicTower());
                psCiv.setInt(7, civ.getChurch());
                psCiv.setInt(8, civ.getFarm());
                psCiv.setInt(9, civ.getSmithy());
                psCiv.setInt(10, civ.getCarpentry());
                psCiv.setInt(11, civ.getTechnologyDefense());
                psCiv.setInt(12, civ.getTechnologyAtack());
                psCiv.setInt(13, civ.getBattles());
            }
            psCiv.executeUpdate();

            // 3. Guardar el ejército recorriendo la lista plana dinámicamente
            ArrayList<MilitaryUnit> army = civ.getArmy();
            int unitIdCounter = 1;

            String sqlAttack = "INSERT INTO attack_units_stats (civilization_id, unit_id, type, armor, base_damage, experience, sanctified) VALUES (?, ?, ?, ?, ?, ?, ?)";
            String sqlDefense = "INSERT INTO defense_units_stats (civilization_id, unit_id, type, armor, base_damage, experience, sanctified) VALUES (?, ?, ?, ?, ?, ?, ?)";
            String sqlSpecial = "INSERT INTO special_units_stats (civilization_id, unit_id, type, armor, base_damage, experience) VALUES (?, ?, ?, ?, ?, ?)";

            psAttack = conn.prepareStatement(sqlAttack);
            psDefense = conn.prepareStatement(sqlDefense);
            psSpecial = conn.prepareStatement(sqlSpecial);

            for (MilitaryUnit unit : army) {
                String type = unit.getClass().getSimpleName();

                if (type.equals("Swordsman") || type.equals("Spearman") || type.equals("Crossbow") || type.equals("Cannon")) {
                    psAttack.setInt(1, saveSlot);
                    psAttack.setInt(2, unitIdCounter++);
                    psAttack.setString(3, type);
                    psAttack.setInt(4, unit.getActualArmor());
                    psAttack.setInt(5, unit.attack());
                    psAttack.setInt(6, unit.getExperience());
                    psAttack.setBoolean(7, false); 
                    psAttack.addBatch();
                } else if (type.equals("ArrowTower") || type.equals("Catapult") || type.equals("RocketLauncher") || type.equals("RocketLauncherTower")) {
                    psDefense.setInt(1, saveSlot);
                    psDefense.setInt(2, unitIdCounter++);
                    psDefense.setString(3, type);
                    psDefense.setInt(4, unit.getActualArmor());
                    psDefense.setInt(5, unit.attack());
                    psDefense.setInt(6, unit.getExperience());
                    psDefense.setBoolean(7, false);
                    psDefense.addBatch();
                } else if (type.equals("Magician") || type.equals("Priest")) {
                    psSpecial.setInt(1, saveSlot);
                    psSpecial.setInt(2, unitIdCounter++);
                    psSpecial.setString(3, type);
                    psSpecial.setInt(4, unit.getActualArmor()); 
                    psSpecial.setInt(5, unit.attack());
                    psSpecial.setInt(6, unit.getExperience());
                    psSpecial.addBatch();
                }
            }

            psAttack.executeBatch();
            psDefense.executeBatch();
            psSpecial.executeBatch();

            conn.commit(); 
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            return false;
        } finally {
            close(null, psCheck);
            close(null, psCiv);
            close(null, psAttack);
            close(null, psDefense);
            close(conn, psSpecial);
        }
    }

    /**
     * CARGA LA PARTIDA COMPLETA (M02 - Base de datos)
     * Recupera los recursos y reconstruye el ejército añadiendo directamente a la lista plana.
     */
    public Civilization loadGame(int saveSlot) {
        String sqlCiv = "SELECT * FROM Civilization_stats WHERE civilization_id = ?";
        String sqlAttack = "SELECT * FROM attack_units_stats WHERE civilization_id = ?";
        String sqlDefense = "SELECT * FROM defense_units_stats WHERE civilization_id = ?";
        String sqlSpecial = "SELECT * FROM special_units_stats WHERE civilization_id = ?";

        Connection conn = null;
        PreparedStatement psCiv = null;
        PreparedStatement psAttack = null;
        PreparedStatement psDefense = null;
        PreparedStatement psSpecial = null;
        ResultSet rsCiv = null;
        ResultSet rsAttack = null;
        ResultSet rsDefense = null;
        ResultSet rsSpecial = null;
        
        Civilization civ = null;
        try {
            conn = getConnection();
            
            // 1. Cargar estadísticas principales
            psCiv = conn.prepareStatement(sqlCiv);
            psCiv.setInt(1, saveSlot);
            rsCiv = psCiv.executeQuery();

            if (rsCiv.next()) {
                civ = new Civilization();
                civ.setWood(rsCiv.getInt("wood_amount"));
                civ.setIron(rsCiv.getInt("iron_amount"));
                civ.setFood(rsCiv.getInt("food_amount"));
                civ.setMana(rsCiv.getInt("mana_amount"));
                civ.setMagicTower(rsCiv.getInt("magicTower_counter"));
                civ.setChurch(rsCiv.getInt("church_counter"));
                civ.setFarm(rsCiv.getInt("farm_counter"));
                civ.setSmithy(rsCiv.getInt("smithy_counter"));
                civ.setCarpentry(rsCiv.getInt("carpentry_counter"));
                civ.setTechnologyDefense(rsCiv.getInt("technology_defense_level"));
                civ.setTechnologyAtack(rsCiv.getInt("technology_attack_level"));
                civ.setBattles(rsCiv.getInt("battles_counter"));
                civ.updateArmy(new ArrayList<MilitaryUnit>()); // Inicializa la lista plana en blanco
            } else {
                return null; // Slot vacío
            }

            // 2. Reconstruir Ejército de Ataque
            psAttack = conn.prepareStatement(sqlAttack);
            psAttack.setInt(1, saveSlot);
            rsAttack = psAttack.executeQuery();
            while (rsAttack.next()) {
                String type = rsAttack.getString("type");
                int armor = rsAttack.getInt("armor");
                int damage = rsAttack.getInt("base_damage");
                int exp = rsAttack.getInt("experience");
                
                MilitaryUnit unit = null;
                switch (type) {
                    case "Swordsman": unit = new Swordsman(armor, damage); break;
                    case "Spearman":  unit = new Spearman(armor, damage);  break;
                    case "Crossbow":  unit = new Crossbow(armor, damage);  break;
                    case "Cannon":    unit = new Cannon(armor, damage);    break;
                }
                if (unit != null) {
                    unit.setExperience(exp);
                    civ.getArmy().add(unit); // Se añade directamente a la lista plana
                }
            }

            // 3. Reconstruir Ejército de Defensa
            psDefense = conn.prepareStatement(sqlDefense);
            psDefense.setInt(1, saveSlot);
            rsDefense = psDefense.executeQuery();
            while (rsDefense.next()) {
                String type = rsDefense.getString("type");
                int armor = rsDefense.getInt("armor");
                int damage = rsDefense.getInt("base_damage");
                int exp = rsDefense.getInt("experience");
                
                MilitaryUnit unit = null;
                switch (type) {
                    case "ArrowTower":     unit = new ArrowTower(armor, damage); break;
                    case "Catapult":       unit = new Catapult(armor, damage);   break;
                    case "RocketLauncher": 
                    case "RocketLauncherTower": unit = new RocketLauncherTower(armor, damage); break;
                }
                if (unit != null) {
                    unit.setExperience(exp);
                    civ.getArmy().add(unit);
                }
            }

            // 4. Reconstruir Unidades Especiales
            psSpecial = conn.prepareStatement(sqlSpecial);
            psSpecial.setInt(1, saveSlot);
            rsSpecial = psSpecial.executeQuery();
            while (rsSpecial.next()) {
                String type = rsSpecial.getString("type");
                int armor = rsSpecial.getInt("armor");
                int damage = rsSpecial.getInt("base_damage");
                int exp = rsSpecial.getInt("experience");
                
                MilitaryUnit unit = null;
                switch (type) {
                    case "Magician": unit = new Magician(armor, damage); break;
                    case "Priest":   unit = new Priest(armor, damage);   break;
                }
                if (unit != null) {
                    unit.setExperience(exp);
                    civ.getArmy().add(unit);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rsCiv != null) try { rsCiv.close(); } catch (SQLException e) {}
            if (rsAttack != null) try { rsAttack.close(); } catch (SQLException e) {}
            if (rsDefense != null) try { rsDefense.close(); } catch (SQLException e) {}
            if (rsSpecial != null) try { rsSpecial.close(); } catch (SQLException e) {}
            close(null, psCiv);
            close(null, psAttack);
            close(null, psDefense);
            close(conn, psSpecial);
        }

        return civ;
    }

    public boolean deleteCivilization(int saveSlot) {
        deleteAllBattlesBySave(saveSlot);
        String sqlUnits[] = {
            "DELETE FROM attack_units_stats WHERE civilization_id = ?",
            "DELETE FROM defense_units_stats WHERE civilization_id = ?",
            "DELETE FROM special_units_stats WHERE civilization_id = ?"
        };
        String sqlCiv = "DELETE FROM Civilization_stats WHERE civilization_id = ?";

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            for (String sql : sqlUnits) {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, saveSlot);
                ps.executeUpdate();
                ps.close();
            }

            ps = conn.prepareStatement(sqlCiv);
            ps.setInt(1, saveSlot);
            int res = ps.executeUpdate();
            
            conn.commit();
            return res > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) try { conn.rollback(); } catch (SQLException ex) {}
            return false;
        } finally {
            close(conn, ps);
        }
    }

    public boolean insertBattle(int saveSlot, int battleNumber, String summaryReport, String developmentReport, String winner) {
        String sql = "INSERT INTO battle_report (save_slot, battle_number, summary_report, development_report, winner) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, saveSlot);
            ps.setInt(2, battleNumber);
            ps.setString(3, summaryReport);
            ps.setString(4, developmentReport);
            ps.setString(5, winner);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            close(conn, ps);
        }
    }

    public String getBattleSummary(int saveSlot, int battleNumber) {
        String sql = "SELECT summary_report FROM battle_report WHERE save_slot = ? AND battle_number = ?";
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, saveSlot);
            ps.setInt(2, battleNumber);
            rs = ps.executeQuery();
            if (rs.next()) return rs.getString("summary_report");
        } catch (SQLException e) { e.printStackTrace(); } finally { close(conn, ps, rs); }
        return null;
    }

    public String getBattleDevelopment(int saveSlot, int battleNumber) {
        String sql = "SELECT development_report FROM battle_report WHERE save_slot = ? AND battle_number = ?";
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, saveSlot);
            ps.setInt(2, battleNumber);
            rs = ps.executeQuery();
            if (rs.next()) return rs.getString("development_report");
        } catch (SQLException e) { e.printStackTrace(); } finally { close(conn, ps, rs); }
        return null;
    }

    public ArrayList<String> getBattleList(int saveSlot) {
        String sql = "SELECT battle_number, winner, created_at FROM battle_report WHERE save_slot = ? ORDER BY battle_number DESC";
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        ArrayList<String> battles = new ArrayList<>();
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, saveSlot);
            rs = ps.executeQuery();
            while (rs.next()) {
                String linea = "Batalla " + rs.getInt("battle_number") + " | Ganador: " + rs.getString("winner") + " | Fecha: " + rs.getString("created_at");
                battles.add(linea);
            }
        } catch (SQLException e) { e.printStackTrace(); } finally { close(conn, ps, rs); }
        return battles;
    }

    public boolean deleteBattle(int saveSlot, int battleNumber) {
        String sql = "DELETE FROM battle_report WHERE save_slot = ? AND battle_number = ?";
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, saveSlot);
            ps.setInt(2, battleNumber);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; } finally { close(conn, ps); }
    }

    private void deleteAllBattlesBySave(int saveSlot) {
        String sql = "DELETE FROM battle_report WHERE save_slot = ?";
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, saveSlot);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); } finally { close(conn, ps); }
    }
}