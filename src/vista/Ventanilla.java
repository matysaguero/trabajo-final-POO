package vista;

import javax.swing.*;
import java.awt.Image;

public class Ventanilla {
    private final JLabel labelPersonaje;
    
    // El tamaño fijo que el personaje ocupará visualmente en el juego
    private final int ANCHO_NPC = 230;
    private final int ALTO_NPC = 260;

    public Ventanilla() {
        this.labelPersonaje = new JLabel();
        
        this.labelPersonaje.setBounds(100, 245, ANCHO_NPC, ALTO_NPC); 
        
        // Centramos la imagen dentro del recuadro por si las proporciones no son exactas
        this.labelPersonaje.setHorizontalAlignment(SwingConstants.CENTER);
        this.labelPersonaje.setVerticalAlignment(SwingConstants.BOTTOM);
    }

    // El Controlador usará este método, pasándole la ruta que viene del modelo
    public void mostrarIngresante(String rutaAsset) {
        if (rutaAsset != null && !rutaAsset.isEmpty()) {
            
            ImageIcon iconoOriginal = new ImageIcon(rutaAsset);
            
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(ANCHO_NPC, ALTO_NPC, Image.SCALE_SMOOTH);
            // SCALE_SMOOTH hace que los bordes del pixel art o dibujo queden prolijos, asi no nos preocupamos de las dimensiones de cada asset que utilicemos
            
            this.labelPersonaje.setIcon(new ImageIcon(imagenEscalada)); //Usamos la imagen escalada
        } else {
            limpiarVentanilla(); //Para que no explote el codigo.
        }
    }

    public void limpiarVentanilla() { //Para poder limpiar la ventanilla.
        this.labelPersonaje.setIcon(null); 
    }

    // Getter para que el Escenario pueda agarrar este JLabel y pegarlo al fondo
    public JLabel getVentanilla() {
        return this.labelPersonaje;
    }
}