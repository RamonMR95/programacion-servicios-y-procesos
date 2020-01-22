package threads;

public class ThreadEx extends Thread {

	public static void main(String[] args) {
		Thread hilo = new ThreadEx();
		hilo.start();
	}

	@Override
	public void run() {
		System.out.println("Hello from thread inheritance");
	}

}
