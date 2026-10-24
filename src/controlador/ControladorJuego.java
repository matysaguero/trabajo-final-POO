package controlador;

import modelo.politicas.ReglasDia;
import modelo.Partida;
import modelo.Ingresantes.Ingresante;
import vista.GestorVista;

public class ControladorJuego {
    private final GestorVista ui;
    private final Partida partida;

    public ControladorJuego(GestorVista ui, Partida partida) {
        this.ui = ui;
        this.partida = partida;

        this.partida.suscribir(this.ui);

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
                 this.manejarErrorEstado(e); // MODIFICADO
            }
        });

        this.ui.alHacerClicEnLibroReglas(evento -> {
            try {
                this.ui.alternarLibroReglas(this.partida.getReglasDia());
            } catch (IllegalStateException e) {
                this.manejarErrorEstado(e); // MODIFICADO
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
                // 1. El Modelo gestiona la acción del juego.
                this.partida.solicitarDocumentos();

                // 2. Recuperamos el ingresante actual.
                Ingresante ingresante = this.partida.getIngresanteActual();

                // 3. Enviamos los datos necesarios a la Vista.
                this.ui.desplegarDocumentos(ingresante.getDocumentos(), ingresante.getClan());

            } catch (IllegalStateException e) { this.manejarErrorEstado(e);}});

        this.ui.alHacerClicEnVolverFinPartida(evento -> {
            this.ui.volverAlMenuDesdeFinPartida();
        });

        this.ui.alHacerClicEnAceptarCartel(evento -> this.ui.ocultarCartelReputacion());
        
        // NUEVO: Cerramos el anuncio cuando se toca el ACEPTAR invisible
        this.ui.alHacerClicEnAceptarAnuncio(evento -> this.ui.ocultarAnuncioGrafico());

        this.ui.alHacerClicEnInterrogar(evento -> {
            try {
                String respuesta = this.partida.interrogar();
                if (respuesta.equals("DOCUMENTOS")) {
                   Ingresante ingresante =
        this.partida.getIngresanteActual();

        this.ui.desplegarDocumentos( ingresante.getDocumentos(), ingresante.getClan());
        
        this.ui.mostrarDialogoInterrogatorio("- Inspector: Documentos, por favor.\n- Ingresante: Aquí tiene.");
            } else {
                    this.ui.mostrarDialogoInterrogatorio(respuesta);
                }
            } catch (IllegalStateException e) {
                this.manejarErrorEstado(e); // MODIFICADO
            }
        });
    }

    private void abrirVentanaDecision() {
        try {
            this.partida.usarSello();
            this.ui.mostrarVentanaDecision();
        } catch (IllegalStateException e) {
            this.manejarErrorEstado(e); // MODIFICADO
        }
    }

    private void procesarVeredicto(boolean decisionJugador) {
        this.ui.ocultarVentanaDecision();
        try {
            this.partida.procesarDecisionJugador(decisionJugador);
        } catch (Exception e) {
            this.ui.mostrarError("Error al procesar el veredicto: " + e.getMessage());
        }
    }

    // NUEVO: Enrutador de mensajes. Revisa qué mandó el Modelo y muestra la imagen correspondiente.
    private void manejarErrorEstado(IllegalStateException e) {
        String mensaje = e.getMessage();
        if (mensaje.contains("Primero debes llamar a uno con el meg") || mensaje.contains("No hay ning")) {
            this.ui.mostrarAnuncioGrafico("assets/imagenes/paneles/anuncioSello1.png");
        } 
        else if (mensaje.contains("sin solicitar documentos primero")) {
            this.ui.mostrarAnuncioGrafico("assets/imagenes/paneles/anuncioSello2.png");
        } 
        else if (mensaje.contains("mientras ya hay uno en ventanilla") || mensaje.contains("Ya hay un ingresante")) {
            this.ui.mostrarAnuncioGrafico("assets/imagenes/paneles/anuncioSirena1.png"); 
        } 
        else {
            // Para otros errores ("Partida Finalizada", etc.), se mantiene el cartel estándar
            this.ui.mostrarError(mensaje);
        }
    }
}