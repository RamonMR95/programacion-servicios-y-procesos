import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class BolsaService extends Thread {

	private Socket s;
	private Broker miBroker;
	private Scanner in;
	private PrintWriter out;

	public BolsaService(Socket s, Broker miBroker) {
		this.s = s;
		this.miBroker = miBroker;
	}

	@Override
	public void run() {
		try {
			in = new Scanner(s.getInputStream());
			out = new PrintWriter(s.getOutputStream());
			out.println("Bienvenido a su bolsa online");
			out.println("----------------------------");
			out.println(">");
			out.flush();
			doService();
		} 
		catch (IOException | InterruptedException e) {}
	}

	private void doService() throws InterruptedException {
		while (!s.isClosed()) {
			String comando = in.nextLine();

			if (comando.contains("ve")) {
				int num = procesaComando(comando)[0];
				int cantidad = procesaComando(comando)[1];
				miBroker.vendeCantidad(num, cantidad);
			}
			else if (comando.contains("co")) {
				int num = procesaComando(comando)[0];
				int cantidad = procesaComando(comando)[1];
				if (!miBroker.compraCantidad(num, cantidad)) {
					out.println("No hay suficientes existencias de valor: " + miBroker.getNombre(num)
							+ " ( " + miBroker.getCantidad(num) + ")");
					out.flush();
				}
			}
		}

	}

	private int[] procesaComando(String comando) {
		int[] valores = new int[5];
		valores[0] = Integer.parseInt(comando.split(" ")[1]);
		valores[1] = Integer.parseInt(comando.split(" ")[2]);
		return valores;
	}

}
