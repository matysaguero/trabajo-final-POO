package modelo;

import java.util.ArrayList;
import java.util.List;
import modelo.Ingresantes.Ingresante;
import modelo.politicas.Resolutor;

public class Partida {

    private final GestorReputacion gestor;
    private final Resolutor resolutor;
    private final List<Ingresante> ingresantesIniciales = new ArrayList<>();
    private final List<Ingresante> filaIngresantes = new ArrayList<>();
    private Ingresante ingresanteActual;
    private final List<ObservadorPartida> observadores = new ArrayList<>();

    public Partida(GestorReputacion gestor, Resolutor resolutor) {
        if (gestor == null || resolutor == null) {
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

    // Carga la lista de ingresantes del día
    public void cargarIngresantes(List<Ingresante> ingresantes) {
        if (ingresantes != null) {
            this.ingresantesIniciales.clear();
            this.ingresantesIniciales.addAll(ingresantes);
        }
    }

    // Reinicia la reputación a 10 y vuelve a armar la fila cuando se da a "Jugar"
    public void iniciarPartida() {
        this.gestor.reiniciar();
        this.filaIngresantes.clear();
        this.filaIngresantes.addAll(this.ingresantesIniciales);
        llamarSiguienteIngresante();
    }

    // Pasa al siguiente ingresante de la fila y avisa a la vista (Patrón Observer)
    public boolean llamarSiguienteIngresante() {
        if (this.ingresanteActual != null || this.filaIngresantes.isEmpty()) {
            return false;
        }
        Ingresante siguiente = this.filaIngresantes.remove(0);
        setIngresanteActual(siguiente);
        return true;
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

    public ResultadoDecision procesarDecisionJugador(boolean decisionJugador) throws Exception {
        boolean puedeIngresar = resolutor.puedeIngresar(this.ingresanteActual);
        boolean acierto = gestor.evaluarDecision(decisionJugador, puedeIngresar);

        ResultadoDecision resultado = new ResultadoDecision(
            puedeIngresar,
            acierto,
            gestor.getReputacion(),
            gestor.juegoPerdido()
        );

        // Despachamos al ingresante actual; la ventanilla queda vacía hasta tocar btnLlamar
        this.ingresanteActual = null;

        // Notificamos el veredicto a la vista (limpia mesa/ventanilla y muestra el cartel)
        for (ObservadorPartida obs : this.observadores) {
            obs.actualizarVeredicto(decisionJugador, resultado);
        }

        return resultado;
    }
}