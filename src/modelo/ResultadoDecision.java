package modelo;

public class ResultadoDecision {
    private final boolean puedeIngresar;
    private final boolean acierto;
    private final int reputacionActual;
    private final boolean partidaPerdida;
    private final String motivo;

    public ResultadoDecision(boolean puedeIngresar, boolean acierto, int reputacionActual, boolean partidaPerdida, String motivo) {
        this.puedeIngresar = puedeIngresar;
        this.acierto = acierto;
        this.reputacionActual = reputacionActual;
        this.partidaPerdida = partidaPerdida;
        this.motivo = motivo;
    }

    public boolean getPuedeIngresar() { return puedeIngresar; }
    public boolean getAcierto() { return acierto; }
    public int getReputacionActual() { return reputacionActual; }
    public boolean getPartidaPerdida() { return partidaPerdida; }
    
    public String getMotivo() { return motivo; } 
}