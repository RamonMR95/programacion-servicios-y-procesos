
public class Cuenta {

	public int saldo;

	public Cuenta(int saldo) {
		this.saldo = saldo;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
	
//	public synchronized int reintegro(int cantidad) {
	public int reintegro(int cantidad) {
		int re;
		saldo -= cantidad;
		re = cantidad;
		return re;
//		if (saldo >= cantidad) {
//			this.saldo = saldo - cantidad;
//			re = cantidad;
//		}
//		else {
//			re = 0;
//		}
//		return re;
		
	}

}
