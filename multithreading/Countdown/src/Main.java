import java.util.Random;

public class Main {

	public static void main(String[] args) {
		int maxHilos = 20;
		Random random = new Random();
		CountDown countDown = new CountDown(maxHilos);
		Hilo[] hilo = new Hilo[maxHilos];
		
		for (int i = 0; i < maxHilos; i++) {
			hilo[i] = new Hilo(i, countDown);
			hilo[i].start();
		}
		
		System.out.println("Esperando a que terminen los hilos");
		countDown.await();
		
		int espera = random.nextInt(1000);
		
		try {
			Thread.sleep(espera);
		} 
		catch (InterruptedException e) {}
		
		System.out.println("Hilos finalizados!!");
		
	}
}
