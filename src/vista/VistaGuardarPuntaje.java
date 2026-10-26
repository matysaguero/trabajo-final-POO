package vista;

import javax.swing.*;
import java.awt.*;
import java.io.File;


public class VistaGuardarPuntaje {
    private JDialog dialogoRegistro;
    private String nombreIngresado = null;

    public VistaGuardarPuntaje(JFrame ventanaPadre) {
        this.dialogoRegistro = new JDialog(ventanaPadre, true); 
        this.dialogoRegistro.setUndecorated(true);
        this.dialogoRegistro.setSize(600, 200); 
        this.dialogoRegistro.setLocationRelativeTo(ventanaPadre);

        JPanel panelFondo = new JPanel() {
            private final Image fondo = new ImageIcon("assets/imagenes/paneles/ingresa_nombre.png").getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (fondo != null) {
                    g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        panelFondo.setLayout(null);

        // Campo de texto transparente
        JTextField campoNombre = new JTextField();
        campoNombre.setBounds(200, 75, 250, 30);
        campoNombre.setFont(new Font("Monospaced", Font.BOLD, 18));
        campoNombre.setOpaque(false);
        campoNombre.setForeground(new Color(120, 235, 255));;
        campoNombre.setCaretColor(Color.WHITE);
        campoNombre.setBorder(null);
        panelFondo.add(campoNombre);

        JButton btnAceptar = crearBotonImagen("assets/imagenes/botones/boton_aceptar.png", 150, 130, 100, 40);
        
        btnAceptar.addActionListener(e -> {
            this.nombreIngresado = campoNombre.getText();
            this.dialogoRegistro.dispose(); // Cierra el diálogo y devuelve el control
        });
        
        JButton btnCancelar = crearBotonImagen("assets/imagenes/botones/boton_cancelar.png", 350, 130, 100, 40);
        btnCancelar.addActionListener(e -> {
            this.nombreIngresado = null;
            this.dialogoRegistro.dispose();
        });

        panelFondo.add(btnAceptar);
        panelFondo.add(btnCancelar);

        this.dialogoRegistro.setContentPane(panelFondo);
    }

    private JButton crearBotonImagen(String ruta, int x, int y, int ancho, int alto) {
        JButton boton = new JButton();
        boton.setBounds(x, y, ancho, alto);
        File archivo = new File(ruta);
        if (archivo.exists()) {
            Image img = new ImageIcon(archivo.getAbsolutePath()).getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            boton.setIcon(new ImageIcon(img));
        }
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }

    public String getNombreIngresado() { return this.nombreIngresado; }
    
    public String pedirNombreJugador() {
        this.dialogoRegistro.setVisible(true); 
        return this.getNombreIngresado();
    }

}