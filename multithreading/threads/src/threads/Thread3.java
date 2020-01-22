package threads;

public class Thread3 {

	public static void main(String[] args) {
		Thread hilo = new Thread(() -> System.out.println("LOL"));
		hilo.start();
	}
}
