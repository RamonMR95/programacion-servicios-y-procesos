import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServicioCliente extends Thread {

	private Socket s;

	public ServicioCliente(Socket cliente) {
		this.s = cliente;
	}

	@Override
	public void run() {
		System.out.println("Cliente conectado " + s.getRemoteSocketAddress());
		try {
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			Persona persona = new Persona("Juan", "Programación de Servicios", 0);
			oos.writeObject(persona);
			System.out.println("Enviando: " + persona.getNombre() + ", " + persona.getAsignatura() + ", " + persona.getNota());
			
			/* Creamos el inputStream para leer el objeto de vuelta */
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Persona personaRecibida = (Persona) ois.readObject();
			System.out.println(personaRecibida);
			
			oos.close();
			ois.close();
		} 
		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
