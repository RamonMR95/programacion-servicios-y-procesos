package object_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ObjectServer {

	public static void main(String[] args) throws IOException, InterruptedException {
		int puerto = 4000;
		ServerSocket servidor = new ServerSocket(puerto);
		System.out.println("Esperando clientes...");

		while (true) {
			Socket cliente = servidor.accept();
			ServicioCliente servicio = new ServicioCliente(cliente);
			servicio.start();
		}
	}

}
