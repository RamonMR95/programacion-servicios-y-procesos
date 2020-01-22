import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Caballo extends Thread {
	private CyclicBarrier barrera;
	private int pista;
	private int posicion = 0;
	private int dorsal;
	private int avance;
	private int ganador;
	private boolean fin = false;
	private PipedWriter emisor;
	private PrintWriter flujoS;

	public Caballo(int i, int pista, CyclicBarrier barrera, PipedReader receptor) {
		this.barrera = barrera;
		this.pista = pista;
		this.posicion = 0;
		this.dorsal = i;
		this.ganador = 99;
		try {
			this.emisor = new PipedWriter(receptor);
			this.flujoS = new PrintWriter(emisor);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		Random rand = new Random();
		try {
			barrera.await();
			// mientras que no llega al final simula la carrera
			while ((posicion < pista) && (!fin)) {
				avance = (int) (Math.random() * 5);
				if ((posicion + avance) < pista) {
					posicion += avance;
				} else {
					posicion = pista;
				}
				long aleatorio = rand.nextInt(300);
				Thread.sleep(aleatorio);
			}
		} catch (InterruptedException | BrokenBarrierException e) {
			// e.printStackTrace();
			System.out.println("Finalizado caballo " + dorsal);
		}
	}

	public void getPosicion() {
		this.flujoS.println(this.posicion);
	}

	public void finalizar() {
		// fin = true;
		// Thread t = this.currentThread();
		interrupt();
	}
}
