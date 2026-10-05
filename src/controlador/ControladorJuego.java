package controlador;

import javax.swing.JOptionPane;
import modelo.*;
import modelo.Ingresantes.Ingresante;
import vista.Escenario;
import vista.MenuPrincipal;
import vista.VistaConsola;
import vista.VistaDecision;
import vista.VistaMesaDocumentos;


public class ControladorJuego {
    
    private final MenuPrincipal menu;
    private final Escenario vista;
    private final VistaDecision vistaDecision;
    private final VistaConsola vistaConsola;
    private final VistaMesaDocumentos vistaMesaDocumentos;
    private final Partida partida; // reemplaza a GestorReputacion, Resolutor y a Ingresante.
    
    // NUEVO: 2/10, Se implemento la clase Partida, para alivianar al controlador y,
    // que solo se encargue de conectar la vista con los modelos.
    
    // El controlador recibe las dependencias necesarias. 
 
    public ControladorJuego(MenuPrincipal menu, Escenario vista, VistaDecision vistaDecision, VistaConsola vistaConsola, VistaMesaDocumentos vistaMesaDocumentos, Partida partida) {
        this.menu = menu;
        this.vista = vista;
        this.vistaDecision = vistaDecision;
        this.vistaConsola = vistaConsola;
        this.partida = partida;
        this.vistaMesaDocumentos = vistaMesaDocumentos;

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



    public void setIngresanteActual(Ingresante ingresante) {
        partida.setIngresanteActual(ingresante);

        // NUEVO: la VistaConsola muestra el mismo ingresante que está en el juego
        this.vistaConsola.mostrarIngresante(ingresante);
    }

    // --- MÉTODOS PRIVADOS QUE ATIENDEN A LAS LAMBDAS ---

    private void abrirVentanaDecision() {

        if (this.partida.getIngresanteActual() == null) {
            System.out.println("No hay ingresante en la ventanilla todavía.");
            return;
        }

        this.vistaDecision.mostrar();
    }

    // NUEVO: ahora el veredicto se procesa usando el Resolutor,
    // que evalúa todas las políticas activas y devuelve si el ingresante puede pasar.
    // Luego esa respuesta se compara con la decisión del jugador para saber si acertó.
    private void procesarVeredicto(boolean decisionJugador) {

        // 1. Ocultamos la ventana de decisión
        this.vistaDecision.ocultar();

        try {

            /* 2. El Resolutor determina si el ingresante debería pasar
            boolean puedeIngresar = resolutor.puedeIngresar(this.ingresanteActual);

              3. GestorReputacion compara la decisión del jugador con el resultado real
            boolean acierto = gestor.evaluarDecision(decisionJugador, puedeIngresar); */
            
            // Esta parte se va reemplazada por un llamado a partida.procesarDecisionJugador que hace exactamente lo
            // mismo pero en otra clase.

            // NUEVO 4/10: Partida procesa toda la decisión y devuelve un ResultadoDecision.
            // Este objeto reúne los resultados del turno para evitar consultar a Partida
            // varias veces por separado. Luego se accede a cada dato mediante sus getters.
            ResultadoDecision resultado = partida.procesarDecisionJugador(decisionJugador);

 

            // 4. Vista gráfica
            if (resultado.getAcierto()) {

                JOptionPane.showMessageDialog(
                    vista.getVentana(),
                    "¡Decisión correcta! Reputación: " + resultado.getReputacionActual()
                );

            } else {

                JOptionPane.showMessageDialog(
                    vista.getVentana(),
                    "¡Penalización! Reputación: " + resultado.getReputacionActual()
                );
            }

            // 5. NUEVO: mostramos el mismo resultado en la consola
            vistaConsola.mostrarPuedeIngresar(resultado.getPuedeIngresar());
            vistaConsola.mostrarDecisionJugador(decisionJugador);
            vistaConsola.mostrarResultado(resultado.getAcierto(),resultado.getReputacionActual());

            // 6. Despachamos al ingresante
            // this.ingresanteActual = null; 
            // NUEVO 2/10: De esto se encarga ya partida.procesarDecisionJugador al final del metodo. 


            // 7. NUEVO: fin de juego si el jugador se quedó sin reputación. DEBE SALIR HACIA LA VISTA.
            if (resultado.getPartidaPerdida()) {
                vistaConsola.mostrarFinDeJuego();
                JOptionPane.showMessageDialog(vista.getVentana(), "Te quedaste sin reputación. Fin del juego.");
            System.exit(0); // provisional: se reemplaza cuando exista Partida
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
}