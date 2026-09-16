package controlador;

//CONTROL DE SISTEMA DE REPUTACION MEDIANTE INTERFACE ES LO IDEAL O N0?
//public abstract interface GestorReputacion {
//    Double sumaReputacion (Double reputacionJugador);

public class GestorReputacion {
    private Jugador jugador;

    public GestorReputacion(Jugador jugador) {
        if (jugador == null) {
            throw new IllegalArgumentException("El jugador no puede ser nulo.");
        }
        this.jugador = jugador;
    }


    public boolean evaluarDecision(boolean decisionDelJugador, boolean puedeIngresar) {
        if (decisionDelJugador == puedeIngresar) {
            jugador.sumaReputacion();
        return true;
        } else {
            jugador.restaReputacion();
        return false;
        }
    }



}
