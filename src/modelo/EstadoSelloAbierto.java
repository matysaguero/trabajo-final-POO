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
    public void procesarDecision(Partida partida,boolean decisionJugador) throws Exception {

        ResultadoDecision resultado =partida.resolverTurno(decisionJugador); // Le solicita a Partida que resuelva el turno. resolverTurno() utiliza la lógica del juego para evaluar si el jugador tomó la decisión correcta y actualizar su reputación.
        TipoFinPartida fin = null; // Esta variable almacena el resultado final de la partida, si es que terminó (DERROTA O VICTORIA).

        if (resultado.getPartidaPerdida()) {
            partida.cambiarEstado(new EstadoReputacionTerminada());
            fin = TipoFinPartida.DERROTA;
            } else if (!partida.quedanIngresantes()) {
                partida.cambiarEstado(new EstadoPartidaFinalizada());
                fin = TipoFinPartida.VICTORIA;
        } else {
            partida.cambiarEstado(new EstadoEsperandoLlamado());}

        // Primero informamos el resultado de la decisión.
        partida.notificarVeredicto(decisionJugador, resultado);

        // Después informamos si terminó la partida.
        if (fin != null) {
            partida.notificarFinPartida(fin);
        }
    }

    @Override
    public void cancelarDecision(Partida partida) {
        // Cerraste el panel del sello, por lo tanto, volvemos al estado anterior.
        partida.cambiarEstado(new EstadoAtendiendoIngresante());
    }

    @Override 
    public void solicitarDocumentos(Partida partida){
        throw new IllegalStateException ("Los documentos ya estan en la mesa");
    }
}
