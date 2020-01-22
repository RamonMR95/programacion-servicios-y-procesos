import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class CestoManzanas {
	
	public static void main(String[] args) {
		int maxHilos = 100;
		int maxConcurrente = 5;
		int maxManzanas = 20000;
		
		Comedor[] comedores = new Comedor[maxHilos];
		Semaphore accesoComer = new Semaphore(maxConcurrente);
		CyclicBarrier inicioComer = new CyclicBarrier(maxHilos);
		CountDownLatch esperaTodosTerminen = new CountDownLatch(maxHilos);
		Caja cajaManzanas = new Caja(maxManzanas, accesoComer);
		
		for (int i = 0; i < maxHilos; i++) {
			comedores[i] = new Comedor(i, inicioComer, esperaTodosTerminen, cajaManzanas);
			comedores[i].start();
		}
		
		try {
			System.out.println("Iniciados todos los hilos...");
			esperaTodosTerminen.await();
			System.out.println("Finalizados todos los hilos...");
		} 
		catch (InterruptedException e) {}
	}
}
