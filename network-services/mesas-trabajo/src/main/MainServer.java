package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {

	public static void main(String[] args) throws IOException {
		final int clientTCPPort = 9999;
		final int multiUDPPort = 8888;
		final String multiUDPNet = "226.0.0.1";
		final int maxMesas = 20;
		int clientes = 0;
		ContadorMesas mesas = new ContadorMesas(maxMesas);
		ServerSocket miServer = new ServerSocket(clientTCPPort);
		System.out.println("Servidor de mesas funcionando");
		System.out.println("Esperando conexiones");
		Socket s = null;
		
		while (true) {
			if (clientes < maxMesas) {
				s = miServer.accept();
				clientes++;
				String ipCliente = s.getRemoteSocketAddress().toString();
				System.out.println("Puesto " + clientes + " conectado desde " + ipCliente);
				MesaService miServicio = new MesaService(s, mesas, multiUDPPort, multiUDPNet, clientes);
			}
			else {
				System.out.println("Limite de conexiones alcanzado");
				System.out.println("Libere alguna mesa");
			}
		}
	}
}
