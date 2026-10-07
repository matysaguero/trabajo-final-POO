package vista;

import java.awt.*;
import javax.swing.*;

public class VistaMesaDocumentos {
    private final JLayeredPane panelDocumentos;

    public VistaMesaDocumentos() {
        Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();

        // JLayeredPane permite componentes que se superpongan
        this.panelDocumentos = new JLayeredPane();
        this.panelDocumentos.setOpaque(false); //Transparente, no hace falta settear un fondo.
        this.panelDocumentos.setBounds((int) (tamanoPantalla.width*0.337), //Posicion x
                                       (int) (tamanoPantalla.height*0.35), //Posicion Y
                                       (int) (tamanoPantalla.width*0.66), // Ancho
                                       (int) (tamanoPantalla.height*0.65)); // Alto 

    }

    public JLayeredPane getPanelDocumentos(){
    return this.panelDocumentos;
    }

    public void agregarDocumentoEnMesa(JPanel documentoVisual) {
        // Le agregamos un pequeño seguro para evitar futuros NullPointerException
        if (documentoVisual != null) {
            this.panelDocumentos.add(documentoVisual, JLayeredPane.DEFAULT_LAYER);
            this.panelDocumentos.repaint();
        }
    }
    
    public void limpiarMesa() {
        this.panelDocumentos.removeAll();
        this.panelDocumentos.repaint();
    }
} 