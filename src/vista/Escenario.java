package vista;

import java.awt.*;
import javax.swing.*;

public class Escenario {

    // Regla del profe: Tiene una ventana, no es una ventana
    private final JFrame ventana;

    // Botón invisible para la decisión (Sello)
    private final BotonInvisible btnDecidir;

    // Botón invisible para llamar al siguiente ingresante
    private final BotonInvisible btnLlamar;
    

    public Escenario(VistaMesaDocumentos vistaMesa, VistaVentanilla ventanilla) {
        this.ventana = new JFrame("Frontier");
        this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.ventana.setUndecorated(true);

        JPanel panelFondo = new JPanel() {
            private final Image imagenFondo = new ImageIcon("assets/imagenes/paneles/gameplay.jpg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imagenFondo != null) {
                    g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        panelFondo.setLayout(null);

        Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();

        // Instanciio y ubicamos el botón invisible alineado al sprite completo del "Sello"
        this.btnDecidir = new BotonInvisible();
        this.btnDecidir.setBounds(
            (int) (tamanoPantalla.width * 0.2237),  // X
            (int) (tamanoPantalla.height * 0.662), // Y
            (int) (tamanoPantalla.width * 0.09),  // Ancho
            (int) (tamanoPantalla.height * 0.199)  // Alto
        );
        this.btnDecidir.configurarImagenHover("assets/imagenes/botones/sello_seleccionado.png");

        panelFondo.add(this.btnDecidir);
        this.ventana.setContentPane(panelFondo);
        this.ventana.add(vistaMesa.getPanelDocumentos(), JLayeredPane.DEFAULT_LAYER);
        this.ventana.add(ventanilla.getVentanilla(), JLayeredPane.DEFAULT_LAYER);

        this.btnLlamar = new BotonInvisible();
        this.btnLlamar.setBounds(
            (int) (tamanoPantalla.width * 0.2765),
            (int) (tamanoPantalla.height * 0.180),
            (int) (tamanoPantalla.width * 0.0607),
            (int) (tamanoPantalla.height * 0.105)
        );
        this.btnLlamar.configurarImagenHover("assets/imagenes/botones/boton_llamar_seleccionado.png");
        panelFondo.add(this.btnLlamar);
    }

    public void mostrar() {
        this.ventana.setVisible(true);
    }

    public void ocultar() {
        this.ventana.setVisible(false);
    }

    public JButton getBtnDecidir() {
        return this.btnDecidir;
    }

    public JButton getBtnLlamar() {
        return this.btnLlamar;
    }

    public JFrame getVentana() {
        return this.ventana;
    }
}