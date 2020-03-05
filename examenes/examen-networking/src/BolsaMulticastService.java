import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class BolsaMulticastService extends Thread {

	private BufferedReader br;
	private String multiUDPNet;
	private int multiUDPPort;

	public BolsaMulticastService(String multiUDPNet, int multiUDPPort, PipedReader receptor) {
		this.multiUDPNet = multiUDPNet;
		this.multiUDPPort = multiUDPPort;
		this.br = new BufferedReader(receptor);
	}

	@Override
	public void run() {
		DatagramSocket s;
		DatagramPacket mSalida;

		while (true) {
			try {
				String data;

				if ((data = br.readLine()) != null) {
					s = new DatagramSocket();
					mSalida = new DatagramPacket(data.getBytes(), data.getBytes().length,
							InetAddress.getByName(multiUDPNet), multiUDPPort);
					s.send(mSalida);
					s.close();
					System.out.println("MCast: " + data);
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
