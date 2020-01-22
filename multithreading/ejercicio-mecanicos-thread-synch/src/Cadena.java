import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Cadena {

	public static void main(String[] args) {
		int maxCurrantes = 5;
		
		Currante currante[] = new Currante[maxCurrantes];
		Semaphore[] esperaHerramientas = new Semaphore[maxCurrantes];
		CountDownLatch trabajos = new CountDownLatch(maxCurrantes);
		
		int[][] usaHerramientas = {
			{0, 4},
			{0, 1},
			{1, 2},
			{2, 3},
			{3, 4}
		};
		
		for (int i = 0; i < maxCurrantes; i++) {
			esperaHerramientas[i] = new Semaphore(1);
		}
		
		for (int i = 0; i < maxCurrantes; i++) {
			currante[i] = new Currante(i, usaHerramientas[i], esperaHerramientas, trabajos);
			currante[i].start();
		}
		
		try {
			trabajos.await();
			System.out.println("Coche terminado");
		} 
		catch (InterruptedException e) {}	
		
	}

}
