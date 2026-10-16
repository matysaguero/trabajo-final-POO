
package modelo;

public class EstadoPartidaFinalizada implements EstadoPartida {

    // El jugador intenta utilizar el sello.
    @Override
    public void usarSello(Partida partida) {
        System.out.println("La partida terminó. No puede utilizar el sello.");
    }

    // El jugador intenta llamar a otro ingresante.
    @Override
    public void usarMegafono(Partida partida) {
        System.out.println("La partida terminó. No puede llamar a otro ingresante.");
    }

    // El jugador intenta aceptar o rechazar.
    @Override
    public void procesarDecision(Partida partida, boolean decisionJugador) {
        System.out.println("La partida terminó. No puede procesar decisiones.");
    }

}
