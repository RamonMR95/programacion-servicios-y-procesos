import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClienteRMI {

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		RMIInterface conector = 
				(RMIInterface) Naming.lookup("//localhost/ServerRMI");
		double interes = 4.00;
		double tiempo = 20 * 12;
	}

}
