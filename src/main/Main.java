package main;

import controlador.ControladorJuego;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import java.sql.Connection;

import modelo.*;
import modelo.Ingresantes.*;
import modelo.politicas.*;
import vista.*;
import persistencia.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // 1. MODELO
                Jugador jugador = new Jugador("Inspector", 10);
                GestorReputacion gestor = new GestorReputacion(jugador);

                // Se prohíbe únicamente el clan ALIEN para permitir el paso de ingresantes regulares válidos
                PoliticaProhibicionClan politicaClan = new PoliticaProhibicionClan(List.of(TipoClan.UNION_MAGICA));
                PoliticaIngresante politicaIngresante = new PoliticaIngresante();

                List<PoliticaFronteriza> politicas = new ArrayList<>();
                politicas.add(politicaClan);
                politicas.add(politicaIngresante);

                Resolutor resolutor = new Resolutor(politicas);
                ReglasDia reglasBase = new ReglasVariacion1();
                
                Connection conexion = Conexion.getInstancia().get();
                PuntajeDAO puntajeDAO = new PuntajeDAOSQLite(conexion);
                Partida partida = new Partida(gestor, resolutor, reglasBase, puntajeDAO);

                // Cargamos los 10 personajes desde la Fábrica
                partida.cargarIngresantes(FabricaIngresantes.crearPlantelInicial());

                // 2. VISTAS
                MenuPrincipal menu = new MenuPrincipal();
                VistaRanking vistaRanking = new VistaRanking(menu.getVentana(), puntajeDAO.mejores(4));
                VistaVentanilla ventanilla = new VistaVentanilla();
                VistaMesaDocumentos vistaMesa = new VistaMesaDocumentos();
                VistaContadorIngresantes vistaContador = new VistaContadorIngresantes();
                Escenario vistaPrincipal = new Escenario(vistaMesa, ventanilla, vistaContador);
                VistaDecision vistaPopUp = new VistaDecision(vistaPrincipal.getVentana());
                FabricaVistaDocumento fabricaVistaDocumento = new FabricaVistaDocumento();
                VistaConsola vistaConsola = new VistaConsola();
                VistaTutorial vistaTutorial = new VistaTutorial();
                VistaFinPartida vistaFinPartida = new VistaFinPartida();
                VistaCartelReputacion vistaCartel = new VistaCartelReputacion(vistaPrincipal.getVentana());
                VistaGuardarPuntaje vistaPuntaje = new VistaGuardarPuntaje(vistaFinPartida.getVentana());
                
                // Instanciamos el Anuncio para cuando un usuario haga algo que no debe (ej; usar el sello sin haber solicitado documentos)
                VistaAnuncio vistaAnuncio = new VistaAnuncio(vistaPrincipal.getVentana());
                VistaInterrogatorio vistaInterrogatorio = new VistaInterrogatorio(vistaPrincipal.getVentana());
                VistaAnimacionSello vistaSello = new VistaAnimacionSello(vistaPrincipal.getVentana().getLayeredPane());

                GestorVista ui = new GestorVista(menu, vistaRanking, vistaPrincipal, vistaPopUp, vistaConsola, vistaMesa, ventanilla, 
                    fabricaVistaDocumento, vistaTutorial, vistaFinPartida, vistaCartel, vistaContador, vistaAnuncio, vistaInterrogatorio, vistaSello, vistaPuntaje);
                
                    // 3. CONTROLADOR
                ControladorJuego controlador = new ControladorJuego(ui, partida);

                // 4. MOSTRAR MENÚ INICIAL
                menu.mostrar();



            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}