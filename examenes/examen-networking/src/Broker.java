import java.io.PipedWriter;
import java.io.PrintWriter;
import java.util.concurrent.Semaphore;

public class Broker {

	private Titulo titulos;
	private Semaphore accesoBroker;
	private PrintWriter flujosS;

	public Broker(Titulo titulos, PipedWriter emisor, Semaphore accesoBroker) {
		this.titulos = titulos;
		this.accesoBroker = accesoBroker;
		this.flujosS = new PrintWriter(emisor);
	}

	public void enviarOperacion(String message) {
		flujosS.println(message);
		flujosS.flush();
	}

	public String getNombre(int valor) throws InterruptedException {
		String nombre = "";
		this.accesoBroker.acquire();
		nombre = this.titulos.getNombre(valor);
		this.accesoBroker.release();
		return nombre;
	}

	public int getCantidad(int valor) throws InterruptedException {
		this.accesoBroker.acquire();
		int cantidad = titulos.getCantidad(valor);
		this.accesoBroker.release();
		return cantidad;
	}

	public boolean compraCantidad(int valor, int cantidad) throws InterruptedException {
		boolean exito = false;
		this.accesoBroker.acquire();
		if (this.titulos.compraCantidad(valor, cantidad)) {
			enviarOperacion("Compra| valor: " + valor + ", cantidad: " + cantidad);
			exito = true;
		}
		this.accesoBroker.release();
		return exito;
	}

	public boolean vendeCantidad(int valor, int cantidad) throws InterruptedException {
		boolean exito = false;
		this.accesoBroker.acquire();
		if (this.titulos.vendeCantidad(valor, cantidad)) {
			enviarOperacion("Venta| valor: " + valor + ", cantidad: " + cantidad);
			exito = true;
		}
		this.accesoBroker.release();
		return exito;
	}

	public int getNumeroTitulos() throws InterruptedException {
		int num = 0;
		this.accesoBroker.acquire();
		num = this.titulos.getNumeroTitulos();
		this.accesoBroker.release();
		return num;
	}
}
