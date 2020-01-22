import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class BankService extends Thread {

	private Scanner in;
	private PrintWriter out;
	private Socket s;
	private Banco banco;

	public BankService(Socket s, Banco banco) {
		this.s = s;
		this.banco = banco;
	}

	@Override
	public void run() {
		try {
			in = new Scanner(s.getInputStream());
			out = new PrintWriter(s.getOutputStream());
			out.println("Bienvenido a su banco online!");
			out.println("------------------------------");
			out.println(">");
			out.flush();
			doService();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void doService() {
		while (true) {
			if (!in.hasNext()) {
				return;
			}
			String comando = in.next();

			if (comando.equals("quit")) {
				return;
			}
			procesaComando(comando);
		}
	}

	private void procesaComando(String comando) {
		if (comando.equals("sal") || comando.equals("dep") || comando.equals("ret")) {
			if (comando.contains("sal")) {
				int cuenta = in.nextInt();
				double saldo = banco.saldo(cuenta);
				out.println(saldo);
			}

			if (comando.contains("dep")) {
				int cuenta = in.nextInt();
				double cantidad = in.nextDouble();
				if (banco.deposito(cuenta, cantidad)) {
					out.println("Deposito realizado con existo, saldo: " + banco.saldo(cuenta));
				}
				else {
					out.println("No se ha podido realizar el deposito, saldo: " + banco.saldo(cuenta));
				}
			}
			
			if (comando.contains("ret")) {
				int cuenta = in.nextInt();
				double cantidad = in.nextDouble();
				if (banco.reintegro(cuenta, cantidad)) {
					out.println("Reintegro realizado con existo, saldo: " + banco.saldo(cuenta));
				}
				else {
					out.println("Error al realizar el reintegro, saldo: " + banco.saldo(cuenta));
				}
			}
		}
		else {
			out.println("Comando incorrecto!!!");
		}
		out.flush();
	}

}
