package vista;

import javax.swing.*;
import java.awt.*;

public class Escenario {
    
    // Regla del profe: Tiene una ventana, no es una ventana
    private final JFrame ventana;
    
    // Botón invisible para la decisión
    private final BotonInvisible btnDecidir;

    public Escenario() {
<<<<<<< HEAD
        super("Frontier"); // Llama al constructor de JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
=======
        this.ventana = new JFrame("Frontier");
        this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ventana.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.ventana.setUndecorated(true);
        
        // Creamos el panel de fondo en el mismo lugar
        JPanel panelFondo = new JPanel() {
            private final Image imagenFondo = new ImageIcon("assets/imagenes/Gameplay.jpg").getImage();
            
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
>>>>>>> 96af83830fbc0917965c3fbedeb8c6c521b5936c
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        // Instanciamos y ubicamos el botón invisible debajo del "Sello"
        this.btnDecidir = new BotonInvisible();
        // Coordenadas y tamaño del botón invisible, ajustadas al diseño del juego
        this.btnDecidir.setBounds(
            (int)(screenSize.width * 0.22),  // X: 22% de la pantalla hacia la derecha
            (int)(screenSize.height * 0.70), // Y: 70% de la pantalla hacia abajo
            (int)(screenSize.width * 0.08),  // Ancho
            (int)(screenSize.height * 0.10)  // Alto
        );
        
        panelFondo.add(this.btnDecidir);
        this.ventana.setContentPane(panelFondo);
    }
    
    public void mostrar() { 
        this.ventana.setVisible(true); 
    }

<<<<<<< HEAD
    // --- GETTERS: La vista expone lo que el controlador necesita para registrarse
    public JButton getBtnRequisitos() { return btnRequisitos; }
    public JButton getBtnRevisar() { return btnRevisar; }
    public JButton getBtnDecidir() { return btnDecidir; }

    // Clase interna para el fondo
    class FondoPanel extends JPanel {
        private Image imagenFondo;
        public FondoPanel(String ruta) {
            File archivoFondo = new File(ruta);
            if (archivoFondo.exists()) {
                imagenFondo = new ImageIcon(archivoFondo.getAbsolutePath()).getImage();
            }
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); 
            if(imagenFondo != null) {
                g.drawImage(imagenFondo, 0, 0, this.getWidth(), this.getHeight(), this); 
            }
        }
=======
    // La vista expone lo que el controlador necesita para registrarse, y nada más
    public JButton getBtnDecidir() { 
        return this.btnDecidir; 
    }
    
    // Getter temporal para pasarle al JDialog emergente de decisión
    public JFrame getVentana() {
        return this.ventana;
>>>>>>> 96af83830fbc0917965c3fbedeb8c6c521b5936c
    }
}