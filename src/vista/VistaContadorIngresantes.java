package vista;

import java.awt.*;
import javax.swing.*;

public class VistaContadorIngresantes {

    private static final String RUTA_CARTEL ="assets/imagenes/paneles/cartel_contador_ingresantes.png";
    private static final Color COLOR_TEXTO = new Color(120, 235, 255);
    private final JLabel etiqueta;

    public VistaContadorIngresantes() {

        Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        // Cargar imagen del cartel
        ImageIcon iconoCartel = new ImageIcon(RUTA_CARTEL);

        if (iconoCartel.getIconWidth() <= 0 || iconoCartel.getIconHeight() <= 0) {
            throw new IllegalStateException("No se pudo cargar el cartel: " + RUTA_CARTEL);
        }

        Image imagenCartel = iconoCartel.getImage();

        // Calcular dimensiones del cartel
        int anchoCartel =(int) (tamanoPantalla.width * 0.225);
        int altoCartel = (int) Math.round(anchoCartel *(double) iconoCartel.getIconHeight() /iconoCartel.getIconWidth());

        // Crear JLabel con imagen de fondo
        this.etiqueta = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                try {
                    // Mantener la nitidez del pixel art
                    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
                    // Dibujar el cartel
                    g2.drawImage(imagenCartel,0,0,getWidth(),getHeight(),this);

                } finally {
                    g2.dispose();
                }
                // Dibujar el texto sobre el cartel
                super.paintComponent(g);
            }
        };

        // Configurar apariencia del texto
        this.etiqueta.setOpaque(false);
        this.etiqueta.setFont(new Font("Monospaced",Font.BOLD,Math.max(14,(int) (tamanoPantalla.width * 0.0104))));
        this.etiqueta.setForeground(COLOR_TEXTO);
        this.etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
        this.etiqueta.setVerticalAlignment(SwingConstants.CENTER);

        // Margen para centrar el texto en la pantalla negra
        this.etiqueta.setBorder(BorderFactory.createEmptyBorder(altoCartel / 12, 0, 0, 0));

        //nPosicionar el cartel
        int posX = (int) (tamanoPantalla.width * 0.012);
        int posY = 0; // Pegado al borde superior

        this.etiqueta.setBounds( posX, posY, anchoCartel, altoCartel);

        //nMostrar cantidad inicial
        mostrarRestantes(0);
    }

    // Actualizar cantidad de ingresantes restantes
    public void mostrarRestantes(int restantes) {
        this.etiqueta.setText("Ingresantes en fila: " + restantes);
    }

    // Permitir que GestorVista acceda al JLabel
    public JLabel getEtiqueta() {
        return this.etiqueta;
    }
}
