import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ObjectServer {

	public static void main(String[] args) throws IOException {
		int puerto = 4000;
		ServerSocket server = new ServerSocket(puerto);
		System.out.println("Esperando Clientes en puerto: " + puerto);

		while (true) {
			Socket cliente = server.accept();
			ServicioCliente servicioCliente = new ServicioCliente(cliente);
			servicioCliente.start();
		}
	}

}
