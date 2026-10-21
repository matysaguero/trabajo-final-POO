package vista;

import javax.swing.*;
import modelo.politicas.ReglasDia;

public class VistaLibroReglas {

    private final JLayeredPane mesa;
    private JPanel libro;
    private final FabricaVistaReglas fabrica; // NUEVO: La fábrica inyectada

    public VistaLibroReglas(JLayeredPane mesa, FabricaVistaReglas fabrica) {
        if (mesa == null || fabrica == null) {
            throw new IllegalArgumentException("La mesa y la fábrica deben existir");
        }
        this.mesa = mesa;
        this.fabrica = fabrica;
    }

    // Alternar entre abrir y cerrar. Ahora necesita saber qué reglas abrir
    public void alternar(ReglasDia reglas) {
        if (libro == null) {
            abrir(reglas);
        } else {
            cerrar();
        }
    }

    // Crear y mostrar el libro delegando en la fábrica
    private void abrir(ReglasDia reglas) {
        // 1. Le pedimos a la fábrica que arme el panel visual.
        this.libro = this.fabrica.crearVista(reglas, 20, 20);

        // 2. Lo agregamos a la capa Palette para que quede por encima de los documentos
        mesa.add(this.libro, JLayeredPane.PALETTE_LAYER);
        mesa.revalidate();
        mesa.repaint();
    }

    // Cerrar el libro
    public void cerrar() {
        if (libro == null) return;
        mesa.remove(libro);
        libro = null;
        mesa.revalidate();
        mesa.repaint();
    }

    // Recuperar el libro (Queda igual que antes)
    public void restaurarSiAbierto() {
        if (libro == null || libro.getParent() == mesa) return;
        mesa.add(libro, JLayeredPane.PALETTE_LAYER);
        mesa.revalidate();
        mesa.repaint();
    }
}