package vista;

import java.awt.*;
import javax.swing.*;

public class VistaMesaDocumentos {
    
    private final JFrame Escritorio;
    private final JLayeredPane Documento;

    public VistaMesaDocumentos(JFrame Mesa) {
        this.Escritorio = new JFrame("Escritorio");
        this.Escritorio.setUndecorated(true);
        this.Escritorio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Sin layout porque necesito el espacio libre para poder arrastrar los documentos que quiero controlar
        this.Escritorio.setLayout(null);

        // JLayeredPane permite componentes que se superpongan
        this.Documento = new JLayeredPane();
        this.Documento.setBackground(new Color(45, 50, 45)); // Dark desk color
        this.Documento.setOpaque(true);
        this.Documento.add(Escritorio); 

        this.Documento.add(crearDocumento("Pasaporte", Color.GREEN, 100, 150), JLayeredPane.DEFAULT_LAYER);
        this.Documento.add(crearDocumento("Permiso entrada", Color.GRAY, 300, 200), JLayeredPane.DEFAULT_LAYER);
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


    public void mostrar() { this.Escritorio.setVisible(true); }
    public void ocultar() { this.Escritorio.setVisible(false); }
  //preguntar si esta bien la distro de las tareas de esta clase  
} 