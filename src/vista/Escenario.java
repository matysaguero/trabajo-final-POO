package vista;

import java.awt.*;
import javax.swing.*;

public class Escenario {
    
    // Regla del profe: Tiene una ventana, no es una ventana
    private final JFrame ventana;
    
    // Botón invisible para la decisión
    private final BotonInvisible btnDecidir;

    public Escenario(VistaMesaDocumentos vistaMesa) {
        this.ventana = new JFrame("Frontier");
        this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ventana.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.ventana.setUndecorated(true);
        
        // Creamos el panel de fondo en el mismo lugar
        JPanel panelFondo = new JPanel() {
            private final Image imagenFondo = new ImageIcon("assets/imagenes/paneles/gameplay.jpg").getImage();
            
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); 
                if(imagenFondo != null) {
                    g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this); 
                }
            }
        };
        // layout null para mapear píxeles del juego.
        panelFondo.setLayout(null); 
        
        Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        
        // Instanciamos y ubicamos el botón invisible debajo del "Sello"
        this.btnDecidir = new BotonInvisible();
        // Coordenadas y tamaño del botón invisible, ajustadas al diseño del juego
        this.btnDecidir.setBounds(
            (int)(tamanoPantalla.width * 0.22),  // X: 22% de la pantalla hacia la derecha
            (int)(tamanoPantalla.height * 0.70), // Y: 70% de la pantalla hacia abajo
            (int)(tamanoPantalla.width * 0.08),  // Ancho
            (int)(tamanoPantalla.height * 0.10)  // Alto
        );
        
        panelFondo.add(this.btnDecidir);
        this.ventana.setContentPane(panelFondo);
        this.ventana.add(vistaMesa.getPanelDocumentos(), 0);
    }
    
    public void mostrar() { 
        this.ventana.setVisible(true); 
    }

    // La vista expone lo que el controlador necesita para registrarse, y nada más
    public JButton getBtnDecidir() { 
        return this.btnDecidir; 
    }
    
    // Getter temporal para pasarle al JDialog emergente de decisión
    public JFrame getVentana() {
        return this.ventana;
    }
}