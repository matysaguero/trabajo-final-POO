package vista;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class PapersPleaseDesk extends JFrame {
    private JLayeredPane desktop;

    public PapersPleaseDesk() {
        setTitle("Desk Interface");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);

        // JLayeredPane allows overlapping components
        desktop = new JLayeredPane();
        desktop.setBackground(new Color(45, 50, 45)); // Dark desk color
        desktop.setOpaque(true);
        add(desktop);

        // Add a couple of draggable game documents
        desktop.add(createDocument("PASSPORT", Color.RED, 100, 150), JLayeredPane.DEFAULT_LAYER);
        desktop.add(createDocument("ENTRY PERMIT", Color.BLUE, 300, 200), JLayeredPane.DEFAULT_LAYER);
    }

    private JPanel createDocument(String title, Color color, int x, int y) {
        JPanel doc = new JPanel();
        doc.setBackground(color);
        doc.setBounds(x, y, 150, 200); // Set exact size and starting position
        doc.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        JLabel label = new JLabel(title, SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        doc.setLayout(new BorderLayout());
        doc.add(label, BorderLayout.CENTER);

        // Add custom drag capabilities
        ComponentMover mover = new ComponentMover(doc);
        doc.addMouseListener(mover);
        doc.addMouseMotionListener(mover);

        return doc;
    }

    // Inner class to handle smooth dragging and depth sorting
    private class ComponentMover extends MouseAdapter {
        private final JPanel targetPanel;
        private Point screenOffset;

        public ComponentMover(JPanel targetPanel) {
            this.targetPanel = targetPanel;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // Bring clicked document to the very top layer
            desktop.moveToFront(targetPanel);
            
            // Remember exactly where the mouse clicked inside the panel
            screenOffset = e.getPoint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (screenOffset == null) return;

            // Compute new position relative to parent desktop coordinates
            int newX = targetPanel.getX() + e.getX() - screenOffset.x;
            int newY = targetPanel.getY() + e.getY() - screenOffset.y;

            // (Optional) Add constraints here to stop documents from leaving the screen
            
            targetPanel.setLocation(newX, newY);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            screenOffset = null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PapersPleaseDesk().setVisible(true));
    }
}
