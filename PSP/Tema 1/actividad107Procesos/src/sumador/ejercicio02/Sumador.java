package sumador.ejercicio02;

public class Sumador {

	public static void main(String[] args) {	
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
		
		int minimo = Math.min(num1, num2);
		int maximo = Math.max(num2, num1);
		int suma = 0;
		
		for (int i = minimo; i <= maximo; i++) {
			suma += i;
		}
		
		System.out.println("Suma = " + suma);

	}

}
