package controlador;

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
        
        this.ui.getBtnTutorial().addActionListener(
            evento -> System.out.println("El tutorial se implementará pronto.")
        );
        
        // NUEVA LÓGICA: Al tocar el boton tutorial, ocultamos el menú y mostramos el tutorial
        this.ui.getBtnTutorial().addActionListener(evento -> {
            this.ui.ocultarMenuPrincipal();
            this.ui.mostrarTutorial();
        });

        // NUEVA LÓGICA: Ya en la vista del tutorial, al tocar el boton de Volver, ocultamos tutorial y mostramos menú
        this.ui.getBtnVolver().addActionListener(evento -> {
            this.ui.ocultarTutorial();;
            this.ui.mostrarMenuPrincipal();
        });

        // Si no hay ingresante en ventanilla, el botón Llamar hace pasar al siguiente
        this.ui.getBtnLlamar().addActionListener(evento -> {
            if (this.partida.getIngresanteActual() == null) {
                boolean hayMas = this.partida.llamarSiguienteIngresante();
                if (!hayMas) {
                    this.ui.mostrarError("¡Ya no quedan más ingresantes en la fila por hoy!");
                }
            }
        });

        this.ui.getBtnDecidir().addActionListener(evento -> this.abrirVentanaDecision());
        
        this.ui.getBtnAceptar().addActionListener(evento -> this.procesarVeredicto(true));
        this.ui.getBtnRechazar().addActionListener(evento -> this.procesarVeredicto(false));
    }

    public void setIngresanteActual(Ingresante ingresante) {
        this.partida.setIngresanteActual(ingresante);
    }

    private void abrirVentanaDecision() {
        if (this.partida.getIngresanteActual() != null) {
            this.ui.mostrarVentanaDecision();
        } else {
            this.ui.mostrarError("No hay ningún ingresante en la ventanilla.");
        }
    }

    private void procesarVeredicto(boolean decisionJugador) {
        this.ui.mostrarVentanaDecision();
        try {
            this.partida.procesarDecisionJugador(decisionJugador);
        } catch (Exception e) {
            this.ui.mostrarError("Error al procesar el veredicto: " + e.getMessage());
        }
    }
}