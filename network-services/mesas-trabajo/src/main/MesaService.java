package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MesaService extends Thread {

	private Socket s;
	private ContadorMesas mesas;
	private int multiUDPPort;
	private String multiUDPNet;
	private int mesaAsignada;
	private PrintWriter out;
	private Scanner in;
	private int puesto;

	public MesaService(Socket s, ContadorMesas mesas, int multiUDPPort, String multiUDPNet, int clientes) {
		this.s = s;
		this.mesas = mesas;
		this.multiUDPPort = multiUDPPort;
		this.multiUDPNet = multiUDPNet;
		this.mesaAsignada = 0;
		this.puesto = clientes;
	}

	@Override
	public void run() {
		try {
			out = new PrintWriter(s.getOutputStream());
			in = new Scanner(s.getInputStream());
			out.println("Servicio de mesas, puesto: " + puesto);
			out.println("Indica la mesa que quieres atender");
			out.flush();
			procesaCliente();
		} 
		catch (IOException e) {
		}
	}
	
	private void procesaCliente() throws IOException {
		while (!s.isClosed()) {
			String comando = "";
			try {
				comando = in.next();
			} catch (Exception e) {
				in.close();
			}
			
			if (comando.contains("quit")) {
				in.close();
				out.close();
				s.close();
			}
		}
	}

}
