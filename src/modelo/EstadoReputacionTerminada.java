package modelo;

public class EstadoReputacionTerminada implements EstadoPartida {

    @Override
    public void usarSello(Partida partida) {
        throw new IllegalStateException("Has cometido muchos errores. Estás despedido. Partida finalizada.");
    }

    @Override
    public void usarMegafono(Partida partida) {
        throw new IllegalStateException("Has cometido muchos errores. Estás despedido. Partida finalizada.");
    }

    @Override
    public void procesarDecision(Partida partida, boolean decisionJugador) throws Exception {
        throw new IllegalStateException("Has cometido muchos errores. Estás despedido. Partida finalizada.");
    }
}