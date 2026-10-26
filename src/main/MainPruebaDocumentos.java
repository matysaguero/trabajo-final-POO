package main;

import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import modelo.TipoClan;
import modelo.documentos.DNI;
import modelo.documentos.Documento;
import modelo.documentos.Pasaporte;
import modelo.documentos.Permiso;
import vista.FabricaVistaDocumento;
import vista.VistaMesaDocumentos;

public class MainPruebaDocumentos {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 1. Crear ventana de prueba
            JFrame ventanaPrueba = new JFrame("Prueba de Documentos");
            ventanaPrueba.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventanaPrueba.setSize(1200, 800); // Tamaño grande para ver bien la mesa
            ventanaPrueba.setLocationRelativeTo(null);
            ventanaPrueba.setLayout(null); // Posicionamiento libre

            // 2. Instanciar la mesa y la fábrica
            VistaMesaDocumentos mesa = new VistaMesaDocumentos();
            // Ajustamos la mesa temporalmente para que ocupe toda esta ventana de prueba
            mesa.getPanelDocumentos().setBounds(0, 0, 1200, 800); 
            FabricaVistaDocumento fabrica = new FabricaVistaDocumento();

            // 3. Crear documentos de prueba (con datos falsos para ver cómo quedan)
            ArrayList<Documento> docsPrueba = new ArrayList<>();
            
            // DNI de prueba
            docsPrueba.add(new DNI(
                "assets/imagenes/documento.png", "Prueba Nombre", "Ciudad Test", true,
                LocalDate.of(1990, 1, 1), 12345678, LocalDate.of(2165, 1, 1),
                "assets/imagenes/huellas/huella.png", "assets/imagenes/foto_doc/carnet_caballero.png", "HUMANO"
            ));

            // Permiso de prueba
            docsPrueba.add(new Permiso(
                "Prueba Nombre", "Ciudad Test", true, LocalDate.of(2165, 1, 1),
                "12345678", "Trabajador", 9999, TipoClan.PUERTO_UMBRAL
            ));

            // Pasaporte de prueba
            docsPrueba.add(new Pasaporte(
                "Prueba Nombre", "Ciudad Test", true, LocalDate.of(2165, 1, 1),
                LocalDate.of(1990, 1, 1), "HUMANO", "assets/imagenes/huellas/huella.png",
                TipoClan.PUERTO_UMBRAL, "assets/imagenes/foto_doc/carnet_caballero.png", 
                "assets/imagenes/pasaportes/pasp_pu.jpg"
            ));

            // 4. Dibujarlos en la mesa con distintas coordenadas iniciales para que no se superpongan
            int posX = 50;
            int posY = 50;
            for (Documento doc : docsPrueba) {
                // Le pasamos el clan del permiso como clan "general"
                mesa.agregarDocumentoEnMesa(fabrica.crearVista(doc, TipoClan.PUERTO_UMBRAL, posX, posY));
                posX += 380; // Los separamos horizontalmente
            }

            // 5. Mostrar la ventana
            ventanaPrueba.add(mesa.getPanelDocumentos());
            ventanaPrueba.setVisible(true);
        });
    }
}