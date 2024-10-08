package actividad106Procesos;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class Maestro {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		

		try {
			ProcessBuilder pb = new ProcessBuilder("java", "-cp", "./src/actividad106Procesos", "Esclavo");
			Process procesoEsclavo = pb.start();
			String linea = "";
			
			
			OutputStream output = procesoEsclavo.getOutputStream(); 
			
			System.out.println("Escribe algo (dejalo vacío para terminar):");
			
			while (true) {
				linea = sc.nextLine();
				
				if (linea.isEmpty()) {
					break;
				}
				
				
				output.write((linea + "\n").getBytes());
				output.flush();
				
			}
			
			 output.close(); // Cerrar el flujo de salida
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sc.close();

	}
}