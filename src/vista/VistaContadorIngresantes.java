package vista;

import java.awt.*;
import javax.swing.*;

public class VistaContadorIngresantes {

    private static final Font FUENTE = new Font("Monospaced", Font.BOLD, 16);
    private static final Color COLOR_TEXTO = new Color(120, 235, 255);
    private static final Color COLOR_FONDO = new Color(0, 0, 0, 160);
    private final JLabel etiqueta;

    public VistaContadorIngresantes() {
         Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();

        // Fondo semitransparente: se pinta a mano para evitar artefactos de Swing con colores con alfa.
        this.etiqueta = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(COLOR_FONDO);
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        this.etiqueta.setOpaque(false);
        this.etiqueta.setFont(FUENTE);
        this.etiqueta.setForeground(COLOR_TEXTO);
        this.etiqueta.setHorizontalAlignment(SwingConstants.CENTER);

        // Esquina superior izquierda, sobre la fila de gente que espera (proporcional al monitor).
        this.etiqueta.setBounds(
            (int) (tamanoPantalla.width * 0.012),
            (int) (tamanoPantalla.height * 0.015),
            (int) (tamanoPantalla.width * 0.150),
            (int) (tamanoPantalla.height * 0.045)
        );

        mostrarRestantes(0);
    }

    public void mostrarRestantes(int restantes) {
        this.etiqueta.setText("Ingresantes en fila: " + restantes);
    }

    public JLabel getEtiqueta() {
        return this.etiqueta;
    }

}
