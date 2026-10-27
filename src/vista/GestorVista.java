package vista;

import modelo.ObservadorPartida;
import modelo.ResultadoDecision;
import modelo.TipoClan;
import modelo.TipoFinPartida;
import modelo.Ingresantes.Ingresante;
import modelo.documentos.Documento;
import modelo.politicas.ReglasDia;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.util.List;

public class GestorVista implements ObservadorPartida {
    private final MenuPrincipal menu;
    private final VistaRanking vistaRanking;
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
    private final VistaAnuncio vistaAnuncio;
    private final VistaInterrogatorio vistaInterrogatorio;
    private final VistaAnimacionSello animacionSello;
    private final VistaGuardarPuntaje vistaPuntaje;
    private final VistaMensajeExito vistaMensajeExito;
    private TipoFinPartida finPendiente = null; // es una bandera para retrasar la pantalla de Game Over hasta que se cierre el cartel

    public GestorVista(MenuPrincipal menu, VistaRanking vistaRanking, Escenario escenario, VistaDecision vistaDecision, VistaConsola consola, VistaMesaDocumentos mesa, VistaVentanilla ventanilla, FabricaVistaDocumento fabrica, VistaTutorial vistaTutorial, 
        VistaFinPartida vistaFinPartida, VistaCartelReputacion cartelReputacion, VistaContadorIngresantes contador, 
        VistaAnuncio vistaAnuncio, VistaInterrogatorio vistaInterrogatorio, VistaAnimacionSello animacionSello, VistaGuardarPuntaje vistaPuntaje,
        VistaMensajeExito vistaMensajeExito) {
        this.menu = menu;
        this.vistaRanking = vistaRanking;
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
        this.vistaAnuncio = vistaAnuncio; 
        this.vistaInterrogatorio = vistaInterrogatorio;
        this.animacionSello = animacionSello;
        this.fabricaReglas = new FabricaVistaReglas();
        this.libroReglas = new VistaLibroReglas(mesa.getPanelDocumentos(), this.fabricaReglas);
        this.vistaPuntaje = vistaPuntaje;
        this.vistaMensajeExito = vistaMensajeExito;
    }

    public void mostrarMenuPrincipal() { this.menu.mostrar(); }
    public void ocultarMenuPrincipal() { this.menu.ocultar(); }
    public void mostrarTutorial() { this.vistaTutorial.mostrar(); }
    public void ocultarTutorial() { this.vistaTutorial.ocultar(); }
    
    // Métodos de navegación del tutorial
    public void avanzarTutorial() { this.vistaTutorial.paginaSiguiente(); }
    public void retrocederTutorial() { this.vistaTutorial.paginaAnterior(); }

    public void iniciarJuego() {
        this.menu.ocultar();
        this.mesa.limpiarMesa();
        this.ventanilla.limpiarVentanilla();
        ocultarDocumento();
        this.escenario.mostrar();
    }

    public void mostrarVentanaDecision() { this.vistaDecision.mostrar(); }
    public void ocultarVentanaDecision() { this.vistaDecision.ocultar(); }

    // Mantiene sincronizadas la visibilidad y la interacción del botón.
    private void actualizarBotonDocumentos(boolean disponible) {
        JButton boton = this.escenario.getBtnDocumentos();

        boton.setEnabled(disponible);
        boton.setVisible(disponible);

        // Refresca el fondo del botón transparente.
        if (boton.getParent() != null) {
            boton.getParent().revalidate();
            boton.getParent().repaint();
        }
    }
      
    private void mostrarDocumentos() {
        actualizarBotonDocumentos(true);
    }

    private void ocultarDocumento() {
        actualizarBotonDocumentos(false);
    }

    public void volverAlMenuDesdeFinPartida() {
        this.vistaFinPartida.ocultar();
        this.menu.mostrar();
    }

    public void alternarLibroReglas(ReglasDia reglasActivas) {
        this.libroReglas.alternar(reglasActivas);
    }

    public JFrame getVentanaMenu() { return this.menu.getVentana(); }

    public void mostrarCartelReputacion(String mensaje) {
        this.cartelReputacion.mostrarMensaje(mensaje);
    }

    public void ocultarCartelReputacion() {
        this.cartelReputacion.ocultar();
        
        // Cuando el usuario cierra el cartel, verificamos si el juego había terminado
        if (this.finPendiente != null) {
            this.vistaDecision.ocultar();
            this.libroReglas.cerrar();
            this.escenario.ocultar();
            this.vistaFinPartida.mostrar(this.finPendiente);
            this.finPendiente = null; // Reiniciamos la bandera
        }
    }

    // Métodos para controlar el anuncio de errores
    public void mostrarAnuncioGrafico(String rutaImagen) {
        this.vistaAnuncio.mostrarImagen(rutaImagen);
    }

    public void ocultarAnuncioGrafico() {
        this.vistaAnuncio.ocultar();
    }

    public void alHacerClicEnAceptarCartel(ActionListener listener) {
        this.cartelReputacion.getBtnAceptar().addActionListener(listener);
    }

    // Oyente para el botón del nuevo anuncio
    public void alHacerClicEnAceptarAnuncio(ActionListener listener) {
        this.vistaAnuncio.getBtnAceptar().addActionListener(listener);
    }

    public void alHacerClicEnJugar(ActionListener listener) { this.menu.getBtnJugar().addActionListener(listener); }
    public void alHacerClicEnTutorial(ActionListener listener) { this.menu.getBtnTutorial().addActionListener(listener); }
    public void alHacerClickEnPuntaje(ActionListener listener) { this.menu.getBtnPuntaje().addActionListener(listener); }
    public void alHacerClicEnSalir(ActionListener listener) { this.menu.getBtnSalir().addActionListener(listener); }
    public void alHacerClicEnSalirJuego(ActionListener listener) { this.escenario.getBtnSalir().addActionListener(listener); }
    public void alHacerClicEnVolverTutorial(ActionListener listener) { this.vistaTutorial.getBtnVolver().addActionListener(listener); }
    public void alHacerClicEnSiguienteTutorial(ActionListener listener) {this.vistaTutorial.getBtnSiguiente().addActionListener(listener);} //Oyentes para pasar a la siguiente/anteior página del tutorial
    public void alHacerClicEnAnteriorTutorial(ActionListener listener) {this.vistaTutorial.getBtnAnterior().addActionListener(listener);}
    public void alHacerClicEnLlamar(ActionListener listener) { this.escenario.getBtnLlamar().addActionListener(listener); }
    public void alHacerClicEnDecidir(ActionListener listener) { this.escenario.getBtnDecidir().addActionListener(listener); }
    public void alHacerClicEnAceptar(ActionListener listener) { this.vistaDecision.getBtnAceptar().addActionListener(listener); }
    public void alHacerClicEnRechazar(ActionListener listener) { this.vistaDecision.getBtnRechazar().addActionListener(listener); }
    public void alHacerClicEnVolverDecision(ActionListener listener) { this.vistaDecision.getBtnVolver().addActionListener(listener); }
    public void alHacerClicEnLibroReglas(ActionListener listener) { this.escenario.getBtnLibroReglas().addActionListener(listener); }
    public void alHacerClicEnDocumentos(ActionListener listener) { this.escenario.getBtnDocumentos().addActionListener(listener); }
    public void alHacerClicEnVolverFinPartida(ActionListener listener) { this.vistaFinPartida.getBtnVolverMenu().addActionListener(listener); }
    public void alHacerClicEnGuardar(ActionListener listener) { this.vistaFinPartida.getBtnGuardarPuntaje().addActionListener(listener); }
    public void alHacerClicEnInterrogar(ActionListener listener) { this.escenario.getBtnInterrogar().addActionListener(listener); }

    public void mostrarDialogoInterrogatorio(String dialogo) {
        // 1. Apagamos (deshabilitamos) el botón para que no se pueda spamear
        this.escenario.getBtnInterrogar().setEnabled(false);
        
        // 2. Mostramos el diálogo y le pasamos la acción a ejecutar cuando se oculte (reactivar el botón)
        this.vistaInterrogatorio.mostrar(dialogo, () -> {
            this.escenario.getBtnInterrogar().setEnabled(true);
        });
    }

    public void mostrarAnimacionSello(boolean decision, Runnable alTerminar) {
        this.animacionSello.reproducir(decision, this.mesa, alTerminar);
    }

    public String pedirNombreJugador() { return this.vistaPuntaje.pedirNombreJugador();}

    @Override
    public void actualizarNuevoIngresante(Ingresante ingresante) {
        ocultarDocumento(); // Cada ingresante debe ser entrevistado antes de acceder a sus documentos.
        this.mesa.limpiarMesa();
        this.consola.mostrarIngresante(ingresante);
        this.ventanilla.mostrarIngresante(ingresante.getRutaImagen());
        this.libroReglas.restaurarSiAbierto();
    }

    @Override
    public void actualizarDocumentosDisponibles() {
        mostrarDocumentos();
    }

    public void desplegarDocumentos(List<Documento> documentos, TipoClan clan) {

        if (documentos == null || !this.escenario.getBtnDocumentos().isEnabled()) {
            return;
        }
        int posicionX = 30;
        int posicionY = 30;

        for (Documento documento : documentos) {
            JPanel vistaDoc = this.fabrica.crearVista(documento, clan, posicionX, posicionY);
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

        // 1. Aquí se decide si es correcto o penalización e incrustamos el motivo
        String mensaje = resultado.getAcierto() 
             ? "<html><center>¡Decisión correcta!<br>Reputación: " + resultado.getReputacionActual() + "</center></html>"
             : "<html><center>¡Penalización!<br>" + resultado.getMotivo() + "<br>Reputación: " + resultado.getReputacionActual() + "</center></html>";
                     
        this.cartelReputacion.mostrarMensaje(mensaje);

        ocultarDocumento();
        this.consola.mostrarPuedeIngresar(resultado.getPuedeIngresar());
        this.consola.mostrarDecisionJugador(decisionJugador);
        this.consola.mostrarResultado(resultado.getAcierto(), resultado.getReputacionActual());
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this.escenario.getVentana(), mensaje);
    }

    public void mostrarMensajeExito(String mensaje) {
        VistaMensajeExito aviso = new VistaMensajeExito(this.vistaFinPartida.getVentana(), mensaje);
        aviso.mostrar();
    }

    @Override
    public void actualizarFinPartida(TipoFinPartida resultado) {
        // En vez de cerrar todo y mostrar el fin de la partida instantáneamente, 
        // se guarda como pendiente. La pantalla se mostrará recién al cerrar el cartel.
        this.finPendiente = resultado;
    }

    @Override
    public void actualizarIngresantesRestantes(int restantes) {
        this.contador.mostrarRestantes(restantes);
    }


}