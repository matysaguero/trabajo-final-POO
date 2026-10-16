package modelo.Ingresantes;

import java.io.File;
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
            crearSoloDNI("Sir Arthur", "Hasgard", true, "10/05/1990", 40123101, "12/12/2027",
                         0, TipoRaza.HUMANO.name()),
            false, false
        ));

        // 2. ALIEN
        plantel.add(crearPersonaje(
            "Zorglub", 195, 65, TipoIngresante.TURISTA, TipoRaza.ALIEN,
            TipoClan.ALIEN, TipoCiudad.LANDER, TipoAspecto.ALIEN,
            crearSoloDNI("Zorglub", "Lander", true, "22/08/1985", 35890102, "05/11/2028",
                         1, TipoRaza.ALIEN.name()),
            false, false
        ));

        // 3. ALIEN_PONELE
        plantel.add(crearPersonaje(
            "Xylar el Humano", 172, 70, TipoIngresante.TURISTA, TipoRaza.ALIEN,
            TipoClan.PUERTO_UMBRAL, TipoCiudad.LANDER, TipoAspecto.ALIEN_PONELE,
            crearSoloDNI("Xylar", "Lander", false, "01/01/1995", 42110103, "01/02/2025",
                         2, TipoRaza.ALIEN.name()),
            true, true
        ));

        // 4. ASTRONAUTA
        plantel.add(crearPersonaje(
            "Cmdte. Vance", 178, 76, TipoIngresante.TRABAJADOR, TipoRaza.CYBORG,
            TipoClan.UNION_MAGICA, TipoCiudad.BALES, TipoAspecto.ASTRONAUTA,
            crearSoloDNI("Cmdte. Vance", "Bales", true, "14/03/1988", 38440104, "20/08/2027",
                         3, TipoRaza.CYBORG.name()),
            true, false
        ));

        // 5. VIAJERO_POBRE
        plantel.add(crearPersonaje(
            "Lázaro", 168, 60, TipoIngresante.TRABAJADOR, TipoRaza.HUMANO,
            TipoClan.PUERTO_UMBRAL, TipoCiudad.BALES, TipoAspecto.VIAJERO_POBRE,
            crearSoloDNI("Lázaro", "Bales", false, "09/09/1992", 39550105, "10/10/2026",
                         4, TipoRaza.HUMANO.name()),
            true, true
        ));

        // 6. MAGO_CLAN_2
        plantel.add(crearPersonaje(
            "Aldebarán", 175, 68, TipoIngresante.CIUDADANO, TipoRaza.GUARDIANES,
            TipoClan.UNION_MAGICA, TipoCiudad.HASGARD, TipoAspecto.MAGO_CLAN_2,
            crearSoloDNI("Aldebarán", "Hasgard", true, "18/07/1980", 30112106, "15/05/2029",
                         5, TipoRaza.GUARDIANES.name()),
            false, false
        ));

        // 7. MAGO_CLAN_3
        plantel.add(crearPersonaje(
            "Malakor", 182, 74, TipoIngresante.TURISTA, TipoRaza.GUARDIANES,
            TipoClan.ALIEN, TipoCiudad.NAMEK, TipoAspecto.MAGO_CLAN_3,
            crearSoloDNI("Malakor", "Namek", true, "03/11/1983", 32998107, "19/09/2027",
                         6, TipoRaza.GUARDIANES.name()),
            false, false
        ));

        // 8. MAGO_CLAN_4
        plantel.add(crearPersonaje(
            "Lyra", 165, 58, TipoIngresante.TRABAJADOR, TipoRaza.HUMANO,
            TipoClan.UNION_MAGICA, TipoCiudad.HASGARD, TipoAspecto.MAGO_CLAN_4,
            crearSoloDNI("Lyra", "Hasgard", true, "25/12/1998", 44776108, "30/04/2028",
                         7, TipoRaza.HUMANO.name()),
            false, false
        ));

        // 9. VIAJERO
        plantel.add(crearPersonaje(
            "Marco Polo", 176, 72, TipoIngresante.TURISTA, TipoRaza.HUMANO,
            TipoClan.PUERTO_UMBRAL, TipoCiudad.LANDER, TipoAspecto.VIAJERO,
            crearSoloDNI("Marco Polo", "Lander", true, "05/06/1991", 37665109, "14/07/2028",
                         8, TipoRaza.HUMANO.name()),
            false, false
        ));

        // 10. VIKINGO
        plantel.add(crearPersonaje(
            "Ragnar", 192, 98, TipoIngresante.CIUDADANO, TipoRaza.HUMANO,
            TipoClan.ALIEN, TipoCiudad.HASGARD, TipoAspecto.VIKINGO,
            crearSoloDNI("Ragnar", "Hasgard", true, "11/11/1979", 29334110, "22/11/2027",
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
            String fechaNacimiento, int nroDni, String vencimiento,
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