package vista;

import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class VistaReloj {
    private final JLabel lblContenedorReloj;
    private final JLabel lblHora;

    public VistaReloj(int x, int y, int ancho, int alto) {
        // 1. Label principal que contendrá la imagen del reloj como fondo
        this.lblContenedorReloj = new JLabel();
        this.lblContenedorReloj.setBounds(x, y, ancho, alto);
        
        ImageIcon iconoReloj = cargarImagen("assets/imagenes/paneles/reloj.png", ancho, alto);
        if (iconoReloj != null) {
            this.lblContenedorReloj.setIcon(iconoReloj);
        }
        this.lblContenedorReloj.setLayout(null); // Permite posicionar el texto interno con precisión

        // 2. Label interno para la hora (Estilo idéntico al contador de ingresantes: fuente monospaced y color digital)
        this.lblHora = new JLabel("00:00:00", JLabel.CENTER);
        
        // Coordenadas relativas para centrar el texto dentro de la pantallita del asset
        int textoAncho = ancho - 20;
        int textoAlto = 20;
        int textoX = (ancho - textoAncho) / 2;
        int textoY = (alto - textoAlto) / 2;
        
        this.lblHora.setBounds(textoX, textoY, textoAncho, textoAlto);
        
        // Color y tipografía estilo digital (ajustado al tono del contador de ingresantes)
        this.lblHora.setForeground(new Color(255, 180, 0)); // Amarillo/Naranja clásico de panel digital
        this.lblHora.setFont(new Font("Monospaced", Font.BOLD, 15)); 
        
        this.lblContenedorReloj.add(this.lblHora);

        // 3. Temporizador en tiempo real (actualización cada 1 segundo)
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        Timer timer = new Timer(1000, e -> {
            this.lblHora.setText(formatoHora.format(new Date()));
        });
        timer.start();
    }

    private ImageIcon cargarImagen(String ruta, int ancho, int alto) {
        File archivo = new File(ruta);
        if (archivo.exists()) {
            Image img = new ImageIcon(archivo.getAbsolutePath()).getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        }
        System.err.println("ERROR GRAFICO: No se encuentra el asset del reloj en: " + ruta);
        return null;
    }

    public JLabel getComponente() {
        return this.lblContenedorReloj;
    }
}