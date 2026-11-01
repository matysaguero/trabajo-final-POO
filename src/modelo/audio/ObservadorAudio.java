package modelo.audio;

/**
 * Contrato que implementan quienes quieren enterarse de que el estado ON/OFF
 * de la música cambió (patrón Observer, igual que ObservadorPartida).
 * El modelo no sabe quién lo escucha: solo conoce esta interfaz.
 */
public interface ObservadorAudio {

    /** @param musicaActiva true si la música está habilitada (ON), false si está silenciada (OFF). */
    void actualizarEstadoMusica(boolean musicaActiva);
}