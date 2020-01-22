import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Parking {

	public static int vehiculosEstacionados = 0;
	public final static int maxCochesParking = 50;
	public final static int maxCoches = 250;
	
	public static void main(String[] args) {
		Coche[] vehiculo = new Coche[maxCoches];

		/* Elementos para la sincronización */
		CyclicBarrier iniciaCoches = new CyclicBarrier(maxCoches);
		CountDownLatch esperaFin = new CountDownLatch(maxCoches);
		Semaphore estacionarCoche = new Semaphore(maxCochesParking);
		Semaphore accesoContador = new Semaphore(1);

		/* Creamos los coches */
		for (int i = 0; i < maxCoches; i++) {
			vehiculo[i] = new Coche(i, iniciaCoches, esperaFin, estacionarCoche, accesoContador);
			vehiculo[i].start();
		}

		Cartel cartel = new Cartel(accesoContador);
		cartel.start();
		
		/* Esperamos que todos los coches han entrado */
		try {
			System.out.println("Parking en marcha!");
			esperaFin.await();
			cartel.finalizar();
			System.out.println("Parking finalizado!");
		} 
		catch (InterruptedException e) {}
	}
}
