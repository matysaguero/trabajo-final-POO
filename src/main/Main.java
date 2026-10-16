package main;

import controlador.ControladorJuego;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import modelo.*;
import modelo.Ingresantes.*;
import modelo.politicas.*;
import vista.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // 1. MODELO
                Jugador jugador = new Jugador("Inspector", 10);
                GestorReputacion gestor = new GestorReputacion(jugador);

                // Se prohíbe únicamente el clan ALIEN para permitir el paso de ingresantes regulares válidos
                PoliticaClan politicaClan = new PoliticaClan(List.of(TipoClan.ALIEN));
                PoliticaIngresante politicaIngresante = new PoliticaIngresante();

                List<PoliticaFronteriza> politicas = new ArrayList<>();
                politicas.add(politicaClan);
                politicas.add(politicaIngresante);

                Resolutor resolutor = new Resolutor(politicas);
                Partida partida = new Partida(gestor, resolutor);

                // Cargamos los 10 personajes desde la Fábrica
                partida.cargarIngresantes(FabricaIngresantes.crearPlantelInicial());

                // 2. VISTAS
                MenuPrincipal menu = new MenuPrincipal();
                VistaVentanilla ventanilla = new VistaVentanilla();
                VistaMesaDocumentos vistaMesa = new VistaMesaDocumentos();
                Escenario vistaPrincipal = new Escenario(vistaMesa, ventanilla);
                VistaDecision vistaPopUp = new VistaDecision(vistaPrincipal.getVentana());
                FabricaVistaDocumento fabricaVistaDocumento = new FabricaVistaDocumento();
                VistaConsola vistaConsola = new VistaConsola();
                VistaTutorial vistaTutorial = new VistaTutorial();

                GestorVista ui = new GestorVista(
                    menu, vistaPrincipal, vistaPopUp, vistaConsola,
                    vistaMesa, ventanilla, fabricaVistaDocumento, vistaTutorial
                );

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