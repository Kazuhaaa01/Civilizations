package civilizations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GameDAO {

    private static final String DB_URL = "jdbc:mysql://192.168.152.251/civilizations";
    private static final String DB_USER = "civil12";
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
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        close(conn, ps);
    }

    // Guarda la civilizacion en el slot elegido.
    public boolean insertCivilization(int saveSlot, Civilization civ) {
        String sql = "INSERT INTO civilizations "
                + "(save_slot, technology_defense, technology_attack, wood, iron, food, mana, "
                + "magic_tower, church, farm, smithy, carpentry, battles) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, saveSlot);
            ps.setInt(2, civ.getTechnologyDefense());
            ps.setInt(3, civ.getTechnologyAtack());
            ps.setInt(4, civ.getWood());
            ps.setInt(5, civ.getIron());
            ps.setInt(6, civ.getFood());
            ps.setInt(7, civ.getMana());
            ps.setInt(8, civ.getMagicTower());
            ps.setInt(9, civ.getChurch());
            ps.setInt(10, civ.getFarm());
            ps.setInt(11, civ.getSmithy());
            ps.setInt(12, civ.getCarpentry());
            ps.setInt(13, civ.getBattles());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            close(conn, ps);
        }
    }

    // Carga una civilizacion guardada desde un slot.
    public Civilization getCivilization(int saveSlot) {
        String sql = "SELECT * FROM civilization_save WHERE save_slot = ?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Civilization civ = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, saveSlot);
            rs = ps.executeQuery();

            if (rs.next()) {
                civ = new Civilization();

                civ.setTechnologyDefense(rs.getInt("technology_defense"));
                civ.setTechnologyAtack(rs.getInt("technology_attack"));
                civ.setWood(rs.getInt("wood"));
                civ.setIron(rs.getInt("iron"));
                civ.setFood(rs.getInt("food"));
                civ.setMana(rs.getInt("mana"));
                civ.setMagicTower(rs.getInt("magic_tower"));
                civ.setChurch(rs.getInt("church"));
                civ.setFarm(rs.getInt("farm"));
                civ.setSmithy(rs.getInt("smithy"));
                civ.setCarpentry(rs.getInt("carpentry"));
                civ.setBattles(rs.getInt("battles"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }

        return civ;
    }

    // Actualiza la civilizacion almacenada en ese slot.
    public boolean updateCivilization(int saveSlot, Civilization civ) {
        String sql = "UPDATE civilization_save SET "
                + "technology_defense = ?, technology_attack = ?, wood = ?, iron = ?, food = ?, "
                + "mana = ?, magic_tower = ?, church = ?, farm = ?, smithy = ?, carpentry = ?, battles = ? "
                + "WHERE save_slot = ?";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, civ.getTechnologyDefense());
            ps.setInt(2, civ.getTechnologyAtack());
            ps.setInt(3, civ.getWood());
            ps.setInt(4, civ.getIron());
            ps.setInt(5, civ.getFood());
            ps.setInt(6, civ.getMana());
            ps.setInt(7, civ.getMagicTower());
            ps.setInt(8, civ.getChurch());
            ps.setInt(9, civ.getFarm());
            ps.setInt(10, civ.getSmithy());
            ps.setInt(11, civ.getCarpentry());
            ps.setInt(12, civ.getBattles());
            ps.setInt(13, saveSlot);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            close(conn, ps);
        }
    }

    // Borra la civilizacion y tambien sus batallas guardadas.
    public boolean deleteCivilization(int saveSlot) {
        deleteAllBattlesBySave(saveSlot);

        String sql = "DELETE FROM civilization_save WHERE save_slot = ?";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, saveSlot);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            close(conn, ps);
        }
    }

    // Guarda el resumen y el desarrollo completo de una batalla.
    public boolean insertBattle(int saveSlot, int battleNumber, String summaryReport,
            String developmentReport, String winner) {
        String sql = "INSERT INTO battle_report "
                + "(save_slot, battle_number, summary_report, development_report, winner) "
                + "VALUES (?, ?, ?, ?, ?)";

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

    // Devuelve el resumen de una batalla concreta.
    public String getBattleSummary(int saveSlot, int battleNumber) {
        String sql = "SELECT summary_report FROM battle_report WHERE save_slot = ? AND battle_number = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, saveSlot);
            ps.setInt(2, battleNumber);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("summary_report");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }

        return null;
    }

    // Devuelve el desarrollo paso a paso de una batalla concreta.
    public String getBattleDevelopment(int saveSlot, int battleNumber) {
        String sql = "SELECT development_report FROM battle_report WHERE save_slot = ? AND battle_number = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, saveSlot);
            ps.setInt(2, battleNumber);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("development_report");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }

        return null;
    }

    // Devuelve una lista simple con las batallas guardadas.
    public ArrayList<String> getBattleList(int saveSlot) {
        String sql = "SELECT battle_number, winner, created_at FROM battle_report "
                + "WHERE save_slot = ? ORDER BY battle_number DESC";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<String> battles = new ArrayList<>();

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, saveSlot);
            rs = ps.executeQuery();

            while (rs.next()) {
                String linea = "Batalla " + rs.getInt("battle_number")
                        + " | Ganador: " + rs.getString("winner")
                        + " | Fecha: " + rs.getString("created_at");
                battles.add(linea);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }

        return battles;
    }

    // Borra una batalla concreta.
    public boolean deleteBattle(int saveSlot, int battleNumber) {
        String sql = "DELETE FROM battle_report WHERE save_slot = ? AND battle_number = ?";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, saveSlot);
            ps.setInt(2, battleNumber);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            close(conn, ps);
        }
    }

    // Borra todas las batallas de un slot.
    private void deleteAllBattlesBySave(int saveSlot) {
        String sql = "DELETE FROM battle_report WHERE save_slot = ?";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, saveSlot);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps);
        }
    }
}
