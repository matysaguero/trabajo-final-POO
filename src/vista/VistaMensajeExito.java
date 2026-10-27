package vista;

import javax.swing.*;
import java.awt.*;

public class VistaMensajeExito {
    private final JDialog dialogoMensaje;
    private final BotonInvisible btnAceptar;

    public VistaMensajeExito(JFrame ventanaPadre, String mensaje) {
        this.dialogoMensaje = new JDialog(ventanaPadre, true);
        this.dialogoMensaje.setUndecorated(true);
        this.dialogoMensaje.setBackground(new Color(0, 0, 0, 0));
        this.dialogoMensaje.setSize(700, 350);
        this.dialogoMensaje.setLocationRelativeTo(ventanaPadre);

        // Puedes reutilizar la imagen de tu cartel anterior o usar una más pequeña
        JPanel panelFondo = new JPanel() {
            private final Image fondo = new ImageIcon("assets/imagenes/paneles/cartel_reputacion.png").getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (fondo != null) {
                    g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        panelFondo.setLayout(null);
        panelFondo.setOpaque(false);

        Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();

        JLabel etiquetaMensaje = new JLabel(mensaje);
        etiquetaMensaje.setBounds(250, 150, 350, 30);
        etiquetaMensaje.setFont(new Font("Monospaced", Font.BOLD, 18));
        etiquetaMensaje.setForeground(Color.WHITE);
        etiquetaMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        panelFondo.add(etiquetaMensaje);

        // Botón de aceptar con tu propio estilo
        this.btnAceptar = new BotonInvisible();
        this.btnAceptar.setBounds(240, 230, 220, 55);
        btnAceptar.addActionListener(e -> this.dialogoMensaje.dispose());
        
        panelFondo.add(btnAceptar);
        this.dialogoMensaje.setContentPane(panelFondo);
    }

    public void mostrar() {
        this.dialogoMensaje.setVisible(true);
    }
}