package controlador;

import java.awt.event.ActionListener;

import modelo.Partida;
import modelo.Ingresantes.Ingresante;
import vista.GestorVista;

public class ControladorJuego {

    private final GestorVista ui;
    private final Partida partida;

    public ControladorJuego(GestorVista ui, Partida partida) {
        this.ui = ui;
        this.partida = partida;

        // CONEXIÓN CLAVE DEL PATRÓN OBSERVER: la vista se suscribe a la partida
        this.partida.suscribir(this.ui);

        // Al darle a Jugar, se muestra el escenario y se inicia/reinicia la partida con 10 de reputación
        this.ui.getBtnJugar().addActionListener(evento -> {
            this.ui.iniciarJuego();
            this.partida.iniciarPartida();
        });

        // Al tocar el botón tutorial, ocultamos el menú y mostramos el tutorial
        this.ui.getBtnTutorial().addActionListener(evento -> {
            this.ui.ocultarMenuPrincipal();
            this.ui.mostrarTutorial();
        });

        // Ya en la vista del tutorial, al tocar el botón de Volver, ocultamos tutorial y mostramos menú
        this.ui.getBtnVolver().addActionListener(evento -> {
            this.ui.ocultarTutorial();
            this.ui.mostrarMenuPrincipal();
        });

        // Si no hay ingresante en ventanilla, el botón Llamar hace pasar al siguiente
        this.ui.getBtnLlamar().addActionListener(evento -> {
            try { 
                this.partida.usarMegafono();
            } 
                catch (IllegalStateException e) { 
                    this.ui.mostrarError(e.getMessage()); 
                }
        });

        
        this.ui.getBtnLibroReglas().addActionListener(evento -> { try {
                this.ui.alternarLibroReglas();
            } catch (IllegalStateException e) {
                this.ui.mostrarError(e.getMessage());
            }
        });

        
        this.ui.getBtnDecidir().addActionListener(evento -> this.abrirVentanaDecision());
        
        this.ui.getBtnVolverDecision().addActionListener(evento -> {
            this.ui.ocultarVentanaDecision();
            this.partida.cancelarDecision();
        });

        this.ui.getBtnAceptar().addActionListener(evento -> this.procesarVeredicto(true));
        this.ui.getBtnRechazar().addActionListener(evento -> this.procesarVeredicto(false));
    }

    /*
    public void setIngresanteActual(Ingresante ingresante) {
        this.partida.setIngresanteActual(ingresante);
    }*/

    private void abrirVentanaDecision() {
        try {
            // Primero se consulta al estado actual.
            this.partida.usarSello();

            // Solo se abre si State permitió la acción.
            this.ui.mostrarVentanaDecision();

            } catch (IllegalStateException e) {

                // Informar que la acción no está permitida.
                this.ui.mostrarError(e.getMessage());
                }
    }

    private void procesarVeredicto(boolean decisionJugador) {
        // Primero cerramos la ventana de los botones Aceptar/Rechazar
        this.ui.ocultarVentanaDecision();
        try {
            this.partida.procesarDecisionJugador(decisionJugador);
        } catch (Exception e) {
            this.ui.mostrarError("Error al procesar el veredicto: " + e.getMessage());
        }
    }
}   