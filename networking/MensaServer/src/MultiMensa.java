import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MultiMensa extends Thread {

	private String multiNet;
	private int multiPort;
	private InetAddress multiAddress;
	private DatagramSocket s;
	private DatagramPacket enviar;
	private boolean ejecutar = true;
	private byte[] bufferIn =new byte[1024];
	
	public MultiMensa(String MULTICAST_NET, int MULTICAST_PORT) {
		this.multiNet = MULTICAST_NET;
		this.multiPort = MULTICAST_PORT;
		try {
			this.multiAddress = InetAddress.getByName(MULTICAST_NET);
			this.s = new DatagramSocket();
		} 
		catch (UnknownHostException | SocketException e) {
		}

		this.start();
	}

	@Override
	public void run() {
		while (ejecutar) {
			
		}
	}

	public void close() {
		this.s.close();
		this.ejecutar = false;
	}

	public synchronized void send(String mensa) throws IOException {
		this.enviar = new DatagramPacket(mensa.getBytes(), mensa.getBytes().length, multiAddress, multiPort);
		s.send(enviar);
	}

}
