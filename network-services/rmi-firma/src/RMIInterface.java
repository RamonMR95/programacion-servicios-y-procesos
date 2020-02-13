import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends Remote {
	
	public boolean validarFirma(String pubKeyFile, String dataFile, String signFile) throws RemoteException, Exception;
	
}
