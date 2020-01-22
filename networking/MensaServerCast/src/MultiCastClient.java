import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MultiCastClient {

	public static void main(String[] args) throws IOException, InterruptedException {
		int serverPort = 3333;
		String serverIP = "225.0.0.1";
		byte[] bufferIn = new byte[1024];
		String sData;
		InetAddress server = InetAddress.getByName(serverIP);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		MulticastSocket s = new MulticastSocket(serverPort);
		s.joinGroup(server);
		DatagramPacket in = new DatagramPacket(bufferIn, bufferIn.length);

		System.out.println("MultiCastClient running!");
		
		while (true) {
			s.receive(in);
			sData = new String(in.getData(), 0, in.getData().length).trim();
			System.out.println("Client>" + sData);
			Thread.sleep(1000);
			
			for (int i = 0; i < bufferIn.length; i++) {
				bufferIn[i] = 0;
			}
		}

	}

}
