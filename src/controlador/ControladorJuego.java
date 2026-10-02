package controlador;

import vista.Escenario;
import vista.VistaDecision;
import vista.MenuPrincipal;
import vista.VistaConsola;

import modelo.*;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import java.util.ArrayList;
import java.util.List;

public class ControladorJuego {
    
    private final MenuPrincipal menu;
    private final Escenario vista;
    private final VistaDecision vistaDecision;
    private final GestorReputacion gestor;
    private final Resolutor resolutor; // NUEVO: el controlador ya no conoce una política concreta
    private final VistaConsola vistaConsola;

    private Ingresante ingresanteActual; 

    // El controlador recibe las dependencias necesarias
    public ControladorJuego(MenuPrincipal menu, Escenario vista, VistaDecision vistaDecision, GestorReputacion gestor, Resolutor resolutor, VistaConsola vistaConsola) {
        this.menu = menu;
        this.vista = vista;
        this.vistaDecision = vistaDecision;
        this.gestor = gestor;
        this.resolutor = resolutor;
        this.vistaConsola = vistaConsola;

        // Lambdas
        this.menu.getBtnJugar().addActionListener(evento -> this.iniciarJuego());
        this.menu.getBtnTutorial().addActionListener(evento -> System.out.println("El tutorial se implementará pronto."));
        
        this.vista.getBtnDecidir().addActionListener(evento -> this.abrirVentanaDecision());
        
        this.vistaDecision.getBtnAceptar().addActionListener(evento -> this.procesarVeredicto(true));
        this.vistaDecision.getBtnRechazar().addActionListener(evento -> this.procesarVeredicto(false));
    }

    private void iniciarJuego() {
        this.menu.ocultar();
        this.vista.mostrar();
    }

    // --- MÉTODOS PÚBLICOS QUE ATIENDEN A LA LÓGICA DEL JUEGO ---

    /*
    public Ingresante getIngresanteActual() {
        return this.ingresanteActual;
    }
    */

    public void setIngresanteActual(Ingresante ingresante) {
        this.ingresanteActual = ingresante;

        // NUEVO: la VistaConsola muestra el mismo ingresante que está en el juego
        this.vistaConsola.mostrarIngresante(ingresante);
    }

    // --- MÉTODOS PRIVADOS QUE ATIENDEN A LAS LAMBDAS ---

    private void abrirVentanaDecision() {

        if (this.ingresanteActual == null) {
            System.out.println("No hay ingresante en la ventanilla todavía.");
            return;
        }

        this.vistaDecision.mostrar();
    }

    // NUEVO: ahora el veredicto se procesa usando el Resolutor,
    // que evalúa todas las políticas activas y devuelve si el ingresante puede pasar.
    // Luego esa respuesta se compara con la decisión del jugador para saber si acertó.
    private void procesarVeredicto(boolean decisionJugador) {

        // 1. Ocultamos la ventana de decisión
        this.vistaDecision.ocultar();

        try {

            // 2. El Resolutor determina si el ingresante debería pasar
            boolean puedeIngresar = resolutor.puedeIngresar(this.ingresanteActual);

            // 3. GestorReputacion compara la decisión del jugador con el resultado real
            boolean acierto = gestor.evaluarDecision(decisionJugador, puedeIngresar);

            // 4. Vista gráfica
            if (acierto) {

                JOptionPane.showMessageDialog(
                    vista.getVentana(),
                    "¡Decisión correcta! Reputación: " + gestor.getJugador().getReputacion()
                );

            } else {

                JOptionPane.showMessageDialog(
                    vista.getVentana(),
                    "¡Penalización! Reputación: " + gestor.getJugador().getReputacion()
                );
            }

            // 5. NUEVO: mostramos el mismo resultado en la consola
            vistaConsola.mostrarPuedeIngresar(puedeIngresar);
            vistaConsola.mostrarDecisionJugador(decisionJugador);
            vistaConsola.mostrarResultado(acierto, gestor.getJugador().getReputacion());

            // 6. Despachamos al ingresante
            this.ingresanteActual = null;

            // 7. NUEVO: fin de juego si el jugador se quedó sin reputación
            if (gestor.juegoPerdido()) {
                vistaConsola.mostrarFinDeJuego();
                JOptionPane.showMessageDialog(vista.getVentana(), "Te quedaste sin reputación. Fin del juego.");
            System.exit(0); // provisional: se reemplaza cuando exista Partida
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // MAIN: creación y conexión de los objetos principales del juego
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
                // 2. VISTAS
                MenuPrincipal menu = new MenuPrincipal();
                Escenario vistaPrincipal = new Escenario();
                VistaDecision vistaPopUp = new VistaDecision(vistaPrincipal.getVentana());

                // NUEVO: segunda vista del juego, representada por consola
                VistaConsola vistaConsola = new VistaConsola();

                // 3. CONTROLADOR
                ControladorJuego controlador = new ControladorJuego(menu, vistaPrincipal, vistaPopUp, gestor, resolutor, vistaConsola);

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