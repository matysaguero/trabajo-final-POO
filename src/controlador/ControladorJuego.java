package controlador;

import modelo.politicas.ReglasDia;

import java.util.List;

import javax.swing.SwingWorker;

import modelo.Partida;
import modelo.Puntaje;
import modelo.Ingresantes.Ingresante;
import vista.GestorVista;
import vista.VistaRanking;

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

        this.ui.alHacerClickEnPuntaje(evento -> this.verMejores());

        this.ui.alHacerClicEnTutorial(evento -> {
            this.ui.ocultarMenuPrincipal();
            this.ui.mostrarTutorial();
        });

        this.ui.alHacerClicEnVolverTutorial(evento -> {
            this.ui.ocultarTutorial();
            this.ui.mostrarMenuPrincipal();
        });

        // Lógica de navegación del tutorial
        this.ui.alHacerClicEnSiguienteTutorial(evento -> this.ui.avanzarTutorial());
        this.ui.alHacerClicEnAnteriorTutorial(evento -> this.ui.retrocederTutorial());

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
        
        // Cerramos el anuncio cuando se toca el ACEPTAR invisible
        this.ui.alHacerClicEnAceptarAnuncio(evento -> this.ui.ocultarAnuncioGrafico());

        this.ui.alHacerClicEnInterrogar(evento -> {
            try {
                    String respuesta = this.partida.interrogar();
                    this.ui.mostrarDialogoInterrogatorio(respuesta);
                } catch (IllegalStateException e) {
                    this.manejarErrorEstado(e);
            }
        });

        this.ui.alHacerClicEnGuardar(evento -> {
            
            String nombre = this.ui.pedirNombreJugador();
            if (nombre != null && !nombre.trim().isEmpty()) {
                this.partida.guardarPuntaje(nombre);
                this.ui.mostrarError("¡Puntaje guardado con éxito!");
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
        this.ui.mostrarAnimacionSello(decisionJugador, () -> {
            try {
                this.partida.procesarDecisionJugador(decisionJugador);
            } catch (Exception e) {
                this.ui.mostrarError("Error al procesar el veredicto: " + e.getMessage());
            }
        });
    }

    // Enrutador de mensajes. Revisa qué mandó el Modelo y muestra la imagen correspondiente.
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
        else if (mensaje.contains("Primero debes interrogar al ingresante")) {
            this.ui.mostrarAnuncioGrafico("assets/imagenes/paneles/anuncioSello3.png");
        }
        else {
            // Para otros errores ("Partida Finalizada", etc.), se mantiene el cartel estándar
            this.ui.mostrarError(mensaje);
        }

    }

    private void verMejores() {
        new SwingWorker<List<Puntaje>, Void>() {
            
            @Override
            protected List<Puntaje> doInBackground() {
                return partida.tablaDeHonor(); 
            }
            
            @Override
            protected void done() {
                try {
                    // Volvemos al hilo de la interfaz para dibujar los componentes[cite: 4]
                    List<Puntaje> mejores = get(); 
                    
                    // Instanciamos el diálogo pasándole la lista obtenida
                    VistaRanking tabla = new VistaRanking(ui.getVentanaMenu(), mejores);
                    tabla.mostrar();
                    
                } catch (Exception ex) {
                    System.out.println("Error al cargar puntajes: " + ex.getMessage());
                }
            }
        }.execute();
    }
}