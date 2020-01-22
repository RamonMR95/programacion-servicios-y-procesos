package carreraCaballos;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Caballo extends Thread {
	
	CyclicBarrier barrera;
	boolean fin = false;
	int pista;
	int posicion = 0;
	int dorsal;
	int avance;
	int ganador;

	public void run() {
		Random rand = new Random();
		
		try {
			barrera.await();
			// mientras que no llega al final simula la carrera
			while ((posicion < pista) && !fin) {
				avance = (int) (Math.random() * 5);
				if ((posicion + avance) < pista) {
					posicion += avance;
				} 
				else {
					posicion = pista;
				}
				long aleatorio = rand.nextInt(300);
				Thread.sleep(aleatorio);
			}
		} 
		catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

	public Caballo(int i, int pista, CyclicBarrier barrera) {
		this.barrera = barrera;
		this.pista = pista;
		this.posicion = 0;
		this.dorsal = i;
		this.ganador = 99;
	}

	public int getPosicion() {
		return posicion;
	}

	public void finalizar() {
		Thread t = this.currentThread();
		t.interrupt();
	}
}