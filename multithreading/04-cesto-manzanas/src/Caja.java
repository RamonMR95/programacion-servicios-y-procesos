import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Caja {

	private int manzanas;
	private Semaphore accesoComer;
	private Lock candado;
	private Random rand;
	
	public Caja(int maxManzanas, Semaphore accesoComer) {
		this.manzanas = maxManzanas;
		this.accesoComer = accesoComer;
		this.candado = new ReentrantLock();
		this.rand = new Random();
	}

	public boolean vacia() {
		candado.lock();
		
		if (manzanas > 0) {
			candado.unlock();
			return false;
		}
		candado.unlock();
		return true;
	}

	public int getManzanas() throws InterruptedException {
			accesoComer.acquire();
			int intentoComer = rand.nextInt(5) + 1;
			candado.lock();
			if (manzanas >= intentoComer) {
				manzanas -= intentoComer;
			}
			else {
				intentoComer = 0;
			}
			candado.unlock();
			accesoComer.release();

		return intentoComer;
	}

}
