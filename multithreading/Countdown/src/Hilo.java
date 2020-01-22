import java.util.Random;

public class Hilo extends Thread {
	
	private int id;
	private CountDown countDown;
	private Random random;

	public Hilo(int id, CountDown countDown) {
		this.id = id;
		this.countDown = countDown;
		this.random = new Random();
	}

	@Override
	public void run() {
		int espera = random.nextInt(500);
		
		try {
			Thread.sleep(espera);
			System.out.println("Hilo " + id + " finalizado!");
			countDown.countDown();
		} 
		catch (InterruptedException e) {}

	}

}
