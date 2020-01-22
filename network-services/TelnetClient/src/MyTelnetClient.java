import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.telnet.TelnetClient;

/* USA BANKSERVER */
public class MyTelnetClient {

	public static void main(String[] args) throws SocketException, IOException {
		TelnetClient telnet = new TelnetClient();
		String serverIP = "127.0.0.1";
		int serverPort = 10101;
		telnet.connect(serverIP, serverPort);
		ReadWrite readWrite = new ReadWrite(telnet.getInputStream(), 
				telnet.getOutputStream(), 
				System.in, 
				System.out);
				
		while (true) {

		}
	}

}
