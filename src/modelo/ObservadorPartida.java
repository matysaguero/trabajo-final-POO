package modelo;

import modelo.Ingresantes.Ingresante;

public interface ObservadorPartida {
    void actualizarNuevoIngresante(Ingresante ingresante);
    void actualizarVeredicto(boolean decisionJugador, ResultadoDecision resultado);
    void actualizarFinPartida(TipoFinPartida resultado);
    void actualizarIngresantesRestantes(int restante);
    void actualizarDocumentosDisponibles(); // La entrevista terminó: se permite solicitar los documentos.
}