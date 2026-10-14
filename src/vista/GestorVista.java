package vista;

import modelo.Ingresantes.Ingresante;
import modelo.documentos.Documento;
import javax.swing.JPanel;

public class GestorVista {
    private final MenuPrincipal menu;
    private final Escenario escenario;
    private final VistaDecision vistaDecision;
    private final VistaConsola consola;
    private final VistaMesaDocumentos mesa;
    private final VistaVentanilla ventanilla;
    private final FabricaVistaDocumento fabrica;

    public GestorVista(MenuPrincipal menu, Escenario escenario, VistaDecision vistaDecision, VistaConsola consola, VistaMesaDocumentos mesa, VistaVentanilla ventanilla, FabricaVistaDocumento fabrica){
        this.menu = menu;
        this.escenario = escenario;
        this.vistaDecision = vistaDecision;
        this.consola = consola;
        this.mesa = mesa;
        this.ventanilla = ventanilla;
        this.fabrica = fabrica;
    }

    public void iniciarJuego(){
        this.menu.ocultar();
        this.escenario.mostrar();
    }

    public void mostrarIngresante(Ingresante ingresante) {
        this.mesa.limpiarMesa();
        this.consola.mostrarIngresante(ingresante);
        this.ventanilla.mostrarIngresante(ingresante.getRutaImagen());

        int posicionX = 30;
        int posicionY = 30;

        for (Documento documento : ingresante.getDocumentos()) {
            JPanel vistaDocumentos = this.fabrica.crearVista(documento, posicionX, posicionY);
            this.mesa.agregarDocumentoEnMesa(vistaDocumentos);
            
            posicionX += 40;
            posicionY += 40;
        }
    }

    public MenuPrincipal getMenuPrincipal() {return this.menu;}
    public Escenario getEscenario() {return this.escenario;}
    public VistaDecision getVistaDecision() {return this.vistaDecision;}
    public VistaConsola getVistaConsola() {return this.consola;}
    public VistaMesaDocumentos getVistaMesaDocumentos() {return this.mesa;}
    public VistaVentanilla getVistaVentanilla() {return this.ventanilla;}
    public FabricaVistaDocumento getFabricaVistaDocumento() {return this.fabrica;}
}
