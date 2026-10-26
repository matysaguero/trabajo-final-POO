package vista;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class VistaDecision {
   
    private final JDialog ventanaEmergente;
    private final JButton btnAceptar;
    private final JButton btnRechazar;
    private final JButton btnVolver;


    public VistaDecision(JFrame ventanaPadre) {
        this.ventanaEmergente = new JDialog(ventanaPadre, "Decisión", false);
        this.ventanaEmergente.setUndecorated(true);
        this.ventanaEmergente.setBackground(new Color(0, 0, 0, 0));
       
        // Cargar la imagen de fondo.
        ImageIcon imagenOriginal = new ImageIcon("assets/imagenes/paneles/tabla_de_botones.png");

        // Reducir la imagen
        Image imagenReducida = imagenOriginal.getImage().getScaledInstance(620, 277, Image.SCALE_REPLICATE);
        ImageIcon imagenFondo = new ImageIcon(imagenReducida);

        // JLabel que muestra el PNG de fondo.
        JLabel fondo = new JLabel(imagenFondo);
        fondo.setOpaque(false);

        // Mantenemos FlowLayout como antes, pero ahora sobre la imagen.
        int margenVertical = Math.max(0, (imagenFondo.getIconHeight() - 65) / 2);
        fondo.setLayout(new FlowLayout(FlowLayout.CENTER, 6, margenVertical));

        // Escalamos las imágenes a un tamaño equivalente en píxeles
        this.btnAceptar = crearBotonImagen("assets/imagenes/botones/boton_aceptar.png", 200, 65);
        this.btnRechazar = crearBotonImagen("assets/imagenes/botones/boton_rechazar.png", 200, 65);
        this.btnVolver = crearBotonImagen("assets/imagenes/botones/boton_volver.png", 80, 37);

        fondo.add(this.btnAceptar);
        fondo.add(this.btnRechazar);
        fondo.add(this.btnVolver);

        // El contenido del diálogo ahora es la imagen de fondo.
        this.ventanaEmergente.setContentPane(fondo);

        // Regla del profe: pack() en vez de setSize(). Dimensiona la ventana automáticamente
        this.ventanaEmergente.pack();
        this.ventanaEmergente.setLocationRelativeTo(ventanaPadre);
    }


    private JButton crearBotonImagen(String rutaImagen, int ancho, int alto) {
        JButton boton = new JButton();
        File archivoImagen = new File(rutaImagen);
        if (archivoImagen.exists()) {
            ImageIcon iconoOriginal = new ImageIcon(archivoImagen.getAbsolutePath());
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            boton.setIcon(new ImageIcon(imagenEscalada));
        }

        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setOpaque(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Le indicamos al FlowLayout el tamaño que debe respetar
        boton.setPreferredSize(new Dimension(ancho, alto));

        return boton;
    }

    public void mostrar() {
        // Volvemos a centrar por si cambió algo en la ventana padre.
        this.ventanaEmergente.setLocationRelativeTo(this.ventanaEmergente.getOwner());
        this.ventanaEmergente.setVisible(true);}

    public void ocultar() {this.ventanaEmergente.setVisible(false);}

    public JButton getBtnAceptar() {return this.btnAceptar;}

    public JButton getBtnRechazar() {return this.btnRechazar;}

    public JButton getBtnVolver() {return this.btnVolver;}
}