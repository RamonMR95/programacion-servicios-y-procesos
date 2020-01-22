package java;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class LanzadorPrincipal {

	private static final int MAX_HILOS = 10;

	public static void main(String[] args) {
		Semaphore seccionCritica = new Semaphore(1);
		Pintor[] pintores = new Pintor[MAX_HILOS];
		CyclicBarrier barrera = new CyclicBarrier(MAX_HILOS);
		CountDownLatch espera = new CountDownLatch(MAX_HILOS);
		PipedWriter emisor = new PipedWriter();
		PipedReader receptor;
		Pantalla pantalla;
		try {
			receptor = new PipedReader(emisor);
			pantalla = new Pantalla(receptor);

			pantalla.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Crear los hilos
		for (int i = 0; i < MAX_HILOS; i++) {
			pintores[i] = new Pintor(i, emisor, seccionCritica, barrera, espera);
			pintores[i].start();
		}
		try {
			espera.await();
			Thread.sleep(2000);
			System.out.println("app Finalizada");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
