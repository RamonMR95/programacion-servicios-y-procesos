import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MensaServer {
	
	public static int conectados;

	public static void main(String[] args) throws IOException {
		final int CLIENTES_MAX = 3;
		final int SERVER_PORT = 4000;
		int cliente = 0;
		final String MULTICAST_NET = "225.0.0.1";
		final int MULTICAST_PORT = 9899;
		ServerSocket server = new ServerSocket(SERVER_PORT);
		MultiMensa multiCast = new MultiMensa(MULTICAST_NET, MULTICAST_PORT);

		System.out.println("Esperando conexiones de clientes");		
		while (true) {
			Socket s = server.accept();
			
			if (conectados < CLIENTES_MAX) {
				MensaService service = new MensaService(s, cliente, multiCast);
				service.start();
				conectados++;
				cliente++;
			} 
			else {
				s.close();
				multiCast.close();
				server.close();
			}

		}

	}

}
