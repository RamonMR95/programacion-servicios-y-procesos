package com.ramonmr95.app;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * CalculaPrimos.java
 * @author Ramón Moñino Rubio - 2º DAM
 */
public class CalculaPrimos {

	static int datosPipe = 0;
	
	public static void main(String[] args) {
		int maxProcesos = 100;
		int maxProcesosCpu = 10;
		Procesador[] procesador = new Procesador[maxProcesos];
		CyclicBarrier iniciaProceso = new CyclicBarrier(maxProcesos);
		CountDownLatch esperaFinProceso = new CountDownLatch(maxProcesos);
		CountDownLatch esperaFinOrdena = new CountDownLatch(1);
		Semaphore concurrencia = new Semaphore(maxProcesosCpu);
		Semaphore accesoPipeCounter = new Semaphore(1);
		
		PipedReader receptor = new PipedReader();
		PipedWriter emisor = null;
		try {
			emisor = new PipedWriter(receptor);
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("Inicio del proceso");

		for (int i = 0; i < maxProcesos; i++) {
			procesador[i] = new Procesador(i, iniciaProceso, esperaFinProceso, concurrencia, emisor, accesoPipeCounter);
			procesador[i].start();
		}
		
		OrdenaDatos ordenaDatos = new OrdenaDatos(esperaFinProceso, esperaFinOrdena, receptor);
		ordenaDatos.start();
		
		try {
			esperaFinOrdena.await();
			System.out.println("Operación terminada");
		} 
		catch (InterruptedException e2) {
			e2.printStackTrace();
		}
	}
}
