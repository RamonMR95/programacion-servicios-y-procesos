import java.util.concurrent.Semaphore;

public class CountDown {

	private int contador;
	private int inicial = 0;
	private Semaphore s;
	private Semaphore v;
	
	public CountDown(int contador) {
		this.contador = contador;
		this.inicial = contador;
		this.s = new Semaphore(1);
		try {
			this.s.acquire();
		} 
		catch (InterruptedException e) {}
	}

	public void await() {
		try {
			this.s.acquire();
		} 
		catch (InterruptedException e) {}
	}

	public void countDown() {
		try {
			v.acquire();
			if (contador > 0) {
				contador--;
			}
			if (contador == 0) {
				s.release();
			}
			v.release();
		} 
		catch (InterruptedException e) {}
	}
	
	public void reset() {
		setCounter(inicial);
	}
	
	public void setCounter(int contador) {
		try {
			v.acquire();
			this.contador = contador;
			this.inicial = contador;
			v.release();
		} 
		catch (InterruptedException e) {}
	}

}
