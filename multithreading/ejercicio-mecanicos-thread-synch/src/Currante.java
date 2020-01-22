import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Currante extends Thread {

	private int curranteID;
	private int usaHerramientaIZQ;
	private int usaHerramientaDER;
	private Semaphore[] esperaHerramientas;
	private CountDownLatch trabajos;
	private boolean terminado;

	public Currante(int i, int[] usaHerramientas, Semaphore[] esperaHerramientas, CountDownLatch trabajos) {
		this.curranteID = i;
		this.usaHerramientaIZQ = usaHerramientas[0];
		this.usaHerramientaDER = usaHerramientas[1];
		this.esperaHerramientas = esperaHerramientas;
		this.trabajos = trabajos;
		this.terminado = false;
	}

	@Override
	public void run() {
		System.out.println("Currante: " + curranteID + " iniciado");
		while (!terminado) {
			if (esperaHerramientas[usaHerramientaIZQ].tryAcquire()) {
				System.out.println("El currante " + curranteID + " tiene herramienta " + usaHerramientaIZQ);
				if (esperaHerramientas[usaHerramientaDER].tryAcquire()) {
					System.out.println("El currante " + curranteID + " tiene herramienta " + usaHerramientaDER);
					System.out.println("El currante " + curranteID + " ha terminado");
					trabajos.countDown();
					esperaHerramientas[usaHerramientaIZQ].release();
					esperaHerramientas[usaHerramientaDER].release();
					terminado = true;
				}
				else {
					esperaHerramientas[usaHerramientaIZQ].release();
				}
			}

		}
	}

}
