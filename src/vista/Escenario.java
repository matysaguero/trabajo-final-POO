package vista;

import javax.swing.*;
import java.awt.*;

public class Escenario extends JFrame {
    
    // 1. Declaración de los componentes visuales
    private JLabel lblInfoIngresante;
    private JButton btnAceptar;
    private JButton btnRechazar;

    public Escenario() {
        // 2. Configuración básica de la ventana principal
        this.setTitle("Frontier - Control de Frontera");
        this.setSize(600, 400); // Ancho y alto inicial
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(10, 10)); // BorderLayout con márgenes
        this.setLocationRelativeTo(null); // Centra la ventana en la pantalla

        // 3. Crear el panel superior/central para el Ingresante
        JPanel panelIngresante = new JPanel();
        panelIngresante.setBorder(BorderFactory.createTitledBorder("Información del Ingresante"));
        panelIngresante.setLayout(new BorderLayout()); // Para que el texto ocupe todo el espacio
        
        // Usamos JLabel. En el futuro, si son muchos datos, podrías cambiarlo a un JTextArea
        lblInfoIngresante = new JLabel("Esperando al siguiente ingresante...", SwingConstants.CENTER);
        panelIngresante.add(lblInfoIngresante, BorderLayout.CENTER);

        // 4. Crear el panel inferior para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Alineados al centro con separación
        
        btnAceptar = new JButton("Aceptar (Sello Verde)");
        btnRechazar = new JButton("Rechazar (Sello Rojo)");
        
        // Colores opcionales para guiar al jugador
        btnAceptar.setBackground(new Color(144, 238, 144)); // Verde claro
        btnRechazar.setBackground(new Color(255, 182, 193)); // Rojo claro

        panelBotones.add(btnAceptar);
        panelBotones.add(btnRechazar);

        // 5. Agregar los paneles a la ventana principal
        this.add(panelIngresante, BorderLayout.CENTER);
        this.add(panelBotones, BorderLayout.SOUTH);
    }

    // =================================================================
    // GETTERS PARA EL CONTROLADOR (Muy importante para respetar el MVC)
    // =================================================================
    
    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public JButton getBtnRechazar() {
        return btnRechazar;
    }

    // =================================================================
    // MÉTODOS DE ACTUALIZACIÓN DE INTERFAZ
    // =================================================================
    
    /**
     * El controlador llamará a este método pasándole los datos del 
     * modelo Ingresante para que se muestren en la pantalla.
     */
    public void actualizarDatosIngresante(String datos) {
        // En un JLabel, usar HTML permite los saltos de línea (<br>)
        lblInfoIngresante.setText("<html>" + datos.replace("\n", "<br>") + "</html>");
    }
}