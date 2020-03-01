package domain;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MesaMultiCastService extends Thread {
	private int multiUDPPort;
	private String multiUDPNet;
	private String pantallaMensa;
	private DatagramSocket s;
	private DatagramPacket mSalida;
	public MesaMultiCastService(String multiUDPNet, int multiUDPPort, String pantallaMensa) {
		this.multiUDPNet = multiUDPNet;
		this.multiUDPPort = multiUDPPort;
		this.pantallaMensa = pantallaMensa;
	}
	public void run() {
		try {
			s = new DatagramSocket();

			mSalida = new DatagramPacket(pantallaMensa.getBytes(),pantallaMensa.getBytes().length,InetAddress.getByName(multiUDPNet),multiUDPPort);
			s.send(mSalida);

			s.close();
			System.out.println("MCast: "+pantallaMensa);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
