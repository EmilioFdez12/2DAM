package main;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

public class CifraRSAApp {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Introduce dos parámetros: <Texto> <Alias>");
			return;
		}

		String textoACifrar = args[0];
		String alias = args[1];

		try {
			Cipher.getInstance(textoACifrar, alias);
		} catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException e) {
			e.printStackTrace();
		}

	}

}
