package swing;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.MainApp;
import utils.Cliente;

import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class MainWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JMenu mnClientes;
    private JMenu mnProductos;
    private JList<Cliente> listClientes;
    private DefaultListModel<Cliente> modelClientes;  // Modelo para la lista de clientes

    /**
     * Create the frame.
     */
    public MainWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(750, 350, 473, 397);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        mnClientes = new JMenu("Clientes");
        menuBar.add(mnClientes);
        
        JMenuItem mnAltaCliente = new JMenuItem("Alta Cliente");
        mnAltaCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentanaAltaCliente();
            }
        });
        mnClientes.add(mnAltaCliente);

        JMenuItem mnBajaCliente = new JMenuItem("Baja Cliente");
        mnBajaCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentanaBajaCliente();
            }
        });
        mnClientes.add(mnBajaCliente);
        
        mnProductos = new JMenu("Productos");
        menuBar.add(mnProductos);
        
        JMenuItem mnAltaProductos = new JMenuItem("Alta Productos");
        mnAltaProductos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentanaAltaProductos();
            }
        });
        mnProductos.add(mnAltaProductos);
        
        JMenuItem mnListarProductos = new JMenuItem("Listar Productos");
        mnListarProductos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentanaListarProductos();
            }
        });
        mnProductos.add(mnListarProductos);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 437, 314);
        contentPane.add(scrollPane);
        
        modelClientes = new DefaultListModel<>();  // Inicializa el modelo de la lista
        listClientes = new JList<>(modelClientes);
        scrollPane.setViewportView(listClientes);
    }

    // Método para devolver el modelo de lista de clientes
    public DefaultListModel<Cliente> getModelClientes() {
        return modelClientes;
    }

    private void abrirVentanaAltaCliente() {
        AltaClientes ventanaAltaCliente = new AltaClientes(this); // Pasa la referencia de MainWindow
        ventanaAltaCliente.setVisible(true); // Muestra la ventana
    }

    private void abrirVentanaBajaCliente() {
        BajaClientes ventanaBajaCliente = new BajaClientes();
        ventanaBajaCliente.setVisible(true);
    }

    private void abrirVentanaAltaProductos() {
        AltaProductos ventanaAltaProductos = new AltaProductos();
        ventanaAltaProductos.setVisible(true);
    }

    private void abrirVentanaListarProductos() {
        ListarProductos ventanaListarProductos = new ListarProductos();
        ventanaListarProductos.setVisible(true);
    }
}
