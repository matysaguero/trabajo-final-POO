package vista;

import java.awt.*;
import java.io.File;
import javax.swing.*;

public class VistaCartelReputacion {
    
    private final JDialog ventanaEmergente;
    private final JLabel lblMensaje;
    private final JButton btnAceptar;

    public VistaCartelReputacion(JFrame ventanaPadre) {
        // JDialog modal: detiene la ejecución de la ventana padre hasta que se cierre
        this.ventanaEmergente = new JDialog(ventanaPadre, "Resultado", false);
        this.ventanaEmergente.setUndecorated(true);
        this.ventanaEmergente.setBackground(new Color(0, 0, 0, 0));

        // Cargar imagen de fondo
        ImageIcon imagenOriginal = new ImageIcon("assets/imagenes/paneles/cartel_reputacion.png");
        Image imagenReducida = imagenOriginal.getImage().getScaledInstance(578, 432, Image.SCALE_REPLICATE);
        JLabel fondo = new JLabel(new ImageIcon(imagenReducida));
        fondo.setOpaque(false);
        // BorderLayout divide en regiones. Centro para el texto, Sur para el botón.
        fondo.setLayout(new BorderLayout());

        // Mensaje dinámico
        this.lblMensaje = new JLabel("", SwingConstants.CENTER);
        this.lblMensaje.setFont(new Font("Monospaced", Font.BOLD, 20)); // Fuente y tamaño del mensaje reputacion
        this.lblMensaje.setForeground(Color.WHITE);
        
        // Agregamos un margen invisible a la izquierda para empujarlo a la derecha
        this.lblMensaje.setBorder(BorderFactory.createEmptyBorder(115, 200, -10, 60));
        
        fondo.add(this.lblMensaje, BorderLayout.CENTER);
        fondo.add(this.lblMensaje, BorderLayout.CENTER);

        // Panel inferior para el botón
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.setOpaque(false);

        // Agregamos un margen invisible abajo para empujar el botón ACEPTAR hacia arriba
        panelBoton.setBorder(BorderFactory.createEmptyBorder(0, 0, 75, 0));

        this.btnAceptar = crearBotonImagen("assets/imagenes/botones/boton_aceptar.png", 190, 70);
        panelBoton.add(this.btnAceptar);
        
        fondo.add(panelBoton, BorderLayout.SOUTH);

        this.ventanaEmergente.setContentPane(fondo);
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
        boton.setPreferredSize(new Dimension(ancho, alto));
        return boton;
    }

    public void mostrarMensaje(String mensaje) {
        this.lblMensaje.setText(mensaje);
        this.ventanaEmergente.setLocationRelativeTo(this.ventanaEmergente.getOwner());
        this.ventanaEmergente.setVisible(true);
    }

    public void ocultar() {
        this.ventanaEmergente.setVisible(false);
    }

    public JButton getBtnAceptar() {
        return this.btnAceptar;
    }
}