import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class ServidorRMI extends UnicastRemoteObject implements RMIInterface {

	private static final long serialVersionUID = 1L;

	protected ServidorRMI() throws RemoteException {
		super();
	}

	public static void main(String[] args) {
		try {
			Naming.rebind("rmi://localhost/ServerRMI", new ServidorRMI());
		} catch (RemoteException | MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public double calculaCuota(double capital, double interes, double tiempo) throws RemoteException {
		double plazoMes = tiempo / 12.00;
		double interesMes = interes / 12.00 / 100.00;
		double cuota = (capital * interes) / 
			(100.00 * ( 1 - (Math.pow(interesMes,plazoMes)))); 
		return cuota;
	}

}
