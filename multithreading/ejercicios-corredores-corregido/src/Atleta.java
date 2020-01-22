import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Atleta extends Thread {

	private int dorsal;
	private Testigo testigo;
	private CyclicBarrier cb;
	private CountDownLatch countDownLatch;

	public Atleta(int dorsal, Testigo testigo, CyclicBarrier cb, CountDownLatch countDownLatch) {
		this.dorsal = dorsal;
		this.testigo = testigo;
		this.cb = cb;
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		try {
			cb.await();
			testigo.correr(dorsal);
			countDownLatch.countDown();
		} 
		catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

}
