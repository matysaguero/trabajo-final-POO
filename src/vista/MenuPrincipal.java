package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        // 1. Configuración básica de la ventana en Pantalla Completa
        setTitle("Frontier");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true); // Quita los bordes superiores de Windows (Minimizar, Cerrar)
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiza a pantalla completa

        // Obtenemos el tamaño exacto del monitor donde se está ejecutando el juego
        Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int anchoPantalla = tamanoPantalla.width;
        int altoPantalla = tamanoPantalla.height;

        // 2. Creación del Panel de Fondo personalizado
        JPanel panelFondo = new JPanel() {
            private Image imagenFondo = new ImageIcon("assets/imagenes/menu_principal.png").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dibuja la imagen estirándola al ancho y alto total de la pantalla
                g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelFondo.setLayout(null); // Desactivamos el diseño automático para usar porcentajes

        // 3. Cálculo de coordenadas por porcentaje (Puerta Izquierda)
        int anchoBoton = (int) (anchoPantalla * 0.10); // El botón ocupa el 10% del ancho del monitor
        int altoBoton = (int) (altoPantalla * 0.07);   // El botón ocupa el 7% del alto del monitor
        
        int posY = (int) (altoPantalla * 0.68);   // 48% hacia abajo (arriba/medio de la puerta)
        int posXJugar = (int) (anchoPantalla * 0.382);       // 37% hacia la derecha (cae en la puerta izquierda)
        int posXTutorial = (int) (altoPantalla * 0.916);// 60% hacia abajo (debajo del botón Jugar)

        // 4. Creación de los botones con sus propias imágenes
        JButton btnJugar = crearBotonConImagen("assets/imagenes/boton_jugar.png", posXJugar, posY, anchoBoton, altoBoton);
        JButton btnTutorial = crearBotonConImagen("assets/imagenes/boton_tutorial.png", posXTutorial, posY, anchoBoton, altoBoton);

        // 5. Acciones al hacer clic (Eventos de prueba para la entrega)
        btnJugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "¡Iniciando Día 1 en la frontera!");
                // Más adelante, acá cerraremos el menú y abriremos la ventana del juego
            }
        });

        btnTutorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Aquí se mostrarán las reglas básicas de inspección.");
            }
        });

        // Agregamos los botones al panel y el panel a la ventana
        panelFondo.add(btnJugar);
        panelFondo.add(btnTutorial);
        add(panelFondo);
    }

    // Método auxiliar para crear botones invisibles que solo muestran tu imagen pixel art
    private JButton crearBotonConImagen(String rutaImagen, int x, int y, int ancho, int alto) {
        JButton boton = new JButton();
        boton.setBounds(x, y, ancho, alto);

        // Cargamos la imagen del botón y la escalamos al tamaño calculado
        ImageIcon iconoOriginal = new ImageIcon(rutaImagen);
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        boton.setIcon(new ImageIcon(imagenEscalada));

        // Quitamos el fondo gris y los bordes por defecto de Windows
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        
        // Cambia la flecha del mouse por la manito al pasar por encima
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return boton;
    }

    // Método main temporal para que puedan probar la ventana directamente desde acá
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuPrincipal menu = new MenuPrincipal();
            menu.setVisible(true);
        });
    }
}