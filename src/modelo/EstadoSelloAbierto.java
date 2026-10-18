package modelo;

public class EstadoSelloAbierto implements EstadoPartida {
    
    @Override
    public void usarSello(Partida partida) {
        throw new IllegalStateException( "El sello ya está abierto. Debes aceptar o rechazar al ingresante.");
    }

    @Override
    public void usarMegafono(Partida partida) {
        throw new IllegalStateException("No puedes llamar a otro ingresante mientras hay una decisión pendiente.");
    }

    // El jugador acepta o rechaza al ingresante.
    @Override 
    public void procesarDecision(Partida partida, boolean decisionJugador) throws Exception {

        // Resolver la decisión que toma el jugador con la logica del modelo.
        ResultadoDecision resultado = partida.resolverTurno(decisionJugador);
        // Primero comprueba si perdio la partida (reputación <= 0)
        if (resultado.getPartidaPerdida()) {
            // Cambia al estado específico de derrota por baja reputación
            partida.cambiarEstado(new EstadoReputacionTerminada());
        } else if (!partida.quedanIngresantes()) {
            // Cambia al estado de fin de partida normal (superó el día)
            partida.cambiarEstado(new EstadoPartidaFinalizada());
        } else {
            // La ventanilla queda vacía y vuelve al estado del principio: esperando otro ingresante de la fila.
            partida.cambiarEstado(new EstadoEsperandoLlamado());
        }
        // Notificar a las vistas mediante Observer.
        partida.notificarVeredicto(decisionJugador, resultado);
    }

}
