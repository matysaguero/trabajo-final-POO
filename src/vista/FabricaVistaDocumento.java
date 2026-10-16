package vista;

import java.awt.*;
import javax.swing.*;
import modelo.documentos.DNI;
import modelo.documentos.Documento;
import modelo.documentos.Pasaporte;
import modelo.documentos.Permiso;

public class FabricaVistaDocumento {

    private final Image imagenFondo = new ImageIcon("assets/imagenes/pasaportes/pasaporte_mago.jpeg").getImage();

    public JPanel crearVista(Documento doc, int posX, int posY) {
        JPanel panelFondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imagenFondo != null) {
                    g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        panelFondo.setBounds(posX, posY, 300, 400);
        panelFondo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelFondo.setLayout(new BorderLayout());

        StringBuilder detalleExtra = new StringBuilder();
        if (doc instanceof DNI) {
            DNI dni = (DNI) doc;
            detalleExtra.append("<br>Nro DNI: ").append(dni.getNroDni())
                        .append("<br>Raza: ").append(dni.getRaza())
                        .append("<br>Vence: ").append(dni.getVencimiento());
        } else if (doc instanceof Pasaporte) {
            Pasaporte pas = (Pasaporte) doc;
            detalleExtra.append("<br>Nacimiento: ").append(pas.getFechaNacimiento())
                        .append("<br>Código Raza: ").append(pas.getRaza())
                        .append("<br>Huella: ").append(pas.getHuella());
        } else if (doc instanceof Permiso) {
            Permiso perm = (Permiso) doc;
            detalleExtra.append("<br>Permiso Nro: ").append(perm.getNumPermiso())
                        .append("<br>Ocupación: ").append(perm.getOcupacion())
                        .append("<br>Vence: ").append(perm.getFechaVencimiento());
        }

        String texto = "<html><center><b>" + doc.getClass().getSimpleName() + "</b>"
                + "<br><br>Nombre: " + doc.getNombre()
                + "<br>Origen: " + doc.getOrigen()
                + "<br>Válido: " + (doc.isValido() ? "Sí" : "No")
                + detalleExtra
                + "</center></html>";

        JLabel label = new JLabel(texto, SwingConstants.CENTER);
        label.setForeground(Color.BLACK);

        panelFondo.setBackground(new Color(220, 220, 200));
        panelFondo.add(label, BorderLayout.CENTER);

        MoverComponentes mover = new MoverComponentes(panelFondo);
        panelFondo.addMouseListener(mover);
        panelFondo.addMouseMotionListener(mover);

        return panelFondo;
    }
}