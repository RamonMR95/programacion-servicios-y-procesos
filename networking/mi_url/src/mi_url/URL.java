package mi_url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class URL {

	public static void main(String[] args) {
		
		try {
			java.net.URL miUrl = new java.net.URL("http://172.16.6.43/cgi-bin/backwards.pl");
			
			System.out.println("Protocolo: " + miUrl.getProtocol());
			System.out.println("Puerto: " + miUrl.getPort());
			System.out.println("Puerto: " + miUrl.getDefaultPort());
			
			InputStreamReader sr = new InputStreamReader(miUrl.openStream());
			BufferedReader br = new BufferedReader(sr);
			String line;
			
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} 
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
