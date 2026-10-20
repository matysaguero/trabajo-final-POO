
package modelo;

public class EstadoEsperandoDocumentos implements EstadoPartida {

    // El jugador solicita los documentos.
    @Override
    public void solicitarDocumentos(Partida partida) {
        partida.cambiarEstado(new EstadoAtendiendoIngresante());
    }

    // No puede usar el sello sin solicitar documentos.
    @Override
    public void usarSello(Partida partida) {
        throw new IllegalStateException("No puede usar el sello sin solicitar documentos primero.");
    }

    // No puede llamar a otro ingresante.
    @Override
    public void usarMegafono(Partida partida) {
        throw new IllegalStateException("Ya hay un ingresante en la ventanilla.");
    }

    // No puede aceptar ni rechazar.
    @Override
    public void procesarDecision(Partida partida, boolean decisionJugador) {
       throw new IllegalStateException("No puede aceptar ni rechazar.");
    }

    // No puede cancelar un sello que todavía no abrió.
    @Override
    public void cancelarDecision(Partida partida) {
        throw new IllegalStateException("El sello todavía no fue abierto.");
    }
}
