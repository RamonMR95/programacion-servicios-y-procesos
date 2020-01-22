package object_server;

import util.Fichero;

import java.io.*;
import java.net.Socket;

public class ServicioCliente extends Thread {
    private Socket s;

    public ServicioCliente(Socket cliente) {
        this.s = cliente;
    }

    public void run() {
        System.out.println("Cliente conectado " + s.getRemoteSocketAddress());

        try {
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.writeUTF("Introduce el nombre del fichero: ");
            dos.flush();
            dos.writeUTF("Introduce el path en el que se encuentra el fichero: ");
            dos.flush();

            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            Fichero fichero = (Fichero) ois.readObject();

            if (fichero.getFichero().exists()) {
                sendFile(fichero.getFichero());
            }
            else {
                dos.writeUTF("El fichero no existe!!!");
            }
            dos.flush();
            dos.close();
        }
        catch (IOException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getLocalizedMessage());
        }
    }

    private void sendFile(File file) throws IOException {
        byte[] sDatos = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
		OutputStream os = s.getOutputStream();

        bis.read(sDatos);
        System.out.println("Enviando archivo..." + file + " " + sDatos.length + " bytes");

        os.write(sDatos);
        System.out.println("Archivo enviado...");

		os.flush();
        os.close();
        bis.close();
        s.close();
    }
}
