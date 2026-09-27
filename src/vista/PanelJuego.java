package vista;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

public class PanelJuego extends JPanel {
    
    private Image imagenFondo;
    private BotonInvisible btnRequisitos;
    private BotonInvisible btnWanted;
    private BotonInvisible btnRevisar;
    private BotonInvisible btnVerificar;
    private BotonInvisible btnDecidir;

    public PanelJuego() {
        // Desactivamos el layout para usar coordenadas absolutas, igual que antes
        this.setLayout(null);
        
        // Cargamos la imagen al estilo del profesor[cite: 26]
        File archivoFondo = new File("assets/imagenes/Panel del juego del controlador de frontera.png");
        if (archivoFondo.exists()) {
            imagenFondo = new ImageIcon(archivoFondo.getAbsolutePath()).getImage();
        } else {
            System.out.println("No se encontró la imagen de fondo.");
        }

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Usamos nuestra nueva clase personalizada
        btnRequisitos = new BotonInvisible();
        btnRequisitos.setBounds(
            (int)(screenSize.width * 0.67),   
            (int)(screenSize.height * 0.11),  
            (int)(screenSize.width * 0.25),   
            (int)(screenSize.height * 0.26)   
        );
        this.add(btnRequisitos);
        
        btnWanted = new BotonInvisible();
        btnWanted.setBounds(
            (int)(screenSize.width * 0.67), (int)(screenSize.height * 0.42), 
            (int)(screenSize.width * 0.25), (int)(screenSize.height * 0.26)
        );
        this.add(btnWanted);
        
        btnRevisar = new BotonInvisible();
        btnRevisar.setBounds(
            (int)(screenSize.width * 0.26), (int)(screenSize.height * 0.86), 
            (int)(screenSize.width * 0.11), (int)(screenSize.height * 0.05)
        );
        this.add(btnRevisar);

        btnVerificar = new BotonInvisible();
        btnVerificar.setBounds(
            (int)(screenSize.width * 0.43), (int)(screenSize.height * 0.86), 
            (int)(screenSize.width * 0.11), (int)(screenSize.height * 0.05)
        );
        this.add(btnVerificar);

        btnDecidir = new BotonInvisible();
        btnDecidir.setBounds(
            (int)(screenSize.width * 0.60), (int)(screenSize.height * 0.86), 
            (int)(screenSize.width * 0.11), (int)(screenSize.height * 0.05)
        );
        this.add(btnDecidir);
    }

    // Getters para el controlador
    public BotonInvisible getBtnRequisitos() { return btnRequisitos; }
    public BotonInvisible getBtnWanted() { return btnWanted; }
    public BotonInvisible getBtnRevisar() { return btnRevisar; }
    public BotonInvisible getBtnVerificar() { return btnVerificar; }
    public BotonInvisible getBtnDecidir() { return btnDecidir; }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenFondo != null) {
            // Dibuja la imagen escalándola al tamaño actual del panel[cite: 26]
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}