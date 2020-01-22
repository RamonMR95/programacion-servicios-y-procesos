import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class FileService extends Thread {
	private Socket s;
	private int cliente;
	private Scanner in;
	private PrintWriter out;

	private File ficheroEnviar;
	private FileInputStream fis;
	private BufferedInputStream bis;
	private OutputStream os;
	
	private ObjectOutputStream oos;

	public FileService(Socket s, int cliente) {
		this.s = s;
		this.cliente = cliente;
		this.ficheroEnviar = new File("/home/dam18-17/archivo-enviar.txt");
		this.start();
	}

	public void run() {
		System.out.println("El cliente " + cliente + " se ha conectado");
		try {
			byte[] sDatos = new byte[(int) ficheroEnviar.length()];
			fis = new FileInputStream(ficheroEnviar);
			bis = new BufferedInputStream(fis);
			oos = new ObjectOutputStream(s.getOutputStream());
			bis.read(sDatos);
			os = s.getOutputStream();
			System.out.println("Enviando archivo..." + ficheroEnviar + " " + sDatos.length + " bytes");
			os.write(sDatos);
			os.flush();
			oos.writeObject(ficheroEnviar.getAbsolutePath());

			FileServer.conectados--;
			System.out.println("Archivo enviado...");
			oos.close();
			os.close();
			s.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}// end-run
}