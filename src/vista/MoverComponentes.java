package vista;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import org.w3c.dom.events.MouseEvent;


public class MoverComponentes extends MouseAdapter {
        private final JPanel panelObjetivo;
        private Point screenOffset;

        public MoverComponentes(JPanel panelObjetivo) {
            this.panelObjetivo = panelObjetivo;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // Bring clicked document to the very top layer
            .moveToFront(panelObjetivo);
            
            // Remember exactly where the mouse clicked inside the panel
            screenOffset = e.getPoint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (screenOffset == null) return;

            // Compute new position relative to parent desktop coordinates
            int newX = panelObjetivo.getX() + e.getX() - screenOffset.x;
            int newY = panelObjetivo.getY() + e.getY() - screenOffset.y;

            // Se deben agregar reestricciones (CONSTRAINTS) para que no puedan salir de la ventana los documentos creados
            
            panelObjetivo.setLocation(newX, newY);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            screenOffset = null;
        }
    }
