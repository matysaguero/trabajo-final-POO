package modelo.Ingresantes;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import modelo.*;
import modelo.documentos.*;

public class FabricaIngresantes {

    // Ajusta la extensión (.jpg o .png) según cómo esté guardado el archivo en tu carpeta assets
    private static final String RUTA_PLANTILLA_DNI = "assets/imagenes/documento.png";
    private static final String CARPETA_HUELLAS = "assets/imagenes/huellas/";
    private static final String CARPETA_FOTOS_DOC = "assets/imagenes/foto_doc/";

    public static List<Ingresante> crearPlantelInicial() {
        List<Ingresante> plantel = new ArrayList<>();

        // 1. CABALLERO
        plantel.add(crearPersonaje(
            "Sir Arthur", 180, 80, TipoIngresante.CIUDADANO, TipoRaza.HUMANO,
            TipoClan.PUERTO_UMBRAL, TipoCiudad.HASGARD, TipoAspecto.CABALLERO,
            crearSoloDNI("Sir Arthur", "Hasgard", true, LocalDate.of(1990, 05, 10), 40123101, LocalDate.of(2159, 12, 12),
                         0, TipoRaza.HUMANO.name()),
            false, false
        ));

        // 2. ALIEN
        plantel.add(crearPersonaje(
            "Zorglub", 195, 65, TipoIngresante.TURISTA, TipoRaza.ALIEN,
            TipoClan.TITAN_DIGITAL, TipoCiudad.LANDER, TipoAspecto.ALIEN,
            crearSoloDNI("Zorglub", "Lander", true, LocalDate.of(1985, 8, 22), 35890102, LocalDate.of(2159, 11, 05),
                         1, TipoRaza.ALIEN.name()),
            false, false
        ));

        // 3. ALIEN_PONELE
        plantel.add(crearPersonaje(
            "Xylar el Humano", 172, 70, TipoIngresante.TURISTA, TipoRaza.ALIEN,
            TipoClan.PUERTO_UMBRAL, TipoCiudad.LANDER, TipoAspecto.ALIEN_PONELE,
            crearSoloDNI("Xylar", "Lander", false, LocalDate.of(1990, 1, 1), 42110103, LocalDate.of(2159, 11, 1),
                         2, TipoRaza.ALIEN.name()),
            true, true
        ));

        // 4. ASTRONAUTA
        plantel.add(crearPersonaje(
            "Cmdte. Vance", 178, 76, TipoIngresante.TRABAJADOR, TipoRaza.CYBORG,
            TipoClan.UNION_MAGICA, TipoCiudad.BALES, TipoAspecto.ASTRONAUTA,
            crearSoloDNI("Cmdte. Vance", "Bales", true, LocalDate.of(2040, 10, 30), 38440104, LocalDate.of(2150, 12, 7),
                         3, TipoRaza.CYBORG.name()),
            true, false
        ));

        // 5. VIAJERO_POBRE
        plantel.add(crearPersonaje(
            "Lázaro", 168, 60, TipoIngresante.TRABAJADOR, TipoRaza.HUMANO,
            TipoClan.PUERTO_UMBRAL, TipoCiudad.BALES, TipoAspecto.VIAJERO_POBRE,
            crearSoloDNI("Lázaro", "Bales", false, LocalDate.of(2150, 6, 1), 39550105, LocalDate.of(2159, 12, 20),
                         4, TipoRaza.HUMANO.name()),
            true, true
        ));

        // 6. MAGO_CLAN_2
        plantel.add(crearPersonaje(
            "Aldebarán", 175, 68, TipoIngresante.CIUDADANO, TipoRaza.GUARDIANES,
            TipoClan.UNION_MAGICA, TipoCiudad.HASGARD, TipoAspecto.MAGO_CLAN_2,
            crearSoloDNI("Aldebarán", "Hasgard", true, LocalDate.of(2100, 12, 20), 30112106, LocalDate.of(2159, 11, 15),
                         5, TipoRaza.GUARDIANES.name()),
            false, false
        ));

        // 7. MAGO_CLAN_3
        plantel.add(crearPersonaje(
            "Malakor", 182, 74, TipoIngresante.TURISTA, TipoRaza.GUARDIANES,
            TipoClan.UNION_MAGICA, TipoCiudad.NAMEK, TipoAspecto.MAGO_CLAN_3,
            crearSoloDNI("Malakor", "Namek", true, LocalDate.of(2070, 9, 12), 32998107, LocalDate.of(2159, 11, 27),
                         6, TipoRaza.GUARDIANES.name()),
            false, false
        ));

        // 8. MAGO_CLAN_4
        plantel.add(crearPersonaje(
            "Lyra", 165, 58, TipoIngresante.TRABAJADOR, TipoRaza.HUMANO,
            TipoClan.UNION_MAGICA, TipoCiudad.HASGARD, TipoAspecto.MAGO_CLAN_4,
            crearSoloDNI("Lyra", "Hasgard", true, LocalDate.of(2018, 9, 12), 44776108, LocalDate.of(2160, 12, 20),
                         7, TipoRaza.HUMANO.name()),
            false, false
        ));

        // 9. VIAJERO
        plantel.add(crearPersonaje(
            "Marco Polo", 176, 72, TipoIngresante.TURISTA, TipoRaza.HUMANO,
            TipoClan.PUERTO_UMBRAL, TipoCiudad.LANDER, TipoAspecto.VIAJERO,
            crearSoloDNI("Marco Polo", "Lander", true, LocalDate.of(2011, 6, 26), 37665109, LocalDate.of(2160, 3, 15),
                         8, TipoRaza.HUMANO.name()),
            false, false
        ));

        // 10. VIKINGO
        plantel.add(crearPersonaje(
            "Ragnar", 192, 98, TipoIngresante.CIUDADANO, TipoRaza.HUMANO,
            TipoClan.PUERTO_UMBRAL, TipoCiudad.HASGARD, TipoAspecto.VIKINGO,
            crearSoloDNI("Ragnar", "Hasgard", true, LocalDate.of(2022, 12, 18), 29334110, LocalDate.of(2161, 1, 2),
                         9, TipoRaza.HUMANO.name()),
            false, false
        ));

        Collections.shuffle(plantel);
        return plantel;
    }

    public static Ingresante crearPersonaje(
            String nombre, int altura, int peso,
            TipoIngresante tipo, TipoRaza raza, TipoClan clan, TipoCiudad ciudad,
            TipoAspecto aspecto, ArrayList<Documento> docs,
            boolean esEnemigo, boolean portaBomba) {

        if (esEnemigo) {
            return new Enemigo(
                nombre, altura, peso, 1.0, docs,
                tipo, raza, clan, ciudad, aspecto.getRutaImagen(), portaBomba
            );
        } else {
            return new IngresanteRegular(
                nombre, altura, peso, 1.0, docs,
                tipo, raza, clan, ciudad, aspecto.getRutaImagen()
            );
        }
    }

    // Crea únicamente el DNI seleccionando foto y huella de sus carpetas correspondientes
    private static ArrayList<Documento> crearSoloDNI(
            String nombre, String origen, boolean valido,
            LocalDate fechaNacimiento, int nroDni, LocalDate vencimiento,
            int indiceRecurso, String razaStr) {

        ArrayList<Documento> docs = new ArrayList<>();
        String rutaHuella = elegirImagenDeCarpeta(CARPETA_HUELLAS, indiceRecurso);
        String rutaFoto = elegirImagenDeCarpeta(CARPETA_FOTOS_DOC, indiceRecurso);

        docs.add(new DNI(
            RUTA_PLANTILLA_DNI,
            nombre,
            origen,
            valido,
            fechaNacimiento,
            nroDni,
            vencimiento,
            rutaHuella,
            rutaFoto,
            razaStr
        ));
        return docs;
    }

    // Busca imágenes dentro de la carpeta indicada y selecciona una según el índice
    private static String elegirImagenDeCarpeta(String rutaCarpeta, int indice) {
        File carpeta = new File(rutaCarpeta);
        if (carpeta.exists() && carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles((dir, name) -> {
                String lower = name.toLowerCase();
                return lower.endsWith(".png") || lower.endsWith(".jpg") || lower.endsWith(".jpeg");
            });
            if (archivos != null && archivos.length > 0) {
                Arrays.sort(archivos);
                File elegido = archivos[indice % archivos.length];
                return elegido.getPath().replace("\\", "/");
            }
        }
        return rutaCarpeta;
    }
}