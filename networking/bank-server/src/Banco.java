
public class Banco {

	private static final int maxCuentas = 10;
	private Cuenta[] cuentas = new Cuenta[maxCuentas];

	public Banco() {
		for (int i = 0; i < cuentas.length; i++) {
			cuentas[i] = new Cuenta();
		}
	}

	public double saldo(int nCuenta) {
		return cuentas[nCuenta].saldo();
	}

	public boolean deposito(int cuenta, double cantidad) {
		return cuentas[cuenta].depositar(cantidad);
	}

	public boolean reintegro(int cuenta, double cantidad) {
		return cuentas[cuenta].reintegrar(cantidad);
	}

}
