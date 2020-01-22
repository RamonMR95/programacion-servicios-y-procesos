package mi_url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URLConnection;
import java.net.URLEncoder;

public class TestUrl {
	
	public static void main(String[] args) {
		java.net.URL miUrl;
		
		try {
			miUrl = new java.net.URL("http://172.16.6.43/cgi-bin/backwards.pl");

			URLConnection connect = miUrl.openConnection();
			connect.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(connect.getOutputStream());
			String msgToReverse = URLEncoder.encode("Esto es una prueba", "UTF-8");
			System.out.println(msgToReverse);
			
			out.write("string=" + msgToReverse);
			out.close();
			
			InputStreamReader in = new InputStreamReader(connect.getInputStream());
			BufferedReader flujoE = new BufferedReader(in);
			
			String line;
			
			while ((line = flujoE.readLine()) != null) {
				System.out.println(line);
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
