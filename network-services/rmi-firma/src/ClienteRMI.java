import java.rmi.Naming;
import java.util.Scanner;

public class ClienteRMI {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception {
		RMIInterface conector = (RMIInterface) Naming.lookup("//localhost/ServidorRMI");
		
		System.out.println("Introduce el fichero .pub: ");
		String pubKeyFile = sc.nextLine();
		
		System.out.println("Introduce el fichero .txt: ");
		String dataFile = sc.nextLine();
		
		System.out.println("Introduce el fichero .frm: ");
		String signFile = sc.nextLine();
		
		if (conector.validarFirma(pubKeyFile, dataFile, signFile)) {
			System.out.println("\u001B[32m" + "\nValidación correcta!");
		}
		else {
			System.err.println("\nValidación incorrecta");
		}
	}
}
