import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Comedor extends Thread {

	private int nComedor;
	private CyclicBarrier inicioComer;
	private CountDownLatch esperaTodosTerminen;
	private Caja cajaManzanas;
	private int comidas;
	
	public Comedor(int i, CyclicBarrier inicioComer, CountDownLatch esperaTodosTerminen, Caja cajaManzanas) {
		this.nComedor = i;
		this.inicioComer = inicioComer;
		this.esperaTodosTerminen = esperaTodosTerminen;
		this.cajaManzanas = cajaManzanas;
		this.comidas = 0;
	}

	@Override
	public void run() {
		try {
			this.inicioComer.await();
			
			while (!this.cajaManzanas.vacia()) {
				comidas += cajaManzanas.getManzanas();
			}
			this.esperaTodosTerminen.countDown();
			System.out.println("EL comedor " + nComedor + " ha comido " + comidas);
		} 
		catch (InterruptedException | BrokenBarrierException e) {}
	}

}
