package modelo.Ingresantes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import modelo.*;
import modelo.documentos.*;

public class FabricaIngresantes {

    private static final String RUTA_PLANTILLA_DNI = "assets/imagenes/documento.png";

    public static List<Ingresante> crearPlantelInicial() {
        List<Ingresante> plantel = new ArrayList<>();

        // 1. SIR ARTHUR (Ciudadano Humano - Todo en regla)
        ArrayList<Documento> docsArthur = crearDniEspecifico(
            "Sir Arthur", "Hasgard", true, LocalDate.of(1990, 5, 10), 40123101, 
            LocalDate.of(2165, 12, 12), TipoRaza.HUMANO.name(),
            "assets/imagenes/foto_doc/carnet_caballero.png", 
            "assets/imagenes/huellas/huella.png" 
        );
        plantel.add(crearPersonaje(
            "Sir Arthur", 180, 80, TipoIngresante.CIUDADANO, TipoRaza.HUMANO,
            TipoClan.PUERTO_UMBRAL, TipoCiudad.HASGARD, TipoAspecto.CABALLERO,
            docsArthur, false, false
        ));

        // 2. ZORGLUB (Enemigo Alien - Le robó el DNI a Lázaro, dice ser Humano)
        ArrayList<Documento> docsZorglub = crearDniEspecifico(
            "Zorglub", "Lander", false, LocalDate.of(1985, 8, 22), 35890102, 
            LocalDate.of(2160, 11, 5), TipoRaza.HUMANO.name(), 
            "assets/imagenes/foto_doc/carnet_viajero2.png", // Foto de su disfraz/robo
            "assets/imagenes/huellas/huella_alien.png" // Huella alienígena, lo delata
        );
        plantel.add(crearPersonaje(
            "Zorglub", 195, 65, TipoIngresante.TURISTA, TipoRaza.ALIEN,
            TipoClan.TITAN_DIGITAL, TipoCiudad.LANDER, TipoAspecto.ALIEN,
            docsZorglub, true, true // Es enemigo y porta bomba
        ));

        // 3. CMDTE. VANCE (Trabajador Cyborg - Todo en regla)
        ArrayList<Documento> docsVance = crearDniEspecifico(
            "Cmdte. Vance", "Bales", true, LocalDate.of(2040, 10, 30), 38440104, 
            LocalDate.of(2160, 12, 7), TipoRaza.CYBORG.name(),
            "assets/imagenes/foto_doc/carnet_astronauta.png",
            "assets/imagenes/huellas/huella_2.png"
        );
        plantel.add(crearPersonaje(
            "Cmdte. Vance", 178, 76, TipoIngresante.TRABAJADOR, TipoRaza.CYBORG,
            TipoClan.UNION_MAGICA, TipoCiudad.BALES, TipoAspecto.ASTRONAUTA,
            docsVance, false, false
        ));

        // 4. XYLAR EL HUMANO (Impostor - Humano disfrazado de Alien)
        ArrayList<Documento> docsXylar = crearDniEspecifico(
            "Xylar", "Lander", false, LocalDate.of(1990, 1, 1), 42110103, 
            LocalDate.of(2160, 11, 1), TipoRaza.ALIEN.name(), // Intenta hacerse pasar por Alien
            "assets/imagenes/foto_doc/carnet_alien_impostor.png",
            "assets/imagenes/huellas/huella_3.png" // Su huella es de humano, falsificación!
        );
        plantel.add(crearPersonaje(
            "Xylar el Humano", 172, 70, TipoIngresante.TURISTA, TipoRaza.HUMANO,
            TipoClan.PUERTO_UMBRAL, TipoCiudad.LANDER, TipoAspecto.ALIEN_PONELE,
            docsXylar, false, false
        ));

        // 5. LÁZARO (Viajero Pobre - Zorglub le robó sus papeles)
        ArrayList<Documento> docsLazaro = new ArrayList<>(); // Lista de documentos vacía
        plantel.add(crearPersonaje(
            "Lázaro", 168, 60, TipoIngresante.TRABAJADOR, TipoRaza.HUMANO,
            TipoClan.PUERTO_UMBRAL, TipoCiudad.BALES, TipoAspecto.VIAJERO_POBRE,
            docsLazaro, false, false // No es amenaza, solo tuvo mala suerte
        ));

        // 6. ALDEBARÁN (Mago Clan 2 - Guardián en regla)
        ArrayList<Documento> docsAldebaran = crearDniEspecifico(
            "Aldebarán", "Hasgard", true, LocalDate.of(2100, 12, 20), 30112106, 
            LocalDate.of(2165, 11, 15), TipoRaza.GUARDIANES.name(),
            "assets/imagenes/foto_doc/carnet_mago_2.png",
            "assets/imagenes/huellas/huella_alien_2.png"
        );
        plantel.add(crearPersonaje(
            "Aldebarán", 175, 68, TipoIngresante.CIUDADANO, TipoRaza.GUARDIANES,
            TipoClan.UNION_MAGICA, TipoCiudad.HASGARD, TipoAspecto.MAGO_CLAN_2,
            docsAldebaran, false, false
        ));

        // 7. MALAKOR (Mago Clan 3 - Enemigo Terrorista)
        ArrayList<Documento> docsMalakor = crearDniEspecifico(
            "Malakor", "Namek", true, LocalDate.of(2070, 9, 12), 32998107, 
            LocalDate.of(2162, 11, 27), TipoRaza.GUARDIANES.name(),
            "assets/imagenes/foto_doc/carnet_mago_3.png",
            "assets/imagenes/huellas/huella_alien_3.png"
        );
        plantel.add(crearPersonaje(
            "Malakor", 182, 74, TipoIngresante.TURISTA, TipoRaza.GUARDIANES,
            TipoClan.UNION_MAGICA, TipoCiudad.NAMEK, TipoAspecto.MAGO_CLAN_3,
            docsMalakor, true, true // Papeles en regla, pero porta una bomba (Enemigo)
        ));

        // 8. LYRA (Mago Clan 4 - Humana Mágica)
        ArrayList<Documento> docsLyra = crearDniEspecifico(
            "Lyra", "Hasgard", true, LocalDate.of(2018, 9, 12), 44776108, 
            LocalDate.of(2160, 12, 20), TipoRaza.HUMANO.name(),
            "assets/imagenes/foto_doc/carnet_mago_4.png",
            "assets/imagenes/huellas/huella_4.png"
        );
        plantel.add(crearPersonaje(
            "Lyra", 165, 58, TipoIngresante.TRABAJADOR, TipoRaza.HUMANO,
            TipoClan.UNION_MAGICA, TipoCiudad.HASGARD, TipoAspecto.MAGO_CLAN_4,
            docsLyra, false, false
        ));

        // 9. MARCO POLO (Viajero - DNI Vencido)
        ArrayList<Documento> docsMarco = crearDniEspecifico(
            "Marco Polo", "Lander", false, LocalDate.of(2011, 6, 26), 37665109, 
            LocalDate.of(2150, 3, 15), TipoRaza.HUMANO.name(), // Vencido en el 2150
            "assets/imagenes/foto_doc/carnet_viajero.png",
            "assets/imagenes/huellas/huella_5.png"
        );
        plantel.add(crearPersonaje(
            "Marco Polo", 176, 72, TipoIngresante.TURISTA, TipoRaza.HUMANO,
            TipoClan.PUERTO_UMBRAL, TipoCiudad.LANDER, TipoAspecto.VIAJERO,
            docsMarco, false, false
        ));

        // 10. RAGNAR (Vikingo - Ciudadano en regla)
        ArrayList<Documento> docsRagnar = crearDniEspecifico(
            "Ragnar", "Hasgard", true, LocalDate.of(2022, 12, 18), 29334110, 
            LocalDate.of(2161, 1, 2), TipoRaza.HUMANO.name(),
            "assets/imagenes/foto_doc/carnet_vikingo.png",
            "assets/imagenes/huellas/huella_1.png"
        );
        plantel.add(crearPersonaje(
            "Ragnar", 192, 98, TipoIngresante.CIUDADANO, TipoRaza.HUMANO,
            TipoClan.PUERTO_UMBRAL, TipoCiudad.HASGARD, TipoAspecto.VIKINGO,
            docsRagnar, false, false
        ));

        // Mezclamos el orden para que la partida sea distinta cada vez
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

    private static ArrayList<Documento> crearDniEspecifico(
            String nombre, String origen, boolean valido,
            LocalDate fechaNacimiento, int nroDni, LocalDate vencimiento,
            String razaStr, String rutaFoto, String rutaHuella) {
            
        ArrayList<Documento> docs = new ArrayList<>();
        
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
}