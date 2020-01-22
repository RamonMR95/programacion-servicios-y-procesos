package com.ramonmr95.app;

import java.io.PipedWriter;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * Procesador.java
 * @author Ramón Moñino Rubio - 2º DAM
 */

public class Procesador extends Thread {

	private int nProceso;
	private CyclicBarrier iniciaProceso;
	private CountDownLatch esperaFinProceso;
	private Semaphore concurrencia;
	private Semaphore accesoPipeCounter;
	private PrintWriter flujoS;

	public Procesador(int i, CyclicBarrier iniciaProceso, CountDownLatch esperaFinProceso, Semaphore concurrencia,
			PipedWriter emisor, Semaphore accesoPipeCounter) {

		this.nProceso = i;
		this.iniciaProceso = iniciaProceso;
		this.esperaFinProceso = esperaFinProceso;
		this.concurrencia = concurrencia;
		this.accesoPipeCounter = accesoPipeCounter;
		this.flujoS = new PrintWriter(emisor);
	}

	@Override
	public void run() {
		try {
			this.iniciaProceso.await();

			while (true) {
				if (this.concurrencia.tryAcquire()) {
					if (esprimo(this.nProceso)) {
						if (this.accesoPipeCounter.tryAcquire()) {
							flujoS.println(this.nProceso);
							CalculaPrimos.datosPipe++;
							this.accesoPipeCounter.release();
							this.concurrencia.release();
							break;
						}
						else {
							this.accesoPipeCounter.release();
						}
					}
					else {
						this.concurrencia.release();
						break;
					}
				} 
				else {
					this.concurrencia.release();
					Thread.sleep(new Random().nextInt(300));
				}
			}
			this.esperaFinProceso.countDown();
			
		} 
		catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

	private boolean esprimo(int valor) {
		boolean primo = false;
		
		switch (valor) {
		case 0:
			primo = false;
			break;
		case 1:
			primo = false;
			break;
		case 2:
			primo = true;
			break;
		default:
			int contador = 0;
			for (int i = 1; i <= valor; i++) {
				if ((valor % i) == 0) {
					contador++;
				}
			}
			if (contador <= 2) {
				primo = true;
			} 
			else {
				primo = false;
			}
		}
		return primo;
	}

}
