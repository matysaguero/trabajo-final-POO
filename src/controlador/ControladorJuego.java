package controlador;

import modelo.politicas.ReglasDia;
import modelo.Partida;
import vista.GestorVista;

public class ControladorJuego {

    private final GestorVista ui;
    private final Partida partida;

    public ControladorJuego(GestorVista ui, Partida partida) {
        this.ui = ui;
        this.partida = partida;

        // CONEXIÓN IMPORTANTE DEL PATRÓN OBSERVER: la vista se suscribe a la partida
        this.partida.suscribir(this.ui);

        // Suscripciones delegadas a través de la Fachada (sin solicitar JButtons)
        this.ui.alHacerClicEnJugar(evento -> {
            this.ui.iniciarJuego();
            this.partida.iniciarPartida();
        });

        this.ui.alHacerClicEnSalir(evento -> System.exit(0));

        this.ui.alHacerClicEnTutorial(evento -> {
            this.ui.ocultarMenuPrincipal();
            this.ui.mostrarTutorial();
        });

        this.ui.alHacerClicEnVolverTutorial(evento -> {
            this.ui.ocultarTutorial();
            this.ui.mostrarMenuPrincipal();
        });

        this.ui.alHacerClicEnSalirJuego(evento -> System.exit(0));

        this.ui.alHacerClicEnLlamar(evento -> {
            try {
                 this.partida.usarMegafono();
            } catch (IllegalStateException e) {
                 this.ui.mostrarError(e.getMessage());
            }
        });

        this.ui.alHacerClicEnLibroReglas(evento -> {
            try {
                this.ui.alternarLibroReglas(this.partida.getReglasDia());
            } catch (IllegalStateException e) {
                this.ui.mostrarError(e.getMessage());
            }
        });

        this.ui.alHacerClicEnDecidir(evento -> this.abrirVentanaDecision());

        this.ui.alHacerClicEnVolverDecision(evento -> {
            this.ui.ocultarVentanaDecision();
            this.partida.cancelarDecision();
        });

        this.ui.alHacerClicEnAceptar(evento -> this.procesarVeredicto(true));

        this.ui.alHacerClicEnRechazar(evento -> this.procesarVeredicto(false));

        this.ui.alHacerClicEnDocumentos(evento -> {
            try {
                this.partida.solicitarDocumentos();
                this.ui.desplegarDocumentos(this.partida.getIngresanteActual());
            } catch (IllegalStateException e) {
                this.ui.mostrarError(e.getMessage());
            }
        });

        this.ui.alHacerClicEnVolverFinPartida(evento -> {
            this.ui.volverAlMenuDesdeFinPartida();
        });

        this.ui.alHacerClicEnAceptarCartel(evento -> this.ui.ocultarCartelReputacion());

    }

    private void abrirVentanaDecision() {
        try {
            // Primero se consulta al estado actual.
            this.partida.usarSello();
            // Solo se abre si State permitió la acción
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