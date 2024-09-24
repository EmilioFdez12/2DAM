import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import models.Empleado;

public class BibliotecaCSV {

	/**
	 * Metodo que pasa crea un archivo .dat a partir de un archivo CSV
	 * 
	 * @param empleados Lista de empleados
	 */
	public static void ficheroCSV_To_Binario(String archivo, List<String> filaCSV) {
		String archivoDAT = archivo.replace(".csv", ".dat");
		try (ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(archivoDAT))) {
			for (String fila : filaCSV) {
				escritor.writeObject(fila);
			}
			System.out.println("Archivo creado exitosamente");
		} catch (IOException e) {
			System.out.println("Error");
		}
	}

	/**
	 * 
	 * @param archivo
	 * @param filaCSV
	 */
	public static void ficheroBinario_To_CSV(String archivo) {
		String archivoCSV = archivo.replace(".dat", ".csv");
		List<Object> listaBinaria = new ArrayList<>();
		try (ObjectInputStream lector = new ObjectInputStream(new FileInputStream(archivo));
				BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoCSV))) {

			// Leemos el archivo Binario y los pasamos a CSV
			Object objeto;
			while (true) {
				objeto = lector.readObject();
				listaBinaria.add(objeto);
			}
			
			while ((objeto = lector.readObject()) != null) {
				escritor.write(objeto);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// Ejemplo de uso:
		List<String> ejemploCSV = List.of("fila1", "fila2", "fila3");
		ficheroCSV_To_Binario("archivo.csv", ejemploCSV);
	}

}
