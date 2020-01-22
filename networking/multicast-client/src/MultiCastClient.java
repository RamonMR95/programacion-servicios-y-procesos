import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MultiCastClient {

	public static void main(String[] args) throws IOException, InterruptedException {
		int serverPort = 9898;
		String serverIP = "225.0.0.1";
		byte[] bufferIn =new byte[1024];
		String sData;
		InetAddress server = InetAddress.getByName(serverIP);
		MulticastSocket s = new MulticastSocket(serverPort);
		s.joinGroup(server);
		DatagramPacket in = new DatagramPacket(bufferIn,bufferIn.length);
		System.out.println("Multicast Client running...");
		while (true) {
			s.receive(in);
			sData = new String(in.getData(),0,in.getData().length).trim();
			System.out.println("Client >"+sData);
			Thread.sleep(1000);
			//borra buffer
			for (int i=0; i < bufferIn.length;i++)
				bufferIn[i] = 0;
		}
	}

}
