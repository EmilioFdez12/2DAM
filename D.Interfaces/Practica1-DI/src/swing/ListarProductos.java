package swing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import utils.Producto;

public class ListarProductos extends JFrame {
	
	private DefaultListModel<Producto> modelProductos;
    private static final long serialVersionUID = 1L;
    private JList jListProductos;
    
    // Constructor
    public ListarProductos() {
        setTitle("Listar Productos");
        setBounds(825, 450, 506, 387);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Panel para el formulario de alta
        JPanel panel = new JPanel();
        
        // Añadir el panel a la ventana
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 470, 326);
        panel.add(scrollPane);
               
        modelProductos = new DefaultListModel<>();  // Inicializa el modelo de la lista
        jListProductos = new JList<>(modelProductos);
        scrollPane.setViewportView(jListProductos);
        
        productosDefault();
        
        
        // Hacer visible la ventana
        setVisible(true);
        
        
    }
    
    private void productosDefault() {
    	modelProductos.addElement(new Producto("Manzanas", 1.20, true));
    	modelProductos.addElement(new Producto("Plátanos", 1.10, true));
    	modelProductos.addElement(new Producto("Naranjas", 0.90, true));
    	modelProductos.addElement(new Producto("Uvas", 2.50, true));
    	modelProductos.addElement(new Producto("Fresas", 3.00, true));
    	modelProductos.addElement(new Producto("Leche", 0.80, true));
    	modelProductos.addElement(new Producto("Huevos", 2.00, true));
    	modelProductos.addElement(new Producto("Yogur", 0.75, true));
    	modelProductos.addElement(new Producto("Pan", 1.50, true));
    	modelProductos.addElement(new Producto("Arroz", 1.00, false));
    	modelProductos.addElement(new Producto("Pasta", 0.90, false));
    	modelProductos.addElement(new Producto("Azúcar", 1.30, false));
    	modelProductos.addElement(new Producto("Sal", 0.50, false));
    	modelProductos.addElement(new Producto("Aceite de Oliva", 5.00, false));
    	modelProductos.addElement(new Producto("Vinagre", 1.20, false));
    	modelProductos.addElement(new Producto("Cereal", 2.50, false));
    	modelProductos.addElement(new Producto("Mermelada", 3.40, true));
    	modelProductos.addElement(new Producto("Queso", 2.80, true));
    	modelProductos.addElement(new Producto("Jamon", 4.50, true));
    	modelProductos.addElement(new Producto("Carne de Pollo", 6.00, true));
    	modelProductos.addElement(new Producto("Carne de Res", 10.00, true));
    	modelProductos.addElement(new Producto("Pescado", 8.00, true));
    	modelProductos.addElement(new Producto("Verduras Mixtas", 1.50, true));
    	modelProductos.addElement(new Producto("Patatas", 0.80, true));
    	modelProductos.addElement(new Producto("Cebollas", 0.60, true));
    	modelProductos.addElement(new Producto("Ajo", 0.40, true));
    	modelProductos.addElement(new Producto("Tomates", 1.00, true));
    	modelProductos.addElement(new Producto("Pepinos", 0.70, true));
    	modelProductos.addElement(new Producto("Lentejas", 1.50, false));
    	modelProductos.addElement(new Producto("Frijoles", 1.00, false));
    	modelProductos.addElement(new Producto("Garbanzos", 1.20, false));
    	modelProductos.addElement(new Producto("Chocolate", 2.00, false));
    	modelProductos.addElement(new Producto("Galletas", 1.50, false));
    	modelProductos.addElement(new Producto("Snack Salado", 1.80, false));
    	modelProductos.addElement(new Producto("Bebida Energética", 2.50, false));
    	modelProductos.addElement(new Producto("Café", 3.00, false));
    	modelProductos.addElement(new Producto("Té", 2.00, false));

    }
    
    public DefaultListModel<Producto> getModelProductos() {	
  		return modelProductos;
  	}

}
