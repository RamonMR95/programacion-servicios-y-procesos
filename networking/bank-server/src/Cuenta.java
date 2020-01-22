
public class Cuenta {

	private double balance;
	
	public synchronized double saldo() {
		return this.balance;
	}

	public synchronized boolean depositar(double cantidad) {
		this.balance += cantidad;
		return true;
	}

	public synchronized boolean reintegrar(double cantidad) {
		if (balance >= cantidad) {
			this.balance -= cantidad;
			return true;
		}
		return false;
	}

}
