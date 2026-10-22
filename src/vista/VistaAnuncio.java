package vista;

import java.awt.*;
import java.io.File;
import javax.swing.*;

public class VistaAnuncio {
    private final JDialog ventanaEmergente;
    private final JLabel fondo;
    private final BotonInvisible btnAceptar;

    public VistaAnuncio(JFrame ventanaPadre) {
        this.ventanaEmergente = new JDialog(ventanaPadre, "Aviso", true);
        this.ventanaEmergente.setUndecorated(true);
        this.ventanaEmergente.setBackground(new Color(0, 0, 0, 0));

        // Aplicamos la teoría de la cátedra: setLayout(null) para posicionamiento manual
        this.fondo = new JLabel();
        this.fondo.setLayout(null);
        this.fondo.setOpaque(false);

        // Reutilizamos tu BotonInvisible
        this.btnAceptar = new BotonInvisible();
        this.fondo.add(this.btnAceptar);

        this.ventanaEmergente.setContentPane(this.fondo);
    }

    public void mostrarImagen(String rutaImagen) {
        File archivoImagen = new File(rutaImagen);
        if (archivoImagen.exists()) {
            ImageIcon iconoOriginal = new ImageIcon(archivoImagen.getAbsolutePath());
            // Tamaño estandarizado para los 3 carteles (ajusta si tus png/jpg son más grandes o chicos)
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(450, 300, Image.SCALE_SMOOTH);
            this.fondo.setIcon(new ImageIcon(imagenEscalada));
        }

        // Ubicamos el botón invisible exactamente sobre el área verde donde dice "ACEPTAR"
        this.btnAceptar.setBounds(150, 230, 150, 45);

        this.ventanaEmergente.pack();
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