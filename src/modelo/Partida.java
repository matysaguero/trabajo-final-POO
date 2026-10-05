package modelo;

import modelo.Ingresantes.Ingresante;
import modelo.politicas.Resolutor;

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

// NUEVO 4/10: Procesa un turno completo: evalúa al ingresante actual, compara con la decisión del jugador, actualiza la reputación y devuelve todos los datos del turno en un ResultadoDecision.
public ResultadoDecision procesarDecisionJugador(boolean decisionJugador) throws Exception {
     boolean puedeIngresar = resolutor.puedeIngresar(this.ingresanteActual);
     boolean acierto = gestor.evaluarDecision(decisionJugador, puedeIngresar);

     ResultadoDecision resultado = new ResultadoDecision(puedeIngresar,acierto,gestor.getReputacion(),gestor.juegoPerdido());

    // Despachamos al ingresante actual porque su turno terminó.
     this.ingresanteActual = null;
     return resultado;
 }
}
