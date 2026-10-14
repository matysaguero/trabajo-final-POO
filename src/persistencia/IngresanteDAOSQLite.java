package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.IngresanteDAO;
import modelo.TipoCiudad;
import modelo.TipoClan;
import modelo.TipoIngresante;
import modelo.TipoRaza;
import modelo.Ingresantes.Enemigo;
import modelo.Ingresantes.Ingresante;
import modelo.Ingresantes.IngresanteRegular;
import modelo.documentos.DNI;
import modelo.documentos.Documento;
import modelo.documentos.Pasaporte;

public class IngresanteDAOSQLite implements IngresanteDAO {

    private final Connection conexion;

    public IngresanteDAOSQLite(Connection conexion) throws SQLException {
        if (conexion == null) {
            throw new IllegalArgumentException("La conexion es obligatoria");
        }
        this.conexion = conexion;

        try (Statement sentencia = conexion.createStatement()) {
            // Tabla de ingresantes
            sentencia.execute("CREATE TABLE IF NOT EXISTS ingresantes ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "nombre TEXT NOT NULL, "
                    + "altura INTEGER NOT NULL, "
                    + "peso INTEGER NOT NULL, "
                    + "reputacion REAL NOT NULL, "
                    + "tipo TEXT NOT NULL, "
                    + "raza TEXT NOT NULL, "
                    + "clan TEXT NOT NULL, "
                    + "ciudad TEXT NOT NULL, "
                    + "ruta_imagen TEXT NOT NULL, "
                    + "es_enemigo INTEGER NOT NULL, "
                    + "porta_bomba INTEGER NOT NULL)");

            // Tabla de documentos asociados a cada ingresante
            sentencia.execute("CREATE TABLE IF NOT EXISTS documentos ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "ingresante_id INTEGER NOT NULL, "
                    + "clase_doc TEXT NOT NULL, "
                    + "fecha_vencimiento TEXT NOT NULL, "
                    + "num_id INTEGER NOT NULL, "
                    + "trucho INTEGER NOT NULL, "
                    + "peso INTEGER, "
                    + "altura INTEGER, "
                    + "pais TEXT, "
                    + "ciudad TEXT, "
                    + "huella TEXT, "
                    + "lugar_nacimiento TEXT, "
                    + "sexo TEXT, "
                    + "domicilio TEXT)");
        }
    }

    @Override
    public boolean guardar(Ingresante ingresante) {
        if (ingresante == null) {
            return false;
        }
        String sqlIngresante = "INSERT INTO ingresantes "
                + "(nombre, altura, peso, reputacion, tipo, raza, clan, ciudad, ruta_imagen, es_enemigo, porta_bomba) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement sentencia = this.conexion.prepareStatement(sqlIngresante, Statement.RETURN_GENERATED_KEYS)) {
            sentencia.setString(1, ingresante.getNombreDeclarado());
            sentencia.setInt(2, ingresante.getAlturaVisual());
            sentencia.setInt(3, ingresante.getPesoEnBalanza());
            sentencia.setDouble(4, 5.0); // Valor base válido (> 0)
            sentencia.setString(5, ingresante.getTipoIngresante().name());
            sentencia.setString(6, ingresante.getRaza().name());
            sentencia.setString(7, ingresante.getClan().name());
            sentencia.setString(8, ingresante.getCiudadOrigen().name());
            sentencia.setString(9, ingresante.getRutaImagen());

            boolean esEnemigo = ingresante.esAmenaza();
            boolean portaBomba = (ingresante instanceof Enemigo) && ((Enemigo) ingresante).getportaBomba();

            sentencia.setInt(10, esEnemigo ? 1 : 0);
            sentencia.setInt(11, portaBomba ? 1 : 0);

            int filas = sentencia.executeUpdate();
            if (filas != 1) {
                return false;
            }

            // Obtenemos el ID autogenerado del ingresante para guardar sus documentos
            try (ResultSet claves = sentencia.getGeneratedKeys()) {
                if (claves.next()) {
                    int ingresanteId = claves.getInt(1);
                    for (Documento doc : ingresante.getDocumentos()) {
                        guardarDocumento(ingresanteId, doc);
                    }
                }
            }
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    private void guardarDocumento(int ingresanteId, Documento doc) throws SQLException {
        String sqlDoc = "INSERT INTO documentos "
                + "(ingresante_id, clase_doc, fecha_vencimiento, num_id, trucho, peso, altura, pais, ciudad, huella, lugar_nacimiento, sexo, domicilio) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement sentencia = this.conexion.prepareStatement(sqlDoc)) {
            sentencia.setInt(1, ingresanteId);
            sentencia.setString(2, doc.getClass().getSimpleName());
            sentencia.setString(3, doc.getFechaVencimiento());
            sentencia.setInt(4, doc.getNumId());
            sentencia.setInt(5, doc.getTrucho() ? 1 : 0);

            if (doc instanceof Pasaporte) {
                Pasaporte pas = (Pasaporte) doc;
                sentencia.setInt(6, pas.getPeso());
                sentencia.setInt(7, pas.getAltura());
                sentencia.setString(8, pas.getPais());
                sentencia.setString(9, pas.getCiudad());
            } else if (doc instanceof DNI) {
                DNI dni = (DNI) doc;
                sentencia.setString(10, dni.getHuella());
                sentencia.setString(11, dni.getLugarNacimiento());
                sentencia.setString(12, dni.getSexo());
                sentencia.setString(13, dni.getDomicilio());
            }
            sentencia.executeUpdate();
        }
    }

    @Override
    public List<Ingresante> listarTodos() {
        String sql = "SELECT * FROM ingresantes";
        List<Ingresante> resultado = new ArrayList<>();

        try (PreparedStatement sentencia = this.conexion.prepareStatement(sql);
             ResultSet filas = sentencia.executeQuery()) {

            while (filas.next()) {
                int id = filas.getInt("id");
                String nombre = filas.getString("nombre");
                int altura = filas.getInt("altura");
                int peso = filas.getInt("peso");
                double reputacion = filas.getDouble("reputacion");
                TipoIngresante tipo = TipoIngresante.valueOf(filas.getString("tipo"));
                TipoRaza raza = TipoRaza.valueOf(filas.getString("raza"));
                TipoClan clan = TipoClan.valueOf(filas.getString("clan"));
                TipoCiudad ciudad = TipoCiudad.valueOf(filas.getString("ciudad"));
                String rutaImagen = filas.getString("ruta_imagen");
                boolean esEnemigo = filas.getInt("es_enemigo") == 1;
                boolean portaBomba = filas.getInt("porta_bomba") == 1;

                ArrayList<Documento> documentos = cargarDocumentosDeIngresante(id);

                if (esEnemigo) {
                    resultado.add(new Enemigo(nombre, altura, peso, reputacion, documentos, tipo, raza, clan, ciudad, rutaImagen, portaBomba));
                } else {
                    resultado.add(new IngresanteRegular(nombre, altura, peso, reputacion, documentos, tipo, raza, clan, ciudad, rutaImagen));
                }
            }
        } catch (SQLException e) {
            return resultado;
        }
        return resultado;
    }

    private ArrayList<Documento> cargarDocumentosDeIngresante(int ingresanteId) throws SQLException {
        String sql = "SELECT * FROM documentos WHERE ingresante_id = ?";
        ArrayList<Documento> docs = new ArrayList<>();

        try (PreparedStatement sentencia = this.conexion.prepareStatement(sql)) {
            sentencia.setInt(1, ingresanteId);
            try (ResultSet filas = sentencia.executeQuery()) {
                while (filas.next()) {
                    String clase = filas.getString("clase_doc");
                    String vencimiento = filas.getString("fecha_vencimiento");
                    int numId = filas.getInt("num_id");
                    boolean trucho = filas.getInt("trucho") == 1;

                    if ("Pasaporte".equals(clase)) {
                        docs.add(new Pasaporte(
                            vencimiento, numId,
                            filas.getInt("peso"),
                            filas.getInt("altura"),
                            filas.getString("pais"),
                            filas.getString("ciudad"),
                            trucho
                        ));
                    } else if ("DNI".equals(clase)) {
                        docs.add(new DNI(
                            vencimiento, numId, trucho,
                            filas.getString("huella"),
                            filas.getString("lugar_nacimiento"),
                            filas.getString("sexo"),
                            filas.getString("domicilio")
                        ));
                    }
                }
            }
        }
        return docs;
    }
}