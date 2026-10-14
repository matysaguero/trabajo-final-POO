package controlador;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modelo.Partida;
import modelo.ResultadoDecision;
import modelo.Ingresantes.Ingresante;
import vista.GestorVista;


public class ControladorJuego {
    
    private final GestorVista ui; // Se aplica patron Observer, reemplaza a las 6 vistas definidas hasta ahora.
    private final Partida partida; // reemplaza a GestorReputacion, Resolutor y a Ingresante.
    
    // El controlador recibe las dependencias necesarias. 
 
    public ControladorJuego(GestorVista ui, Partida partida) {
        this.ui = ui;
        this.partida = partida;
        

        // Lambdas
        this.ui.getMenuPrincipal().getBtnJugar().addActionListener(evento -> this.ui.iniciarJuego());
        this.ui.getMenuPrincipal().getBtnTutorial().addActionListener(evento -> System.out.println("El tutorial se implementará pronto."));
        
        // this.vista.getBtnLlamar().addActionListener(evento -> );
        this.ui.getEscenario().getBtnDecidir().addActionListener(evento -> this.abrirVentanaDecision());
        
        this.ui.getVistaDecision().getBtnAceptar().addActionListener(evento -> this.procesarVeredicto(true));
        this.ui.getVistaDecision().getBtnRechazar().addActionListener(evento -> this.procesarVeredicto(false));
    }

    // --- MÉTODOS PÚBLICOS QUE ATIENDEN A LA LÓGICA DEL JUEGO ---

    public void setIngresanteActual(Ingresante ingresante) {
        this.partida.setIngresanteActual(ingresante);
        this.ui.actualizarNuevoIngresante(ingresante);
    }

    // --- MÉTODOS PRIVADOS QUE ATIENDEN A LAS LAMBDAS ---

    private void abrirVentanaDecision() {
        if (this.partida.getIngresanteActual() != null) {
            
            this.ui.getVistaDecision().mostrar();   
        
        } else {
            
            System.out.println("No hay ingresante en ventanilla.");
        }
    }

    // NUEVO: ahora el veredicto se procesa usando el Resolutor,
    // que evalúa todas las políticas activas y devuelve si el ingresante puede pasar.
    // Luego esa respuesta se compara con la decisión del jugador para saber si acertó.
    
    private void procesarVeredicto(boolean decisionJugador) {
        
        this.ui.getVistaDecision().ocultar();

        try {
           // El controlador solo le avisa al modelo.
            this.partida.procesarDecisionJugador(decisionJugador);

        } catch (Exception e) {
            this.ui.mostrarError("Error al procesar el veredicto: "+ e.getMessage());
        }
    }
     
}