package threads2;

import java.util.concurrent.CyclicBarrier;

public class Main {

	public static void main(String[] args) {
		HiloSimple[] arrayHilos = new HiloSimple[1000];
		CyclicBarrier cb = new CyclicBarrier(arrayHilos.length);
		
		for (int i = 0; i < arrayHilos.length; i++) {
			arrayHilos[i] = new HiloSimple(String.valueOf(i), (long) Math.random() * 1000, cb);
		}
		
		for (HiloSimple hilo : arrayHilos) {
			hilo.start();
		}
		
		// Espera a que todos terminen
		for (HiloSimple hiloSimple : arrayHilos) {
			while (hiloSimple.isAlive());
		}
		
		for (int i = 0; i < 10; i++) {
			System.out.println("Fuera del hilo");
		}
	}
}
