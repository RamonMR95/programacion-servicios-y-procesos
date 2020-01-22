import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		final String SERVER_IP = "127.0.0.1";
		final int SERVER_PORT = 4001;
		final String FICHERO = "/home/dam18-17/Escritorio/fichero.txt";
		final int FICHERO_TAM_MAX = 104857600;
		
		int bytesLeidos;
			
		File ficheroRecibir = new File(FICHERO);
		FileOutputStream fos = null;
		
		InputStream is;
		byte[] buffer = new byte[FICHERO_TAM_MAX];
		
		// Recibir archivo
		Socket s = new Socket(SERVER_IP, SERVER_PORT);
		is = s.getInputStream();
		
		fos = new FileOutputStream(ficheroRecibir);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		
		bytesLeidos = is.read(buffer, 0, buffer.length);
		int actual = bytesLeidos;
		
		while (bytesLeidos > -1) {
			bytesLeidos = is.read(buffer, actual, buffer.length - actual);
			if (bytesLeidos >= 0) {
				actual += bytesLeidos;
			}
		}
		
		bos.write(buffer);
		bos.flush();
		System.out.println("Fichero : " + FICHERO + " descargado " + actual + " bytes");
		bos.close();
		fos.close();
		is.close();
		s.close();
	}
}
