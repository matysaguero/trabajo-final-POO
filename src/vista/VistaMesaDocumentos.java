package vista;

import java.awt.*;
import javax.swing.*;

public class VistaMesaDocumentos {
    private final JLayeredPane panelDocumentos;

    public VistaMesaDocumentos() {
        Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();

        // JLayeredPane permite componentes que se superpongan
        this.panelDocumentos = new JLayeredPane();
        this.panelDocumentos.setOpaque(false); //Transparente, no hace falta settear un fondo.
        this.panelDocumentos.setBounds((int) (tamanoPantalla.width*0.337), //Posicion x
                                       (int) (tamanoPantalla.height*0.35), //Posicion Y
                                       (int) (tamanoPantalla.width*0.66), // Ancho
                                       (int) (tamanoPantalla.height*0.65)); // Alto 

        this.panelDocumentos.add(crearDocumento("Pasaporte", Color.GREEN, 100, 150), JLayeredPane.DEFAULT_LAYER);
        this.panelDocumentos.add(crearDocumento("Permiso entrada", Color.GRAY, 300, 200), JLayeredPane.DEFAULT_LAYER);
    }

    public JPanel crearDocumento(String titulo, Color color, int posX, int posY){
        
        JPanel documento = new JPanel();
        documento.setBackground(color);
        documento.setBounds(posX, posY, 150, 200); // Donde va a aparecer el documento y que tamaño va a tener 
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