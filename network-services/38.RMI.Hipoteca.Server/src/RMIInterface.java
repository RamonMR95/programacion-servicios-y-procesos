import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends Remote {
	public double calculaCuota(double capital, double interes, double tiempo) throws RemoteException;
}
