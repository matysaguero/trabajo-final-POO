package vista;

import modelo.ObservadorPartida;
import modelo.ResultadoDecision;
import modelo.TipoFinPartida;
import modelo.Ingresantes.Ingresante;
import modelo.documentos.Documento;
import modelo.politicas.ReglasDia;

import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GestorVista implements ObservadorPartida {
    private final MenuPrincipal menu;
    private final Escenario escenario;
    private final VistaDecision vistaDecision;
    private final VistaConsola consola;
    private final VistaMesaDocumentos mesa;
    private final VistaVentanilla ventanilla;
    private final FabricaVistaDocumento fabrica;
    private final FabricaVistaReglas fabricaReglas;
    private final VistaLibroReglas libroReglas;
    private final VistaTutorial vistaTutorial;
    private final VistaFinPartida vistaFinPartida;
    private final VistaCartelReputacion cartelReputacion;
    private final VistaContadorIngresantes contador;

    public GestorVista(MenuPrincipal menu, Escenario escenario, VistaDecision vistaDecision, VistaConsola consola, VistaMesaDocumentos mesa, VistaVentanilla ventanilla, FabricaVistaDocumento fabrica, VistaTutorial vistaTutorial, VistaFinPartida vistaFinPartida, VistaCartelReputacion cartelReputacion, VistaContadorIngresantes contador) {
        this.menu = menu;
        this.escenario = escenario;
        this.vistaDecision = vistaDecision;
        this.consola = consola;
        this.mesa = mesa;
        this.ventanilla = ventanilla;
        this.fabrica = fabrica;
        this.vistaTutorial = vistaTutorial;       
        this.vistaFinPartida = vistaFinPartida;
        this.cartelReputacion = cartelReputacion;
        this.contador = contador;

        this.fabricaReglas = new FabricaVistaReglas();
        this.libroReglas = new VistaLibroReglas(mesa.getPanelDocumentos(), this.fabricaReglas);
    }

    // --- MÉTODOS DE CONTROL DE NAVEGACIÓN Y VISTAS ---
    public void mostrarMenuPrincipal() {
        this.menu.mostrar();
    }

    public void ocultarMenuPrincipal() {
        this.menu.ocultar();
    }

    public void mostrarTutorial() {
        this.vistaTutorial.mostrar();
    }

    public void ocultarTutorial() {
        this.vistaTutorial.ocultar();
    }
    
    public void iniciarJuego() {
        this.menu.ocultar();
        this.mesa.limpiarMesa();
        this.ventanilla.limpiarVentanilla();
        this.escenario.mostrar();
    }

    public void mostrarVentanaDecision() {
        this.vistaDecision.mostrar();
    }

    public void ocultarVentanaDecision() {
        this.vistaDecision.ocultar();
    }
    
    private void mostrarDocumentos() {
        this.escenario.getBtnDocumentos().setVisible(true);
        this.escenario.getBtnDocumentos().setEnabled(true);
    }

    private void ocultarDocumento() {
        this.escenario.getBtnDocumentos().setVisible(false);
        this.escenario.getBtnDocumentos().setEnabled(false);
    }

    public void volverAlMenuDesdeFinPartida() {
        this.vistaFinPartida.ocultar();
        this.menu.mostrar();
    }

    public void alternarLibroReglas(ReglasDia reglasActivas) {
        this.libroReglas.alternar(reglasActivas);
    }

    public void mostrarCartelReputacion(String mensaje) {
        this.cartelReputacion.mostrarMensaje(mensaje);
    }

    public void ocultarCartelReputacion() {
        this.cartelReputacion.ocultar();
    }

    public void alHacerClicEnAceptarCartel(ActionListener listener) {
        this.cartelReputacion.getBtnAceptar().addActionListener(listener);
    }

    // --- MÉTODOS DE LA FACHADA PARA REGISTRAR EVENTOS (SIN EXPONER COMPONENTES SWING) ---
    public void alHacerClicEnJugar(ActionListener listener) {
        this.menu.getBtnJugar().addActionListener(listener);
    }

    public void alHacerClicEnTutorial(ActionListener listener) {
        this.menu.getBtnTutorial().addActionListener(listener);
    }

    public void alHacerClicEnSalir(ActionListener listener) {
        this.menu.getBtnSalir().addActionListener(listener);
    }

    public void alHacerClicEnSalirJuego(ActionListener listener) {
        this.escenario.getBtnSalir().addActionListener(listener);
    }

    public void alHacerClicEnVolverTutorial(ActionListener listener) {
        this.vistaTutorial.getBtnVolver().addActionListener(listener);
    }

    public void alHacerClicEnLlamar(ActionListener listener) {
        this.escenario.getBtnLlamar().addActionListener(listener);
    }

    public void alHacerClicEnDecidir(ActionListener listener) {
        this.escenario.getBtnDecidir().addActionListener(listener);
    }

    public void alHacerClicEnAceptar(ActionListener listener) {
        this.vistaDecision.getBtnAceptar().addActionListener(listener);
    }

    public void alHacerClicEnRechazar(ActionListener listener) {
        this.vistaDecision.getBtnRechazar().addActionListener(listener);
    }

    public void alHacerClicEnVolverDecision(ActionListener listener) {
        this.vistaDecision.getBtnVolver().addActionListener(listener);
    }

    public void alHacerClicEnLibroReglas(ActionListener listener) {
        this.escenario.getBtnLibroReglas().addActionListener(listener);
    }

    public void alHacerClicEnDocumentos(ActionListener listener) {
        this.escenario.getBtnDocumentos().addActionListener(listener);
    }

    public void alHacerClicEnVolverFinPartida(ActionListener listener) {
        this.vistaFinPartida.getBtnVolverMenu().addActionListener(listener);
    }

    public void alHacerClicEnInterrogar(ActionListener listener) {
        this.escenario.getBtnInterrogar().addActionListener(listener);
    }

    // Método para desplegar la ventanita de diálogo
    public void mostrarDialogoInterrogatorio(String dialogo) {
        JOptionPane.showMessageDialog(this.escenario.getVentana(), dialogo, "Interrogatorio", JOptionPane.INFORMATION_MESSAGE);
    }

    // --- MÉTODOS DE LA INTERFAZ OBSERVADOR ---
    @Override
    public void actualizarNuevoIngresante(Ingresante ingresante) {
        this.mesa.limpiarMesa();
        this.consola.mostrarIngresante(ingresante);
        this.ventanilla.mostrarIngresante(ingresante.getRutaImagen());
        this.libroReglas.restaurarSiAbierto();
        mostrarDocumentos();
    }

    public void desplegarDocumentos(Ingresante ingresante) {
        if (ingresante == null || !this.escenario.getBtnDocumentos().isEnabled()) {
            return;
        }

        int posicionX = 30;
        int posicionY = 30;

        for (Documento documento : ingresante.getDocumentos()) {
            JPanel vistaDoc = this.fabrica.crearVista(documento, posicionX, posicionY);
            this.mesa.agregarDocumentoEnMesa(vistaDoc);
            posicionX += 60;
            posicionY += 20;
        }

        ocultarDocumento();
        this.libroReglas.restaurarSiAbierto();
    }

    @Override
    public void actualizarVeredicto(boolean decisionJugador, ResultadoDecision resultado) {
        this.mesa.limpiarMesa();
        this.libroReglas.cerrar();
        this.ventanilla.limpiarVentanilla();
        this.libroReglas.restaurarSiAbierto();

        String mensaje = resultado.getAcierto() 
            ? "<html><center>¡Decisión correcta!<br>Reputación: " + resultado.getReputacionActual() + "</center></html>"  //Para centrar y que el texto aparezca con un ENTER del teclado, es decir, aprezca en 2 lineas distintas (una arriba, otra abajo)
            : "<html><center>¡Penalización!<br>Reputación: " + resultado.getReputacionActual() + "</center></html>";
            
        this.cartelReputacion.mostrarMensaje(mensaje);

        ocultarDocumento();

        this.consola.mostrarPuedeIngresar(resultado.getPuedeIngresar());
        this.consola.mostrarDecisionJugador(decisionJugador);
        this.consola.mostrarResultado(resultado.getAcierto(), resultado.getReputacionActual());

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

    @Override
    public void actualizarFinPartida(TipoFinPartida resultado) {
        this.vistaDecision.ocultar();
        this.libroReglas.cerrar();
        this.escenario.ocultar();
        this.vistaFinPartida.mostrar(resultado);
    }
    @Override
    public void actualizarIngresantesRestantes(int restantes) {
        this.contador.mostrarRestantes(restantes);
    }
}
