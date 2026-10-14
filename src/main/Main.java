package main;

import controlador.ControladorJuego;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import modelo.*;
import modelo.Ingresantes.*;
import modelo.documentos.*;
import modelo.politicas.*;
import vista.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            try {

                // 1. MODELOS
                // a. DOCUMENTOS
                Pasaporte pasaporte = new Pasaporte("3/10/25", 1, 60, 180, "Argentina", "Comodoro Rivadavia", true);
                DNI dni = new DNI("3/6/2006", 2, true, "huella", "Santiago Del Estero", "masculino", "Ruta 25 de mayo");
                
                
                ArrayList<Documento> documentos = new ArrayList<>();
                documentos.add(pasaporte);
                documentos.add(dni);

                /* ArrayList<Documento> documentos2 = new ArrayList<>();
                documentos2.add(pasaporte);
                documentos2.add(dni); */

                // b. LISTA DE INGRESANTES
                IngresanteRegular ingresante1 = new IngresanteRegular(
                    "Andrea", 170, 63, 6.7, documentos,
                    TipoIngresante.TURISTA, TipoRaza.HUMANO, TipoClan.FENIX,
                    TipoCiudad.COMODORO_RIVADAVIA, "assets/imagenes/ingresantes/caballero.png"
                );

                IngresanteRegular ingresante2 = new IngresanteRegular(
                    "Marcos", 180, 75, 5.0, documentos,
                    TipoIngresante.CIUDADANO, TipoRaza.HUMANO, TipoClan.SPACERS, // Clan prohibido -> debe rechazarse
                    TipoCiudad.TRELEW, "assets/imagenes/ingresantes/caballero.png"
                );

                Enemigo ingresante3 = new Enemigo(
                    "Javier", 150, 50, 300, documentos,
                    TipoIngresante.TRABAJADOR, TipoRaza.CYBORG, TipoClan.ERUDITO,
                    TipoCiudad.LAS_HERAS, "assets/imagenes/ingresantes/caballero.png", true // Es amenaza -> debe rechazarse
                );

                // Jugador arranca con 10 puntos de reputación
                Jugador jugador = new Jugador("Inspector", 10);
                GestorReputacion gestor = new GestorReputacion(jugador);

                PoliticaClan politicaClan = new PoliticaClan(List.of(TipoClan.SPACERS));
                PoliticaIngresante politicaIngresante = new PoliticaIngresante();

                List<PoliticaFronteriza> politicas = new ArrayList<>();
                politicas.add(politicaClan);
                politicas.add(politicaIngresante);

                Resolutor resolutor = new Resolutor(politicas);
                Partida partida = new Partida(gestor, resolutor);
                
                // Cargamos todos los ingresantes en la partida
                partida.cargarIngresantes(List.of(ingresante1, ingresante2, ingresante3));

                
                // 2. VISTAS
                MenuPrincipal menu = new MenuPrincipal();
                
                VistaVentanilla ventanilla = new VistaVentanilla();
                VistaMesaDocumentos vistaMesa = new VistaMesaDocumentos();
                
                Escenario vistaPrincipal = new Escenario(vistaMesa, ventanilla);

                VistaDecision vistaPopUp = new VistaDecision(vistaPrincipal.getVentana());
                
                FabricaVistaDocumento fabricaVistaDocumento = new FabricaVistaDocumento();

                VistaConsola vistaConsola = new VistaConsola();

                GestorVista ui = new GestorVista(menu, vistaPrincipal, vistaPopUp, vistaConsola, vistaMesa, ventanilla, fabricaVistaDocumento);

                // 3. CONTROLADOR
                ControladorJuego controlador = new ControladorJuego(ui, partida);

                // 4. Inicializamos el juego
                controlador.setIngresanteActual(ingresante1);
                //controlador.setIngresanteActual(ingresante2);
                //controlador.setIngresanteActual(ingresante3);

                // 5. Mostramos la ventana
                menu.mostrar();

            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }
}
