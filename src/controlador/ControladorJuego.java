package controlador;

import modelo.Partida;
import modelo.Ingresantes.Ingresante;
import vista.GestorVista;
import vista.GestorVista;


public class ControladorJuego {
    
    private final GestorVista ui; // Se aplica patron Observer, reemplaza a las 6 vistas definidas hasta ahora.
    private final Partida partida; // reemplaza a GestorReputacion, Resolutor y a Ingresante.
    
    // El controlador recibe las dependencias necesarias. 
 
    public ControladorJuego(GestorVista ui, Partida partida) {
        if (ui == null || partida == null) {
            throw new IllegalArgumentException("La interfaz y la partida son obligatorias");
        }

        this.ui = ui;
        this.partida = partida;
        
        

        // Lambdas
        this.ui.getMenuPrincipal().getBtnJugar().addActionListener(evento -> this.ui.iniciarJuego());
        this.ui.getMenuPrincipal().getBtnTutorial().addActionListener(evento -> System.out.println("El tutorial se implementará pronto."));
        this.ui.getMenuPrincipal().getBtnJugar().addActionListener(evento -> this.ui.iniciarJuego());
        this.ui.getMenuPrincipal().getBtnTutorial().addActionListener(evento -> System.out.println("El tutorial se implementará pronto."));
        
        // this.vista.getBtnLlamar().addActionListener(evento -> );
        this.ui.getEscenario().getBtnDecidir().addActionListener(evento -> this.abrirVentanaDecision());
        this.ui.getEscenario().getBtnDecidir().addActionListener(evento -> this.abrirVentanaDecision());
        
        this.ui.getVistaDecision().getBtnAceptar().addActionListener(evento -> this.procesarVeredicto(true));
        this.ui.getVistaDecision().getBtnRechazar().addActionListener(evento -> this.procesarVeredicto(false));
        this.ui.getVistaDecision().getBtnAceptar().addActionListener(evento -> this.procesarVeredicto(true));
        this.ui.getVistaDecision().getBtnRechazar().addActionListener(evento -> this.procesarVeredicto(false));
    }

    // --- MÉTODOS PÚBLICOS QUE ATIENDEN A LA LÓGICA DEL JUEGO ---

    public void setIngresanteActual(Ingresante ingresante) {
        this.partida.setIngresanteActual(ingresante);
    }

    // --- MÉTODOS PRIVADOS QUE ATIENDEN A LAS LAMBDAS ---

    private void abrirVentanaDecision() {
        if (this.partida.getIngresanteActual() != null) {
            this.ui.getVistaDecision().mostrar();
        } else {
            this.ui.mostrarError("No hay ingresante en ventanilla.");
        }
    }

    private void procesarVeredicto(boolean decisionJugador) {
        
        this.ui.getVistaDecision().ocultar();
        
        this.ui.getVistaDecision().ocultar();

        try {
           // El controlador solo le avisa solo a Partida y partida se encarga de modificar las vistas.
            this.partida.procesarDecisionJugador(decisionJugador);
            // 4. Vista gráfica
        } catch (Exception e) {
            this.ui.mostrarError("Error al procesar veredicto " + e.getMessage());;
        }
    }
     
}