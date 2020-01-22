import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ObjectClient {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		String server = "127.0.0.1";
		int puerto = 4000;
		Socket cliente = new Socket(server, puerto);
		ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
		Persona persona = (Persona) ois.readObject();
		System.out.println(persona);
		
		/* Modificamos los valores */
		persona.setNota(10);
		
		/* Enviamos el objeto de vuelta */
		ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
		oos.writeObject(persona);
		
		ois.close();
		oos.close();
		cliente.close();
	}
}
