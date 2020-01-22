import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {
	static int conectados;

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		final int CLIENTES_MAX = 3;
		final int SERVER_PORT = 4001;
		int cliente = 0;
		ServerSocket server = new ServerSocket(SERVER_PORT);

		System.out.println("Esperando conexiones de clientes");
		
		while (true) {
			Socket s = server.accept();
			if (conectados < CLIENTES_MAX) {
				FileService service = new FileService(s, cliente);
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				System.out.println("Nombre del fichero: " + ois.readObject());
				conectados++;
				cliente++;
				System.out.println("Hay " + conectados + " clientes conectados");
			} 
			else {
				s.close();
			}

		}

	}

}