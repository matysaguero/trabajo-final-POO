package vista;

import modelo.documentos.Documento;
import java.awt.*;
import javax.swing.*;

public class FabricaVistaDocumento {

    public JPanel crearVista(Documento doc, int posX, int posY) {
        JPanel panelFondo = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if(imagenFondo != null) {
                    g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        panelFondo.setBounds(posX, posY, 300, 400); 
        panelFondo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelFondo.setLayout(new BorderLayout());

        // Aquí extraemos los datos reales de la base de datos/modelo
        String texto = "<html><center>" + doc.getClass().getSimpleName() + 
                       "<br><br>" + doc.getNumId() + 
                       "<br>Vence: " + doc.getFechaVencimiento() + 
                       "</center></html>";

        JLabel label = new JLabel("", SwingConstants.CENTER);
        label.setForeground(Color.BLACK);
        panelFondo.setBackground(new Color(220, 220, 200)); // Color papel
        panelFondo.add(label, BorderLayout.CENTER);

        // Le agregamos la capacidad de arrastre
        MoverComponentes mover = new MoverComponentes(panelFondo);
        panelFondo.addMouseListener(mover);
        panelFondo.addMouseMotionListener(mover);

        return panelFondo;
    }

private final Image imagenFondo = new ImageIcon("assets/imagenes/documentos/pasaporte_vigente.jpeg").getImage();
}

