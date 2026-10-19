package vista;

import java.awt.*;
import java.io.File;
import javax.swing.*;

import modelo.documentos.DNI;
import modelo.documentos.Documento;

public class FabricaVistaReglas {
    private static final int ANCHO = 285;
    private static final int ALTO = 500;
    private static final Font FUENTE_DATOS = new Font("Monospaced", Font.BOLD, 14);
    private static final Color COLOR_TEXTO = new Color(35, 25, 20); 

    public JPanel crearVista(Documento doc, int posX, int posY){
        if (doc instanceof ) {
            return crearVistaReglas((DNI) doc, posX, posY);
        }
        return new JPanel();
    }

}
