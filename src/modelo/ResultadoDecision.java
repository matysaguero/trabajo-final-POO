package modelo;

public class ResultadoDecision {
    private final boolean puedeIngresar; // Según las políticas activas del Resolutor, ¿el ingresante debía poder pasar?
    private final boolean acierto; // ¿La decisión del jugador coincidió con puedeIngresar? (true = acertó)
    private final int reputacionActual; // Reputación del jugador DESPUÉS de aplicar el resultado de este turno.
    private final boolean partidaPerdida; // true si el jugador se quedó sin reputación: la partida terminó.

    public ResultadoDecision (boolean puedeIngresar, boolean acierto, int reputacionActual, boolean partidaPerdida) {
        this.puedeIngresar = puedeIngresar;
        this.acierto = acierto;
        this.reputacionActual = reputacionActual;
        this.partidaPerdida = partidaPerdida;
    }

    public boolean getPuedeIngresar() {
        return puedeIngresar;
     }

     public boolean getAcierto() {
        return acierto;
     }

     public int getReputacionActual() {
        return reputacionActual;
     }

     public boolean getPartidaPerdida() {
        return partidaPerdida;
     }
}
    

