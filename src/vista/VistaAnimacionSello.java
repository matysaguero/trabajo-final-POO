package vista;

import java.awt.*;
import java.io.File;
import javax.swing.*;

public class VistaAnimacionSello {
    private final JLayeredPane capaPrincipal;
    private final JLabel lblManoSello;

    public VistaAnimacionSello(JLayeredPane capaPrincipal) {
        this.capaPrincipal = capaPrincipal;
        this.lblManoSello = new JLabel();
    }

    public void reproducir(boolean aceptado, VistaMesaDocumentos mesa, Runnable alTerminar) {
        // 1. CARGAMOS LA IMAGEN ÚNICA DE LA MANO
        int anchoMano = 250;
        int altoMano = 350;
        ImageIcon iconoMano = cargarIcono("assets/imagenes/sello/mano_sello.png", anchoMano, altoMano);
        if (iconoMano != null) this.lblManoSello.setIcon(iconoMano);

        // 2. BUSCAMOS DÓNDE ESTÁ EL DOCUMENTO EN LA PANTALLA
        JPanel panelDoc = mesa.obtenerDocumentoPrincipal();
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        
        int xDestino, yDestino;
        
        if (panelDoc != null) {
            // Sumamos la posición de la mesa + la posición interna del documento arrastrado
            xDestino = mesa.getPanelDocumentos().getX() + panelDoc.getX() + (panelDoc.getWidth() / 2) - (anchoMano / 2);
            yDestino = mesa.getPanelDocumentos().getY() + panelDoc.getY() + (panelDoc.getHeight() / 2) - (altoMano / 2);
        } else {
            // Si el ingresante no trajo documentos (ej. Lázaro), estampamos en el medio de la mesa
            xDestino = (int) (pantalla.width * 0.40);
            yDestino = (int) (pantalla.height * 0.35);
        }
        
        int yInicio = -altoMano; // Entra desde arriba de la pantalla
        this.lblManoSello.setBounds(xDestino, yInicio, anchoMano, altoMano);
        this.capaPrincipal.add(this.lblManoSello, JLayeredPane.DRAG_LAYER);

        int[] frame = {0}; 
        int framesBajada = 12; 
        int framesPausa = 15;  
        int framesSubida = 10; 
        
        Timer timer = new Timer(20, null);
        timer.addActionListener(e -> {
            frame[0]++;
            
            if (frame[0] < framesBajada) {
                // FASE 1: La mano cae
                float progreso = (float) frame[0] / framesBajada;
                int actualY = (int) (yInicio + (yDestino - yInicio) * progreso);
                this.lblManoSello.setLocation(xDestino, actualY);
                
            } else if (frame[0] == framesBajada) {
                // FASE 2 (IMPACTO EXACTO): Insertar la marca visual en el documento
                if (panelDoc != null) {
                    String rutaMarca = aceptado ? "assets/imagenes/sello/sello_verde.png" 
                                                : "assets/imagenes/sello/sello_rojo.png";
                    
                    // Tamaño de la mancha que deja el sello (ajusta a tu gusto)
                    JLabel lblMarca = new JLabel(cargarIcono(rutaMarca, 120, 120)); 
                    
                    // Centramos la marca dentro del pasaporte (o DNI)
                    int marcaX = (panelDoc.getWidth() / 2) - 60;
                    int marcaY = (panelDoc.getHeight() / 2) + 20;
                    lblMarca.setBounds(marcaX, marcaY, 120, 120);
                    
                    // El "0" asegura que el sello quede por encima de la foto y las letras del documento
                    panelDoc.add(lblMarca, 0); 
                    panelDoc.repaint();
                }
                
            } else if (frame[0] <= framesBajada + framesPausa) {
                // FASE 3: Pausa (se está apoyando)
            } else if (frame[0] <= framesBajada + framesPausa + framesSubida) {
                // FASE 4: La mano se retira
                float progreso = (float) (frame[0] - framesBajada - framesPausa) / framesSubida;
                int actualY = (int) (yDestino - (200 * progreso)); 
                this.lblManoSello.setLocation(xDestino, actualY);
            } else {
                // FASE 5: Terminar animación
                timer.stop();
                this.capaPrincipal.remove(this.lblManoSello);
                this.capaPrincipal.repaint();
                if (alTerminar != null) alTerminar.run(); 
            }
        });
        timer.start();
    }

    private ImageIcon cargarIcono(String ruta, int ancho, int alto) {
        File archivo = new File(ruta);
        if (archivo.exists()) {
            Image img = new ImageIcon(archivo.getAbsolutePath()).getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } else {
            System.err.println("ERROR GRAFICO: Falta la imagen en: " + ruta);
            return null;
        }
    }
}