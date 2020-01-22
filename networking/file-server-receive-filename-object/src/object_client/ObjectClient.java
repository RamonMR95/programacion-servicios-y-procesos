package object_client;

import util.Fichero;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ObjectClient {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		String server = "127.0.0.1";
		int puerto = 4000;
		final int FICHERO_TAM_MAX = 104857600;
		Socket cliente = new Socket(server, puerto);
		Scanner sc = new Scanner(System.in);

		DataInputStream dis = new DataInputStream(cliente.getInputStream());
		System.out.println(dis.readUTF());
		String nombreFichero = sc.nextLine();
		System.out.println(dis.readUTF());
		String pathFichero = sc.nextLine();

		ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
		oos.writeObject(new Fichero(nombreFichero, pathFichero));

		FileOutputStream fos = new FileOutputStream(nombreFichero);
		byte[] buffer = new byte[FICHERO_TAM_MAX];
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		InputStream is = cliente.getInputStream();

		int bytesLeidos = is.read(buffer, 0, buffer.length);
		int actual = bytesLeidos;

		while (bytesLeidos > -1) {
			bytesLeidos = is.read(buffer, actual, buffer.length - actual);
			if (bytesLeidos >= 0) {
				actual += bytesLeidos;
			}
		}

		bos.write(buffer, 0, actual);
		bos.flush();
		fos.close();
		is.close();
		oos.close();
		cliente.close();
	}

}
