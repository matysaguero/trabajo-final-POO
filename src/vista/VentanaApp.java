package vista;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaApp extends JFrame {

    public static final String CARD_JUEGO = "JUEGO";
    
    private CardLayout cardLayout;
    private JPanel contenedor;
    private PanelJuego panelJuego;

    public VentanaApp() {
        // Al heredar de JFrame, usamos super() para establecer el título de la ventana
        super("Frontier"); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Los métodos setSize, add y setVisible se heredan de las clases padre Component, Container y Window[cite: 31]
        this.setSize(screenSize.width, screenSize.height); 
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setUndecorated(true); 

        this.cardLayout = new CardLayout();
        this.contenedor = new JPanel(cardLayout);
        
        this.panelJuego = new PanelJuego();
        this.contenedor.add(panelJuego, CARD_JUEGO);

        this.add(contenedor);
        this.setLocationRelativeTo(null);
    }

    public void mostrarTarjeta(String nombreTarjeta) {
        this.cardLayout.show(contenedor, nombreTarjeta);
        this.contenedor.revalidate();
        this.contenedor.repaint();
    }

    public PanelJuego getPanelJuego() {
        return this.panelJuego;
    }
}