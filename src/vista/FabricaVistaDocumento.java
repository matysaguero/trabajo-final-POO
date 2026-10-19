package vista;

import java.awt.*;
import java.io.File;
import java.time.LocalDate;

import javax.swing.*;
import modelo.documentos.DNI;
import modelo.documentos.Documento;

public class FabricaVistaDocumento {

    private static final int ANCHO_DNI = 420;
    private static final int ALTO_DNI = 312;
    private static final Font FUENTE_DATOS = new Font("Monospaced", Font.BOLD, 14);
    private static final Color COLOR_TEXTO = new Color(35, 25, 20);

    public JPanel crearVista(Documento doc, int posX, int posY) {
        if (doc instanceof DNI) {
            return crearVistaDNI((DNI) doc, posX, posY);
        }
        return new JPanel();
    }

    private JPanel crearVistaDNI(DNI dni, int posX, int posY) {
        Image imgPlantilla = cargarImagen(dni.getPlantillaDni());

        JPanel panelDNI = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imgPlantilla != null) {
                    g.drawImage(imgPlantilla, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        panelDNI.setLayout(null);
        panelDNI.setOpaque(false);
        panelDNI.setBounds(posX, posY, ANCHO_DNI, ALTO_DNI);

        // 1. FOTO dentro del recuadro negro (x=21, y=84, ancho=129, alto=132)
        JLabel lblFoto = crearLabelImagen(dni.getFoto(), 21, 84, 129, 132);
        panelDNI.add(lblFoto);

        // 2. HUELLA justo debajo de "HUELLA:" (x=30, y=242, ancho=105, alto=55)
        JLabel lblHuella = crearLabelImagen(dni.getHuella(), 30, 242, 105, 55);
        panelDNI.add(lblHuella);

        // 3. TEXTOS al lado de cada etiqueta de la plantilla
        // Al lado de "NOMBRE:"
        panelDNI.add(crearLabelTexto(dni.getNombre(), 258, 83, 148, 20));

        // Al lado de "RAZA:"
        panelDNI.add(crearLabelTexto(dni.getRaza(), 232, 111, 174, 20));

        // Al lado de "NACIMIENTO:"
        panelDNI.add(crearLabelTexto(dni.getFechaNacimiento(), 316, 139, 92, 20));

        // Al lado de "ORIGEN:"
        panelDNI.add(crearLabelTexto(dni.getOrigen(), 260, 166, 146, 20));

        // Al lado de "DNI:"
        panelDNI.add(crearLabelTexto(String.valueOf(dni.getNroDni()), 216, 194, 188, 20));

        // Fecha de "VENCIMIENTO:" (ubicada junto al rótulo VENCIMIENTO:)
        panelDNI.add(crearLabelTexto(dni.getFechaVencimiento(), 280, 244, 125, 20));

        // Permitir arrastrar el DNI por la mesa
        MoverComponentes mover = new MoverComponentes(panelDNI);
        panelDNI.addMouseListener(mover);
        panelDNI.addMouseMotionListener(mover);

        return panelDNI;
    }

    private JLabel crearLabelTexto(LocalDate texto, int x, int y, int ancho, int alto) {
        JLabel label = new JLabel();
        label.setFont(FUENTE_DATOS);
        label.setForeground(COLOR_TEXTO);
        label.setBounds(x, y, ancho, alto);
        return label;
    }

    private JLabel crearLabelImagen(String ruta, int x, int y, int ancho, int alto) {
        JLabel label = new JLabel();
        label.setBounds(x, y, ancho, alto);
        Image img = cargarImagen(ruta);
        if (img != null) {
            Image escalada = img.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(escalada));
        }
        return label;
    }

    private Image cargarImagen(String ruta) {
        if (ruta == null || ruta.isBlank()) {
            return null;
        }
        // Si la ruta empieza con "/", probamos también sin la barra inicial para rutas relativas
        String rutaLimpia = ruta.startsWith("/") ? ruta.substring(1) : ruta;
        File archivo = new File(rutaLimpia);
        if (!archivo.exists()) {
            archivo = new File(ruta);
        }
        if (archivo.exists()) {
            return new ImageIcon(archivo.getPath()).getImage();
        }
        return new ImageIcon(rutaLimpia).getImage();
    }

}