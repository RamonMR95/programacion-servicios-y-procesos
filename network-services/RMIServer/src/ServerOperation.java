import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerOperation extends UnicastRemoteObject implements RMIInterface {

	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
		try {
			Naming.rebind("rmi://localhostServer", new ServerOperation());
		} 
		catch (RemoteException | MalformedURLException e) {
			e.printStackTrace();
		}
	}

	protected ServerOperation() throws RemoteException {

	}

	@Override
	public String greet(String name) throws RemoteException {
		return "Server: Greetings " + name;
	}

}
