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
        if (gestor == null || resolutor == null){
            throw new IllegalArgumentException("El gestor y el resolutor son obligatorios");
        }
        this.gestor = gestor;
        this.resolutor = resolutor;
    }

    public boolean suscribir(ObservadorPartida observador) {
        if (observador == null) {
            return false;
        }
        this.observadores.add(observador);
        return true;
    }

    public boolean desuscribir(ObservadorPartida observador) {
       return this.observadores.remove(observador);
    }    

    public void setIngresanteActual(Ingresante ingresante) {
        this.ingresanteActual = ingresante;
        
        if (ingresante != null) {
            for (ObservadorPartida obs : this.observadores) {
                obs.actualizarNuevoIngresante(ingresante);
            }
        }
    }

    public Ingresante getIngresanteActual() {
        return this.ingresanteActual;
    }

// NUEVO 4/10: Procesa un turno completo: evalúa al ingresante actual, compara con la decisión del jugador, actualiza la reputación y devuelve todos los datos del turno en un ResultadoDecision.
    public ResultadoDecision procesarDecisionJugador(boolean decisionJugador) throws Exception {
        boolean puedeIngresar = resolutor.puedeIngresar(this.ingresanteActual);
        boolean acierto = gestor.evaluarDecision(decisionJugador, puedeIngresar);
        int reputacionActual = this.gestor.getJugador().getReputacion();

        ResultadoDecision resultado = new ResultadoDecision(puedeIngresar,acierto,gestor.getReputacion(),gestor.juegoPerdido());
        
        // Despachamos al ingresante actual porque su turno terminó.
        this.ingresanteActual = null;
        
        for (ObservadorPartida obs : this.observadores) {
            obs.actualizarVeredicto(decisionJugador, resultado);
        }
        return resultado;
 }
}
