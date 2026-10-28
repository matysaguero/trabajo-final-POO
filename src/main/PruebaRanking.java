package main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import vista.VistaRanking;
import modelo.Puntaje;

public class PruebaRanking {

    public static void main(String[] args) {
        
        List<Puntaje> listaFalsa = new ArrayList<>();
        listaFalsa.add(new Puntaje("Jorji Costava", 500, LocalDate.now()));
        listaFalsa.add(new Puntaje("Sergiu", 450, LocalDate.now()));
        listaFalsa.add(new Puntaje("Elisaion", 320, LocalDate.now()));
        listaFalsa.add(new Puntaje("Vince Blood", 210, LocalDate.now()));

        SwingUtilities.invokeLater(() -> {
            
            JFrame ventanaMenuFalsa = new JFrame("Simulador de Menú");
            ventanaMenuFalsa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventanaMenuFalsa.setSize(800, 600);
            ventanaMenuFalsa.setLocationRelativeTo(null); 
            ventanaMenuFalsa.setVisible(true);

            //Instanciamos la vista pasándole la ventana y los datos falsos
            VistaRanking tabla = new VistaRanking(ventanaMenuFalsa, listaFalsa);

            tabla.mostrar();
            
            System.out.println("El ranking se cerró correctamente.");
            
        });
    }
}