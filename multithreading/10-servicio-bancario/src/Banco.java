import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Banco {
	private static Cuenta miCuenta = new Cuenta(80);
	
	public static void main(String[] args) {
		
		Lock candado = new ReentrantLock();
		ServicioBancario sb1 = new ServicioBancario(miCuenta, candado);
		ServicioBancario sb2 = new ServicioBancario(miCuenta, candado);
		
		sb1.setName("Ana");
		sb2.setName("Pedro");
		
		sb1.start();
		sb2.start();
		
//		try {
//			sb1.join();
//			sb2.join();
//		} 
//		catch (InterruptedException e) {
//		}
		
	}
}
