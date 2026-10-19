

package vista;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;

public class VistaLibroReglas {

    private static final String RUTA_IMAGEN = "assets/imagenes/permisos de entrada, libro de reglas y sellos/libro_de_reglas_abierto.png";
    private static final int ANCHO_MAXIMO = 500;
    private static final int MARGEN = 20;
    private final JLayeredPane mesa;
    private JPanel libro;

    public VistaLibroReglas(JLayeredPane mesa) {
        this.mesa = mesa;
    }

    // Alternar entre abrir y cerrar.
    public void alternar() {
        if (libro == null) {
            abrir();
        } else {
            cerrar();
        }
    }

    // Crear y mostrar el libro.
    private void abrir() {
        ImageIcon icono = new ImageIcon(RUTA_IMAGEN);
        if (icono.getIconWidth() <= 0 ||
            icono.getIconHeight() <= 0) {
            throw new IllegalStateException(
                "No se pudo cargar la imagen del libro."
            );
        }

        Image imagen = icono.getImage();
        // Ajustar el tamaño sin deformar la imagen.
        int anchoDisponible = Math.max(1, mesa.getWidth() - 2 * MARGEN);
        int altoDisponible = Math.max(1, mesa.getHeight() - 2 * MARGEN);
        double escala = Math.min(
            (double) Math.min(ANCHO_MAXIMO, anchoDisponible)
                / icono.getIconWidth(),
            (double) altoDisponible / icono.getIconHeight()
        );

        int ancho = Math.max(1,(int) Math.round(icono.getIconWidth() * escala));
        int alto = Math.max(1,(int) Math.round(icono.getIconHeight() * escala));

        libro = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(
                    imagen, 0, 0, getWidth(), getHeight(), this
                );
            }
        };

        libro.setOpaque(false);
        libro.setBounds(MARGEN, MARGEN, ancho, alto);
        // Permitir arrastrar el libro.
        MoverComponentes mover = new MoverComponentes(libro);
        libro.addMouseListener(mover);
        libro.addMouseMotionListener(mover);
        restaurarSiAbierto();
    }

    // Cerrar el libro.
    public void cerrar() {
        if (libro == null) return;
        mesa.remove(libro);
        libro = null;
        mesa.revalidate();
        mesa.repaint();
    }

    // Recuperar el libro después de limpiar los DNI.
    public void restaurarSiAbierto() {
        if (libro == null || libro.getParent() == mesa) return;
        mesa.add(libro, JLayeredPane.PALETTE_LAYER);
        mesa.revalidate();
        mesa.repaint();
    }
}
