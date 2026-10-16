package modelo.Ingresantes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import modelo.*;
import modelo.documentos.*;

public class FabricaIngresantes {

    private static final String RUTA_PLANTILLA_DNI = "assets/imagenes/documento.png";

    // Devuelve la lista con los 10 personajes (1 de cada aspecto) mezclados al azar
    public static List<Ingresante> crearPlantelInicial() {
        List<Ingresante> plantel = new ArrayList<>();

        // 1. CABALLERO (Regular - Todo en regla)
        plantel.add(crearPersonaje(
            "Sir Arthur", 180, 80, TipoIngresante.CIUDADANO, TipoRaza.HUMANO,
            TipoClan.PUERTO_UMBRAL, TipoCiudad.HASGARD, TipoAspecto.CABALLERO,
            crearDocBasicos("Sir Arthur", "Comodoro Rivadavia", true, "10/05/1990", 101, "12/12/2027",
                            TipoAspecto.CABALLERO.getRutaImagen(), TipoRaza.HUMANO.name(), 1),
            false, false
        ));

        // 2. ALIEN (Regular - Todo en regla)
        plantel.add(crearPersonaje(
            "Zorglub", 195, 65, TipoIngresante.TURISTA, TipoRaza.ALIEN,
            TipoClan.ALIEN, TipoCiudad.LANDER, TipoAspecto.ALIEN,
            crearDocConPermiso("Zorglub", "Trelew", true, "22/08/1985", 102, "05/11/2028",
                               TipoAspecto.ALIEN.getRutaImagen(), TipoRaza.ALIEN.name(), 2, "Explorador"),
            false, false
        ));

        // 3. ALIEN_PONELE (Enemigo disfrazado - Es amenaza, debe ser rechazado)
        plantel.add(crearPersonaje(
            "Xylar el Humano", 172, 70, TipoIngresante.TURISTA, TipoRaza.ALIEN,
            TipoClan.PUERTO_UMBRAL, TipoCiudad.LANDER, TipoAspecto.ALIEN_PONELE,
            crearDocBasicos("Xylar el Humano", "Las Heras", false, "01/01/1995", 103, "01/02/2025",
                            TipoAspecto.ALIEN_PONELE.getRutaImagen(), TipoRaza.ALIEN.name(), 3),
            true, true // Es Enemigo y porta bomba
        ));

        // 4. ASTRONAUTA (Enemigo infiltrado - Es amenaza)
        plantel.add(crearPersonaje(
            "Comandante Vance", 178, 76, TipoIngresante.TRABAJADOR, TipoRaza.CYBORG,
            TipoClan.UNION_MAGICA, TipoCiudad.BALES, TipoAspecto.ASTRONAUTA,
            crearDocConPermiso("Comandante Vance", "Bariloche", true, "14/03/1988", 104, "20/08/2027",
                               TipoAspecto.ASTRONAUTA.getRutaImagen(), TipoRaza.CYBORG.name(), 4, "Piloto"),
            true, false // Es Enemigo (sin bomba)
        ));

        // 5. VIAJERO_POBRE (Enemigo contrabandista - Es amenaza)
        plantel.add(crearPersonaje(
            "Lázaro", 168, 60, TipoIngresante.TRABAJADOR, TipoRaza.HUMANO,
            TipoClan.PUERTO_UMBRAL, TipoCiudad.BALES, TipoAspecto.VIAJERO_POBRE,
            crearDocConPermiso("Lázaro", "Bales", false, "09/09/1992", 105, "10/10/2026",
                               TipoAspecto.VIAJERO_POBRE.getRutaImagen(), TipoRaza.HUMANO.name(), 1, "Comerciante"),
            true, true // Es Enemigo y porta bomba
        ));

        // 6. MAGO_CLAN_2 (Regular - Todo en regla)
        plantel.add(crearPersonaje(
            "Aldebarán", 175, 68, TipoIngresante.CIUDADANO, TipoRaza.GUARDIANES,
            TipoClan.UNION_MAGICA, TipoCiudad.HASGARD, TipoAspecto.MAGO_CLAN_2,
            crearDocConPermiso("Aldebarán", "Bariloche", true, "18/07/1980", 106, "15/05/2029",
                               TipoAspecto.MAGO_CLAN_2.getRutaImagen(), TipoRaza.GUARDIANES.name(), 4, "Erudito"),
            false, false
        ));

        // 7. MAGO_CLAN_3 (Regular)
        plantel.add(crearPersonaje(
            "Malakor", 182, 74, TipoIngresante.TURISTA, TipoRaza.GUARDIANES,
            TipoClan.ALIEN, TipoCiudad.NAMEK, TipoAspecto.MAGO_CLAN_3,
            crearDocConPermiso("Malakor", "Trelew", true, "03/11/1983", 107, "19/09/2027",
                               TipoAspecto.MAGO_CLAN_3.getRutaImagen(), TipoRaza.GUARDIANES.name(), 4, "Hechicero"),
            false, false
        ));

        // 8. MAGO_CLAN_4 (Regular - Todo en regla)
        plantel.add(crearPersonaje(
            "Lyra", 165, 58, TipoIngresante.TRABAJADOR, TipoRaza.HUMANO,
            TipoClan.UNION_MAGICA, TipoCiudad.HASGARD, TipoAspecto.MAGO_CLAN_4,
            crearDocBasicos("Lyra", "Comodoro Rivadavia", true, "25/12/1998", 108, "30/04/2028",
                            TipoAspecto.MAGO_CLAN_4.getRutaImagen(), TipoRaza.HUMANO.name(), 1),
            false, false
        ));

        // 9. VIAJERO (Regular - Todo en regla)
        plantel.add(crearPersonaje(
            "Marco Polo", 176, 72, TipoIngresante.TURISTA, TipoRaza.HUMANO,
            TipoClan.PUERTO_UMBRAL, TipoCiudad.LANDER, TipoAspecto.VIAJERO,
            crearDocBasicos("Marco Polo", "Santiago de Chile", true, "05/06/1991", 109, "14/07/2028",
                            TipoAspecto.VIAJERO.getRutaImagen(), TipoRaza.HUMANO.name(), 1),
            false, false
        ));

        // 10. VIKINGO (Regular)
        plantel.add(crearPersonaje(
            "Ragnar", 192, 98, TipoIngresante.CIUDADANO, TipoRaza.HUMANO,
            TipoClan.ALIEN, TipoCiudad.HASGARD, TipoAspecto.VIKINGO,
            crearDocBasicos("Ragnar", "Las Heras", true, "11/11/1979", 110, "22/11/2027",
                            TipoAspecto.VIKINGO.getRutaImagen(), TipoRaza.HUMANO.name(), 1),
            false, false
        ));

        // Mezclamos el orden para que cada partida sea distinta
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

    // --- MÉTODOS AUXILIARES ADAPTADOS A LOS CONSTRUCTORES ACTUALES DE DOCUMENTOS ---

    private static ArrayList<Documento> crearDocBasicos(
            String nombre, String origen, boolean valido,
            String fechaNacimiento, int id, String vencimiento,
            String foto, String razaStr, int razaCod) {
        ArrayList<Documento> docs = new ArrayList<>();
        docs.add(new Pasaporte(nombre, origen, valido, fechaNacimiento, razaCod, id + 5000));
        docs.add(new DNI(RUTA_PLANTILLA_DNI, nombre, origen, valido, fechaNacimiento,
                         id + 1000, vencimiento, "HUELLA-" + id, foto, razaStr));
        return docs;
    }

    private static ArrayList<Documento> crearDocConPermiso(
            String nombre, String origen, boolean valido,
            String fechaNacimiento, int id, String vencimiento,
            String foto, String razaStr, int razaCod, String ocupacion) {
        ArrayList<Documento> docs = crearDocBasicos(
            nombre, origen, valido, fechaNacimiento, id, vencimiento, foto, razaStr, razaCod
        );
        docs.add(new Permiso(nombre, origen, valido, String.valueOf(id + 1000), ocupacion, id + 2000, vencimiento));
        return docs;
    }
}