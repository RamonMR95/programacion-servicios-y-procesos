
public class Titulo {
	String[] nombreAcciones = { "Renfe", "Iberia", "Iberdrola", "GasNatural", "Amadeus", "Inditext", "Repsol", "Audax",
			"Roca", "Porcelanosa" };
	int[] cantidadAcciones = new int[nombreAcciones.length];

	public Titulo(int cantidad) {
		for (int i = 0; i < nombreAcciones.length; i++)
			this.cantidadAcciones[i] = cantidad;
	}

	public String getNombre(int valor) {
		String retValor;
		if (valor < nombreAcciones.length) {
			retValor = nombreAcciones[valor];
		} 
		else {
			retValor = "";
		}
		return retValor;
	}

	public int getCantidad(int valor) {
		int retValor;
		if (valor < nombreAcciones.length) {
			retValor = cantidadAcciones[valor];
		} 
		else {
			retValor = 0;
		}
		return retValor;
	}

	public boolean compraCantidad(int valor, int cantidad) {
		boolean retOp;
		if ((valor < nombreAcciones.length) && (cantidad <= cantidadAcciones[valor])) {
			cantidadAcciones[valor] -= cantidad;
			retOp = true;
		} 
		else {
			retOp = false;
		}
		return retOp;
	}

	public boolean vendeCantidad(int valor, int cantidad) {
		boolean retOp;
		if (valor < nombreAcciones.length) {
			cantidadAcciones[valor] += cantidad;
			retOp = true;
		} 
		else {
			retOp = false;
		}
		return retOp;
	}

	public int getNumeroTitulos() {
		return nombreAcciones.length;
	}
}