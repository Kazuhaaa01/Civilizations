package civilizations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    public static void main(String[] args) {
        String usuario = "civil12";
        String clave = "123";
        String url = "jdbc:mysql://192.168.152.251/civilizations";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, usuario, clave);
            System.out.println("Conexión creada correctamente");
            conn.close();
        } catch (ClassNotFoundException ex) {
            System.out.println("No se encontró el driver MySQL para JDBC");
        } catch (SQLException ex) {
            System.out.println("Error de SQL");
            ex.printStackTrace();
        }
    }
}