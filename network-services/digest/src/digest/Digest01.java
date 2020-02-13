package digest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

public class Digest01 {

	public static void main(String[] args) {
		try {
			MessageDigest md;
			String texto = "Este es el texto de pruebas";
			md = MessageDigest.getInstance("SHA-256");
			byte[] textoBytes = texto.getBytes();
			md.update(textoBytes);
			byte[] textoDigest = md.digest();
			System.out.println("Texto original: " + texto);
			System.out.println("Resumen: " + hexadecimal(textoDigest));
			System.out.println("Algoritmo: " + md.getAlgorithm());
			System.out.println("Proveedor: " + md.getProvider());
			
			/* Tamaño clase de encriptación */
			String algoritmo = "RSA";
			int keysize = 1024;
			String outFile = "claves";
			
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algoritmo);
			keyPairGenerator.initialize(keysize);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			OutputStream os = null;
			System.out.println("Formato clave privada: " + keyPair.getPrivate().getFormat());
			System.out.println("Guardando clave privada en " + outFile + ".key");
			os = new FileOutputStream(outFile + ".key");
			guardaBinario(os, keyPair.getPrivate());
			os.flush();
			
			System.out.println("Formato clave publica: " + keyPair.getPublic().getFormat());
			System.out.println("Guardando clave publica en " + outFile + ".pub");
			os = new FileOutputStream(outFile + ".pub");
			guardaBinario(os, keyPair.getPublic());
			os.close();
			
			PrivateKey miClavePrivada = keyPair.getPrivate();
			PublicKey miClavePublica = keyPair.getPublic();
			String firmaAlg = "SHA256withRSA";
			Signature sign = Signature.getInstance(firmaAlg);
			sign.initSign(miClavePrivada);
			sign.update(textoDigest);
			byte[] firma = sign.sign();
			System.out.println("Firma: " + hexadecimal(firma));
			
			Signature veriSig = Signature.getInstance(firmaAlg);
			veriSig.initVerify(miClavePublica);
			veriSig.update(textoDigest);
			if (veriSig.verify(firma)) {
				System.out.println("SI");
			}
			else {
				System.out.println("NO");
			}
		} 
		catch (NoSuchAlgorithmException |  IOException | InvalidKeyException | SignatureException e) {
			e.printStackTrace();
		}
	}

	private static void guardaBinario(OutputStream os, Key key) throws IOException {
		os.write(key.getEncoded());
	}

	private static String hexadecimal(byte[] textoDigest) {
		String hex = "";
		for (int i = 0; i < textoDigest.length; i++) {
			String caracter = Integer.toHexString(textoDigest[i] & 0xFF);
			if (caracter.length() == 1) {
				caracter = "0" + caracter;
			}
			hex += caracter;
		}
		return hex.toUpperCase();
	}
}
