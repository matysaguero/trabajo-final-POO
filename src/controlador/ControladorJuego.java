package controlador;

import vista.Escenario;
import vista.VistaDecision;
import vista.MenuPrincipal;
import modelo.*;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.util.ArrayList;

public class ControladorJuego {
    
    private final MenuPrincipal menu;
    private final Escenario vista;
    private final VistaDecision vistaDecision;
    private final GestorReputacion gestor;
    private final PoliticaFronteriza politicaActual;
    
    private Ingresante ingresanteActual; 

    // El controlador recibe también el menú principal por inyección de dependencias
    public ControladorJuego(MenuPrincipal menu, Escenario vista, VistaDecision vistaDecision, GestorReputacion gestor, PoliticaFronteriza politicaActual) {
        this.menu = menu;
        this.vista = vista;
        this.vistaDecision = vistaDecision;
        this.gestor = gestor;
        this.politicaActual = politicaActual;

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

    /* getIngresanteActual() {
        return this.ingresanteActual; --------------------------> tenemos que implementar esto despues 28/09/26
    } */               

    public void setIngresanteActual(Ingresante ingresante) {
        this.ingresanteActual = ingresante;
        System.out.println("Un nuevo ingresante se acerca a la ventanilla...");
    }

    // --- MÉTODOS PRIVADOS QUE ATIENDEN A LAS LAMBDAS ---

    private void abrirVentanaDecision() {
        if (this.ingresanteActual == null) {
            System.out.println("No hay ingresante en la ventanilla todavía.");
            return;
        }
        // Mostramos un pop-up por asi decirlo, el juego se pausa aquí hasta que se elija una opción
        this.vistaDecision.mostrar();
    }

    private void procesarVeredicto(boolean decisionJugador) {
        // 1. Para ocultar la ventanita
        this.vistaDecision.ocultar();
        
        // 2. Evaluamos la lógica del negocio
        boolean esValido = politicaActual.esValido(this.ingresanteActual); 
        boolean acierto = gestor.evaluarDecision(decisionJugador, esValido);

        // 3. Mostramos feedback que seria temporal de momento, pero que sirve para ver que la lógica funciona. En el juego final esperaremos esto sería reemplazado por animaciones, sonidos, etc.
        if (acierto) {
            JOptionPane.showMessageDialog(vista.getVentana(), "¡Decisión correcta! Reputación: " + gestor.getJugador().getReputacion());
        } else {
            JOptionPane.showMessageDialog(vista.getVentana(), "¡Penalización! Reputación: " + gestor.getJugador().getReputacion());
        }
        
        // 4. Despachamos al ingresante
        this.ingresanteActual = null; 
    }

    // El único lugar donde aparecen los "new" de las tres capas
    public static void main(String[] args) {
        // Ejecución en el hilo de Swing como recomienda la cátedra
        SwingUtilities.invokeLater(() -> {
            // 1. Modelos
            Jugador jugador = new Jugador("Inspector", 3);
            GestorReputacion gestor = new GestorReputacion(jugador);
            PoliticaFronterizaDia1 politica = new PoliticaFronterizaDia1("07/09/2026", TipoCiudad.COMODORO_RIVADAVIA, TipoClan.JUSTICIALISTA, TipoRaza.HUMANO);
                
            // 2. Vistas
            MenuPrincipal menu = new MenuPrincipal();
            Escenario vistaPrincipal = new Escenario();
            VistaDecision vistaPopUp = new VistaDecision(vistaPrincipal.getVentana());
            
            // 3. Controlador
            ControladorJuego controlador = new ControladorJuego(menu, vistaPrincipal, vistaPopUp, gestor, politica);
                
            IngresanteRegular ingresante = new IngresanteRegular("Andrea", 170, 63, 6.7, new ArrayList<>(), TipoIngresante.TURISTA, TipoRaza.HUMANO, TipoClan.LLA, TipoCiudad.COMODORO_RIVADAVIA, 9);
            controlador.setIngresanteActual(ingresante);

            // 4. Mostrar el punto de entrada inicial
            menu.mostrar();
        });
    }
}