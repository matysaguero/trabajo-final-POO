package main;

import controlador.ControladorJuego;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import modelo.GestorReputacion;
import modelo.Jugador;
import modelo.Partida;
import modelo.TipoCiudad;
import modelo.TipoClan;
import modelo.TipoIngresante;
import modelo.TipoRaza;
import modelo.Ingresantes.IngresanteRegular;
import modelo.politicas.PoliticaClan;
import modelo.politicas.PoliticaFronteriza;
import modelo.politicas.PoliticaIngresante;
import modelo.politicas.Resolutor;
import vista.Escenario;
import vista.MenuPrincipal;
import vista.VistaConsola;
import vista.VistaDecision;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            try {

                // 1. MODELOS
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
                Escenario vistaPrincipal = new Escenario();
                VistaDecision vistaPopUp = new VistaDecision(vistaPrincipal.getVentana());

                // NUEVO: segunda vista del juego, representada por consola
                VistaConsola vistaConsola = new VistaConsola();

                // 3. CONTROLADOR
                ControladorJuego controlador = new ControladorJuego(menu, vistaPrincipal, vistaPopUp, vistaConsola, partida);

                // 4. INGRESANTE
                IngresanteRegular ingresante = new IngresanteRegular("Andrea", 170, 63, 6.7, new ArrayList<>(), TipoIngresante.TURISTA, TipoRaza.HUMANO, TipoClan.LLA, TipoCiudad.COMODORO_RIVADAVIA);

                controlador.setIngresanteActual(ingresante);

                // 5. Mostramos la ventana
                menu.mostrar();

            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }
}
