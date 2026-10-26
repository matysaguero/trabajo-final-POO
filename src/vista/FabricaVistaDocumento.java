package vista;

import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

import modelo.documentos.DNI;
import modelo.documentos.Documento;
import modelo.TipoClan; // NUEVO
import modelo.documentos.Permiso; // NUEVO
import modelo.documentos.Pasaporte; // NUEVO

public class FabricaVistaDocumento {

    private static final Font FUENTE_DATOS = new Font("Monospaced", Font.BOLD, 14);
    private static final Color COLOR_TEXTO = new Color(35, 25, 20);
    private static final int ANCHO_DNI = 420;
    private static final int ALTO_DNI = 312;
    private static final int ANCHO_PERMISO = 320;
    private static final int ALTO_PERMISO = 456;
    private static final int ANCHO_PASAPORTE = 360;
    private static final int ALTO_PASAPORTE = 440;

    // Defino una interfaz funcional (una interfaz de un solo metodo abstracto) para poder usar lambdas
@FunctionalInterface
    private interface CreadorVista {
        JPanel crear(Documento doc, TipoClan clan, int posX, int posY);
    }

    private final Map<Class<? extends Documento>, CreadorVista> creadores = new HashMap<>();

    public FabricaVistaDocumento() {
        //Registramos cada tipo de documento con la lambda que sabe dibujarlo
        creadores.put(DNI.class, (doc, clan, x, y) -> crearVistaDNI((DNI) doc, x, y));
        creadores.put(Permiso.class, (doc, clan, x, y) -> crearVistaPermiso((Permiso) doc, x, y));
        creadores.put(Pasaporte.class, (doc, clan, x, y) -> crearVistaPasaporte((Pasaporte) doc, x, y));
    }
    
    public JPanel crearVista(Documento doc,TipoClan clan, int posX, int posY) {
        // if con doc == null
        // Buscamos en el mapa la lambda que corresponde a la clase de este documento específico
        CreadorVista creador = creadores.get(doc.getClass());
        
        if (creador != null) {
            return creador.crear(doc,clan, posX, posY); // Ejecuta el método específico
        }
        
        // Si llega un documento para el cual no programaste vista, devuelve panel vacío
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
        panelDNI.add(crearLabelTexto(dni.getNombre(), 265, 64, 148, 20));

        // Al lado de "RAZA:"
        panelDNI.add(crearLabelTexto(dni.getRaza(), 237, 93, 174, 20));

        // Al lado de "NACIMIENTO:"
        panelDNI.add(crearLabelTexto(dni.getFechaNacimiento().toString(), 320, 121, 92, 20));

        // Al lado de "ORIGEN:"
        panelDNI.add(crearLabelTexto(dni.getOrigen(), 265, 150, 146, 20));

        // Al lado de "DNI:"
        panelDNI.add(crearLabelTexto(String.valueOf(dni.getNroDni()), 222, 180, 188, 20));

        // Fecha de "VENCIMIENTO:" (ubicada junto al rótulo VENCIMIENTO:)
        panelDNI.add(crearLabelTexto(dni.getFechaVencimiento().toString(), 260, 244, 125, 20));

        // Permitir arrastrar el DNI por la mesa
        MoverComponentes mover = new MoverComponentes(panelDNI);
        panelDNI.addMouseListener(mover);
        panelDNI.addMouseMotionListener(mover);

        return panelDNI;
    }

    private JPanel crearVistaPermiso(Permiso permiso, int posX, int posY) {

        // 1. Elegir la plantilla según el clan EMISOR del permiso.
        String ruta = obtenerRutaPermiso(permiso.getClanEmisor());

        // Reutilizamos tu método existente.
        Image imagenPlantilla = cargarImagen(ruta);

        // 2. Crear el panel con la plantilla de fondo.
        JPanel panelPermiso = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                try {
                    // Conserva los bordes del pixel art.
                    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
                    g2.drawImage(imagenPlantilla,0, 0,getWidth(), getHeight(),this);
                } finally {
                    g2.dispose();
                }
            }
        };

        panelPermiso.setLayout(null);
        panelPermiso.setOpaque(false);
        panelPermiso.setBounds(posX, posY, ANCHO_PERMISO, ALTO_PERMISO);

        // 3. Incorporar la información del Modelo.
        panelPermiso.add(crearLabelTexto(permiso.getNombre(), 120, 160, 190, 22));
        panelPermiso.add(crearLabelTexto(permiso.getDni(), 80, 190, 190, 22));
        panelPermiso.add(crearLabelTexto(permiso.getOcupacion(), 153, 215, 160, 22));
        panelPermiso.add(crearLabelTexto(permiso.getOrigen(), 115, 246, 230, 22));
        panelPermiso.add(crearLabelTexto(String.valueOf(permiso.getNumPermiso()), 152, 270, 150, 22));
        panelPermiso.add(crearLabelTexto(permiso.getFechaVencimiento().toString(), 105, 297, 200, 22));

        // 4. Permitir mover el documento por la mesa.
        MoverComponentes mover = new MoverComponentes(panelPermiso);
        panelPermiso.addMouseListener(mover);
        panelPermiso.addMouseMotionListener(mover);

        return panelPermiso;
    }

    private JPanel crearVistaPasaporte(Pasaporte pasaporte, int posX, int posY) {

        Image imgPlantilla =cargarImagen(pasaporte.getPlantillaPasaporte());
        
        JPanel panelPasaporte = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
            super.paintComponent(g);
                if (imgPlantilla != null) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    try {
                        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
                        g2.drawImage( imgPlantilla, 0, 0, getWidth(), getHeight(), this);
                    } finally {
                        g2.dispose();
                    }
                }
            }
        };

        panelPasaporte.setLayout(null);
        panelPasaporte.setOpaque(false);
        panelPasaporte.setBounds( posX, posY, ANCHO_PASAPORTE, ALTO_PASAPORTE);

        // Foto carnet.
        panelPasaporte.add(crearLabelImagen(pasaporte.getFoto(),34, 86, 111, 115));

        // Nombre.
        panelPasaporte.add(crearLabelTexto(pasaporte.getNombre(),225, 78, 125, 20));

        // Raza.
        panelPasaporte.add(crearLabelTexto(pasaporte.getRaza(),212, 106, 138, 20));

        // Fecha de nacimiento.
        panelPasaporte.add(crearLabelTexto(pasaporte.getFechaNacimiento().toString(),260, 132, 88, 20));

        // Origen.
        panelPasaporte.add(crearLabelTexto(pasaporte.getOrigen(),225, 160, 125, 20));

        // Huella: se muestra como imagen.
        panelPasaporte.add(crearLabelImagen(pasaporte.getHuella(), 230, 183, 75, 32));

        // Arrastre por la mesa.
        MoverComponentes mover = new MoverComponentes(panelPasaporte);
        panelPasaporte.addMouseListener(mover);
        panelPasaporte.addMouseMotionListener(mover);
        panelPasaporte.setName("Pasaporte");
        return panelPasaporte;
    }

    private JLabel crearLabelTexto(String texto, int x, int y, int ancho, int alto) {
        JLabel label = new JLabel(texto);
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

    private String obtenerRutaPermiso(TipoClan clan) {
        if (clan == null) {
            throw new IllegalArgumentException("El clan no puede ser nulo");
        }
        return switch (clan) {
            case UNION_MAGICA -> "assets/imagenes/permisos/permiso_um.jpg";
            case PUERTO_UMBRAL -> "assets/imagenes/permisos/permiso_pu.jpg";
            case TITAN_DIGITAL -> "assets/imagenes/permisos/permiso_ca.jpg";};
    }

}