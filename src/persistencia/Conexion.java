package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static Conexion instancia;
    private final Connection conexion;

    private Conexion() throws SQLException {
        // Crea el archivo frontier.db en la raíz del proyecto
        this.conexion = DriverManager.getConnection("jdbc:sqlite:frontier.db");
    }

    public static Conexion getInstancia() throws SQLException {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection get() {
        return this.conexion;
    }
}