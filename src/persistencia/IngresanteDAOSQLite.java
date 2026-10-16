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
import modelo.documentos.Permiso;

public class IngresanteDAOSQLite implements IngresanteDAO {

    private final Connection conexion;

    public IngresanteDAOSQLite(Connection conexion) throws SQLException {
        if (conexion == null) {
            throw new IllegalArgumentException("La conexion es obligatoria");
        }
        this.conexion = conexion;
        try (Statement sentencia = conexion.createStatement()) {
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

            sentencia.execute("CREATE TABLE IF NOT EXISTS documentos ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "ingresante_id INTEGER NOT NULL, "
                    + "clase_doc TEXT NOT NULL, "
                    + "nombre TEXT NOT NULL, "
                    + "origen TEXT NOT NULL, "
                    + "valido INTEGER NOT NULL, "
                    + "fecha_nacimiento TEXT, "
                    + "raza_int INTEGER, "
                    + "huella_int INTEGER, "
                    + "nro_dni INTEGER, "
                    + "vencimiento TEXT, "
                    + "huella_str TEXT, "
                    + "foto TEXT, "
                    + "raza_str TEXT, "
                    + "dni_permiso TEXT, "
                    + "ocupacion TEXT, "
                    + "num_permiso INTEGER)");
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
            sentencia.setDouble(4, 5.0);
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
                + "(ingresante_id, clase_doc, nombre, origen, valido, fecha_nacimiento, "
                + "raza_int, huella_int, nro_dni, vencimiento, huella_str, foto, raza_str, "
                + "dni_permiso, ocupacion, num_permiso) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement sentencia = this.conexion.prepareStatement(sqlDoc)) {
            sentencia.setInt(1, ingresanteId);
            sentencia.setString(2, doc.getClass().getSimpleName());
            sentencia.setString(3, doc.getNombre());
            sentencia.setString(4, doc.getOrigen());
            sentencia.setInt(5, doc.isValido() ? 1 : 0);

            if (doc instanceof Pasaporte) {
                Pasaporte pas = (Pasaporte) doc;
                sentencia.setString(6, pas.getFechaNacimiento());
                sentencia.setInt(7, pas.getRaza());
                sentencia.setInt(8, pas.getHuella());
            } else if (doc instanceof DNI) {
                DNI dni = (DNI) doc;
                sentencia.setString(6, dni.getFechaNacimiento());
                sentencia.setInt(9, dni.getNroDni());
                sentencia.setString(10, dni.getVencimiento());
                sentencia.setString(11, dni.getHuella());
                sentencia.setString(12, dni.getFoto());
                sentencia.setString(13, dni.getRaza());
            } else if (doc instanceof Permiso) {
                Permiso perm = (Permiso) doc;
                sentencia.setString(10, perm.getFechaVencimiento());
                sentencia.setString(14, perm.getDni());
                sentencia.setString(15, perm.getOcupacion());
                sentencia.setInt(16, perm.getNumPermiso());
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
                    String nombre = filas.getString("nombre");
                    String origen = filas.getString("origen");
                    boolean valido = filas.getInt("valido") == 1;

                    if ("Pasaporte".equals(clase)) {
                        docs.add(new Pasaporte(
                            nombre,
                            origen,
                            valido,
                            filas.getString("fecha_nacimiento"),
                            filas.getInt("raza_int"),
                            filas.getInt("huella_int")
                        ));
                    } else if ("DNI".equals(clase)) {
                        docs.add(new DNI(
                            "assets/imagenes/documento.png",
                            nombre,
                            origen,
                            valido,
                            filas.getString("fecha_nacimiento"),
                            filas.getInt("nro_dni"),
                            filas.getString("vencimiento"),
                            filas.getString("huella_str"),
                            filas.getString("foto"),
                            filas.getString("raza_str")
                        ));
                    } else if ("Permiso".equals(clase)) {
                        docs.add(new Permiso(
                            nombre,
                            origen,
                            valido,
                            filas.getString("dni_permiso"),
                            filas.getString("ocupacion"),
                            filas.getInt("num_permiso"),
                            filas.getString("vencimiento")
                        ));
                    }
                }
            }
        }
        return docs;
    }
}