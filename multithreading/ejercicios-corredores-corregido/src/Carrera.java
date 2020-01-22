import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Carrera {

	public static void main(String[] args) {
		int maxAtletas = 5;
		Atleta[] atletas = new Atleta[maxAtletas];
		Testigo testigo = new Testigo(maxAtletas);
		
		CyclicBarrier cb = new CyclicBarrier(maxAtletas);
		CountDownLatch countDownLatch = new CountDownLatch(maxAtletas);
		
		for (int i = 0; i < atletas.length; i++) {
			atletas[i] = new Atleta(i, testigo, cb, countDownLatch);
			atletas[i].start();
		}
		
		try {
			countDownLatch.await();
			System.out.println("Carrera finalizada!");
			System.out.println("Tiempo total de la carrera: " + ((double) testigo.getTiempoCarrera() / 1000) + "s");
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
