package actividad106Procesos;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Esclavo {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		

		try {
			ProcessBuilder pb = new ProcessBuilder("java", "-cp", "./src/actividad106Procesos", "Maestro");
			Process procesoEsclavo = pb.start();
			
			
			InputStream input = procesoEsclavo.getInputStream(); 
			
			String texto = input.readAllBytes().toString();
			
			System.out.println(texto);
			
						
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sc.close();

	}
}
