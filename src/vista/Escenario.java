package vista;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Escenario extends JFrame {
    
    // Los botones (fuentes de eventos)
    private JButton btnRequisitos;
    private JButton btnWanted;
    private JButton btnRevisar;
    private JButton btnVerificar;
    private JButton btnDecidir;

    public Escenario() {
        super("Frontier"); // Llama al constructor de JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setUndecorated(true);
        
        FondoPanel panelPrincipal = new FondoPanel("assets/imagenes/Panel del juego del controlador de frontera.png"); 
        panelPrincipal.setLayout(null);
        
        // Creamos los botones (simplificados para el ejemplo)
        btnRequisitos = crearBotonInvisible();
        btnRequisitos.setBounds((int)(screenSize.width * 0.67), (int)(screenSize.height * 0.11), (int)(screenSize.width * 0.25), (int)(screenSize.height * 0.26));
        panelPrincipal.add(btnRequisitos);
        
        btnRevisar = crearBotonInvisible();
        btnRevisar.setBounds((int)(screenSize.width * 0.26), (int)(screenSize.height * 0.86), (int)(screenSize.width * 0.11), (int)(screenSize.height * 0.05));
        panelPrincipal.add(btnRevisar);

        btnDecidir = crearBotonInvisible();
        btnDecidir.setBounds((int)(screenSize.width * 0.60), (int)(screenSize.height * 0.86), (int)(screenSize.width * 0.11), (int)(screenSize.height * 0.05));
        panelPrincipal.add(btnDecidir);
        
        this.setContentPane(panelPrincipal);
    }
    
    private JButton crearBotonInvisible() {
        JButton boton = new JButton();
        boton.setOpaque(false); 
        boton.setContentAreaFilled(false); 
        boton.setBorderPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        return boton;
    }

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
    }
}