package modelo.Ingresantes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import modelo.*;
import modelo.documentos.*;

public class FabricaIngresantes {

    // Devuelve la lista con los 10 personajes (1 de cada aspecto) mezclados al azar
    public static List<Ingresante> crearPlantelInicial() {
        List<Ingresante> plantel = new ArrayList<>();

        // 1. CABALLERO (Regular - Todo en regla)
        plantel.add(crearPersonaje(
            "Sir Arthur", 180, 80, TipoIngresante.CIUDADANO, TipoRaza.HUMANO,
            TipoClan.PUERTO_UMBRAL, TipoCiudad.HASGARD, TipoAspecto.CABALLERO,
            crearDocBasicos("12/12/2027", 101, 80, 180, "Argentina", "Comodoro Rivadavia", false),
            false, false
        ));

        // 2. ALIEN (Regular - Todo en regla)
        plantel.add(crearPersonaje(
            "Zorglub", 195, 65, TipoIngresante.TURISTA, TipoRaza.ALIEN,
            TipoClan.ALIEN, TipoCiudad.LANDER, TipoAspecto.ALIEN,
            crearDocConPermiso("05/11/2028", 102, 65, 195, "Nebulosa", "Trelew", "Sello Galáctico", "Explorador", false),
            false, false
        ));

        // 3. ALIEN_PONELE (Enemigo disfrazado - Es amenaza, debe ser rechazado)
        plantel.add(crearPersonaje(
            "Xylar el Humano", 172, 70, TipoIngresante.TURISTA, TipoRaza.ALIEN,
            TipoClan.PUERTO_UMBRAL, TipoCiudad.LANDER, TipoAspecto.ALIEN_PONELE,
            crearDocBasicos("01/02/2025", 103, 70, 172, "Marte", "Las Heras", true),
            true, true // Es Enemigo y porta bomba
        ));

        // 4. ASTRONAUTA (Enemigo infiltrado - Es amenaza, aunque otro astronauta podría ser Regular)
        plantel.add(crearPersonaje(
            "Comandante Vance", 178, 76, TipoIngresante.TRABAJADOR, TipoRaza.CYBORG,
            TipoClan.UNION_MAGICA, TipoCiudad.BALES, TipoAspecto.ASTRONAUTA,
            crearDocConPermiso("20/08/2027", 104, 76, 178, "Estación Lunar", "Bariloche", "Sello Espacial", "Piloto", false),
            true, false // Es Enemigo (sin bomba)
        ));

        // 5. VIAJERO_POBRE (Enemigo contrabandista - Es amenaza, aunque otro viajero pobre podría ser Regular)
        plantel.add(crearPersonaje(
            "Lázaro", 168, 60, TipoIngresante.TRABAJADOR, TipoRaza.HUMANO,
            TipoClan.PUERTO_UMBRAL, TipoCiudad.BALES, TipoAspecto.VIAJERO_POBRE,
            crearDocEnTramite("10/10/2026", 105, "Firma Borrosa", true),
            true, true // Es Enemigo y porta bomba
        ));

        // 6. MAGO_CLAN_2 (Regular - Todo en regla)
        plantel.add(crearPersonaje(
            "Aldebarán", 175, 68, TipoIngresante.CIUDADANO, TipoRaza.GUARDIANES,
            TipoClan.UNION_MAGICA, TipoCiudad.HASGARD, TipoAspecto.MAGO_CLAN_2,
            crearDocConPlaca("15/05/2029", 106, 68, 175, "Arcadia", "Bariloche", "ERUDITO", 884, false),
            false, false
        ));

        // 7. MAGO_CLAN_3 (Regular pero de Clan Prohibido SPACERS -> debe ser rechazado por PoliticaClan)
        plantel.add(crearPersonaje(
            "Malakor", 182, 74, TipoIngresante.TURISTA, TipoRaza.GUARDIANES,
            TipoClan.PUERTO_UMBRAL, TipoCiudad.NAMEK, TipoAspecto.MAGO_CLAN_3,
            crearDocConPlaca("19/09/2027", 107, 74, 182, "Arcadia", "Trelew", "SPACERS", 991, false),
            false, false
        ));

        // 8. MAGO_CLAN_4 (Regular - Todo en regla)
        plantel.add(crearPersonaje(
            "Lyra", 165, 58, TipoIngresante.TRABAJADOR, TipoRaza.HUMANO,
            TipoClan.UNION_MAGICA, TipoCiudad.HASGARD, TipoAspecto.MAGO_CLAN_4,
            crearDocBasicos("30/04/2028", 108, 58, 165, "Argentina", "Comodoro Rivadavia", false),
            false, false
        ));

        // 9. VIAJERO (Regular - Todo en regla)
        plantel.add(crearPersonaje(
            "Marco Polo", 176, 72, TipoIngresante.TURISTA, TipoRaza.HUMANO,
            TipoClan.PUERTO_UMBRAL, TipoCiudad.LANDER, TipoAspecto.VIAJERO,
            crearDocBasicos("14/07/2028", 109, 72, 176, "Chile", "Santiago de Chile", false),
            false, false
        ));

        // 10. VIKINGO (Regular pero de Clan Prohibido SPACERS -> debe ser rechazado por PoliticaClan)
        plantel.add(crearPersonaje(
            "Ragnar", 192, 98, TipoIngresante.CIUDADANO, TipoRaza.HUMANO,
            TipoClan.PUERTO_UMBRAL, TipoCiudad.HASGARD, TipoAspecto.VIKINGO,
            crearDocBasicos("22/11/2027", 110, 98, 192, "Valhalla", "Las Heras", false),
            false, false
        ));

        // Mezclamos el orden para que cada partida sea distinta
        Collections.shuffle(plantel);
        return plantel;
    }

    // Método creador que instancia IngresanteRegular o Enemigo con CUALQUIER aspecto
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

    // --- MÉTODOS AUXILIARES PARA FABRICAR COMBINACIONES DE DOCUMENTOS ---

    private static ArrayList<Documento> crearDocBasicos(
            String vencimiento, int id, int peso, int altura,
            String pais, String ciudad, boolean trucho) {
        ArrayList<Documento> docs = new ArrayList<>();
        docs.add(new Pasaporte(vencimiento, id, peso, altura, pais, ciudad, trucho));
        docs.add(new DNI(vencimiento, id + 1000, trucho, "Huella-" + id, ciudad, "N/A", "Calle Frontera " + id));
        return docs;
    }

    private static ArrayList<Documento> crearDocConPermiso(
            String vencimiento, int id, int peso, int altura,
            String pais, String ciudad, String sello, String ocupacion, boolean trucho) {
        ArrayList<Documento> docs = crearDocBasicos(vencimiento, id, peso, altura, pais, ciudad, trucho);
        docs.add(new Permiso(vencimiento, id + 2000, sello, ocupacion, trucho));
        return docs;
    }

    private static ArrayList<Documento> crearDocConPlaca(
            String vencimiento, int id, int peso, int altura,
            String pais, String ciudad, String clan, int idPlaca, boolean trucho) {
        ArrayList<Documento> docs = crearDocBasicos(vencimiento, id, peso, altura, pais, ciudad, trucho);
        docs.add(new Placa(vencimiento, id + 3000, clan, idPlaca, trucho));
        return docs;
    }

    private static ArrayList<Documento> crearDocEnTramite(
            String vencimiento, int id, String firma, boolean trucho) {
        ArrayList<Documento> docs = new ArrayList<>();
        docs.add(new PapelEnTramite(vencimiento, id + 4000, firma, trucho));
        return docs;
    }
}