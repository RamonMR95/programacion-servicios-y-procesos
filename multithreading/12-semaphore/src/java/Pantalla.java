package java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;

public class Pantalla extends Thread {

	private BufferedReader flujoEntrada;

	public Pantalla(PipedReader receptor) {
		this.flujoEntrada = new BufferedReader(receptor);
	}

	@Override
	public void run() {

		while (true) {
			try {
				System.out.println(flujoEntrada.readLine());
			} catch (IOException e) {
				// e.printStackTrace();
				try {
					flujoEntrada.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				break;
			}
		}
	}
}
