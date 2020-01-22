package threads;

public class RunnableEx implements Runnable {

	public static void main(String[] args) {
		Thread hilo = new Thread(new RunnableEx());
		hilo.start();
	}

	@Override
	public void run() {
		System.out.println("Hi from runnable");
	}
}
