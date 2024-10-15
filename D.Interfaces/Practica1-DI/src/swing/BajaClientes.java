package swing;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import utils.Cliente;

import java.awt.event.*;

public class BajaClientes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtBuscarNombre;
	private JList<Cliente> listClientes;
	private DefaultListModel<Cliente> modelClientes;
	private JButton btnEliminar;

	public BajaClientes(DefaultListModel<Cliente> clientes) {
		setTitle("Baja Cliente");
		setBounds(825, 450, 410, 284);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Inicializar el modelo de clientes
		this.modelClientes = clientes;

		// Panel para el formulario de baja
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblBuscar = new JLabel("Buscar por nombre");
		lblBuscar.setBounds(10, 14, 120, 14);
		panel.add(lblBuscar);

		txtBuscarNombre = new JTextField();
		txtBuscarNombre.setBounds(140, 11, 194, 20);
		panel.add(txtBuscarNombre);
		txtBuscarNombre.setColumns(10);

		// Lista para mostrar el cliente encontrado
		listClientes = new JList<>();
		JScrollPane scrollPane = new JScrollPane(listClientes);
		scrollPane.setBounds(10, 45, 370, 148);
		panel.add(scrollPane);

		// Botón para eliminar al cliente
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false); // Desactivado al principio
		btnEliminar.setBounds(150, 204, 100, 30);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarCliente();
			}
		});
		panel.add(btnEliminar);

		// Agregar el DocumentListener para realizar la búsqueda cada vez que se cambia
		// el texto
		txtBuscarNombre.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				buscarCliente();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				buscarCliente();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				buscarCliente();
			}
		});

		// Establecemos el modelo de la lista con los clientes iniciales
		listClientes.setModel(modelClientes);

		// Añadir el ListSelectionListener para habilitar/deshabilitar el botón eliminar
		listClientes.addListSelectionListener(e -> {
			// Habilitamos el botón solo si hay un cliente seleccionado
			if (!e.getValueIsAdjusting() && listClientes.getSelectedValue() != null) {
				btnEliminar.setEnabled(true);
			} else {
				btnEliminar.setEnabled(false);
			}
		});
	}

	// Método para buscar al cliente por nombre
	private void buscarCliente() {
		String nombreBuscado = txtBuscarNombre.getText().trim().toLowerCase();
		if (nombreBuscado.isEmpty()) {
			listClientes.setModel(new DefaultListModel<>()); // Limpiar la lista si el campo está vacío
			return;
		}

		// Filtramos los clientes que coinciden con el nombre (de forma parcial)
		DefaultListModel<Cliente> resultadoBusqueda = new DefaultListModel<>();
		for (int i = 0; i < modelClientes.getSize(); i++) {
			Cliente cliente = modelClientes.getElementAt(i);
			if (cliente.getNombre().toLowerCase().contains(nombreBuscado)) {
				resultadoBusqueda.addElement(cliente);
			}
		}

		listClientes.setModel(resultadoBusqueda);
	}

	// Método para eliminar un cliente seleccionado
	private void eliminarCliente() {
		Cliente clienteSeleccionado = listClientes.getSelectedValue();
		if (clienteSeleccionado != null) {
			// Eliminamos el cliente de la lista
			modelClientes.removeElement(clienteSeleccionado);
			// Actualizamos la lista
			listClientes.setModel(modelClientes);
			JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente.");
		} else {
			JOptionPane.showMessageDialog(this, "Selecciona un cliente para eliminar.");
		}
	}
}
