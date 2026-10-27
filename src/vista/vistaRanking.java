package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import modelo.Puntaje;

public class VistaRanking {
    
    private final JDialog dialogoRanking;

    //El constructor recibe la ventana del menú para superponerse, y la lista ya cargada
    public VistaRanking(JFrame ventanaPadre, List<Puntaje> listaPuntajes) {

        this.dialogoRanking = new JDialog(ventanaPadre, false); 
        this.dialogoRanking.setUndecorated(true);
        this.dialogoRanking.setBackground(new Color(0, 0, 0, 0)); //Fondo invisible
        
        this.dialogoRanking.setSize(600, 300);

        this.dialogoRanking.setLocationRelativeTo(ventanaPadre);

        //Creamos el panel principal con la imagen de fondo
        JPanel panelFondo = new JPanel() {
            private final Image fondo = new ImageIcon("assets/imagenes/paneles/puntajes.PNG").getImage();
            
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
        
        int yInicial = 110; //Coordenada Y donde empieza el primer renglón
        int desplazamientoY = 33; //Espaciado entre cada celda

        int xRango = 40;    
        int xJugador = 180; 
        int xPuntuacion = 420;

        //Limito a 4 guardados segun el asset
        int limite = Math.min(listaPuntajes.size(), 4);

        for (int i = 0; i < limite; i++) {
            Puntaje puntaje = listaPuntajes.get(i);
            int yActual = yInicial + (i * desplazamientoY);

            //Columna Rango
            panelFondo.add(crearEtiqueta(String.valueOf(i + 1), xRango, yActual, 80));
            
            //Columna Jugador
            panelFondo.add(crearEtiqueta(puntaje.getJugador(), xJugador, yActual, 180));
            
            //Columna Puntuación (usando la reputación como puntaje)
            panelFondo.add(crearEtiqueta(String.valueOf(puntaje.getReputacion()), xPuntuacion, yActual, 120));
        }

     
        panelFondo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dialogoRanking.dispose();
            }
        });
        
        panelFondo.setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.dialogoRanking.setContentPane(panelFondo);
    }

    private JLabel crearEtiqueta(String texto, int x, int y, int ancho) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setBounds(x, y, ancho, 30);
        etiqueta.setFont(new Font("Monospaced", Font.BOLD, 16));
        etiqueta.setForeground(new Color(120, 235, 255)); 
        etiqueta.setHorizontalAlignment(SwingConstants.CENTER); 
        etiqueta.setOpaque(false);
        return etiqueta;
    }

    public void mostrar() {
        this.dialogoRanking.setVisible(true);
    }
}