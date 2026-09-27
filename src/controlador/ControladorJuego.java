package controlador;

import vista.Escenario;
import modelo.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;

// Ya no necesita "implements ActionListener"[cite: 30]
public class ControladorJuego {
    
    private Escenario vista;
    private GestorReputacion gestor;
    private PoliticaFronteriza politicaActual;
    private Ingresante ingresanteActual; 

    public ControladorJuego(Escenario vista, GestorReputacion gestor, PoliticaFronteriza politicaActual) {
        this.vista = vista;
        this.gestor = gestor;
        this.politicaActual = politicaActual;

        // Delegación de eventos usando Lambdas: Un receptor por evento, escrito donde se registra[cite: 30].
        this.vista.getBtnRequisitos().addActionListener(evento -> this.leerRequisitos());
        this.vista.getBtnRevisar().addActionListener(evento -> this.revisarPapeles());
        this.vista.getBtnDecidir().addActionListener(evento -> this.tomarDecision());
    }

    public void setIngresanteActual(Ingresante ingresante) {
        this.ingresanteActual = ingresante;
        System.out.println("Un nuevo ingresante se acerca a la ventanilla...");
    }

    // --- MÉTODOS PRIVADOS PARA CADA BOTÓN (Como sugiere la Clase 10)[cite: 30] ---

    private void leerRequisitos() {
        System.out.println("\n--- LEYENDO REQUISITOS DEL DÍA ---");
        politicaActual.mostrarDetalle();
    }

    private void revisarPapeles() {
        if (ingresanteActual != null) {
            System.out.println("\n--- REVISANDO PAPELES ---");
            ingresanteActual.mostrarDetalle();
        }
    }

    private void tomarDecision() {
        if (ingresanteActual == null) return;

        Object[] opciones = {"Aceptar (Sello Verde)", "Rechazar (Sello Rojo)"};
        int eleccion = JOptionPane.showOptionDialog(vista,
                "¿Cuál es el veredicto para " + ingresanteActual.getNombreDeclarado() + "?",
                "Decisión", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, opciones, opciones[0]);

        if (eleccion == JOptionPane.CLOSED_OPTION) return; 
        
        boolean decisionJugador = (eleccion == JOptionPane.YES_OPTION);
        
        // El controlador modifica el modelo[cite: 36]
        boolean esValido = politicaActual.esValido(ingresanteActual); 
        boolean acierto = gestor.evaluarDecision(decisionJugador, esValido);

        if (acierto) {
            JOptionPane.showMessageDialog(vista, "¡Decisión correcta! Reputación: " + gestor.getJugador().getReputacion());
        } else {
            JOptionPane.showMessageDialog(vista, "¡Penalización! Reputación: " + gestor.getJugador().getReputacion());
        }
        
        this.ingresanteActual = null; 
    }

    // MAIN: El único lugar donde aparecen los new de las tres clases[cite: 34]
    public static void main(String[] args) {
        Jugador jugador = new Jugador("Inspector", 3);
        GestorReputacion gestor = new GestorReputacion(jugador);
        PoliticaFronterizaDia1 politica = new PoliticaFronterizaDia1("07/09/2026", TipoCiudad.COMODORO_RIVADAVIA, TipoClan.JUSTICIALISTA, TipoRaza.HUMANO);
            
        Escenario vista = new Escenario();
        ControladorJuego controlador = new ControladorJuego(vista, gestor, politica);
            
        IngresanteRegular ingresante = new IngresanteRegular("Andrea", 170, 63, 6.7, new ArrayList<>(), TipoIngresante.TURISTA, TipoRaza.HUMANO, TipoClan.LLA, TipoCiudad.COMODORO_RIVADAVIA, 9);
        controlador.setIngresanteActual(ingresante);

        // Se muestra la ventana tras conectar todo[cite: 34]
        vista.setVisible(true);
    }
}