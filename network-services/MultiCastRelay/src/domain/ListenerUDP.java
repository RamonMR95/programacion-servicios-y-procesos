package domain;

import java.io.IOException;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Arrays;
import java.util.concurrent.Semaphore;

import main.MainServer;

public class ListenerUDP extends Thread {
    private String multiUDPNet;
    private int multiUDPPort;
    private PipedWriter[] emisor;
    private int maxClientes;
    private int clientesConectados = 0;
    private byte[] bufferIn = new byte[1024];
    private String sData;
    private MulticastSocket s;
    private PrintWriter[] flujoS;

    public ListenerUDP(String multiUDPNet, int multiUDPPort, PipedWriter[] emisor, int maxClientes) throws IOException {
        this.multiUDPNet = multiUDPNet;
        this.multiUDPPort = multiUDPPort;
        this.emisor = emisor;
        this.maxClientes = maxClientes;
        this.s = new MulticastSocket(multiUDPPort);
        this.s.joinGroup(InetAddress.getByName(multiUDPNet));
        this.flujoS = new PrintWriter[maxClientes];
        for (int i = 0; i < maxClientes; i++)
            this.flujoS[i] = new PrintWriter(this.emisor[i]);
    }

    public void run() {
        DatagramPacket in = new DatagramPacket(bufferIn, bufferIn.length);
        System.out.println("Multicast Client running...");

        while (true) {
            try {
                MainServer.lock.acquire();
                if (MainServer.clientesActuales > 0)
                    clientesConectados = MainServer.clientesActuales;
                MainServer.lock.release();

                s.receive(in);

                sData = new String(in.getData(), 0, in.getData().length).trim();

                if (clientesConectados > 0) {
                    for (int i = 0; i < clientesConectados; i++)
                        flujoS[i].println(sData);
                }

                System.out.println("Listener: >" + sData + " conectados:" + clientesConectados);

                //borra buffer
				Arrays.fill(bufferIn, (byte) 0);

            } catch (IOException | InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }//end-try-catch
        }//end-while
    }//end-run
}
