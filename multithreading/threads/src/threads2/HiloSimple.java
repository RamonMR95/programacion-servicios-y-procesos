package threads2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class HiloSimple extends Thread {

	private String nombre;
	private long espera;
	private CyclicBarrier cb;

	public HiloSimple(String nombre, long espera, CyclicBarrier cb) {
		this.nombre = nombre;
		this.espera = espera;
		this.cb = cb;
	}

	@Override
	public void run() {
		try {
			cb.await();
			
			for (int i = 0; i < 10; i++) {
				System.out.println("En el hilo " + nombre);
				Thread.sleep(espera);
			}
		} 
		catch (InterruptedException | BrokenBarrierException e) { }
	}
}
