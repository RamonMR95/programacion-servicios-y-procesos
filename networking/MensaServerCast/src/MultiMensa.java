import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MultiMensa extends Thread {

	private String multiNet;
	private int multiPort;
	private InetAddress multiAddress;
	//private DatagramSocket s;
//	private DatagramPacket enviar;
	private DatagramPacket entrada;
	private boolean ejecutar = true;
	private MulticastSocket s;
	byte[] bufferIn =new byte[1024];
	private String sData;	


	public MultiMensa(String MULTICAST_NET, int MULTICAST_PORT) throws IOException {
		this.multiNet = MULTICAST_NET;
		this.multiPort = MULTICAST_PORT;
		this.multiAddress = InetAddress.getByName(MULTICAST_NET);
		// this.s = new DatagramSocket();
		s = new MulticastSocket(MULTICAST_PORT);
		s.joinGroup(multiAddress);
		this.entrada = new DatagramPacket(bufferIn, bufferIn.length);
		this.start();
	}

	@Override
	public void run() {
		while (ejecutar) {
			try {
				s.receive(this.entrada);
				sData = new String(this.entrada.getData(), 0, this.entrada.getLength()).trim();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public void close() {
		this.s.close();
		this.ejecutar = false;
	}

//	public synchronized void send(String mensa) throws IOException {
//		this.enviar = new DatagramPacket(mensa.getBytes(), mensa.getBytes().length, multiAddress, multiPort);
//		s.send(enviar);
//	}

	public synchronized String receive() throws IOException {
		return sData;
	}

}
