package vista;

import java.awt.*;
import javax.swing.*;

public class VistaMesaDocumentos {
    private final JLayeredPane panelDocumentos;

    public VistaMesaDocumentos() {

        // JLayeredPane permite componentes que se superpongan
        this.panelDocumentos = new JLayeredPane();
        this.panelDocumentos.setBackground(new Color(45, 50, 45)); // Dark desk color
        this.panelDocumentos.setOpaque(false);
        this.panelDocumentos.setBounds(520,305,1000, 560);

        this.panelDocumentos.add(crearDocumento("Pasaporte", Color.GREEN, 100, 150), JLayeredPane.DEFAULT_LAYER);
        this.panelDocumentos.add(crearDocumento("Permiso entrada", Color.GRAY, 300, 200), JLayeredPane.DEFAULT_LAYER);
    }

    public JPanel crearDocumento(String titulo, Color color, int posX, int posY){
        JPanel documento = new JPanel();
        documento.setBackground(color);
        documento.setBounds(posX, posY, 150, 200); // Donde va a aparecer el documento
        documento.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    
        JLabel label = new JLabel(titulo, SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        documento.setLayout(new BorderLayout());
        documento.add(label, BorderLayout.CENTER);

        // Para poder arrastrar el documento
        MoverComponentes mover = new MoverComponentes(documento);
        documento.addMouseListener(mover);
        documento.addMouseMotionListener(mover);

        return documento;
    }

public JLayeredPane getPanelDocumentos(){
    return this.panelDocumentos;
}
  //preguntar si esta bien la distro de las tareas de esta clase  
} 