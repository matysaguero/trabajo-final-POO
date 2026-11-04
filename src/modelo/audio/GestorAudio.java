package modelo.audio;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public final class GestorAudio {

    private static final String RUTA_MUSICA = "assets/audio/System of a Down - Toxicity - Medieval Style Bardcore.wav";
    private static final GestorAudio INSTANCIA = new GestorAudio();    // SINGLETON: la única instancia, creada una sola vez cuando se carga la clase.
    private final List<ObservadorAudio> observadores = new ArrayList<>(); // OBSERVER: lista de suscriptores
    private Clip clip;         
    private boolean musicaActiva = true;        
    private PantallaJuego pantallaActual = null; 
    private boolean sonando = false;

    // Constructor privado: nadie fuera de esta clase puede crear otra instancia.
    private GestorAudio() {
        cargarMusica();
    }

    public static GestorAudio getInstancia() {
        return INSTANCIA;
    }

    // Si algun recurso falla el juego sigue sin música.
    private void cargarMusica() {
        File archivo = new File(RUTA_MUSICA);
        if (!archivo.isFile()) {
            System.err.println("[Audio] Música deshabilitada: no se encontró " + archivo.getAbsolutePath());
            return;
        }
        try (AudioInputStream flujo = AudioSystem.getAudioInputStream(archivo)) {
            Clip nuevo = AudioSystem.getClip();
            nuevo.open(flujo); // carga todo el audio en memoria
            this.clip = nuevo;

        } catch (Exception e) {
            System.err.println("[Audio] Música deshabilitada: " + e.getMessage());
        }
    }

    // Operaciones del modelo

    // Botón ON/OFF: invierte el estado, ajusta la reproducción y avisa a los observadores. 
    public void alternarMusica() {
        this.musicaActiva = !this.musicaActiva;
        sincronizarReproduccion();
        notificarEstado();
    }

    // La vista informa la pantalla actual; el modelo decide si ahí corresponde música. 
    public void cambiarPantalla(PantallaJuego pantalla) {
        this.pantallaActual = pantalla;
        sincronizarReproduccion();
    }

    // OBSERVER: anota a un suscriptor y le informa de inmediato el estado actual. 
    public void suscribir(ObservadorAudio observador) {
        this.observadores.add(observador);
        observador.actualizarEstadoMusica(this.musicaActiva);
    }

    //Se llama al cerrar el juego para soltar la línea de audio del sistema.
    public void liberarRecursos() {
        if (this.clip != null) {
            this.clip.stop();
            this.clip.close();
        }
    }

    // Lógica interna

    private void sincronizarReproduccion() {
        if (this.clip == null) {
            return; // sin audio cargado no hay nada que reproducir
        }
        boolean debeSonar = this.musicaActiva
                && this.pantallaActual != null
                && this.pantallaActual.tieneMusica();

        if (debeSonar && !this.sonando) {
            this.clip.loop(Clip.LOOP_CONTINUOUSLY); // arranca, o continúa donde quedó
            this.sonando = true;
        } else if (!debeSonar && this.sonando) {
            this.clip.stop();                       // pausa y recuerda la posición
            this.sonando = false;
        }
    }

    // OBSERVER: avisa a todos los suscriptores que cambió el ON/OFF.
    private void notificarEstado() {
        for (ObservadorAudio observador : this.observadores) {
            observador.actualizarEstadoMusica(this.musicaActiva);
        }
    }
}