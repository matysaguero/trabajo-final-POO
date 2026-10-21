package vista;

import modelo.ObservadorPartida;
import modelo.ResultadoDecision;
import modelo.TipoFinPartida;
import modelo.Ingresantes.Ingresante;
import modelo.documentos.Documento;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;

public class GestorVista implements ObservadorPartida{
    private final MenuPrincipal menu;
    private final Escenario escenario;
    private final VistaDecision vistaDecision;
    private final VistaConsola consola;
    private final VistaMesaDocumentos mesa;
    private final VistaVentanilla ventanilla;
    private final FabricaVistaDocumento fabrica;
    private final VistaTutorial vistaTutorial;
    private final VistaLibroReglas libroReglas;
    private final VistaFinPartida vistaFinPartida;


    public GestorVista(MenuPrincipal menu, Escenario escenario, VistaDecision vistaDecision, VistaConsola consola, VistaMesaDocumentos mesa, VistaVentanilla ventanilla, FabricaVistaDocumento fabrica, VistaTutorial vistaTutorial, VistaFinPartida vistaFinPartida){
        this.menu = menu;
        this.escenario = escenario;
        this.vistaDecision = vistaDecision;
        this.consola = consola;
        this.mesa = mesa;
        this.ventanilla = ventanilla;
        this.fabrica = fabrica;
        this.vistaTutorial = vistaTutorial;       
        this.libroReglas = new VistaLibroReglas( mesa.getPanelDocumentos() );
        this.vistaFinPartida = vistaFinPartida;   
    }

    // METODOS FACHADA CONTROL DE VISTAS
    public void mostrarMenuPrincipal(){
        this.menu.mostrar();
    }

    public void ocultarMenuPrincipal(){
        this.menu.ocultar();
    }

    public void mostrarTutorial(){
        this.vistaTutorial.mostrar();
    }

    public void ocultarTutorial(){
        this.vistaTutorial.ocultar();
    }
    
    public void iniciarJuego(){
        this.menu.ocultar();

        // Limpiar los elementos de la atención anterior.
        this.mesa.limpiarMesa();
        this.ventanilla.limpiarVentanilla();

        // Mostrar el puesto de control vacío.
        this.escenario.mostrar();
    }

    public void mostrarVentanaDecision(){
        this.vistaDecision.mostrar();
    }

    public void ocultarVentanaDecision(){
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

    //NUEVO
    public void volverAlMenuDesdeFinPartida() {
        this.vistaFinPartida.ocultar();
        this.menu.mostrar();
    }


    // METODOS DE LA FACHADA PARA EXPONER LOS BOTONES AL CONTROLADOR
    public JButton getBtnJugar() {
        return this.menu.getBtnJugar();
    }

    public JButton getBtnTutorial() {
        return this.menu.getBtnTutorial();
    }

    public JButton getBtnSalir() {
        return this.menu.getBtnSalir();
    }

    // Botón Salir del gameplay.
    public JButton getBtnSalirJuego() {
        return this.escenario.getBtnSalir();
    }

    public JButton getBtnVolver(){
        return this.vistaTutorial.getBtnVolver();
    }

    public JButton getBtnLlamar() {
        return this.escenario.getBtnLlamar();
    }

    public JButton getBtnDecidir() {
        return this.escenario.getBtnDecidir();
    }

    public JButton getBtnAceptar() {
        return this.vistaDecision.getBtnAceptar();
    }

    public JButton getBtnRechazar() {
        return this.vistaDecision.getBtnRechazar();
    }

    public JButton getBtnVolverDecision() {
        return this.vistaDecision.getBtnVolver();
    }

    public JButton getBtnLibroReglas() {
        return this.escenario.getBtnLibroReglas();
    }

    public void alternarLibroReglas() {
        this.libroReglas.alternar();
    }

    public JButton getBtnDocumentos() {
        return this.escenario.getBtnDocumentos();
    }

    // NUEVO: Boton para colcer al menu cuando termina la partida
    public JButton getBtnVolverFinPartida() {
        return this.vistaFinPartida.getBtnVolverMenu();
    }
    
@Override
    public void actualizarNuevoIngresante(Ingresante ingresante) {
        this.mesa.limpiarMesa();
        this.consola.mostrarIngresante(ingresante);
        this.ventanilla.mostrarIngresante(ingresante.getRutaImagen());
        
        int posicionX = 30;
        int posicionY = 30;

        /*for (Documento documento : ingresante.getDocumentos()) {
            JPanel vistaDoc = this.fabrica.crearVista(documento, posicionX, posicionY);
            this.mesa.agregarDocumentoEnMesa(vistaDoc);
            posicionX += 60; 
            posicionY += 20; 
        } */
        
        this.libroReglas.restaurarSiAbierto();
        mostrarDocumentos();
    }


    public void desplegarDocumentos(Ingresante ingresante) {
        // Verificar que exista un ingresante
        // y que el botón todavía esté habilitado.
        if (ingresante == null ||!this.escenario.getBtnDocumentos().isEnabled()) {
            return;
        }

            int posicionX = 30;
            int posicionY = 30;

        // Crear y mostrar los documentos sobre la mesa.
        for (Documento documento : ingresante.getDocumentos()) {
            JPanel vistaDoc = this.fabrica.crearVista(documento, posicionX, posicionY);
            this.mesa.agregarDocumentoEnMesa(vistaDoc);
            posicionX += 60;
            posicionY += 20;
        }

        // Ocultar el botón después de desplegar los DNI.
        ocultarDocumento();

        // Mantener el libro de reglas sobre los documentos.
        this.libroReglas.restaurarSiAbierto();
    }

@Override
    public void actualizarVeredicto(boolean decisionJugador, ResultadoDecision resultado) {
        // 1. Limpiamos la mesa y la ventanilla porque el turno terminó
        this.mesa.limpiarMesa();
        this.libroReglas.cerrar();
        this.ventanilla.limpiarVentanilla();
        this.libroReglas.restaurarSiAbierto();

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

        ocultarDocumento();

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

    @Override
    public void actualizarFinPartida(TipoFinPartida resultado) {
        this.vistaDecision.ocultar();
        this.libroReglas.cerrar();
        this.escenario.ocultar();
        this.vistaFinPartida.mostrar(resultado);
    }

}
