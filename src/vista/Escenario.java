package vista;

import java.awt.*;
import javax.swing.*;

public class Escenario {

    // Regla del profe: Tiene una ventana, no es una ventana
    private final JFrame ventana;

    // Botón invisible para la decisión (Sello)
    private final BotonInvisible btnDecidir;

    // Botón invisible para llamar al siguiente ingresante
    private final BotonInvisible btnLlamar;

    // Botón invisible para abrir el libro de reglas
    private final BotonInvisible btnLibroReglas;  
    
    // Boton invisible para abrir los documentos en la mesa
    private final BotonInvisible btnDocumentos;

    // Boton no invisible para salir del gameplay
    private final JButton btnSalir;

    // Botón invisible para interrogar al ingresante
    private final JButton btnInterrogar;
    

    public Escenario(VistaMesaDocumentos vistaMesa, VistaVentanilla ventanilla, VistaContadorIngresantes contador) {
        this.ventana = new JFrame("Frontier");
        this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.ventana.setUndecorated(true);

        JPanel panelFondo = new JPanel() {
            private final Image imagenFondo = new ImageIcon("assets/imagenes/paneles/gameplay.jpg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imagenFondo != null) {
                    g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        panelFondo.setLayout(null);
        Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();

        // Instanciio y ubicamos el botón invisible alineado al sprite completo del "Sello"
        this.btnDecidir = new BotonInvisible();
        this.btnDecidir.setBounds(
            (int) (tamanoPantalla.width * 0.2237),  // X
            (int) (tamanoPantalla.height * 0.662), // Y
            (int) (tamanoPantalla.width * 0.09),  // Ancho
            (int) (tamanoPantalla.height * 0.199)  // Alto
        );
        this.btnDecidir.configurarImagenHover("assets/imagenes/botones/sello_seleccionado.png");

        panelFondo.add(this.btnDecidir);
        this.ventana.setContentPane(panelFondo);
        this.ventana.add(vistaMesa.getPanelDocumentos(), JLayeredPane.DEFAULT_LAYER);
        this.ventana.add(ventanilla.getVentanilla(), JLayeredPane.DEFAULT_LAYER);

        this.btnLlamar = new BotonInvisible();
        this.btnLlamar.setBounds(
            (int) (tamanoPantalla.width * 0.2765),
            (int) (tamanoPantalla.height * 0.180),
            (int) (tamanoPantalla.width * 0.0607),
            (int) (tamanoPantalla.height * 0.105)
        );
        this.btnLlamar.configurarImagenHover("assets/imagenes/botones/boton_llamar_seleccionado.png");
        panelFondo.add(this.btnLlamar);

        // Botón invisible ubicado sobre el libro de reglas
        this.btnLibroReglas = new BotonInvisible();
        this.btnLibroReglas.setBounds(
            (int) (tamanoPantalla.width * 0.198),  // X
            (int) (tamanoPantalla.height * 0.868), // Y
            (int) (tamanoPantalla.width * 0.055),  // Ancho
            (int) (tamanoPantalla.height * 0.115)  // Alto
        );
        this.btnLibroReglas.configurarImagenHover("assets/imagenes/permisos de entrada, libro de reglas y sellos/libro_de_reglas_cerrado.png");
        panelFondo.add(this.btnLibroReglas);

        // Botón de documentos.
        this.btnDocumentos = new BotonInvisible();
        this.btnDocumentos.setBounds(
            (int) (tamanoPantalla.width * 0.073),
            (int) (tamanoPantalla.height * 0.720),
            (int) (tamanoPantalla.width * 0.051),
            (int) (tamanoPantalla.height * 0.100)
        );

        // Cargar el PNG.
        ImageIcon icono = new ImageIcon("assets/imagenes/documentos de identidad/dorso_dni.png");

        // Escalar la imagen al tamaño del botón.
        Image imagenEscalada = icono.getImage().getScaledInstance(this.btnDocumentos.getWidth(),this.btnDocumentos.getHeight(),Image.SCALE_REPLICATE);

        this.btnDocumentos.setIcon(new ImageIcon(imagenEscalada));

        // Inicialmente oculto y deshabilitado.
        this.btnDocumentos.setVisible(false);
        this.btnDocumentos.setEnabled(false);
        panelFondo.add(this.btnDocumentos);

    
        // Posición y dimensiones del botón Salir.
        int anchoBotonSalir = (int) (tamanoPantalla.width * 0.045);
        int altoBotonSalir = (int) (tamanoPantalla.height * 0.075);
        int posXSalir = (int) (tamanoPantalla.width * 0.94);
        int posYSalir = (int) (tamanoPantalla.height * 0.020);

        // Crear botón.
        this.btnSalir = new JButton();
        this.btnSalir.setBounds(posXSalir, posYSalir,anchoBotonSalir, altoBotonSalir);

        // Cargar imagen PNG.
        ImageIcon iconoSalir = new ImageIcon( "assets/imagenes/botones/boton_cruz.png");
        Image imagenSalirEscalada = iconoSalir.getImage().getScaledInstance(anchoBotonSalir,altoBotonSalir,Image.SCALE_REPLICATE);
        this.btnSalir.setIcon(new ImageIcon(imagenSalirEscalada));

        // Quitar fondo y bordes predeterminados.
        this.btnSalir.setContentAreaFilled(false);
        this.btnSalir.setBorderPainted(false);
        this.btnSalir.setFocusPainted(false);
        this.btnSalir.setOpaque(false);

        // Cursor interactivo.
        this.btnSalir.setCursor( new Cursor(Cursor.HAND_CURSOR));

        // Agregarlo al escenario.
        panelFondo.add(this.btnSalir);

        // NUEVO: contador de ingresantes restantes (solo se muestra, no genera eventos)
        panelFondo.add(contador.getEtiqueta());

        // Botón de interrogar al ingresante
        this.btnInterrogar = new JButton("Interrogar");
        this.btnInterrogar.setBounds((int)(tamanoPantalla.width * 0.45), (int)(tamanoPantalla.height * 0.85), 150, 40); // Ajusta X e Y para centrarlo
        this.btnInterrogar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelFondo.add(this.btnInterrogar);
    }

    public void mostrar() {
        this.ventana.setVisible(true);
    }

    public void ocultar() {
        this.ventana.setVisible(false);
    }

    public JButton getBtnDecidir() {
        return this.btnDecidir;
    }

    public JButton getBtnLlamar() {
        return this.btnLlamar;
    }

    public JButton getBtnLibroReglas() {
        return this.btnLibroReglas;
    }

    public JButton getBtnDocumentos() {
        return this.btnDocumentos;
    }

    public JButton getBtnSalir() {
        return this.btnSalir;
    }

    public JFrame getVentana() {
        return this.ventana;
    }

    public JButton getBtnInterrogar() { 
        return this.btnInterrogar;
    }
}