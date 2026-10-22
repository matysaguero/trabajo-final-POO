package modelo;

public class EstadoEsperandoDocumentos implements EstadoPartida {

    @Override
    public void solicitarDocumentos(Partida partida) {
        partida.cambiarEstado(new EstadoAtendiendoIngresante());
    }

    @Override
    public void usarSello(Partida partida) {
        throw new IllegalStateException("No puede usar el sello sin solicitar documentos primero.");
    }

    @Override
    public void usarMegafono(Partida partida) {
        throw new IllegalStateException("No puedes llamar a otro ingresante mientras ya hay uno en ventanilla.");
    }

    @Override
    public void procesarDecision(Partida partida, boolean decisionJugador) {
       throw new IllegalStateException("No puede aceptar ni rechazar.");
    }

    @Override
    public void cancelarDecision(Partida partida) {
        throw new IllegalStateException("El sello todavía no fue abierto.");
    }

    @Override
    public String interrogar(Partida partida) {
        partida.cambiarEstado(new EstadoAtendiendoIngresante());
        return "DOCUMENTOS"; 
    }
}