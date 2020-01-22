import java.util.concurrent.locks.Lock;

public class ServicioBancario extends Thread {

	Cuenta miCuenta;
	Lock candado;

	public ServicioBancario(Cuenta miCuenta, Lock candado) {
		this.miCuenta = miCuenta;
		this.candado = candado;
	}

	@Override
	public void run() {
		hacerReintegro(50);
		if (miCuenta.getSaldo() < 0) {
			System.out.println(Thread.currentThread().getName() + " Estamos en numeros rojos");
		}
	}

	private void hacerReintegro(int cantidad) {
		int re;

		while (true) {
			if (candado.tryLock()) {
				if (this.miCuenta.getSaldo() >= cantidad) {
					System.out.println(Thread.currentThread().getName() + " va a retirar " + cantidad);
					re = miCuenta.reintegro(cantidad);
					candado.unlock();
					if (re > 0) {
						System.out.println(Thread.currentThread().getName() + " ha retirado " + cantidad);
					}
				} else {
					System.out.println(Thread.currentThread().getName() + " No tiene dinero " + cantidad);
				}
				break;
			} else {
				System.out.println(Thread.currentThread().getName() + " intentando operar ");
			}
		}

	}

}
