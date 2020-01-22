import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BankServer {

	public static void main(String[] args) {
		final int puerto = 10101;
		ServerSocket server = null;
		
		try {
			Banco banco = new Banco();
			server = new ServerSocket(puerto);
			System.out.println("Servidor esperando conexiones en puerto: " + puerto);
			
			while (true) {
				Socket s = server.accept();
				System.out.println("Cliente conectado " + s.getInetAddress() + ":" + s.getPort());
				BankService service = new BankService(s, banco);
				service.start();
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (server != null) {
				try {
					server.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
