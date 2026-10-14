package modelo;

import java.util.ArrayList;
import java.util.List;
import modelo.Ingresantes.Ingresante;
import modelo.politicas.Resolutor;

public class Partida {
    private final GestorReputacion gestor;
    private final Resolutor resolutor;
    private Ingresante ingresanteActual;

    private final List<ObservadorPartida> observadores = new ArrayList<>();

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
        boolean puedeIngresar = this.resolutor.puedeIngresar(this.ingresanteActual);
        boolean acierto = this.gestor.evaluarDecision(decisionJugador, puedeIngresar);
        int reputacionActual = this.gestor.getJugador().getReputacion();
        boolean partidaPerdida = this.gestor.juegoPerdido();

        ResultadoDecision resultado = new ResultadoDecision(
            puedeIngresar,acierto,gestor.getReputacion(),gestor.juegoPerdido());

    // Despachamos al ingresante actual porque su turno terminó.
     this.ingresanteActual = null;
     return resultado;
 }

    public boolean suscribir(ObservadorPartida observador){
       return this.observadores.remove(observador);
    }
    
    public boolean desuscribir(ObservadorPartida observador){
        if (observador == null) {
            return false;
        }
        this.observadores.add(observador);
        return true;
    }

}
