import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class EchoServer {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		int serverPort = 9999;
		DatagramSocket s = new DatagramSocket(serverPort);
		
		byte[] buffer = new byte[1024];
		
		DatagramPacket in = new DatagramPacket(buffer, buffer.length);
		DatagramPacket out = null;
		
		byte[] datos = null;
		
		System.out.println("SERVIDOR FUNCIONANDO EN: " + serverPort);
		
		while (true) {
			s.receive(in);
			datos = in.getData();
			String sData = new String(datos, 0, datos.length).trim();
			System.out.println(in.getAddress().getHostAddress() + ":" + in.getPort() + " -> " + sData);
			datos = null;
			borrarBuffer(buffer);
			
			sData = "OK:" + sData + "\n";
			out = new DatagramPacket(sData.getBytes(), sData.length(), in.getAddress(), in.getPort());
			s.send(out);
		}
	}

	private static void borrarBuffer(byte[] buffer) {
		if (buffer != null) {
			for (int i = 0; i < buffer.length; i++) {
				buffer[i] = 0;
			}
		}
	}

}
