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
			Naming.rebind("rmi://localhost/ServidorRMI", new ServidorRMI());
			System.out.println("Esperando conexiones");
		} 
		catch (RemoteException | MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean validarFirma(String pubKeyFile, String dataFile, String signFile) throws Exception {
		return new ValidaFirma(pubKeyFile, dataFile, signFile).validar();
	}

}
