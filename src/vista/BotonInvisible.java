package vista;

import javax.swing.JButton;
import java.awt.Cursor;

public class BotonInvisible extends JButton {
    
    public BotonInvisible() {
        super(); // Llama al constructor original
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false); // Cambiar a true temporalmente si necesitan ver los bordes para acomodarlos
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}