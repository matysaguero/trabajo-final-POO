package main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import vista.VistaGuardarPuntaje;


public class PruebaDialogo {

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {
            
            JFrame ventanaPadre = new JFrame();
            ventanaPadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventanaPadre.setSize(800, 600); // Simulamos el tamaño del juego
            ventanaPadre.setLocationRelativeTo(null); // Centrada en pantalla
            ventanaPadre.setVisible(true); // Mostramos el fondo falso
            
            VistaGuardarPuntaje dialogo = new VistaGuardarPuntaje(ventanaPadre);

            String resultado = dialogo.pedirNombreJugador();

            System.out.println("=================================");
            if (resultado == null) {
                System.out.println("El jugador canceló (retornó null).");
            } else if (resultado.trim().isEmpty()) {
                System.out.println("El jugador presionó aceptar pero dejó el texto vacío.");
            } else {
                System.out.println("El jugador ingresó: '" + resultado + "'");
            }
            System.out.println("=================================");

            System.exit(0);
        });
    }
}