
import java.io.InputStream;
import java.io.FileInputStream;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import java.security.KeyFactory;
import java.security.Signature;
import java.security.PublicKey;

import java.security.spec.X509EncodedKeySpec;

import java.util.Base64;

public class ValidaFirma {
	
	private String pubKeyFile;
	private String dataFile;
	private String signFile;
	
	public ValidaFirma(String pubKeyFile, String dataFile, String signFile) {
		this.pubKeyFile = pubKeyFile;
		this.dataFile = dataFile;
		this.signFile = signFile;
	}
	
	public boolean validar() throws Exception {
		Path path = Paths.get(pubKeyFile);
		byte[] bytes = Files.readAllBytes(path);
		String mensaje = new String(bytes).replace("-----BEGIN RSA PUBLIC KEY-----", "").replace("-----END RSA PUBLIC KEY-----", "").replaceAll("\\n", "");

		/* Generate public key. */
		X509EncodedKeySpec ks = new X509EncodedKeySpec(Base64.getDecoder().decode(mensaje.getBytes()));
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PublicKey pub = kf.generatePublic(ks);

		Signature sign = Signature.getInstance("SHA256withRSA");
		sign.initVerify(pub);

		InputStream in = null;
		try {
			in = new FileInputStream(dataFile);
			byte[] buf = new byte[2048];
			int len;
			while ((len = in.read(buf)) != -1) {
				sign.update(buf, 0, len);
			}
		} 
		finally {
			if (in != null)
				in.close();
		}

		/* Read the signature bytes */
		path = Paths.get(signFile);
		bytes = Files.readAllBytes(path);
		
		if (sign.verify(bytes)) {
			System.out.println("\u001B[32m" + "Validación correcta!");
			return true;
		}
		System.err.println("Validación incorrecta");
		return false;
	}
	
}
