package swing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

public class BajaClientes extends JFrame {
    
    private static final long serialVersionUID = 1L;
    
    // Constructor
    public BajaClientes() {
        setTitle("Baja Clientes");
        setBounds(825, 450, 300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Panel para el formulario de alta
        JPanel panel = new JPanel();
        
        // Añadir el panel a la ventana
        getContentPane().add(panel);
        panel.setLayout(null);
        
        // Hacer visible la ventana
        setVisible(true);
    }
}
