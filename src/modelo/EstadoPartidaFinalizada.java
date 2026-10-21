
package modelo;

public class EstadoPartidaFinalizada implements EstadoPartida {

    @Override
    public void usarSello(Partida partida) {
        throw new IllegalStateException("La partida ha finalizado. No puedes utilizar el sello.");
    }

    // El jugador intenta llamar a otro ingresante.
    @Override
    public void usarMegafono(Partida partida) {
        throw new IllegalStateException("La partida ha finalizado. No puedes llamar a otro ingresante.");
    }

    // El jugador intenta aceptar o rechazar.
    @Override
    public void procesarDecision(Partida partida, boolean decisionJugador) {
        throw new IllegalStateException("La partida ha finalizado. No puedes procesar más decisiones.");
    }

    @Override
    public void cancelarDecision(Partida partida){
        throw new IllegalStateException("La partida ha finalizado. No puedes procesar más decisiones.");
    }

    @Override 
    public void solicitarDocumentos(Partida partida){
        throw new IllegalStateException ("La partida ha finalizado. No puedes solcitar mas documentos");
    }

    @Override
    public String interrogar(Partida partida) {
        throw new IllegalStateException("La partida ha finalizado. No hay a quién interrogar.");
    }
}
