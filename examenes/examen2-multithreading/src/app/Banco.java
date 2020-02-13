package app;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Banco {
    public static void main(String[] args) {
        final int TIEMPOMAX = 400;
        final int TIEMPOMIN = 100;

        CyclicBarrier espera = new CyclicBarrier(3);
        Semaphore concurrencia = new Semaphore(1);
        PipedReader receptor = new PipedReader();
        PipedWriter emisor = null;
        try {
            emisor = new PipedWriter(receptor);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Contador contador = new Contador(receptor);
        CountDownLatch finalClientes = new CountDownLatch(3);

        Cuenta cuenta = new Cuenta(2000, 10000, concurrencia, emisor);
        Vector<Persona> gente = new Vector<Persona>();
        gente.addElement(new Persona("Juan", 0, cuenta, espera, TIEMPOMAX, TIEMPOMIN, finalClientes));
        gente.addElement(new Persona("Pepe", 1, cuenta, espera, TIEMPOMAX, TIEMPOMIN, finalClientes));
        gente.addElement(new Persona("Ana" , 2, cuenta, espera, TIEMPOMAX, TIEMPOMIN, finalClientes));
        for (int i = 0; i < gente.size(); i++){
            gente.elementAt(i).start();
        }

        try {
            finalClientes.await();
            contador.terminar();
            System.out.println("Nº operaciones: " + Contador.nOperaciones);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}