package main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import vista.VistaGuardarPuntaje;

public class PruebaDialogo {

    public static void main(String[] args) {
        
        // SwingUtilities.invokeLater es la forma recomendada por la cátedra 
        // para ejecutar interfaces gráficas (Clase 10)
        SwingUtilities.invokeLater(() -> {
            
            // 1. Creamos una ventana padre falsa (invisible) 
            // solo para que el diálogo tenga donde anclarse.
            JFrame ventanaPadre = new JFrame();
            ventanaPadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventanaPadre.setSize(800, 600); // Simulamos el tamaño del juego
            ventanaPadre.setLocationRelativeTo(null); // Centrada en pantalla
            ventanaPadre.setVisible(true); // Mostramos el fondo falso

            // 2. Instanciamos tu VistaGuardarPuntaje pasándole la ventana padre
            VistaGuardarPuntaje dialogo = new VistaGuardarPuntaje(ventanaPadre);

            // 3. Invocamos al método que despliega el pop-up modal 
            // (La ejecución se pausará aquí hasta que toques un botón)
            String resultado = dialogo.pedirNombreJugador();

            // 4. Imprimimos el resultado en la consola para verificar 
            // que los botones Aceptar y Cancelar devuelven lo correcto.
            System.out.println("=================================");
            if (resultado == null) {
                System.out.println("El jugador canceló (retornó null).");
            } else if (resultado.trim().isEmpty()) {
                System.out.println("El jugador presionó aceptar pero dejó el texto vacío.");
            } else {
                System.out.println("El jugador ingresó: '" + resultado + "'");
            }
            System.out.println("=================================");
            
            // Terminamos el programa de prueba
            System.exit(0);
        });
    }
}