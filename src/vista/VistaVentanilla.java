package vista;

import javax.swing.*;
import java.awt.*;

public class VistaVentanilla {

    private final JLabel labelPersonaje;
    private final int anchoNpc;
    private final int altoNpc;

    public VistaVentanilla() {
        Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();

        // Tamaño proporcional al monitor (equivale a ~230x260 en una notebook estándar)
        this.anchoNpc = (int) (tamanoPantalla.width * 0.168);
        this.altoNpc = (int) (tamanoPantalla.height * 0.338);

        // Posición proporcional dentro del recuadro de la ventanilla
        int posX = (int) (tamanoPantalla.width * 0.073);
        int posY = (int) (tamanoPantalla.height * 0.319);

        this.labelPersonaje = new JLabel();
        this.labelPersonaje.setBounds(posX, posY, this.anchoNpc, this.altoNpc);

        this.labelPersonaje.setHorizontalAlignment(SwingConstants.CENTER);
        this.labelPersonaje.setVerticalAlignment(SwingConstants.BOTTOM);
    }

    public void mostrarIngresante(String rutaAsset) {
        if (rutaAsset != null && !rutaAsset.isEmpty()) {
            ImageIcon iconoOriginal = new ImageIcon(rutaAsset);
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(
                this.anchoNpc, this.altoNpc, Image.SCALE_SMOOTH
            );
            this.labelPersonaje.setIcon(new ImageIcon(imagenEscalada));
        } else {
            limpiarVentanilla();
        }
    }

    public void limpiarVentanilla() {
        this.labelPersonaje.setIcon(null);
    }

    public JLabel getVentanilla() {
        return this.labelPersonaje;
    }
}