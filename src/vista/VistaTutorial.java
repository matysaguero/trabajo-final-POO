package vista;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class VistaTutorial {

    private final JFrame ventana;
    private final JButton btnVolver;

    public VistaTutorial() {
        this.ventana = new JFrame("Frontier - Tutorial");
        this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ventana.setUndecorated(true);
        this.ventana.setExtendedState(JFrame.MAXIMIZED_BOTH); 

        Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int anchoPantalla = tamanoPantalla.width;
        int altoPantalla = tamanoPantalla.height;

        JPanel panelFondo = new JPanel() {
            // Reemplaza esta ruta con la imagen de tu tutorial
            private final Image imagenFondo = new ImageIcon("assets/imagenes/paneles/panel_del_tutorial.png").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imagenFondo != null) {
                    g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        panelFondo.setLayout(null); 

        int anchoBoton = (int) (anchoPantalla * 0.10); 
        int altoBoton = (int) (altoPantalla * 0.07);   
        int posY = (int) (altoPantalla * 0.85); // Más abajo para no tapar el tutorial
        int posXVolver = (int) (anchoPantalla * 0.05); // Abajo a la izquierda

        this.btnVolver = crearBotonConImagen("assets/imagenes/botones/boton_volver.png", posXVolver, posY, anchoBoton, altoBoton);

        panelFondo.add(this.btnVolver);
        this.ventana.setContentPane(panelFondo);
    }

    private JButton crearBotonConImagen(String rutaImagen, int x, int y, int ancho, int alto) {
        JButton boton = new JButton("Volver"); // Texto por defecto por si no carga la imagen
        boton.setBounds(x, y, ancho, alto);
        
        File archivoImagen = new File(rutaImagen);
        if (archivoImagen.exists()) {
            ImageIcon iconoOriginal = new ImageIcon(archivoImagen.getAbsolutePath());
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            boton.setIcon(new ImageIcon(imagenEscalada));
            boton.setText(""); // Oculta el texto si hay imagen
        }

        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return boton;
    }

    public void mostrar() { this.ventana.setVisible(true); }
    public void ocultar() { this.ventana.setVisible(false); }

    public JButton getBtnVolver() { return this.btnVolver; }
}