import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.util.concurrent.CyclicBarrier;

public class Carrera {

	final static int maxCaballos = 5;
	final static int pista = 100;
	static Caballo[] caballo = new Caballo[maxCaballos];
	static int ganador = 99;
	static PipedReader[] receptor = new PipedReader[maxCaballos];
	static BufferedReader[] flujoEntrada = new BufferedReader[maxCaballos]; 

	public static void main(String[] args) {

		for (int i = 0; i < maxCaballos; i++) {
			/* Creamos el pipe */
			receptor[i] = new PipedReader();
			/* Enlazo el pipe */
			flujoEntrada[i] = new BufferedReader(receptor[i]);
		}
		
		CyclicBarrier barrera = new CyclicBarrier(maxCaballos);
		
		// inicializamos los hilos
		for (int i = 0; i < maxCaballos; i++) {
			caballo[i] = new Caballo(i, pista, barrera, receptor[i]);
			caballo[i].start();
		}
		
		while (!fincarrera()) {
			for (int i = 0; i < maxCaballos; i++) {
				printCaballo(i);
			}
			System.out.println();
			System.out.println();
		}
		System.out.println();
		System.out.println("El ganador es " + ganador);
		
		// interrumpir hilos
		for (int i = 0; i < maxCaballos; i++) {
			if (caballo[i].isAlive()) {
				caballo[i].finalizar();
				// caballo[i].interrupt();
			}
		}
	}

	private static void printCaballo(int i) {
		System.out.println("");
		caballo[i].getPosicion();
		int tope = 0;
		try {
			tope = Integer.parseInt(flujoEntrada[i].readLine());
			System.out.print(i + " > " + tope + " ");
			for (int p = 0; p < tope; p++) {
				System.out.print("*");
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}

	}

	private static boolean fincarrera() {
		boolean fin = false;
		for (int i = 0; i < maxCaballos; i++) {
			if (caballo[i].isAlive()) {
				fin = false;
			} else {
				fin = true;
				ganador = i;
				break;
			}
		}
		return fin;
	}

}
