package controlador;
import vista.Escenario;
import modelo.Ingresante;
import modelo.IngresanteRegular;
import modelo.TipoCiudad;
import modelo.TipoClan;
import modelo.TipoIngresante;
import modelo.TipoRaza;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// 1. Implementar la interfaz ActionListener
public class ControladorJuego implements ActionListener {
    
    private Escenario vista;
    private GestorReputacion gestor;
    private PoliticaFronteriza politicaActual;
    private Ingresante ingresanteActual; // El ingresante que está en la puerta

    // 2. El constructor recibe la vista y los modelos para conectarlos[cite: 26]
    public ControladorJuego(Escenario vista, GestorReputacion gestor, PoliticaFronteriza politicaActual) {
        this.vista = vista;
        this.gestor = gestor;
        this.politicaActual = politicaActual;

        // 3. Suscribir el controlador a los botones de la vista[cite: 25, 29]
        // La vista expone los botones mediante getters solo para esto[cite: 26, 29].
        this.vista.getBtnAceptar().addActionListener(this);
        this.vista.getBtnRechazar().addActionListener(this);
    }

    // Método para cargar al siguiente ingresante en el juego
    public void setIngresanteActual(Ingresante ingresante) {
        this.ingresanteActual = ingresante;
        // Aquí le diríamos a la vista que actualice el panel de texto
        // this.vista.actualizarDatosIngresante(ingresante.getNombreDeclarado() + "..."); 
    }

    // 4. El método que reacciona a los clics
@Override
    public void actionPerformed(ActionEvent evento) {
        
        // Si no hay nadie en la puerta, no hacemos nada
        if (ingresanteActual == null) {
            System.out.println("No hay ingresantes en este momento.");
            return;
        }

        boolean decisionJugador = false;

        if (evento.getSource() == this.vista.getBtnAceptar()) {
            decisionJugador = true;
            System.out.println("Jugador decidió: ACEPTAR");
        } else if (evento.getSource() == this.vista.getBtnRechazar()) {
            decisionJugador = false;
            System.out.println("Jugador decidió: RECHAZAR");
        }

        // --- LÓGICA DEL JUEGO ---
        boolean esValido = politicaActual.esValido(ingresanteActual); 
        boolean acierto = gestor.evaluarDecision(decisionJugador, esValido);

        if (acierto) {
            System.out.println("¡Decisión correcta! Reputación actual: " + gestor.getJugador().getReputacion());
        } else {
            System.out.println("¡Penalización! Reputación actual: " + gestor.getJugador().getReputacion());
        }
    }
public static void main(String[] args) {
    // 1. Crear los Modelos
    Jugador jugador = new Jugador("Inspector", 3);
    GestorReputacion gestor = new GestorReputacion(jugador);
    PoliticaFronterizaDia1 politicaDia1 = new PoliticaFronterizaDia1("07/09/2026", TipoCiudad.COMODORO_RIVADAVIA, TipoClan.JUSTICIALISTA, TipoRaza.HUMANO);
        
    // 2. Crear la Vista
    Escenario vista = new Escenario();
        
    // 3. Crear el Controlador (uniendo vista y modelos)
    ControladorJuego controlador = new ControladorJuego(vista, gestor, politicaDia1);
        
    // 4. Cargar un ingresante de prueba
    Ingresante ingresantePrueba = new IngresanteRegular("Andrea", 170, 63, 6.7, new ArrayList<>(), TipoIngresante.TURISTA, TipoRaza.HUMANO, TipoClan.LLA, TipoCiudad.COMODORO_RIVADAVIA, 9);
    controlador.setIngresanteActual(ingresantePrueba);

    // 5. Mostrar la ventana
    vista.setVisible(true);
}
}

