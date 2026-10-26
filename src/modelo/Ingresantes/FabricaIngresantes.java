package modelo.Ingresantes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import modelo.*;
import modelo.documentos.*;

public class FabricaIngresantes {

    private static final String RUTA_PLANTILLA_DNI = "assets/imagenes/documento.png";
    private static final String RUTA_PASAPORTE_CA = "assets/imagenes/pasaportes/pasp_ca.jpg";
    private static final String RUTA_PASAPORTE_PU = "assets/imagenes/pasaportes/pasp_pu.jpg";
    private static final String RUTA_PASAPORTE_UM = "assets/imagenes/pasaportes/pasp_um.jpg";

    public static List<Ingresante> crearPlantelInicial() {
        List<Ingresante> plantel = new ArrayList<>();

        // 1. SIR ARTHUR (Ciudadano Humano - Documentacion Autentica)
        // Creo una lista de documentos con:
        // DNI:
        ArrayList<Documento> docsArthur = crearDniEspecifico( "Sir Arthur", "Hasgard", true, LocalDate.of(1990, 5, 10), 40123101, LocalDate.of(2165, 12, 12), TipoRaza.HUMANO.name(), "assets/imagenes/foto_doc/carnet_caballero.png", "assets/imagenes/huellas/huella.png");
        // PERMISO DE ENTRADA:
        docsArthur.add(new Permiso( "Sir Arthur", "Hasgard", true,LocalDate.of(2165, 12, 12), "40123101","Comerciante",1001, TipoClan.PUERTO_UMBRAL));
        // PASAPORTE:
        docsArthur.add(new Pasaporte("Sir Arthur","Hasgard",true,  LocalDate.of(2165, 12, 12), /*Vencimiento*/ LocalDate.of(1990, 5, 10),  /*Nacimiento*/ TipoRaza.HUMANO.name(),"assets/imagenes/huellas/huella.png",TipoClan.PUERTO_UMBRAL,"assets/imagenes/foto_doc/carnet_caballero.png","assets/imagenes/pasaportes/pasp_pu.jpg"));
        // PERSONAJE QUE CONTIENE ESOS DOCUMENTOS:
        plantel.add(crearPersonaje("Sir Arthur", 180, 80, TipoIngresante.CIUDADANO, TipoRaza.HUMANO, TipoClan.PUERTO_UMBRAL, TipoCiudad.HASGARD, TipoAspecto.CABALLERO, docsArthur, false, false, "Vengo a la capital a visitar el mercado."));


        // 2. ZORGLUB (Alien - Enemigo - Le robó el DNI/PERMISO a Lázaro - Docuemntacion humana falsa)
        // DNI:
        ArrayList<Documento> docsZorglub = crearDniEspecifico("Zorglub", "Lander", false, LocalDate.of(1985, 8, 22), 35890102, LocalDate.of(2160, 11, 5), TipoRaza.HUMANO.name(), "assets/imagenes/foto_doc/carnet_viajero2.png", /*Foto de su disfraz o robo*/ "assets/imagenes/huellas/huella_alien.png" /*Huella alienígena, lo delata*/);
        // PERMISO DE ENTRADA:
        docsZorglub.add(new Permiso("Zorglub", "Lander", false, LocalDate.of(2160, 11, 5), "35890102", "Turista", 1002, TipoClan.PUERTO_UMBRAL));
        // PASAPORTE:
        docsZorglub.add(new Pasaporte("Lázaro","Bales",false,LocalDate.of(2158, 11, 5),LocalDate.of(1985, 8, 22),TipoRaza.HUMANO.name(),"assets/imagenes/huellas/huella_alien.png",TipoClan.PUERTO_UMBRAL,"assets/imagenes/foto_doc/carnet_viajero2.png","assets/imagenes/pasaportes/pasp_pu.jpg"));
        // PERSONAJE QUE CONTIENE ESOS DOCUMENTOS:
        plantel.add(crearPersonaje("Zorglub", 195, 65, TipoIngresante.TURISTA, TipoRaza.ALIEN, TipoClan.TITAN_DIGITAL, TipoCiudad.LANDER, TipoAspecto.ALIEN, docsZorglub, true, true, "Eh... soy un humano normal haciendo cosas de humanos." /*Es enemigo y porta bomba*/));


        // 3. CMDTE. VANCE (Trabajador Cyborg - Documentación auténtica)
        // DNI:
        ArrayList<Documento> docsVance = crearDniEspecifico("Cmdte. Vance", "Bales", true, LocalDate.of(2040, 10, 30), 38440104, LocalDate.of(2160, 12, 7), TipoRaza.CYBORG.name(), "assets/imagenes/foto_doc/carnet_astronauta.png", "assets/imagenes/huellas/huella_2.png");
        // PERMISO DE ENTRADA:
        docsVance.add(new Permiso("Cmdte. Vance", "Bales", true, LocalDate.of(2160, 12, 7), "38440104", "Cmdte de estación", 1003, TipoClan.UNION_MAGICA));
        // PASAPORTE:
        docsVance.add(new Pasaporte("Cmdte. Vance","Bales",true,LocalDate.of(2160, 12, 7),LocalDate.of(2040, 10, 30),TipoRaza.CYBORG.name(),"assets/imagenes/huellas/huella_2.png",TipoClan.UNION_MAGICA,"assets/imagenes/foto_doc/carnet_astronauta.png","assets/imagenes/pasaportes/pasp_um.jpg"));
        // PERSONAJE QUE CONTIENE ESOS DOCUMENTOS:
        plantel.add(crearPersonaje("Cmdte. Vance", 178, 76, TipoIngresante.TRABAJADOR, TipoRaza.CYBORG, TipoClan.UNION_MAGICA, TipoCiudad.BALES, TipoAspecto.ASTRONAUTA, docsVance, false, false, "Fui reasignado a esta estación. Vengo por trabajo."));


        // 4. XYLAR EL HUMANO (Humano disfrazado de Alien - Impostor - Documentación alienígena falsa)
        // DNI:
        ArrayList<Documento> docsXylar = crearDniEspecifico("Xylar", "Lander", false, LocalDate.of(1990, 1, 1), 42110103, LocalDate.of(2160, 11, 1), TipoRaza.ALIEN.name(), /*Intenta hacerse pasar por Alien*/ "assets/imagenes/foto_doc/carnet_alien_impostor.png", "assets/imagenes/huellas/huella_3.png"); /*Su huella es de humano, falsificación!*/
        // PERMISO DE ENTRADA:
        docsXylar.add(new Permiso("Xylar", "Lander", false, LocalDate.of(2160, 11, 1), "42110103", "Turista", 1004, TipoClan.TITAN_DIGITAL));
        // PASAPORTE:
        docsXylar.add(new Pasaporte("Xylar","Lander",false,LocalDate.of(2160, 11, 1),LocalDate.of(1990, 1, 1),TipoRaza.ALIEN.name(),"assets/imagenes/huellas/huella_3.png",TipoClan.TITAN_DIGITAL,"assets/imagenes/foto_doc/carnet_alien_impostor.png","assets/imagenes/pasaportes/pasp_ca.jpg"));
        // PERSONAJE QUE CONTIENE ESOS DOCUMENTOS:
        plantel.add(crearPersonaje( "Xylar", 172, 70, TipoIngresante.TURISTA, TipoRaza.HUMANO, TipoClan.PUERTO_UMBRAL, TipoCiudad.LANDER, TipoAspecto.ALIEN_PONELE, docsXylar, false, false, "¡Saludos humano! Digo... colega. Vengo de turismo galáctico."));


        // 5. LÁZARO (Viajero Pobre - Zorglub le robó sus papeles - NO POSEE NINGUN DOCUMENTO)
        // Lista de documentos vacía
        ArrayList<Documento> docsLazaro = new ArrayList<>(); // Lista de documentos vacía
        // PERSONAJE:
        plantel.add(crearPersonaje("Lázaro", 168, 60, TipoIngresante.TRABAJADOR, TipoRaza.HUMANO, TipoClan.PUERTO_UMBRAL, TipoCiudad.BALES, TipoAspecto.VIAJERO_POBRE,docsLazaro, false, false, "Señor, no tengo mis papeles... ¡un alienígena me los robó en la frontera!")); // No es amenaza, solo tuvo mala suerte.


        // 6. ALDEBARÁN (Mago Guardián Clan 2 - Documentación auténtica)
        // DNI:
        ArrayList<Documento> docsAldebaran = crearDniEspecifico("Aldebarán", "Hasgard", true, LocalDate.of(2100, 12, 20), 30112106, LocalDate.of(2165, 11, 15), TipoRaza.GUARDIANES.name(), "assets/imagenes/foto_doc/carnet_mago_2.png", "assets/imagenes/huellas/huella_alien_2.png");
        // PERMISO DE ENTRADA:
        docsAldebaran.add(new Permiso( "Aldebarán", "Hasgard", true, LocalDate.of(2165, 11, 15), "30112106", "Guardián mágico", 1006, TipoClan.UNION_MAGICA));
        // PASAPORTE:
        docsAldebaran.add(new Pasaporte("Aldebarán","Hasgard",true,LocalDate.of(2165, 11, 15),LocalDate.of(2100, 12, 20),TipoRaza.GUARDIANES.name(), "assets/imagenes/huellas/huella_alien_2.png",TipoClan.UNION_MAGICA,"assets/imagenes/foto_doc/carnet_mago_2.png","assets/imagenes/pasaportes/pasp_um.jpg"));
        // PERSONAJE QUE CONTIENE ESOS DOCUMENTOS:
        plantel.add(crearPersonaje("Aldebarán", 175, 68, TipoIngresante.CIUDADANO, TipoRaza.GUARDIANES, TipoClan.UNION_MAGICA, TipoCiudad.HASGARD, TipoAspecto.MAGO_CLAN_2, docsAldebaran, false, false, "Asistiré a la conferencia anual de la Unión Mágica."));


        // 7. MALAKOR (Mago Clan 3 - Enemigo Terrorista - Documentación auténtica)
        // DNI:
        ArrayList<Documento> docsMalakor = crearDniEspecifico( "Malakor", "Namek", true, LocalDate.of(2070, 9, 12), 32998107,  LocalDate.of(2162, 11, 27), TipoRaza.GUARDIANES.name(), "assets/imagenes/foto_doc/carnet_mago_3.png", "assets/imagenes/huellas/huella_alien_3.png");
        // PERMISO DE ENTRADA: 
        docsMalakor.add(new Permiso( "Malakor", "Namek", true, LocalDate.of(2162, 11, 27), "32998107", "Hechicero", 1007, TipoClan.UNION_MAGICA));
        // PASAPORTE:
        docsMalakor.add(new Pasaporte("Malakor","Namek",true,LocalDate.of(2162, 11, 27),LocalDate.of(2070, 9, 12),TipoRaza.GUARDIANES.name(),"assets/imagenes/huellas/huella_alien_3.png",TipoClan.UNION_MAGICA,"assets/imagenes/foto_doc/carnet_mago_3.png","assets/imagenes/pasaportes/pasp_um.jpg"));
        // PERSONAJE QUE CONTIENE ESOS DOCUMENTOS:
        plantel.add(crearPersonaje("Malakor", 182, 74, TipoIngresante.TURISTA, TipoRaza.GUARDIANES,TipoClan.UNION_MAGICA, TipoCiudad.NAMEK, TipoAspecto.MAGO_CLAN_3,docsMalakor, true, true, "No tengo por qué darte explicaciones, oficial... pero si insistes, turismo.")); /*Papeles en regla, pero porta una bomba (Enemigo)*/ 


        // 8. LYRA (Mago Clan 4 - Humana Mágica - Documentación auténtica)
        // DNI:
        ArrayList<Documento> docsLyra = crearDniEspecifico("Lyra", "Hasgard", true, LocalDate.of(2018, 9, 12), 44776108, LocalDate.of(2160, 12, 20), TipoRaza.HUMANO.name(),"assets/imagenes/foto_doc/carnet_mago_4.png","assets/imagenes/huellas/huella_4.png");
        // PERMISO DE ENTRADA:
        docsLyra.add(new Permiso("Lyra", "Hasgard", true,LocalDate.of(2160, 12, 20),"44776108", "Prof. de magia", 1008, TipoClan.UNION_MAGICA));  
        // PASAPORTE:
        docsLyra.add(new Pasaporte("Lyra","Hasgard",true,LocalDate.of(2160, 12, 20),LocalDate.of(2018, 9, 12),TipoRaza.HUMANO.name(),"assets/imagenes/huellas/huella_4.png",TipoClan.UNION_MAGICA,"assets/imagenes/foto_doc/carnet_mago_4.png","assets/imagenes/pasaportes/pasp_um.jpg"));     
        // PERSONAJE QUE CONTIENE ESOS DOCUMENTOS:
        plantel.add(crearPersonaje("Lyra", 165, 58, TipoIngresante.TRABAJADOR, TipoRaza.HUMANO,TipoClan.UNION_MAGICA, TipoCiudad.HASGARD, TipoAspecto.MAGO_CLAN_4,docsLyra, false, false, "Busco trabajo como profesora en la academia de magia."));


        // 9. MARCO POLO (Viajero - DNI Vencido/ Permiso autentico)
        // DNI:
        ArrayList<Documento> docsMarco = crearDniEspecifico("Marco Polo", "Lander", false, LocalDate.of(2011, 6, 26), 37665109, LocalDate.of(2150, 3, 15), TipoRaza.HUMANO.name(),/*Vencido en el 2150*/ "assets/imagenes/foto_doc/carnet_viajero.png","assets/imagenes/huellas/huella_5.png");
        // PERMISO DE ENTRADA:
        docsMarco.add(new Permiso("Marco Polo", "Lander", true, LocalDate.of(2165, 3, 15), "37665109", "Explorador", 1009, TipoClan.PUERTO_UMBRAL));
        // PASAPORTE
        docsMarco.add(new Pasaporte("Marco Polo","Lander",true,LocalDate.of(2165, 3, 15),LocalDate.of(2011, 6, 26),TipoRaza.HUMANO.name(),"assets/imagenes/huellas/huella_5.png",TipoClan.PUERTO_UMBRAL,"assets/imagenes/foto_doc/carnet_viajero.png","assets/imagenes/pasaportes/pasp_pu.jpg"));
        // PERSONAJE QUE CONTIENE ESOS DOCUMENTOS:
        plantel.add(crearPersonaje("Marco Polo", 176, 72, TipoIngresante.TURISTA, TipoRaza.HUMANO, TipoClan.PUERTO_UMBRAL, TipoCiudad.LANDER, TipoAspecto.VIAJERO, docsMarco, false, false, "Vengo a explorar estas tierras. ¡Soy un aventurero!"));
    

        // 10. RAGNAR (Vikingo Ciudadano - Documentación auténtica)
        // DNI:
        ArrayList<Documento> docsRagnar = crearDniEspecifico("Ragnar", "Hasgard", true, LocalDate.of(2022, 12, 18), 29334110, LocalDate.of(2161, 1, 2), TipoRaza.HUMANO.name(), "assets/imagenes/foto_doc/carnet_vikingo.png", "assets/imagenes/huellas/huella_1.png");
        // PERMISO DE ENTRADA:
        docsRagnar.add(new Permiso("Ragnar", "Hasgard", true, LocalDate.of(2161, 1, 2), "29334110", "Guerrero", 1010, TipoClan.PUERTO_UMBRAL));
        // PASAPORTE:
        docsRagnar.add(new Pasaporte("Ragnar","Hasgard",true,LocalDate.of(2161, 1, 2),LocalDate.of(2022, 12, 18),TipoRaza.HUMANO.name(),"assets/imagenes/huellas/huella_1.png",TipoClan.PUERTO_UMBRAL,"assets/imagenes/foto_doc/carnet_vikingo.png","assets/imagenes/pasaportes/pasp_pu.jpg"));
        // PERSONAJE QUE CONTIENE ESOS DOCUMENTOS:
        plantel.add(crearPersonaje("Ragnar", 192, 98, TipoIngresante.CIUDADANO, TipoRaza.HUMANO, TipoClan.PUERTO_UMBRAL, TipoCiudad.HASGARD, TipoAspecto.VIKINGO, docsRagnar, false, false, "Vuelvo a mi hogar después de una larga expedición."));


        // Mezclamos el orden para que la partida sea distinta cada vez
        Collections.shuffle(plantel);
        return plantel;
    }

    // Firma actualizada de crearPersonaje
    public static Ingresante crearPersonaje(
            String nombre, int altura, int peso,
            TipoIngresante tipo, TipoRaza raza, TipoClan clan, TipoCiudad ciudad,
            TipoAspecto aspecto, ArrayList<Documento> docs,
            boolean esEnemigo, boolean portaBomba, String motivoVisita) { // <--- Nuevo parámetro
        
        if (esEnemigo) {
            return new Enemigo(nombre, altura, peso, 1.0, docs, tipo, raza, clan, ciudad, aspecto.getRutaImagen(), motivoVisita, portaBomba);
        } else {
            return new IngresanteRegular(nombre, altura, peso, 1.0, docs, tipo, raza, clan, ciudad, aspecto.getRutaImagen(), motivoVisita);
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