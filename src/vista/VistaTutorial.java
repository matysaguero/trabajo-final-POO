package vista;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class VistaTutorial {
    private final JFrame ventana;
    private final JPanel panelFondo;
    private final JButton btnVolver;
    private final JButton btnAnterior;
    private final JButton btnSiguiente;
    
    // Arreglo con las rutas de las 7 imágenes del tutorial
    private final String[] paginas = {
        "assets/imagenes/paneles/Tutorial_pagina_0.jpg",
        "assets/imagenes/paneles/Tutorial_pagina_1.jpg",
        "assets/imagenes/paneles/Tutorial_pagina_2.jpg",
        "assets/imagenes/paneles/Tutorial_pagina_3.jpeg",
        "assets/imagenes/paneles/Tutorial_pagina_4.jpeg",
        "assets/imagenes/paneles/Tutorial_pagina_5.jpg",
        "assets/imagenes/paneles/Tutorial_pagina_6.jpg",
        "assets/imagenes/paneles/Tutorial_pagina_7.jpg"
    };
    private int paginaActual = 0;
    private Image imagenFondoActual;

    public VistaTutorial() {
        this.ventana = new JFrame("Frontier - Tutorial");
        this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ventana.setUndecorated(true);
        this.ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);

        Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int anchoPantalla = tamanoPantalla.width;
        int altoPantalla = tamanoPantalla.height;

        // Cargamos la primera imagen por defecto
        this.imagenFondoActual = cargarImagen(paginas[paginaActual]);

        this.panelFondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imagenFondoActual != null) {
                    g.drawImage(imagenFondoActual, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        this.panelFondo.setLayout(null);

        // --- BOTÓN VOLVER ---
        int anchoBotonVolver = (int) (anchoPantalla * 0.10);
        int altoBotonVolver = (int) (altoPantalla * 0.07);
        int posYVolver = (int) (altoPantalla * 0.85); 
        int posXVolver = (int) (anchoPantalla * 0.05); 
        this.btnVolver = crearBotonConImagen("assets/imagenes/botones/boton_volver.png", posXVolver, posYVolver, anchoBotonVolver, altoBotonVolver);
        this.panelFondo.add(this.btnVolver);

        // --- BOTONES DE NAVEGACIÓN (FLECHAS) ---
        // Ajustamos el tamaño relativo para las flechas
        int anchoFlecha = (int) (anchoPantalla * 0.12);
        int altoFlecha = (int) (altoPantalla * 0.12);
        
        // Centramos las flechas en el eje Y
        int posYFlechas = (altoPantalla / 2) - (altoFlecha / 2);
        
        // Flecha Anterior: Pegada a la izquierda
        int posXAnterior = (int) (anchoPantalla * 0.175);
        this.btnAnterior = crearBotonConImagen("assets/imagenes/botones/flecha_anterior_tutorial.png", posXAnterior, posYFlechas, anchoFlecha, altoFlecha);
        this.btnAnterior.setVisible(false); // Oculto en la página 1
        this.panelFondo.add(this.btnAnterior);

        // Flecha Siguiente: Pegada a la derecha
        int posXSiguiente = anchoPantalla - anchoFlecha - (int) (anchoPantalla * 0.175);
        this.btnSiguiente = crearBotonConImagen("assets/imagenes/botones/flecha_siguiente_tutorial.png", posXSiguiente, posYFlechas, anchoFlecha, altoFlecha);
        this.panelFondo.add(this.btnSiguiente);

        this.ventana.setContentPane(this.panelFondo);
    }

    private Image cargarImagen(String ruta) {
        File archivoImagen = new File(ruta);
        if (archivoImagen.exists()) {
            return new ImageIcon(archivoImagen.getAbsolutePath()).getImage();
        }
        System.err.println("No se encontró la imagen: " + ruta);
        return null;
    }

    private JButton crearBotonConImagen(String rutaImagen, int x, int y, int ancho, int alto) {
        JButton boton = new JButton();
        boton.setBounds(x, y, ancho, alto);
        
        File archivoImagen = new File(rutaImagen);
        if (archivoImagen.exists()) {
            ImageIcon iconoOriginal = new ImageIcon(archivoImagen.getAbsolutePath());
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            boton.setIcon(new ImageIcon(imagenEscalada));
        } else {
            boton.setText("?"); // Failsafe
        }
        
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }

    // --- MÉTODOS PARA CAMBIAR DE PÁGINA ---
    
    public void paginaSiguiente() {
        if (paginaActual < paginas.length - 1) {
            paginaActual++;
            actualizarPagina();
        }
    }

    public void paginaAnterior() {
        if (paginaActual > 0) {
            paginaActual--;
            actualizarPagina();
        }
    }

    private void actualizarPagina() {
        // Carga la nueva imagen
        this.imagenFondoActual = cargarImagen(paginas[paginaActual]);
        
        // Controla la visibilidad de las flechas
        this.btnAnterior.setVisible(paginaActual > 0);
        this.btnSiguiente.setVisible(paginaActual < paginas.length - 1);
        
        // Redibuja la pantalla
        this.panelFondo.repaint();
    }

    // --- MÉTODOS ESTÁNDAR ---

    public void mostrar() { 
        // Siempre que se abre, forzamos a volver a la pág 1 (Opcional, pero recomendado)
        this.paginaActual = 0;
        actualizarPagina();
        this.ventana.setVisible(true); 
    }
    
    public void ocultar() { 
        this.ventana.setVisible(false); 
    }

    public JButton getBtnVolver() { return this.btnVolver; }
    public JButton getBtnAnterior() { return this.btnAnterior; }
    public JButton getBtnSiguiente() { return this.btnSiguiente; }
}