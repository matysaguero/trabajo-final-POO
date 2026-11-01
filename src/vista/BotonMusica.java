package vista;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Botón de música ON/OFF. Es SOLO vista: alterna entre dos sprites según el booleano que recibe.
 * No conoce el modelo ni el audio, y no registra sus propios listeners (eso lo hace el controlador).
 *
 * Se ubica en la esquina superior derecha, a la izquierda del botón de salir (la cruz),
 * por lo que se ve en el mismo lugar en las tres pantallas con música.
 */
public class BotonMusica extends JButton {

    private static final String RUTA_ICONO_ENCENDIDO = "assets/imagenes/botones/boton_encendido.png";
    private static final String RUTA_ICONO_APAGADO = "assets/imagenes/botones/boton_apagado.png";

    private final ImageIcon iconoEncendido; // null si el archivo no existe
    private final ImageIcon iconoApagado;   // null si el archivo no existe

    public BotonMusica() {
        super();

        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int ancho = (int) (pantalla.width * 0.045);
        int alto = (int) (pantalla.height * 0.075);
        int posX = (int) (pantalla.width * 0.89);
        int posY = (int) (pantalla.height * 0.020);
        this.setBounds(posX, posY, ancho, alto);

        this.iconoEncendido = cargarIcono(RUTA_ICONO_ENCENDIDO, ancho, alto);
        this.iconoApagado = cargarIcono(RUTA_ICONO_APAGADO, ancho, alto);

        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setFocusPainted(false);

        // Con sprites: botón "invisible" que solo muestra la imagen.
        // Sin sprites (archivo faltante): queda un botón estándar con texto, para no perder la función.
        if (this.iconoEncendido != null && this.iconoApagado != null) {
            this.setOpaque(false);
            this.setContentAreaFilled(false);
            this.setBorderPainted(false);
        }

        mostrarEstado(true);
    }

    /** Cambia el asset gráfico según el estado: true = música sonando, false = mudo. */
    public void mostrarEstado(boolean musicaActiva) {
        ImageIcon icono = musicaActiva ? this.iconoEncendido : this.iconoApagado;

        this.setIcon(icono);
        this.setText(icono == null ? (musicaActiva ? "Música ON" : "Música OFF") : "");
        this.setToolTipText(musicaActiva ? "Silenciar la música" : "Activar la música");
    }

    private static ImageIcon cargarIcono(String ruta, int ancho, int alto) {
        File archivo = new File(ruta);
        if (!archivo.exists()) {
            return null;
        }
        Image escalada = new ImageIcon(archivo.getAbsolutePath())
                .getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(escalada);
    }
}
