package vista;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;


public class MoverComponentes extends MouseAdapter {
        private final JPanel panelObjetivo;
        private Point screenOffset;

        public MoverComponentes(JPanel panelObjetivo) {
            this.panelObjetivo = panelObjetivo;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            Container padre = panelObjetivo.getParent();

            // Trae el panel clickeado (los documentos) al frente.
            if (padre instanceof JLayeredPane) { 
                ((JLayeredPane) padre).moveToFront(panelObjetivo); // si el padre es un panel con capas,
                // lo trae al frente del panelObjetivo que va a ser la ventana.
            }
        
            
            // 
            screenOffset = e.getPoint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (screenOffset == null) return;

            // Compute new position relative to parent desktop coordinates
            int newX = panelObjetivo.getX() + e.getX() - screenOffset.x;
            int newY = panelObjetivo.getY() + e.getY() - screenOffset.y;

            // Se deben agregar reestricciones (CONSTRAINTS) para que no puedan salir de la ventana los documentos creados
            Container padre = panelObjetivo.getParent();
            
            if (padre != null) {
                // Math.max evita que pase del borde izquierdo/superior (0)
                // Math.min evita que pase del borde derecho/inferior (anchoPadre - anchoDocumento)
                newX = Math.max(0, Math.min(newX, padre.getWidth() - panelObjetivo.getWidth()));
                newY = Math.max(0, Math.min(newY, padre.getHeight() - panelObjetivo.getHeight()));
            }            
            
            panelObjetivo.setLocation(newX, newY);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            screenOffset = null;
        }
    }
