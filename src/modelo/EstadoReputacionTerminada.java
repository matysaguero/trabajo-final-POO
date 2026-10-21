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

    @Override
    public void cancelarDecision(Partida partida) throws Exception {
        throw new IllegalStateException("Has cometido muchos errores. Estás despedido. Partida finalizada.");
    }

    @Override 
    public void solicitarDocumentos(Partida partida){
        throw new IllegalStateException ("Has cometido muchos errores. Estás despedido. Partida finalizada");
    }

    @Override
    public String interrogar(Partida partida) {
        throw new IllegalStateException("Has sido despedido. Devuelve tu placa y retírate.");
    }
}