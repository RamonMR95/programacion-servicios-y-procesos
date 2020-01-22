import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MensaService extends Thread {
	private Socket s;
	private int cliente;
	private Scanner in;
	private PrintWriter out;
	private MultiMensa multiCast;

	public MensaService(Socket s, int cliente, MultiMensa multiCast) {
		this.s = s;
		this.cliente = cliente;
		this.multiCast = multiCast;
	}

	public void run() {
		System.out.println("El cliente " + cliente + " se ha conectado");
		
		try {
			out = new PrintWriter(s.getOutputStream());
			in = new Scanner(s.getInputStream());
			out.println("Bienvenido cliente " + cliente);
			out.flush();
			doService();
			s.close();
			MensaServer.conectados--;
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void doService() throws IOException {
		while (true) {
			String comando = in.nextLine();
			if (comando.contains("quit")) {
				break;
			}
			procesaComando(comando);
		}
	}

	private void procesaComando(String comando) throws IOException {
		if (comando.contains("rec")) {
//			multiCast.send(" Cliente " + cliente + ":" + comando);
			String mensa = this.multiCast.receive();
			out.println(mensa);
			out.flush();
		}
	}
}
