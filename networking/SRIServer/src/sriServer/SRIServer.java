// Reverse con socket
package sriServer;

import java.net.*;

public class SRIServer {

	public static void main(String[] args) throws Exception {
		final int CLIENTES_MAX = 10;
		SRI sri = new SRI(CLIENTES_MAX);
		final int SERVER_PORT = 0;
		int cliente = 0;

		ServerSocket server = new ServerSocket(SERVER_PORT);
		System.out.println("Esperando conexiones de clientes");
		while (true) {
			Socket s = server.accept();
			cliente++;
			System.out.println("Cliente  " + cliente + " conectado");
			/*
			 * iniciar la clase SRIService en multihilo para gestionar las comunicaciones
			 * con el cliente
			 */

		}
	}
}
