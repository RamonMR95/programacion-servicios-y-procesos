import java.util.Random;
import java.util.concurrent.Semaphore;

public class Testigo {

	int maxAtletas;
	int atletaActual;
	long inicio;
	long end;
	long tiempoCorredor;
	long tiempoCarrera;
	int orden;
	Semaphore esperaTurno;

	public Testigo(int maxAtletas) {
		this.maxAtletas = maxAtletas;
		this.tiempoCarrera = 0;
		this.orden = 0;
		this.esperaTurno = new Semaphore(1);
	}

	public synchronized void correr(int dorsal) {
		while (true) {
			if (orden == dorsal) {
				this.atletaActual = dorsal;
				System.out.println("Corriendo el " + dorsal);
				inicio = System.currentTimeMillis();

				try {
					Thread.sleep(new Random().nextInt(4000) + 1000);
				} 
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				end = System.currentTimeMillis();
				tiempoCorredor = end - inicio;
				tiempoCarrera += tiempoCorredor;
				System.out.println("Finaliza el " + dorsal + ", ha corrido: " + tiempoCorredor);
				orden++;
				notifyAll();
				break;
			} 
			else {
				try {
					wait();
				} 
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public long getTiempoCarrera() {
		return tiempoCarrera;
	}

}
