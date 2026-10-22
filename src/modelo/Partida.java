package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import modelo.Ingresantes.Ingresante;
import modelo.politicas.*;

public class Partida {

    private final GestorReputacion gestor;
    private final Resolutor resolutor;
    private final List<Ingresante> ingresantesIniciales = new ArrayList<>();
    private final List<Ingresante> filaIngresantes = new ArrayList<>();
    private Ingresante ingresanteActual;
    private final List<ObservadorPartida> observadores = new ArrayList<>();
    private EstadoPartida estado; //NUEVO: state
    private ReglasDia reglasActuales;

    public Partida(GestorReputacion gestor, Resolutor resolutor, ReglasDia reglasActuales) {
        if (gestor == null || resolutor == null) {
            throw new IllegalArgumentException("El gestor y el resolutor son obligatorios");
        }
        if (reglasActuales == null) {
            throw new IllegalArgumentException("La partida no puede iniciar sin reglas.");
        }
        this.gestor = gestor;
        this.resolutor = resolutor;
        this.reglasActuales = reglasActuales;
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

        //Se cargan todas las politicas y reglas que se tendran en cuenta durante el dia
        cargarPoliticasDelDia();

        // Resconstruye la fila de ingresantes
        this.filaIngresantes.clear();
        this.filaIngresantes.addAll(this.ingresantesIniciales);
        
        //NUEVO: informa cuántos esperan al comenzar la partida
        notificarIngresantesRestantes();
        
        // La ventanilla comienza vacia
        this.ingresanteActual = null;
        
        // No se llama automaticamnete al primer ingresante.
        if (this.filaIngresantes.isEmpty()) {
            cambiarEstado(new EstadoPartidaFinalizada());
            } else {
                cambiarEstado(new EstadoEsperandoLlamado());
        }
    }

    //NUEVO: notifica cuántos ingresantes quedan esperando en la fila.
    private void notificarIngresantesRestantes() {
        int restantes = this.filaIngresantes.size();
        for (ObservadorPartida obs : this.observadores) {
            obs.actualizarIngresantesRestantes(restantes);
        }
    }
    
    
    // NUEVO: prepararSiguienteIngresante() tiene visibilidad de paquete: las clases State ubicadas en modelo pueden llamarlo, 
    // mientras que el controlador, ubicado en otro paquete no
    boolean prepararSiguienteIngresante() {
        if (this.ingresanteActual != null || this.filaIngresantes.isEmpty()) {
            return false;
        }
            this.ingresanteActual =this.filaIngresantes.remove(0);

            //NUEVO: la fila cambió de tamaño
            notificarIngresantesRestantes();
        
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

    // Notifica a los observadores cuando finaliza la partida.
    void notificarFinPartida(TipoFinPartida resultado) {
        if (resultado == null) {
            throw new IllegalArgumentException("El resultado final no puede ser nulo.");
        }
        for (ObservadorPartida observador : this.observadores) {
            observador.actualizarFinPartida(resultado);
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
    
    public void solicitarDocumentos() {
        System.out.println("[EVENTO] Documentos solicitados. Estado actual: " + this.estado.getClass().getSimpleName());

        this.estado.solicitarDocumentos(this);
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

    public ReglasDia getReglasDia(){
        if (this.reglasActuales == null) {
            return new ReglasDia1();
        } return this.reglasActuales;
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
    
    public String interrogar() {
        return this.estado.interrogar(this);
    }
    
    private void cargarPoliticasDelDia() {
        try {
            this.resolutor.setPoliticas(new ArrayList<>());
            
            LocalDate fechaActual = this.reglasActuales.getFechaDia(); //Depende el dia, la fecha con la que voy a estar comparando.

            //Políticas fijas, siempre se controlan. (discrepancias con nombre, origen, vencimientos) 
            this.resolutor.agregarPolitica(new PoliticaCoherenciaNombre());
            this.resolutor.agregarPolitica(new PoliticaCoherenciaOrigen());
            this.resolutor.agregarPolitica(new PoliticaVencimiento(fechaActual));  
            this.resolutor.agregarPolitica(new PoliticaDocumentacionRequerida());

            //Políticas dinámicas dictadas por el ReglasDia actual, dependiendo el dia, la norma que dicta.
            ReglasDia reglas = getReglasDia();
            
            if (!reglas.getCiudadesProhibidas().isEmpty()) {
                this.resolutor.agregarPolitica(new PoliticaProhibicionCiudad(reglas.getCiudadesProhibidas()));
            }
            if (!reglas.getClanesProhibidos().isEmpty()) {
                this.resolutor.agregarPolitica(new PoliticaProhibicionClan(reglas.getClanesProhibidos()));
            }
            if (!reglas.getRazasProhibidas().isEmpty()) {
                this.resolutor.agregarPolitica(new PoliticaProhibicionRaza(reglas.getRazasProhibidas()));
            }
            
        } catch (Exception e) {
            System.err.println("Error al cargar políticas: " + e.getMessage());
        }
    }
}