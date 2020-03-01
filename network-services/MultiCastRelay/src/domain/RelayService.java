package domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.Scanner;

import main.MainServer;

public class RelayService extends Thread {
    private Socket s;
    private int cliente;
    private PipedReader receptor;
    private BufferedReader flujoE;
    private PrintWriter out;
    private Scanner in;

    public RelayService(Socket s, int clientes, PipedWriter emisor) {
        this.s = s;
        this.cliente = clientes;

        try {
            this.receptor = new PipedReader(emisor);
            this.flujoE = new BufferedReader(receptor);

            MainServer.lock.acquire();
            MainServer.clientesActuales++;
            MainServer.lock.release();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void run() {
        try {

            out = new PrintWriter(s.getOutputStream());
            in = new Scanner(s.getInputStream());
            out.println("Servicio de relay activo ");

            out.flush();
            procesaCliente();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void procesaCliente() throws IOException {

        while (!s.isClosed()) {

            System.out.println("Esperando mensaje");
            String mensa = flujoE.readLine();
            out.println("Relay :" + mensa);

            System.out.println("Relay " + mensa);
            out.flush();
        }
    }
}
