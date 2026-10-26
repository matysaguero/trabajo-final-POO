package vista;

import java.awt.*;
import java.io.File;
import javax.swing.*;

public class VistaInterrogatorio {
    private final JDialog ventanaEmergente;
    private final JLabel lblDialogoJugador;
    private final JLabel lblDialogoIngresante;
    private Timer timerOcultar; 

    private final int anchoCuadro = 300;
    private final int altoCuadro = 100;

    public VistaInterrogatorio(JFrame ventanaPadre) {
        this.ventanaEmergente = new JDialog(ventanaPadre, "Interrogatorio", false);
        this.ventanaEmergente.setUndecorated(true);
        this.ventanaEmergente.setBackground(new Color(0, 0, 0, 0));
        this.ventanaEmergente.setFocusableWindowState(false);

        Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        this.ventanaEmergente.setSize(tamanoPantalla.width, tamanoPantalla.height);
        this.ventanaEmergente.setLocation(0, 0);

        JPanel panelContenedor = new JPanel();
        panelContenedor.setLayout(null); 
        panelContenedor.setOpaque(false);

        ImageIcon iconoDialogo = cargarIcono("assets/imagenes/paneles/cuadro_dialogo.png", anchoCuadro, altoCuadro);

        this.lblDialogoJugador = crearLabelDialogo(iconoDialogo);
        this.lblDialogoIngresante = crearLabelDialogo(iconoDialogo);

        int npcX = (int) (tamanoPantalla.width * 0.00); 
        int npcY = (int) (tamanoPantalla.height * 0.35);
        this.lblDialogoIngresante.setBounds(npcX, npcY, anchoCuadro, altoCuadro);

        int jugadorX = (int) (tamanoPantalla.width * 0.18); // <-- Ajusta este valor para Mover Izq/Der
        int jugadorY = (int) (tamanoPantalla.height * 0.30); // <-- Ajusta este valor para Mover Arriba/Abajo
        this.lblDialogoJugador.setBounds(jugadorX, jugadorY, anchoCuadro, altoCuadro);

        panelContenedor.add(this.lblDialogoIngresante);
        panelContenedor.add(this.lblDialogoJugador);

        this.ventanaEmergente.setContentPane(panelContenedor);
    }

    private ImageIcon cargarIcono(String ruta, int ancho, int alto) {
        ImageIcon iconoOriginal = new ImageIcon(ruta);
        if (iconoOriginal.getIconWidth() > 0) {
            Image img = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } else {
            System.err.println("ERROR GRAFICO: No se pudo encontrar la imagen en: " + ruta);
            return null;
        }
    }

    private JLabel crearLabelDialogo(ImageIcon icono) {
        JLabel label = new JLabel();
        if (icono != null) {
            label.setIcon(icono);
        }
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.CENTER);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Monospaced", Font.BOLD, 13)); 
        return label;
    }

    public void mostrar(String dialogoCompleto, Runnable alTerminar) {
        String[] partes = dialogoCompleto.split("\n");


        if (partes.length >= 2) {
            this.lblDialogoJugador.setText("<html><div style='text-align: center; width: 230px;'>" + partes[0] + "</div></html>");
            this.lblDialogoIngresante.setText("<html><div style='text-align: center; width: 230px;'>" + partes[1] + "</div></html>");
            this.lblDialogoIngresante.setVisible(true); // Aseguramos que se muestre si hay respuesta
        } else {
            this.lblDialogoJugador.setText("<html><div style='text-align: center; width: 230px;'>" + dialogoCompleto + "</div></html>");
            this.lblDialogoIngresante.setVisible(false); // Oculta todo el cuadro del NPC si no responde
        }

        this.ventanaEmergente.setVisible(true);

        if (timerOcultar != null && timerOcultar.isRunning()) {
            timerOcultar.stop();
        }

        timerOcultar = new Timer(4000, e -> {
            ocultar();
            if (alTerminar != null) {
                alTerminar.run();
            }
        });
        timerOcultar.setRepeats(false); 
        timerOcultar.start();
    }

    public void ocultar() {
        this.ventanaEmergente.setVisible(false);
    }
}