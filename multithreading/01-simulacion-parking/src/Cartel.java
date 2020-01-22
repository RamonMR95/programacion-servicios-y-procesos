import java.util.concurrent.Semaphore;

public class Cartel extends Thread {

	private boolean finalizado = false;
	private boolean imprimirC = true;
	private boolean imprimirL = true;
	private int plazasOld;
	private Semaphore accesoContador;
	
	public Cartel(Semaphore accesoContador) {
		this.plazasOld = 0;
		this.accesoContador = accesoContador;
	}

	@Override
	public void run() {
		while (!finalizado) {
			try {
				this.accesoContador.acquire();
				
				if (Parking.vehiculosEstacionados == Parking.maxCochesParking) {
					if (imprimirC) {
						System.out.println("Parking completo!");
						imprimirC = false;
						imprimirL = true;
					}
				}
				else {
					int plazas = Parking.maxCochesParking - Parking.vehiculosEstacionados;
					if (plazas != plazasOld) {
						imprimirL = true;
						plazasOld = plazas;
					}
					
					if (imprimirL) {
						System.out.println("Parking libre: Plazas = " + plazas);
						imprimirC = true;
						imprimirL = false;

					}
				}
				this.accesoContador.release();
			} 
			catch (InterruptedException e) {}
			
		}
	}

	public void finalizar() {
		finalizado = true;
	}

}
