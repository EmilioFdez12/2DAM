package actividad203Seguridad;

import java.security.MessageDigest;
import javax.crypto.SecretKey;

public class CifraAESApp {
	private static final String MENSAJE_DEFAULT = "Mensaje de Prueba";
	private static final String PASSWORD_DEFAULT = "pass123";

	public static void main(String[] args) {
		String mensaje;
		String password;
		
		if (args.length == 0) {
			mensaje = MENSAJE_DEFAULT;
			password = PASSWORD_DEFAULT;
		} else if ( args.length == 1){
			password = args[1];
		} else {
			mensaje = args[0];
			password = args[1];
		}
		
		
			
	    // Método para generar la clave a partir de la contraseña usando SHA-256
	    private static SecretKey generarClave(String password) {
	        // Crear un hash SHA-256 de la contraseña
	        MessageDigest sha = MessageDigest.getInstance("SHA-256");
	        byte[] key = sha.digest(password.getBytes("UTF-8"));

	        // Tomar los primeros 16 bytes (128 bits) para la clave AES
	        key = Arrays.copyOf(key, 16); // Para AES-128, podemos ajustar a 24 o 32 bytes si usamos AES-192 o AES-256

	        // Crear una clave secreta con SecretKeySpec usando la clave generada
	        return new SecretKeySpec(key, "AES");
	        
	    
	}
}

