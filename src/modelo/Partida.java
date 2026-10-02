package modelo;



public class Partida {
    private final GestorReputacion gestor;
    private final Resolutor resolutor;
    private Ingresante ingresanteActual;

    public Partida(GestorReputacion gestor, Resolutor resolutor) {
        this.gestor = gestor;
        this.resolutor = resolutor;
    }

    public void setIngresanteActual(Ingresante ingresante) {
        this.ingresanteActual = ingresante;
    }

    public Ingresante getIngresanteActual() {
        return this.ingresanteActual;
    }

    // Procesa la decisión y devuelve true si el jugador acertó, o false si se equivocó
    public boolean procesarDecisionJugador(boolean decisionJugador) throws Exception {
        boolean puedeIngresar = resolutor.puedeIngresar(this.ingresanteActual);
        boolean acierto = gestor.evaluarDecision(decisionJugador, puedeIngresar);
        
        // Despachamos al ingresante actual porque su turno terminó
        this.ingresanteActual = null; 
        
        return acierto;
    }

    public boolean estaPerdida() {
        return gestor.juegoPerdido();
    }

    public int getReputacionActual() {
        return gestor.getJugador().getReputacion();
    }
}
