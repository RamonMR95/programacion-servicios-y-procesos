import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Coche extends Thread {

	private int cocheID;
	private CyclicBarrier iniciaCoches;
	private CountDownLatch esperaFin;
	private Semaphore estacionarCoche;
	private Semaphore accesoContador;

	public Coche(int cocheID, CyclicBarrier iniciaCoches, CountDownLatch esperaFin, Semaphore estacionarCoche,
			Semaphore accesoContador) {
		this.cocheID = cocheID;
		this.iniciaCoches = iniciaCoches;
		this.esperaFin = esperaFin;
		this.estacionarCoche = estacionarCoche;
		this.accesoContador = accesoContador;
	}

	@Override
	public void run() {
		boolean terminado = false;
		
		try {
			this.iniciaCoches.await();
			Thread.sleep(new Random().nextInt(50));
			
			while (!terminado) {
				if (this.estacionarCoche.tryAcquire()) {
					this.accesoContador.acquire();
					Parking.vehiculosEstacionados++;
					accesoContador.release();
					System.out.println("Coche id = " + this.cocheID + " dentro del parking" );
					Thread.sleep(new Random().nextInt(500));
					
					/* Salimos de un parking */
					this.accesoContador.acquire();
					Parking.vehiculosEstacionados--;
					System.out.println("Coche id = " + this.cocheID + " se va del parking" );
					this.estacionarCoche.release();
					this.accesoContador.release();
					terminado = true;
				}
				else {
					/* Esperamos antes de intentar volver a entrar */
					Thread.sleep(new Random().nextInt(50));
				}
			}
			this.esperaFin.countDown();
			
		} 
		catch (InterruptedException | BrokenBarrierException e) {}
		
	}

}
