package vista;

import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;


import modelo.politicas.ReglasDia;
import modelo.politicas.ReglasDia1;

public class FabricaVistaReglas {
    private static final int ANCHO = 400;
    private static final int ALTO = 435;
    private static final Font FUENTE_DATOS = new Font("Monospaced", Font.BOLD, 20);
    private static final Color COLOR_TEXTO = new Color(35, 25, 20); 
    private static final String ImagenReglas = "assets/imagenes/permisos de entrada, libro de reglas y sellos/libro_de_reglas_abierto.png"; 
    // Sólo vamos a utilizar una única imagen para el libro de reglas.

@FunctionalInterface
    private interface CreadorVista {
        JPanel crear(ReglasDia reglasDia, int posX, int posY);
    }

    private final Map<Class<? extends ReglasDia>, CreadorVista> creadores = new HashMap<>();

    public FabricaVistaReglas() {
        //Registramos cada tipo de documento con la lambda que sabe dibujarlo
        creadores.put(ReglasDia1.class, (reglasDia, x, y) -> crearVistaDia1((ReglasDia1) reglasDia, x, y));
        
        //creadores.put(Pasaporte.class, (doc, x, y) -> crearVistaPasaporte((Pasaporte) doc, x, y));
    }
    public JPanel crearVista(ReglasDia reglasDia, int posX, int posY) {
        CreadorVista creador = creadores.get(reglasDia.getClass());
        
        if (creador != null) {
            return creador.crear(reglasDia, posX, posY); // Ejecuta el método específico
        }
        
        // Si llega un documento para el cual no programaste vista, devuelve panel vacío
        return new JPanel(); 
    }

    public JPanel crearVistaDia1(ReglasDia1 dia1, int posX, int posY){
        Image imgPlantilla = cargarImagen(ImagenReglas);

        JPanel panelDia1 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imgPlantilla != null) {
                    g.drawImage(imgPlantilla, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

    panelDia1.setLayout(null);
    panelDia1.setOpaque(false);
    panelDia1.setBounds(posX, posY, ANCHO, ALTO);

    //Se modelan los textos que van a aparecer en cada vista.
    
    
    int inicioX = 81;
    int inicioY = 75;
    int separacionEntreRenglones = 25;

    List<String> renglones = dia1.getReglasDetalladas();

    for(int i = 0; i < renglones.size(); i++){
        int posicionYactual = inicioY + (i * separacionEntreRenglones);

        String texto = "<html><b>" + renglones.get(i) + "</b><html>";

        panelDia1.add(crearLabelTexto(texto, inicioX, posicionYactual, 350, 25));

    }
    

    MoverComponentes mover = new MoverComponentes(panelDia1);
    panelDia1.addMouseListener(mover);
    panelDia1.addMouseMotionListener(mover);
    return panelDia1;
    }

    private JLabel crearLabelTexto(String texto, int x, int y, int ancho, int alto) {
        JLabel label = new JLabel(texto);
        label.setFont(FUENTE_DATOS);
        label.setForeground(COLOR_TEXTO);
        label.setBounds(x, y, ancho, alto);
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