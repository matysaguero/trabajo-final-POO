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
    private EstadoPartida estado; //NUEVO: state

    public Partida(GestorReputacion gestor, Resolutor resolutor) {
        if (gestor == null || resolutor == null) {
            throw new IllegalArgumentException("El gestor y el resolutor son obligatorios");
        }
        this.gestor = gestor;
        this.resolutor = resolutor;
        this.estado = new EstadoPartidaFinalizada();
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

    // NUEVO: sigue reiniciando la reputación a 10, reconstruye la fila y prepara al primer ingresante, 
    // pero además asigna el estado que corresponde.
    // Esto garantiza dos situaciones:
    // - Si existe un ingresante, la partida comienza en EstadoAtendiendoIngresante.
    // - Si no existen ingresantes, la partida pasa a EstadoPartidaFinalizada.

    public void iniciarPartida() {
        // Reincia la tReputacion
        this.gestor.reiniciar();
        
        // Resconstruye la fila de ingresantes
        this.filaIngresantes.clear();
        this.filaIngresantes.addAll(this.ingresantesIniciales);
        
        // La ventanilla comienza vacia
        this.ingresanteActual = null;
        
        // No se llama automaticamnete al primer ingresante.
        if (this.filaIngresantes.isEmpty()) {
            cambiarEstado(new EstadoPartidaFinalizada());
            } else {
                cambiarEstado(new EstadoEsperandoLlamado());
        }
    }

    // NUEVO: prepararSiguienteIngresante() tiene visibilidad de paquete: las clases State ubicadas en modelo pueden llamarlo, 
    // mientras que el controlador, ubicado en otro paquete no
    boolean prepararSiguienteIngresante() {
        if (this.ingresanteActual != null || this.filaIngresantes.isEmpty()) {
            return false;
        }
            this.ingresanteActual =this.filaIngresantes.remove(0);
        
        for (ObservadorPartida obs : this.observadores) {
            obs.actualizarNuevoIngresante(this.ingresanteActual); 
        }
            return true;   
    }

    //NUEVO: metodos que delegaran acciones en un estado.

    void cambiarEstado(EstadoPartida nuevoEstado) {

        if (nuevoEstado == null) {
             throw new IllegalArgumentException( "El estado no puede ser nulo.");
        }

        // Obtener el nombre del estado anterior.
        String estadoAnterior = this.estado.getClass().getSimpleName();

        // Realizar la transición.
        this.estado = nuevoEstado;

        // Obtener el nombre del estado nuevo.
        String estadoActual = this.estado.getClass().getSimpleName();

        // Mostrar la transición en consola.
        System.out.println(
            "[STATE] " + estadoAnterior + " -> " + estadoActual
        );
    }


    // Comprueba si quedan ingresantes esperando en la fila.
    boolean quedanIngresantes() {
        return !this.filaIngresantes.isEmpty();
    }

    // Notifica el veredicto a los observadores.
    void notificarVeredicto(boolean decisionJugador, ResultadoDecision resultado) {
        for (ObservadorPartida obs : this.observadores) {
            obs.actualizarVeredicto(decisionJugador, resultado);
        }
    }

    public void usarSello() {
        System.out.println( "[EVENTO] Sello presionado. Estado actual: " + this.estado.getClass().getSimpleName());
            
        this.estado.usarSello(this);
    }
    
    public void usarMegafono() {
        System.out.println("[EVENTO] Megáfono presionado. Estado actual: "+ this.estado.getClass().getSimpleName());

        this.estado.usarMegafono(this);
    }

    public void procesarDecisionJugador(boolean decisionJugador) throws Exception {
        this.estado.procesarDecision(this, decisionJugador);
    }

    // Se define nuevo método para poder cerrar el sello y que la partida vuelva al estado anterior.
    public void cancelarDecision(){
        if (this.estado instanceof EstadoSelloAbierto){
            cambiarEstado(new EstadoAtendiendoIngresante());
        }
    }

    public Ingresante getIngresanteActual() {
        return this.ingresanteActual;
    }


    ResultadoDecision resolverTurno(boolean decisionJugador) throws Exception {
        if (this.ingresanteActual == null) {
            throw new IllegalStateException("No hay un ingresante para evaluar.");
        }
        boolean puedeIngresar = this.resolutor.puedeIngresar(this.ingresanteActual);

        boolean acierto = this.gestor.evaluarDecision( decisionJugador, puedeIngresar);

        ResultadoDecision resultado = new ResultadoDecision(puedeIngresar, acierto, this.gestor.getReputacion(), this.gestor.juegoPerdido());

        this.ingresanteActual = null;

        return resultado;
    }
}