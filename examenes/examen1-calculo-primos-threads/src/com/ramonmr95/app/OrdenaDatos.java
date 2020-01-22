package com.ramonmr95.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;


/**
 * OrdenaDatos.java
 * @author Ramón Moñino Rubio - 2º DAM
 */
public class OrdenaDatos extends Thread {

	private CountDownLatch esperaFinProceso;
	private CountDownLatch esperaFinOrdena;
	private BufferedReader flujoE;
	private int[] valores;

	public OrdenaDatos(CountDownLatch esperaFinProceso, CountDownLatch esperaFinOrdena, PipedReader receptor) {
		this.esperaFinProceso = esperaFinProceso;
		this.esperaFinOrdena = esperaFinOrdena;
		this.flujoE = new BufferedReader(receptor);
	}

	@Override
	public void run() {
		try {
			this.esperaFinProceso.await();
			this.valores = new int[CalculaPrimos.datosPipe];
			llenarArrayPrimos();
			Arrays.sort(valores);
			
			for (int i = 0; i < valores.length; i++) {
				System.out.println("Primo: " + (i + 1) + " < " + valores[i]);
			}
			this.esperaFinOrdena.countDown();
			
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void llenarArrayPrimos() {
		int counter = 0;
		
		try {
			while (counter < CalculaPrimos.datosPipe) {
				valores[counter] = Integer.parseInt(flujoE.readLine());
				counter++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
