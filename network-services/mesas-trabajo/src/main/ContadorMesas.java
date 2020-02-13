package main;

import java.util.concurrent.Semaphore;

public class ContadorMesas {

	private int maxMesas;
	private int[] contadorMesa;
	private int[] asignaMesaPuesto;
	private Semaphore lock = new Semaphore(1);
	
	public ContadorMesas(int maxMesas) {
		try {
			lock.acquire();
			this.maxMesas = maxMesas;
			this.contadorMesa = new int[maxMesas];
			this.asignaMesaPuesto = new int[maxMesas];
			resetContadores();
			lock.release();
		} 
		catch (InterruptedException e) {
		}
	}
	
	private void resetContadores() {
		for (int i = 0; i < maxMesas; i++) {
			this.contadorMesa[i] = 0;
			this.asignaMesaPuesto[i] = 0;
		}
	}

}
