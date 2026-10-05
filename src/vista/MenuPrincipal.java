package vista;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MenuPrincipal {

    // Regla del profe: Tiene una ventana, no es una ventana
    private final JFrame ventana;
    private final JButton btnJugar;
    private final JButton btnTutorial;

    public MenuPrincipal() {
        this.ventana = new JFrame("Frontier - Menú Principal");
        this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ventana.setUndecorated(true);
        this.ventana.setExtendedState(JFrame.MAXIMIZED_BOTH); 

        Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int anchoPantalla = tamanoPantalla.width;
        int altoPantalla = tamanoPantalla.height;

        JPanel panelFondo = new JPanel() {
            private final Image imagenFondo = new ImageIcon("assets/imagenes/paneles/menu_principal.png").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imagenFondo != null) {
                    g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        // Posicionamiento manual para mapear los botones sobre el pixel art
        panelFondo.setLayout(null); 

        int anchoBoton = (int) (anchoPantalla * 0.10); 
        int altoBoton = (int) (altoPantalla * 0.07);   
        int posY = (int) (altoPantalla * 0.68);   
        int posXJugar = (int) (anchoPantalla * 0.382);       
        int posXTutorial = (int) (altoPantalla * 0.916);

        // Se crean los botones utilizando las imágenes requeridas
        this.btnJugar = crearBotonConImagen("assets/imagenes/botones/boton_jugar.png", posXJugar, posY, anchoBoton, altoBoton);
        this.btnTutorial = crearBotonConImagen("assets/imagenes/botones/boton_tutorial.png", posXTutorial, posY, anchoBoton, altoBoton);

        panelFondo.add(this.btnJugar);
        panelFondo.add(this.btnTutorial);
        
        this.ventana.setContentPane(panelFondo);
    }

    private JButton crearBotonConImagen(String rutaImagen, int x, int y, int ancho, int alto) {
        JButton boton = new JButton();
        boton.setBounds(x, y, ancho, alto);
        
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

        return boton;
    }

    public void mostrar() { this.ventana.setVisible(true); }
    public void ocultar() { this.ventana.setVisible(false); }

    // La vista expone lo que el controlador necesita para registrarse, y nada más
    public JButton getBtnJugar() { return this.btnJugar; }
    public JButton getBtnTutorial() { return this.btnTutorial; }

}

