package swing;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.Calendar;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;
import utils.Cliente;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AltaClientes extends JFrame {

  private static final long serialVersionUID = 1L;
  private JTextField txtNombre;
  private JTextField txtApellido;
  private JDateChooser dateChooser;
  private JComboBox<String> comboBoxProvincias;
  private MainWindow mainWindow;  // Referencia a MainWindow

  // Constructor
  public AltaClientes(MainWindow window) {
      this.mainWindow = window;  // Recibe la referencia de MainWindow
      setTitle("Alta Clientes");
      setBounds(825, 450, 374, 263);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

      JPanel panel = new JPanel();
      getContentPane().add(panel);
      panel.setLayout(null);

      txtNombre = new JTextField();
      txtNombre.setBounds(74, 11, 186, 20);
      panel.add(txtNombre);
      txtNombre.setColumns(10);

      JLabel lblNewLabel = new JLabel("Nombre");
      lblNewLabel.setBounds(10, 14, 75, 14);
      panel.add(lblNewLabel);

      JLabel lblApellidos = new JLabel("Apellidos");
      lblApellidos.setBounds(10, 45, 75, 14);
      panel.add(lblApellidos);

      txtApellido = new JTextField();
      txtApellido.setColumns(10);
      txtApellido.setBounds(74, 42, 186, 20);
      panel.add(txtApellido);

      dateChooser = new JDateChooser();
      dateChooser.setBounds(74, 76, 186, 20);
      panel.add(dateChooser);

      JLabel lblNewLabel_1 = new JLabel("Edad");
      lblNewLabel_1.setBounds(10, 82, 46, 14);
      panel.add(lblNewLabel_1);

      String[] listaProvincias = { "Málaga", "Jaén", "Córdoba", "Huelva", "Cádiz", "Sevilla", "Almería", "Granada" };
      comboBoxProvincias = new JComboBox<>(listaProvincias);
      comboBoxProvincias.setSelectedIndex(-1);
      comboBoxProvincias.setBounds(74, 117, 186, 22);
      panel.add(comboBoxProvincias);

      JLabel lblNewLabel_2 = new JLabel("Provincia");
      lblNewLabel_2.setBounds(10, 121, 61, 14);
      panel.add(lblNewLabel_2);

      JButton btnAnyadir = new JButton("Añadir");
      btnAnyadir.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres añadir al cliente?",
                      "Confirmar acción", JOptionPane.YES_NO_OPTION);

              if (confirm == JOptionPane.YES_OPTION) {
                  anyadirCliente();
              }
          }
      });
      btnAnyadir.setBounds(121, 173, 102, 36);
      panel.add(btnAnyadir);

      setVisible(true);
  }

  protected void anyadirCliente() {
      String nombre = txtNombre.getText();
      String apellido = txtApellido.getText();
      int edad = calcularEdad();
      String provincia = comboBoxProvincias.getSelectedItem().toString();

      Cliente cliente = new Cliente(nombre, apellido, edad, provincia);

      // Añadir el cliente al modelo de la lista en MainWindow
      mainWindow.getModelClientes().addElement(cliente);

  }

  protected int calcularEdad() {
      Date fechaNacimiento = dateChooser.getDate();
      if (fechaNacimiento == null) {
          return 0;
      }

      Calendar fechaActual = Calendar.getInstance();
      Calendar fechaNac = Calendar.getInstance();
      fechaNac.setTime(fechaNacimiento);

      int edad = fechaActual.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
      if (fechaActual.get(Calendar.DAY_OF_YEAR) < fechaNac.get(Calendar.DAY_OF_YEAR)) {
          edad--;
      }

      return edad;
  }
}
