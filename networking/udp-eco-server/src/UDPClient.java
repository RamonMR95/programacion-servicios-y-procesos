import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
	
	public static void main(String[] args) throws IOException {
		int serverPort = 9999;
		String serverIp = "127.0.0.1";
		String sData;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		DatagramSocket s = new DatagramSocket();
		InetAddress server = InetAddress.getByName(serverIp);

		DatagramPacket in = null;
		DatagramPacket out = null;

		byte[] bufferOut = null;
		byte[] bufferIn = new byte[1024];

		while (true) {
			System.out.println("msg>");
			sData = br.readLine();

			if (sData.contains("quit")) {
				break;
			}
			bufferOut = sData.getBytes();
			out = new DatagramPacket(bufferOut, bufferOut.length, server, serverPort);
			s.send(out);

			in = new DatagramPacket(bufferIn, bufferIn.length);
			s.receive(in);

			String rData = new String(in.getData(), 0, in.getData().length).trim();
			System.out.println(rData);
			borrarBuffer(bufferIn);
//			borrarBuffer(bufferOut);
		}
		s.close();
	}

	private static void borrarBuffer(byte[] buffer) {
		if (buffer != null) {
			for (int i = 0; i < buffer.length; i++) {
				buffer[i] = 0;
			}
		}
	}
}
