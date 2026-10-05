package modelo;

//CONTROL DE SISTEMA DE REPUTACION MEDIANTE INTERFACE ES LO IDEAL O N0?
//public abstract interface GestorReputacion {
//    Double sumaReputacion (Double reputacionJugador);

public class GestorReputacion {
    private Jugador jugador;
    private static final int PUNTOS_ACEPTAR_CORRECTO = 1;   // aceptó a alguien que podía ingresar
    private static final int PUNTOS_ERROR = -1;             // aceptó a quien no debía, o rechazó a quien sí podía
    private static final int PUNTOS_RECHAZAR_CORRECTO = 0;  // rechazó a alguien que no podía ingresar (cambiar a 1 para premiar atraparlo)

    public GestorReputacion(Jugador jugador) {
        if (jugador == null) {
            throw new IllegalArgumentException("El jugador no puede ser nulo.");
        }
        this.jugador = jugador;
    }


    public boolean evaluarDecision(boolean decisionDelJugador, boolean puedeIngresar) {

        // Se considera acierto cuando la decisión del jugador
        // coincide con el resultado obtenido por las políticas.
        boolean acierto = (decisionDelJugador == puedeIngresar);

        // El GestorReputacion decide cuánto cambia la reputación según la situación ocurrida.
        if (!acierto) {
            // Se equivocó: aceptó a alguien que debía ser rechazado, o rechazó a alguien que podía ingresar.
            jugador.modificarReputacion(PUNTOS_ERROR);

        } else if (decisionDelJugador) {
            // Aceptó correctamente a alguien que podía ingresar.
            jugador.modificarReputacion(PUNTOS_ACEPTAR_CORRECTO);

        } else {
            // Rechazó correctamente a alguien que no podía ingresar.
            jugador.modificarReputacion(PUNTOS_RECHAZAR_CORRECTO);
        }

        return acierto;
    }
    
    
/*     
    public Jugador getJugador() {
        return this.jugador;
    }
*/

    public int getReputacion() {
        return this.jugador.getReputacion();
    }

    public boolean juegoPerdido() {
        return this.jugador.estaSinReputacion();
    }

}
