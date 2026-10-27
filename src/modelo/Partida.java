package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import modelo.Ingresantes.Ingresante;
import modelo.politicas.*;

public class Partida {

    private final GestorReputacion gestor;
    private final Resolutor resolutor;
    private final List<Ingresante> ingresantesIniciales = new ArrayList<>();
    private final List<Ingresante> filaIngresantes = new ArrayList<>();
    private Ingresante ingresanteActual;
    private final List<ObservadorPartida> observadores = new ArrayList<>();
    private EstadoPartida estado; //State
    private ReglasDia reglasActuales;
    private PuntajeDAO puntajeDAO;

    public Partida(GestorReputacion gestor, Resolutor resolutor, ReglasDia reglasActuales, PuntajeDAO puntajeDAO) {
        if (gestor == null || resolutor == null) {
            throw new IllegalArgumentException("El gestor y el resolutor son obligatorios");
        }
        if (reglasActuales == null) {
            throw new IllegalArgumentException("La partida no puede iniciar sin reglas.");
        }
        if(puntajeDAO == null) {
            throw new IllegalArgumentException("La partida no puede iniciar sin puntaje.");
        }
        this.gestor = gestor;
        this.resolutor = resolutor;
        this.reglasActuales = reglasActuales;
        this.puntajeDAO = puntajeDAO;
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

    // Sigue reiniciando la reputación a 10, reconstruye la fila y prepara al primer ingresante, 
    // pero además asigna el estado que corresponde.
    // Esto garantiza dos situaciones:
    // - Si existe un ingresante, la partida comienza en EstadoAtendiendoIngresante.
    // - Si no existen ingresantes, la partida pasa a EstadoPartidaFinalizada.

    public void iniciarPartida() {
        // Reincia la tReputacion
        this.gestor.reiniciar();
        
        // Elegir aleatoriamente entre las 3 variaciones
        int variacion = new Random().nextInt(3) + 1; // Genera 1, 2 o 3
        switch (variacion) {
            case 1 -> this.reglasActuales = new ReglasVariacion1();
            case 2 -> this.reglasActuales = new ReglasVariacion2();
            case 3 -> this.reglasActuales = new ReglasVariacion3();
        }
        //Se cargan todas las politicas y reglas que se tendran en cuenta durante el dia
        cargarPoliticasDelDia();

        // Resconstruye la fila de ingresantes
        this.filaIngresantes.clear();
        this.filaIngresantes.addAll(this.ingresantesIniciales);

        // informa cuántos esperan al comenzar la partida
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

    // Notifica cuántos ingresantes quedan esperando en la fila.
    private void notificarIngresantesRestantes() {
        int restantes = this.filaIngresantes.size();
        for (ObservadorPartida obs : this.observadores) {
            obs.actualizarIngresantesRestantes(restantes);
        }
    }
    
    
    // prepararSiguienteIngresante() tiene visibilidad de paquete: las clases State ubicadas en modelo pueden llamarlo, 
    // mientras que el controlador, ubicado en otro paquete no
    boolean prepararSiguienteIngresante() {
        if (this.ingresanteActual != null || this.filaIngresantes.isEmpty()) {
            return false;
        }
            this.ingresanteActual =this.filaIngresantes.remove(0);

            // La fila cambió de tamaño
            notificarIngresantesRestantes();
        
        for (ObservadorPartida obs : this.observadores) {
            obs.actualizarNuevoIngresante(this.ingresanteActual); 
        }
            return true;   
    }

    // Metodos que delegaran acciones en un estado.

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
            return new ReglasVariacion1(); 
        } 
        return this.reglasActuales;
    }


    ResultadoDecision resolverTurno(boolean decisionJugador) throws Exception {
        if (this.ingresanteActual == null) {
            throw new IllegalStateException("No hay un ingresante para evaluar.");
        }
        
        // Obtenemos la política que falló (si la hay) y determinamos si ingresa
        PoliticaFronteriza politicaFallida = this.resolutor.obtenerPoliticaFallida(this.ingresanteActual);
        boolean puedeIngresar = (politicaFallida == null);
        
        boolean acierto = this.gestor.evaluarDecision(decisionJugador, puedeIngresar);
        
        // Lógica de mensajes
        String motivoError = "";
        if (!acierto) {
            if (decisionJugador) {
                motivoError = politicaFallida.getMotivoRechazo();
            } else {
                motivoError = "Todos los datos del ingresante estaban en orden y al dia";
            }
        }

        ResultadoDecision resultado = new ResultadoDecision(
            puedeIngresar, acierto, 
            this.gestor.getReputacion(), 
            this.gestor.juegoPerdido(), 
            motivoError
        );
        
        this.ingresanteActual = null;
        return resultado;
    }
    
    public String interrogar() {
        return this.estado.interrogar(this);
    }

    // Visibilidad de paquete: los estados notifican el hecho de dominio.
    // El modelo no conoce botones ni clases concretas de la vista.
    void notificarDocumentosDisponibles() {
        for (ObservadorPartida observador : this.observadores) {
            observador.actualizarDocumentosDisponibles();
        }
    }

    private void cargarPoliticasDelDia() {
        try {
            this.resolutor.setPoliticas(new ArrayList<>());
            
            LocalDate fechaActual = this.reglasActuales.getFechaDia();
            
            // Políticas fijas
            this.resolutor.agregarPolitica(new PoliticaCoherenciaNombre());
            this.resolutor.agregarPolitica(new PoliticaCoherenciaOrigen());
            this.resolutor.agregarPolitica(new PoliticaCoherenciaDni());
            this.resolutor.agregarPolitica(new PoliticaCoherenciaRaza());
            this.resolutor.agregarPolitica(new PoliticaVencimiento(fechaActual));
            this.resolutor.agregarPolitica(new PoliticaNacimientoValido(fechaActual));
            this.resolutor.agregarPolitica(new PoliticaDocumentacionRequerida());
            
            // Políticas dinámicas
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

    public void guardarPuntaje(String nombreJugador) {
        // El puntaje sería la reputación final.
        Puntaje puntaje = new Puntaje(nombreJugador, this.gestor.getReputacion(), LocalDate.now());
        this.puntajeDAO.guardar(puntaje);
    }

    public List<Puntaje> tablaDeHonor() {
        return this.puntajeDAO.mejores(5); 
    }
}