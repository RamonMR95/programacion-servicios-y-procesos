import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

public class NetworkCardsInfo {

	public static void main(String[] args) {
		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

			for (NetworkInterface miInterfaz : Collections.list(interfaces)) {
				displayInfo(miInterfaz);
			}
		} 
		catch (SocketException e) {}
	}

	public static void displayInfo(NetworkInterface miInterfaz) {
		System.out.println("Interfaz: " + miInterfaz.getDisplayName());
		
		try {
			if (!miInterfaz.getDisplayName().contains("lo")) {
				byte[] mac = miInterfaz.getHardwareAddress();
				StringBuilder sb = new StringBuilder();

				for (int i = 0; i < mac.length; i++) {
					sb.append(((String.format("%02X%s", mac[i], (i < mac.length - 1) ? ":" : ""))));
				}
				System.out.println("MAC" + sb.toString());
			}
		} 
		catch (SocketException e) {}
	}

}
