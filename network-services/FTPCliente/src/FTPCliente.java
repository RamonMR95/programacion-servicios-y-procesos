import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FTPCliente {

	public static void main(String[] args) throws SocketException, IOException {
		FTPClient clienteFTP = new FTPClient();
		String serverFTP = "172.16.6.61";
		int responseFTP;
		System.out.println("Conectando a " + serverFTP);
		clienteFTP.connect(serverFTP, 21);
		responseFTP = clienteFTP.getReplyCode();
		System.out.println("Codigo: " + responseFTP);
		clienteFTP.login("master", "pepe123");
		System.out.println(clienteFTP.getReplyString());

		String workingDir = "/";
		clienteFTP.changeWorkingDirectory(workingDir);
		FTPFile[] carpeta = clienteFTP.listFiles();
		Scanner sc = new Scanner(System.in);
		String comando;
		listaFiles(carpeta);
		System.out.println("Comando: ");
		
		while (true) {
			comando = sc.nextLine();
			if (comando.equalsIgnoreCase("q")) {
				System.out.println("Hasta la vista!");
				break;
			}
			if (comando.equalsIgnoreCase("download")) {
				System.out.println("Nº de fichero");
				int nFile = sc.nextInt();
				String file2Download = carpeta[nFile].getName();
				String fileLocal = file2Download;

				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileLocal));

				boolean res = clienteFTP.retrieveFile(file2Download, bos);
				if (res) {
					System.out.println("descarga correcta");
				} 
				else {
					System.out.println("La descarga ha fallado");
				}
				bos.close();
			}
			listaFiles(carpeta);
			System.out.println("Comando: ");
		}
		sc.close();
	}

	private static void listaFiles(FTPFile[] carpeta) {
		System.out.println("Listado del directorio actual");
		String tipoArchivo[] = { "file", "directory", "sym link" };

		for (int i = 0; i < carpeta.length; i++) {
			System.out.println(i + " " + carpeta[i].getName() + " " + tipoArchivo[carpeta[i].getType()]);
		}
		System.err.println("n files: " + carpeta.length);

	}
}
