
package modelo;

public interface EstadoPartida {

    // El jugador presiona el sello.
    void usarSello(Partida partida);

    // El jugador presiona el megáfono.
    void usarMegafono(Partida partida);

    // El jugador acepta o rechaza al ingresante.
    void procesarDecision(Partida partida, boolean decisionJugador) throws Exception;
}

