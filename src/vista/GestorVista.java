package vista;

import modelo.ObservadorPartida;
import modelo.ResultadoDecision;
import modelo.Ingresantes.Ingresante;
import modelo.documentos.Documento;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GestorVista implements ObservadorPartida{
    private final MenuPrincipal menu;
    private final Escenario escenario;
    private final VistaDecision vistaDecision;
    private final VistaConsola consola;
    private final VistaMesaDocumentos mesa;
    private final VistaVentanilla ventanilla;
    private final FabricaVistaDocumento fabrica;
    private final VistaTutorial vistaTutorial; //NUEVO: para la vista tutorial, agregado en el constructor y su getter

    public GestorVista(MenuPrincipal menu, Escenario escenario, VistaDecision vistaDecision, VistaConsola consola, VistaMesaDocumentos mesa, VistaVentanilla ventanilla, FabricaVistaDocumento fabrica, VistaTutorial vistaTutorial){
        this.menu = menu;
        this.escenario = escenario;
        this.vistaDecision = vistaDecision;
        this.consola = consola;
        this.mesa = mesa;
        this.ventanilla = ventanilla;
        this.fabrica = fabrica;
        this.vistaTutorial = vistaTutorial;
    }

    public void iniciarJuego(){
        this.menu.ocultar();
        this.escenario.mostrar();
    }

@Override
    public void actualizarNuevoIngresante(Ingresante ingresante) {
        this.mesa.limpiarMesa();
        this.consola.mostrarIngresante(ingresante);
        this.ventanilla.mostrarIngresante(ingresante.getRutaImagen());
        
        int posicionX = 30;
        int posicionY = 30;

        for (Documento documento : ingresante.getDocumentos()) {
            JPanel vistaDoc = this.fabrica.crearVista(documento, posicionX, posicionY);
            this.mesa.agregarDocumentoEnMesa(vistaDoc);
            posicionX += 60; 
            posicionY += 20; 
        }
    }

@Override
    public void actualizarVeredicto(boolean decisionJugador, ResultadoDecision resultado) {
        // 1. Limpiamos la mesa y la ventanilla porque el turno terminó
        this.mesa.limpiarMesa();
        this.ventanilla.limpiarVentanilla();

        // 2. Carteles emergentes en la Vista
        if (resultado.getAcierto()) {
            JOptionPane.showMessageDialog(
                this.escenario.getVentana(),
                "¡Decisión correcta! Reputación: " + resultado.getReputacionActual()
            );
        } else {
            JOptionPane.showMessageDialog(
                this.escenario.getVentana(),
                "¡Penalización! Reputación: " + resultado.getReputacionActual()
            );
        }

        // 3. Actualizamos la vista por consola
        this.consola.mostrarPuedeIngresar(resultado.getPuedeIngresar());
        this.consola.mostrarDecisionJugador(decisionJugador);
        this.consola.mostrarResultado(resultado.getAcierto(), resultado.getReputacionActual());

        // 4. Verificamos fin de juego
        if (resultado.getPartidaPerdida()) {
            this.consola.mostrarFinDeJuego();
            JOptionPane.showMessageDialog(
                this.escenario.getVentana(), 
                "Te quedaste sin reputación (0/10). Volviendo al menú principal."
            );
            this.escenario.ocultar();
            this.menu.mostrar();
        }
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this.escenario.getVentana(), mensaje);
    }

    public MenuPrincipal getMenuPrincipal() {return this.menu;}
    public Escenario getEscenario() {return this.escenario;}
    public VistaDecision getVistaDecision() {return this.vistaDecision;}
    public VistaConsola getVistaConsola() {return this.consola;}
    public VistaMesaDocumentos getVistaMesaDocumentos() {return this.mesa;}
    public VistaVentanilla getVistaVentanilla() {return this.ventanilla;}
    public FabricaVistaDocumento getFabricaVistaDocumento() {return this.fabrica;}
    public VistaTutorial getVistaTutorial() {return this.vistaTutorial;}
}
