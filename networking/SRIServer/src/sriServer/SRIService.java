package sriServer;

import java.io.*;
import java.net.*;
import java.util.*;

//Esta clase debe ejecutarse como un hilo 

public class SRIService {
	private Socket s;
	private Scanner in;
	private PrintWriter out;
	private SRI sri;
	private int cliente;
	final int CLIENTES_MAX = 10;

	public SRIService(Socket aSocket, SRI aSRI, int cli) {
		s = aSocket;
		sri = aSRI;
		cliente = cli;

	}// contructor

	// función principal de la clase SRIService
	public void main() {
		try {
			try {
				in = new Scanner(s.getInputStream());
				out = new PrintWriter(s.getOutputStream());
				// función encargada de gestionar las comunicaciones con el cliente
				doService();
			} finally {
				s.close();
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public void doService() throws IOException {
		while (true) {
			if (!in.hasNext()) {
				return;
			}
			String command = in.next();
			// gestionar los comandos que recibimos del cliente-emisor
			executeCommand(command);
		}
	} // doService

	public void executeCommand(String command) {
		int clires = in.nextInt();
		// implementar todos los comandos QUIT/CLIENTE/MENSA
		// dentro del comando MENSA deberemos iniciar una instancia de la clase
		// SRIServiceUDP
		// que será la encargada de gestionar la emisión Multicast
		if (command.equals("XXXXX")) {

		} else if (!command.equals("QUIT")) {
			System.out.println("Comando erroneo");
			out.flush();
			return;
		}
		out.flush();
	}
}
