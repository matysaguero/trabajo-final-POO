package vista;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BotonInvisible extends JButton {

    public BotonInvisible() {
        super();
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void configurarImagenHover(String rutaImagen) {
        File archivoImagen = new File(rutaImagen);
        if (!archivoImagen.exists()) {
            return;
        }

        ImageIcon iconoOriginal = new ImageIcon(archivoImagen.getAbsolutePath());
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(
            this.getWidth(),
            this.getHeight(),
            Image.SCALE_SMOOTH
        );
        ImageIcon iconoHover = new ImageIcon(imagenEscalada);

        // Clase 10: Clase anónima sobre MouseAdapter para sobrescribir solo los eventos necesarios
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evento) {
                setIcon(iconoHover);
            }

            @Override
            public void mouseExited(MouseEvent evento) {
                setIcon(null);
            }

            @Override
            public void mousePressed(MouseEvent evento) {
                // Limpia el sprite al hacer clic para que no quede tildado al abrirse el JDialog
                setIcon(null);
            }
        });
    }
}