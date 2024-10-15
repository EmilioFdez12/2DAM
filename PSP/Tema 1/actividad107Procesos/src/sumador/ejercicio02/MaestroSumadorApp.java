package sumador.ejercicio02;

import java.io.IOException;

public class MaestroSumadorApp {

	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.out.println("Se requieren dos parametros: <Entero> <Entero>");
		}

		int num1 = 0;
		int num2 = 0;
		try {
			num1 = Integer.parseInt(args[0]);
			num2 = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			System.err.println("Debes de introducir como parametros números enteros");
		}

		int diferencia = Math.abs(num1 - num2);

		if (diferencia < 25) {
			lanzarProceso(num1, num2);
		} else if (diferencia <= 100) {
			lanzarProceso(num1, num2);
			lanzarProceso(num1, num2);
		} else {

		}
	}
	
	private static void lanzarProceso(int num1, int num2) throws IOException {
        // Crear el proceso para ejecutar SumadorProcess
        ProcessBuilder builder = new ProcessBuilder("java", "-cp", ".", "SumadorProcess", String.valueOf(num1), String.valueOf(num2));
        builder.start();
	}

}
