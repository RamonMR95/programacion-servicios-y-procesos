import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Semaphore;

public class MainServer {

	public static void main(String[] args) throws IOException {
		final int clientTCPPort = 9999;
		final int multiUDPPort = 8888;
		final String multiUDPNet = "226.0.0.9";

		ServerSocket miServer = new ServerSocket(clientTCPPort);
		Socket s;

		Titulo titulos = new Titulo(1000);

		PipedWriter emisor = new PipedWriter();
		PipedReader receptor = new PipedReader(emisor);

		Semaphore accesoBroker = new Semaphore(1);
		Broker miBroker = new Broker(titulos, emisor, accesoBroker);

		BolsaMulticastService bolsaMulticastService = new BolsaMulticastService(multiUDPNet, multiUDPPort, receptor);
		bolsaMulticastService.start();
		System.out.println("Servidor funcionando");
		System.out.println("Esperando conexiones...");

		while (true) {
			s = miServer.accept();
			BolsaService miServicio = new BolsaService(s, miBroker);
			miServicio.start();
		}
	}
}
