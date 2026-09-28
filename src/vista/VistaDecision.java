package vista;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class VistaDecision {
    
    private final JDialog ventanaEmergente;
    private final JButton btnAceptar;
    private final JButton btnRechazar;

    public VistaDecision(JFrame ventanaPadre) {
        this.ventanaEmergente = new JDialog(ventanaPadre, "Decisión", true);
        this.ventanaEmergente.setUndecorated(true);
        this.ventanaEmergente.getContentPane().setBackground(new Color(40, 40, 40));
        
        // Aplicamos la teoría de la cátedra: FlowLayout ubica en fila y calcula el espacio
        this.ventanaEmergente.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30));

        // Escalamos las imágenes a un tamaño equivalente en píxeles
        this.btnAceptar = crearBotonImagen("assets/imagenes/boton_aceptar.png", 200, 65);
        this.btnRechazar = crearBotonImagen("assets/imagenes/boton_rechazar.png", 200, 65);

        this.ventanaEmergente.add(this.btnAceptar);
        this.ventanaEmergente.add(this.btnRechazar);

        // Regal del profe: pack() en vez de setSize(). Dimensiona la ventana automáticamente
        this.ventanaEmergente.pack();
        
        // Se debe centrar DESPUÉS del pack(), porque usa el tamaño final para calcular el centro
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
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Le indicamos al FlowLayout el tamaño que debe respetar
        boton.setPreferredSize(new Dimension(ancho, alto));
        
        return boton;
    }

    public void mostrar() { this.ventanaEmergente.setVisible(true); }
    public void ocultar() { this.ventanaEmergente.setVisible(false); }

    public JButton getBtnAceptar() { return this.btnAceptar; }
    public JButton getBtnRechazar() { return this.btnRechazar; }
}