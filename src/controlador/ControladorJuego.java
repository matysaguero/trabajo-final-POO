package controlador;

import vista.Escenario;
import vista.VistaDecision;
import vista.MenuPrincipal;
import modelo.*;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.util.ArrayList;
import java.util.List;

public class ControladorJuego {
    
    private final MenuPrincipal menu;
    private final Escenario vista;
    private final VistaDecision vistaDecision;
    private final GestorReputacion gestor;
    private final Resolutor resolutor; //NUEVO: cambiar la politica individual por este atributo. El controlador no conoce PoliticaClan directamente
    
    private Ingresante ingresanteActual; 

    // El controlador recibe también el menú principal por inyección de dependencias
    public ControladorJuego(MenuPrincipal menu, Escenario vista, VistaDecision vistaDecision, GestorReputacion gestor, Resolutor resolutor) { //NUEVO: el constructor recibe el Resolutor
        this.menu = menu;
        this.vista = vista;
        this.vistaDecision = vistaDecision;
        this.gestor = gestor;
        this.resolutor = resolutor;

        // Lambdas
        this.menu.getBtnJugar().addActionListener(evento -> this.iniciarJuego());
        this.menu.getBtnTutorial().addActionListener(evento -> System.out.println("El tutorial se implementará pronto."));
        
        this.vista.getBtnDecidir().addActionListener(evento -> this.abrirVentanaDecision());
        
        this.vistaDecision.getBtnAceptar().addActionListener(evento -> this.procesarVeredicto(true));
        this.vistaDecision.getBtnRechazar().addActionListener(evento -> this.procesarVeredicto(false));
    }

    private void iniciarJuego() {
        this.menu.ocultar();
        this.vista.mostrar();
    }

    // --- MÉTODOS PÚBLICOS QUE ATIENDEN A LA LÓGICA DEL JUEGO ---

    /* getIngresanteActual() {
        return this.ingresanteActual; --------------------------> tenemos que implementar esto despues 28/09/26
    } */               

    public void setIngresanteActual(Ingresante ingresante) {
        this.ingresanteActual = ingresante;
        System.out.println("Un nuevo ingresante se acerca a la ventanilla...");
    }

    // --- MÉTODOS PRIVADOS QUE ATIENDEN A LAS LAMBDAS ---

    private void abrirVentanaDecision() {
        if (this.ingresanteActual == null) {
            System.out.println("No hay ingresante en la ventanilla todavía.");
            return;
        }
        // Mostramos un pop-up por asi decirlo, el juego se pausa aquí hasta que se elija una opción
        this.vistaDecision.mostrar();
    }


    // NUEVO: ahora el veredicto se procesa usando el Resolutor,
    // que evalúa todas las políticas activas y devuelve si el ingresante puede pasar.
    // Luego esa respuesta se compara con la decisión del jugador para saber si acertó.
    private void procesarVeredicto(boolean decisionJugador) {       

    // 1. Para ocultar la ventanita
    this.vistaDecision.ocultar();

    try {

        // 2. Evaluamos la lógica del negocio
        boolean puedeIngresar = resolutor.puedeIngresar(this.ingresanteActual);
        boolean acierto = gestor.evaluarDecision(decisionJugador, puedeIngresar);

        // 3. Mostramos feedback
        if (acierto) {
            JOptionPane.showMessageDialog(
                vista.getVentana(),
                "¡Decisión correcta! Reputación: " + gestor.getJugador().getReputacion()
            );
        } else {
            JOptionPane.showMessageDialog(
                vista.getVentana(),
                "¡Penalización! Reputación: " + gestor.getJugador().getReputacion()
            );
        }

        // 4. Despachamos al ingresante
        this.ingresanteActual = null;

    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    // MAIN: El único lugar donde aparecen los new de las tres clases
    public static void main(String[] args) {
        // Ejecución en el hilo de Swing como recomienda la cátedra
        SwingUtilities.invokeLater(() -> {

            try { 

            // 1. Modelos
            Jugador jugador = new Jugador("Inspector", 3);
            GestorReputacion gestor = new GestorReputacion(jugador);
            PoliticaClan politicaClan = new PoliticaClan(List.of(TipoClan.JUSTICIALISTA));

            List<PoliticaFronteriza> politicas = new ArrayList<>();
            politicas.add(politicaClan);
            Resolutor resolutor = new Resolutor(politicas);
                
            // 2. Vistas
            MenuPrincipal menu = new MenuPrincipal();
            Escenario vistaPrincipal = new Escenario();
            VistaDecision vistaPopUp = new VistaDecision(vistaPrincipal.getVentana());
            
            // 3. Controlador
            ControladorJuego controlador = new ControladorJuego(menu, vistaPrincipal, vistaPopUp, gestor, resolutor);
                
            IngresanteRegular ingresante = new IngresanteRegular("Andrea", 170, 63, 6.7, new ArrayList<>(), TipoIngresante.TURISTA, TipoRaza.HUMANO, TipoClan.LLA, TipoCiudad.COMODORO_RIVADAVIA, 9);
            controlador.setIngresanteActual(ingresante);

        // Se muestra la ventana tras conectar todo
        menu.mostrar();

         } catch (Exception e) { 
                e.printStackTrace();
            }
    });
}
}