package vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import modelo.TipoFinPartida;

// Una única pantalla reutilizable para DERROTA y VICTORIA.
// No interpreta reglas del juego ni registra sus propios listeners.
public class VistaFinPartida {

    // Componentes de la ventana
    private final JFrame ventana;
    private final JPanel panelFondo;

    // Resultado que determina qué imagen mostrar
    private TipoFinPartida resultadoActual = TipoFinPartida.DERROTA;

    // Imágenes de derrota y victoria
    private final Image imagenDerrota = cargarImagen("assets/imagenes/paneles/panel_de_derrota.jpg");
    private final Image imagenVictoria = cargarImagen("assets/imagenes/paneles/panel_de_victoria.jpg");

    // Constructor
    public VistaFinPartida() {
        ventana = new JFrame("Frontier - Fin de partida");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setUndecorated(true);
        ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);

    panelFondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image imagen = resultadoActual == TipoFinPartida.DERROTA? imagenDerrota : imagenVictoria;

                if (imagen != null) {
                    g.drawImage(imagen, 0, 0,getWidth(), getHeight(), this);
                } else {
                    g.setColor(resultadoActual == TipoFinPartida.DERROTA? new Color(42, 20, 29): new Color(20, 49, 45));
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        ventana.setContentPane(panelFondo);
    }


    // Cargar las imágenes desde los assets
    private static Image cargarImagen(String ruta) {
        File archivo = new File(ruta);

        if (!archivo.isFile()) {
            System.err.println("No se encontró la imagen: " + ruta);
                return null;
        }

        ImageIcon icono = new ImageIcon(archivo.getAbsolutePath());
        return icono.getIconWidth() > 0? icono.getImage(): null;
    }

    // Mostrar la pantalla según el resultado
    public void mostrar(TipoFinPartida resultado) {
        if (resultado == null) {
            throw new IllegalArgumentException("El resultado final no puede ser nulo.");
        }

        resultadoActual = resultado;
        panelFondo.repaint();
        ventana.setVisible(true);
    }

    // Ocultar la pantalla
    public void ocultar() {
        ventana.setVisible(false);
    }
}

