package modelo;

import modelo.Ingresantes.Ingresante;

public interface ObservadorPartida{
    void actualizarNuevoIngresante(Ingresante ingresante);
    void actualizarVeredicto(boolean decisionJugador, ResultadoDecision resultado);
}

