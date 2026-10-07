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

                ArrayList<Documento> documentos2 = new ArrayList<>();
                documentos2.add(pasaporte);
                documentos2.add(dni);

                // b. INGRESANTES
                IngresanteRegular ingresante = new IngresanteRegular("Andrea", 170, 63, 6.7, documentos, TipoIngresante.TURISTA, TipoRaza.HUMANO, TipoClan.LLA, TipoCiudad.COMODORO_RIVADAVIA, "assets/imagenes/ingresantes/caballero.png");
                Enemigo enemigo = new Enemigo("Javier", 150, 50, 300, documentos, TipoIngresante.TRABAJADOR, TipoRaza.CYBORG, TipoClan.FENIX, TipoCiudad.LAS_HERAS, "assets/imagenes/ingresantes/caballero.png",  true);

                Jugador jugador = new Jugador("Inspector", 1);
                GestorReputacion gestor = new GestorReputacion(jugador);

                PoliticaClan politicaClan = new PoliticaClan(List.of(TipoClan.JUSTICIALISTA));
                PoliticaIngresante politicaIngresante = new PoliticaIngresante();//NUEVO 02/10

                List<PoliticaFronteriza> politicas = new ArrayList<>();
                politicas.add(politicaClan);
                politicas.add(politicaIngresante);//NUEVO 02/10

                Resolutor resolutor = new Resolutor(politicas);

                Partida partida = new Partida(gestor, resolutor);

                
                // 2. VISTAS
                MenuPrincipal menu = new MenuPrincipal();
                
                Ventanilla ventanilla = new Ventanilla();
                VistaMesaDocumentos vistaMesa = new VistaMesaDocumentos();
                
                Escenario vistaPrincipal = new Escenario(vistaMesa, ventanilla);

                VistaDecision vistaPopUp = new VistaDecision(vistaPrincipal.getVentana());
                
                FabricaVistaDocumento fabricaVistaDocumento = new FabricaVistaDocumento();

                VistaConsola vistaConsola = new VistaConsola();

                // 3. CONTROLADOR
                ControladorJuego controlador = new ControladorJuego(menu, vistaPrincipal, vistaPopUp, vistaConsola, vistaMesa, ventanilla, fabricaVistaDocumento, partida);

                controlador.setIngresanteActual(ingresante);
                controlador.setIngresanteActual(enemigo);

                // 5. Mostramos la ventana
                menu.mostrar();

            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }
}
