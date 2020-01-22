package carreraCaballos;

import java.util.concurrent.CyclicBarrier;

public class Carrera {

	final static int maxCaballos = 5;
	final static int pista = 100;
	static Caballo[] caballo = new Caballo[maxCaballos];
	static int ganador = 99;

	public static void main(String[] args) {

		CyclicBarrier barrera = new CyclicBarrier(maxCaballos);
		
		// inicializamos los hilos
		for (int i = 0; i < maxCaballos; i++) {
			caballo[i] = new Caballo(i, pista, barrera);
			caballo[i].start();
		} // end-for
		
		while (!fincarrera()) {
			for (int i = 0; i < maxCaballos; i++) {
				printCaballo(i);
			} // end-for
			System.out.println();
			System.out.println();
		} // end-while
		System.out.println();
		System.out.println("El ganador es " + ganador);
		
		// Interrumpimos hilos
		for (int i = 0; i < maxCaballos; i++) {
			if (caballo[i].isAlive()) {
				caballo[i].finalizar();
//				caballo[i].interrupt();
			}
		}
	}

	private static void printCaballo(int i) {
		System.out.println("");
		System.out.print(i + " > " + caballo[i].getPosicion() + " ");
		int tope = (int) (caballo[i].getPosicion());
		for (int p = 0; p < tope; p++) {
			System.out.print("*");
		}
	}

	private static boolean fincarrera() {
		boolean fin = false;
		for (int i = 0; i < maxCaballos; i++) {
			if (caballo[i].isAlive()) {
				fin = false;
			} 
			else {
				fin = true;
				ganador = i;
				break;
			}
		}
		return fin;
	}

}