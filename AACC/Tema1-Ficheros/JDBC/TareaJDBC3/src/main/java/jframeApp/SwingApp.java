package jframeApp;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import conexion.Conexion;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class SwingApp extends JFrame {

	// Campos de texto y botones
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFilmId;
	private JTextField txtTitle;
	private JTextField txtReleaseYear;
	private JTextField txtLanguageId;
	private JTextField txtOriginalLanguageId;
	private JTextField txtRentalDuration;
	private JTextField txtRentalRate;
	private JTextField txtLength;
	private JTextField txtReplacementCost;
	private JTextField txtRating;
	private JTextArea txtAreaDescription2;
	private JTextArea txtAreaFeatures;
	private JLabel lblNewLabel;
	private JButton btnGuardar;
	private JButton btnNuevo;
	private JButton btnPrimero;
	private JButton btnAnterior;
	private JButton btnSiguiente;
	private JButton btnUltimo;

	// Consulta SQL para obtener las películas, editarlas o hacer un insert
	private static final String SELECT_PELICULAS = "SELECT * FROM film";
	private static final String INSERT_PELICULAS = """
			INSERT INTO film (title, description, release_year, language_id, rental_duration,
			   rental_rate, length, replacement_cost, rating, special_features)
			VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""";
	private static final String UPDATE_PELICULAS = """
			UPDATE film
			SET title = ?, description = ?, release_year = ?, language_id = ?, rental_duration = ?,
			rental_rate = ?, length = ?, replacement_cost = ?, rating = ?, special_features = ?
			WHERE film_id = ?""";

	private ResultSet rs;
	private Connection connect;
	// Sera false hasta que se le de a nuevo
	private boolean esNuevo = false;

	/**
	 * Create the frame.
	 */
	public SwingApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 976, 850);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// Título de la aplicación
		lblNewLabel = new JLabel("FILMS DB");
		lblNewLabel.setBounds(416, 55, 129, 36);
		lblNewLabel.setFont(new Font("Lexend", Font.BOLD, 24));

		// Etiquetas
		JLabel lblFilmId = new JLabel("Film ID:");
		lblFilmId.setBounds(15, 125, 70, 15);

		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setBounds(15, 155, 70, 15);

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(15, 189, 91, 14);

		JLabel lblReleaseYear = new JLabel("Release Year:");
		lblReleaseYear.setBounds(15, 295, 90, 15);

		JLabel lblLanguageId = new JLabel("Language ID:");
		lblLanguageId.setBounds(15, 324, 91, 14);

		JLabel lblOriginalLanguageId = new JLabel("Original Language ID:");
		lblOriginalLanguageId.setBounds(15, 355, 136, 14);

		JLabel lblRentalDuration = new JLabel("Rental Duration:");
		lblRentalDuration.setBounds(500, 125, 100, 15);

		JLabel lblRentalRate = new JLabel("Rental Rate:");
		lblRentalRate.setBounds(500, 155, 100, 14);

		JLabel lblLength = new JLabel("Length:");
		lblLength.setBounds(500, 185, 68, 14);

		JLabel lblReplacementCost = new JLabel("Replacement Cost:");
		lblReplacementCost.setBounds(500, 215, 112, 14);

		JLabel lblRating = new JLabel("Rating:");
		lblRating.setBounds(500, 245, 91, 14);

		JLabel lblSpecialFeatures = new JLabel("Special Features:");
		lblSpecialFeatures.setBounds(500, 275, 100, 14);

		// Campos de texto
		txtFilmId = new JTextField();
		txtFilmId.setEditable(false);
		txtFilmId.setBounds(150, 120, 300, 20);

		txtTitle = new JTextField();
		txtTitle.setBounds(150, 150, 300, 20);

		txtAreaDescription2 = new JTextArea();
		txtAreaDescription2.setBounds(150, 180, 300, 100);
		txtAreaDescription2.setLineWrap(true);
		txtAreaDescription2.setWrapStyleWord(true);

		txtReleaseYear = new JTextField();
		txtReleaseYear.setBounds(150, 290, 300, 20);

		txtLanguageId = new JTextField();
		txtLanguageId.setEditable(false);
		txtLanguageId.setBounds(150, 320, 300, 20);

		txtOriginalLanguageId = new JTextField();
		txtOriginalLanguageId.setEditable(false);
		txtOriginalLanguageId.setBounds(150, 350, 300, 20);

		txtRentalDuration = new JTextField();
		txtRentalDuration.setBounds(610, 122, 300, 20);

		txtRentalRate = new JTextField();
		txtRentalRate.setBounds(610, 152, 300, 20);

		txtLength = new JTextField();
		txtLength.setBounds(610, 182, 300, 20);

		txtReplacementCost = new JTextField();
		txtReplacementCost.setBounds(610, 212, 300, 20);

		txtRating = new JTextField();
		txtRating.setBounds(610, 242, 300, 20);

		txtAreaFeatures = new JTextArea();
		txtAreaFeatures.setBounds(610, 272, 300, 20);
		txtAreaFeatures.setLineWrap(true);
		txtAreaFeatures.setWrapStyleWord(true);

		// Botones
		btnPrimero = new JButton("Primero");
		btnPrimero.setBounds(137, 438, 129, 31);
		btnPrimero.setFont(new Font("Lexend", Font.BOLD, 18));
		btnPrimero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarPrimeraPelicula();
			}
		});

		btnAnterior = new JButton("Anterior");
		btnAnterior.setBounds(321, 438, 129, 31);
		btnAnterior.setFont(new Font("Lexend", Font.BOLD, 18));
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarAnterior();
			}
		});

		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setBounds(514, 438, 129, 31);
		btnSiguiente.setFont(new Font("Lexend", Font.BOLD, 18));
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarSiguiente();
			}
		});

		btnUltimo = new JButton("Ultimo");
		btnUltimo.setBounds(693, 438, 129, 31);
		btnUltimo.setFont(new Font("Lexend", Font.BOLD, 18));
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarUltimaPelicula();
			}
		});

		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarCampos();
			}
		});
		btnNuevo.setFont(new Font("Lexend", Font.BOLD, 18));
		btnNuevo.setBounds(514, 344, 129, 31);
		contentPane.add(btnNuevo);

		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarPelicula();
			}
		});

		btnGuardar.setFont(new Font("Lexend", Font.BOLD, 18));
		btnGuardar.setBounds(693, 344, 129, 31);
		contentPane.add(btnGuardar);
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel);
		contentPane.add(lblFilmId);
		contentPane.add(lblTitle);
		contentPane.add(lblDescription);
		contentPane.add(lblReleaseYear);
		contentPane.add(lblLanguageId);
		contentPane.add(lblOriginalLanguageId);
		contentPane.add(lblRentalDuration);
		contentPane.add(lblRentalRate);
		contentPane.add(lblLength);
		contentPane.add(lblReplacementCost);
		contentPane.add(lblRating);
		contentPane.add(lblSpecialFeatures);
		contentPane.add(txtFilmId);
		contentPane.add(txtTitle);
		contentPane.add(txtAreaDescription2);
		contentPane.add(txtReleaseYear);
		contentPane.add(txtLanguageId);
		contentPane.add(txtOriginalLanguageId);
		contentPane.add(txtRentalDuration);
		contentPane.add(txtRentalRate);
		contentPane.add(txtLength);
		contentPane.add(txtReplacementCost);
		contentPane.add(txtRating);
		contentPane.add(txtAreaFeatures);
		contentPane.add(btnPrimero);
		contentPane.add(btnAnterior);
		contentPane.add(btnSiguiente);
		contentPane.add(btnUltimo);

		// Cargamos las películas de la base de datos
		cargarPeliculas();
	}

	// Método para cargar las películas desde la base de datos
	private void cargarPeliculas() {
		try {
			connect = Conexion.conectar();
			PreparedStatement st = connect.prepareStatement(SELECT_PELICULAS, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery();

			if (rs.next()) {
				mostrarDatos();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Método para mostrar los datos de la película en los campos de texto
	private void mostrarDatos() {
		try {
			txtFilmId.setText(rs.getString("film_id"));
			txtTitle.setText(rs.getString("title"));
			txtAreaDescription2.setText(rs.getString("description"));
			txtReleaseYear.setText(rs.getString("release_year").substring(0, 4));
			txtLanguageId.setText(rs.getString("language_id"));
			txtOriginalLanguageId.setText(rs.getString("original_language_id"));
			txtRentalDuration.setText(rs.getString("rental_duration"));
			txtRentalRate.setText(rs.getString("rental_rate"));
			txtLength.setText(rs.getString("length"));
			txtReplacementCost.setText(rs.getString("replacement_cost"));
			txtRating.setText(rs.getString("rating"));
			txtAreaFeatures.setText(rs.getString("special_features"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Métodos para ir cambiando de películas
	private void mostrarPrimeraPelicula() {
		try {
			if (rs.first()) {
				mostrarDatos();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void mostrarAnterior() {
		try {
			if (rs.previous()) {
				mostrarDatos();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void mostrarSiguiente() {
		try {
			if (rs.next()) {
				mostrarDatos();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void mostrarUltimaPelicula() {
		try {
			if (rs.last()) {
				mostrarDatos();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Metodo para borrar los campos "Enabled"
	// para insertar una peliculas nueva
	private void borrarCampos() {
		txtFilmId.setText("");
		txtTitle.setText("");
		txtAreaDescription2.setText("");
		txtReleaseYear.setText("");
		txtRentalDuration.setText("");
		txtRentalRate.setText("");
		txtLength.setText("");
		txtReplacementCost.setText("");
		txtRating.setText("");
		txtAreaFeatures.setText("");

		// Cambiamos estado a nuevo
		esNuevo = true;
	}

	// Método para guardar (nuevo o editar)
	private void guardarPelicula() {
		try {
			// Abrimos la conexión
			connect = Conexion.conectar();

			// Si esNuevo hacemos insert, si no, update
			String sql;
			if (esNuevo) {
				sql = INSERT_PELICULAS;
			} else {
				sql = UPDATE_PELICULAS;
			}

			
			PreparedStatement st = connect.prepareStatement(sql);

			// Establecemos los valores de la consulta SQL desde los campos de texto
			st.setString(1, txtTitle.getText());
			st.setString(2, txtAreaDescription2.getText());
			st.setInt(3, Integer.parseInt(txtReleaseYear.getText().substring(0, 4)));
			st.setInt(4, Integer.parseInt(txtLanguageId.getText()));
			st.setInt(5, Integer.parseInt(txtRentalDuration.getText()));
			st.setDouble(6, Double.parseDouble(txtRentalRate.getText()));
			st.setInt(7, Integer.parseInt(txtLength.getText()));
			st.setDouble(8, Double.parseDouble(txtReplacementCost.getText()));
			st.setString(9, txtRating.getText());
			st.setString(10, txtAreaFeatures.getText());

			// Si no es nuevo utilizamos el id de la película
			if (!esNuevo) {
				st.setInt(11, Integer.parseInt(txtFilmId.getText()));
			}

			// Ejecutamos la consulta
			st.executeUpdate();
			// Cerramos la conexión
			st.close();
			connect.close();

			// Actualizamos la lista de películas
			cargarPeliculas();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}