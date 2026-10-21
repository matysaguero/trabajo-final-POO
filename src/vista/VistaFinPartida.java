package vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Toolkit; 
import java.awt.Cursor; 
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton; 
import modelo.TipoFinPartida;

// Una única pantalla reutilizable para DERROTA y VICTORIA.
// No interpreta reglas del juego ni registra sus propios listeners.
public class VistaFinPartida {

    // Componentes de la ventana
    private final JFrame ventana;
    private final JPanel panelFondo;
    // Boton para volver al menu principal
    private final JButton btnVolverMenu;

    // Resultado que determina qué imagen mostrar
    private TipoFinPartida resultadoActual = TipoFinPartida.DERROTA;

    // Imágenes de derrota y victoria
    private final Image imagenDerrota = cargarImagen("assets/imagenes/paneles/panel_de_derrota.jpg");
    private final Image imagenVictoria = cargarImagen("assets/imagenes/paneles/panel_de_victoria.jpg");

    // Constructor
    public VistaFinPartida() {
        ventana = new JFrame("Frontier - Fin de partida");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setUndecorated(true);
        ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // NUEVO: Obtenemos las dimensiones de la pantalla para calcular la posición y el tamaño del botón.
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int anchoPantalla = pantalla.width;
        int altoPantalla = pantalla.height;

        panelFondo = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Image imagen = resultadoActual == TipoFinPartida.DERROTA
                    ? imagenDerrota
                    : imagenVictoria;

                if (imagen != null) {
                    g.drawImage(
                        imagen, 0, 0,
                        getWidth(), getHeight(), this
                    );
                } else {
                    g.setColor(
                        resultadoActual == TipoFinPartida.DERROTA
                            ? new Color(42, 20, 29)
                            : new Color(20, 49, 45)
                    );
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };

        // Usamos posicionamiento manual para colocar el botón en coordenadas específicas sobre la imagen de fondo.
        panelFondo.setLayout(null);

        // Calculamos el tamaño y posicion del botón de manera proporcional
        int anchoBoton = (int) (anchoPantalla * 0.10);
        int altoBoton = (int) (altoPantalla * 0.07);
        int posXVolver = (int) (anchoPantalla * 0.45);
        int posYVolver = (int) (altoPantalla * 0.81);

        // Creamos un botón independiente reutilizando el mismo asset gráfico que utiliza VistaTutorial.
        btnVolverMenu = crearBotonConImagen("assets/imagenes/botones/boton_volver_menu.png",posXVolver,posYVolver,anchoBoton,altoBoton);

        // Agregamos el botón al panel que muestra las imágenes de derrota y victoria.
        panelFondo.add(btnVolverMenu);
        ventana.setContentPane(panelFondo);
    }


    // Cargar las imágenes desde los assets
    private static Image cargarImagen(String ruta) {
        File archivo = new File(ruta);
        if (!archivo.isFile()) {
            System.err.println("No se encontró la imagen: " + ruta);
            return null;
        }
        ImageIcon icono = new ImageIcon(archivo.getAbsolutePath());
        return icono.getIconWidth() > 0? icono.getImage(): null;
    }

    // NUEVO: Método para construir un JButton utilizando una imagen.
    private JButton crearBotonConImagen(String rutaImagen,int x,int y,int ancho,int alto) {

        JButton boton = new JButton("Volver al menú");
        // Establecemos posición y dimensiones.
        boton.setBounds(x, y, ancho, alto);
        Image imagen = cargarImagen(rutaImagen);

        if (imagen != null) {
            // Escalamos la imagen al tamaño del botón.
            Image imagenEscalada = imagen.getScaledInstance(ancho,alto,Image.SCALE_REPLICATE);
            boton.setIcon(new ImageIcon(imagenEscalada));
            boton.setText("");
            } else {
                // Si no se encuentra la imagen, mostramos texto.
                boton.setForeground(Color.WHITE);
        }

        // Eliminamos la apariencia predeterminada de Swing.
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setOpaque(false);

        // Cambiamos el cursor al pasar sobre el botón.
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        return boton;
    }


    // Mostrar la pantalla según el resultado
    public void mostrar(TipoFinPartida resultado) {
        if (resultado == null) {
            throw new IllegalArgumentException("El resultado final no puede ser nulo.");
        }

        resultadoActual = resultado;
        panelFondo.repaint();
        ventana.setVisible(true);
    }

    // Ocultar la pantalla
    public void ocultar() {
        ventana.setVisible(false);
    }

    // NUEVO: Getter para que GestorVista pueda exponer el botón al ControladorJuego
    public JButton getBtnVolverMenu() {
        return btnVolverMenu;
    }
}

