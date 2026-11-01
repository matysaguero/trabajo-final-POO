package controlador;

import modelo.audio.GestorAudio;
import vista.GestorVista;

/**
 * Controlador del audio: coordina Vista y Modelo sin contener lógica de reproducción.
 *
 *  - Vista  -> Modelo: un clic en cualquier botón de música alterna el ON/OFF.
 *  - Vista  -> Modelo: la vista informa qué pantalla muestra; el modelo decide si ahí suena música.
 *  - Modelo -> Vista:  cuando cambia el ON/OFF, el modelo avisa y la vista redibuja los botones.
 */
public class ControladorAudio {

    private final GestorVista ui;
    private final GestorAudio audio;

    public ControladorAudio(GestorVista ui, GestorAudio audio) {
        this.ui = ui;
        this.audio = audio;

        // Modelo -> Vista (Observer). Al suscribirse, la vista recibe el estado actual.
        this.audio.suscribir(this.ui);

        // Evento visual -> acción del modelo.
        this.ui.alHacerClicEnMusica(evento -> this.audio.alternarMusica());

        // La vista reporta la pantalla; el controlador se la pasa al modelo.
        this.ui.alCambiarPantalla(this.audio::cambiarPantalla);
    }
}