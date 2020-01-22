import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class timeServer {

	public static void main(String[] args) throws IOException, InterruptedException {
		int serverPort = 9899;
		String serverIP = "225.0.0.1";
		int hh;
		int mm;
		int ss;
		String sMensa = null;
		InetAddress server = InetAddress.getByName(serverIP);
		DatagramSocket s = new DatagramSocket();
		DatagramPacket out = null;
		System.out.println("Server funcionando en :" + serverIP + ":" + serverPort);

		while (true) {
			Calendar miCalendario = new GregorianCalendar();
			hh = miCalendario.get(Calendar.HOUR_OF_DAY);
			mm = miCalendario.get(Calendar.MINUTE);
			ss = miCalendario.get(Calendar.SECOND);
			sMensa = "Clock " + hh + ":" + mm + ":" + ss;
			out = new DatagramPacket(sMensa.getBytes(), sMensa.getBytes().length, server, serverPort);
			s.send(out);
			System.out.println("Prof " + sMensa);
			Thread.sleep(1000);
		}
	}

}
