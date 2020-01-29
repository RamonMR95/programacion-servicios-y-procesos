package app;

import java.util.Random;
import java.util.concurrent.*;

public class Persona extends Thread {

    private int TIEMPOMAX;
    private int TIEMPOMIN;
    private String nombre;
    private int numCliente;
    private Cuenta cuenta;
    private boolean terminar;
    private CyclicBarrier espera;
    private CountDownLatch finalClientes;

    public Persona(String nombre, int numCliente, Cuenta cuenta, CyclicBarrier espera, int TIEMPOMAX, int TIEMPOMIN,
                   CountDownLatch finalClientes) {
        this.nombre = nombre;
        this.numCliente = numCliente;
        this.cuenta = cuenta;
        this.espera = espera;
        this.finalClientes = finalClientes;
        this.TIEMPOMAX = TIEMPOMAX;
        this.TIEMPOMIN = TIEMPOMIN;
    }


    public void run() {
        try {
            this.espera.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
            e.printStackTrace();
        }

        while (true) {
            int tiempo = ((int) (Math.random()*(TIEMPOMAX - TIEMPOMIN) + TIEMPOMIN));
            int random = new Random().nextInt(2);
            if (random == 0) {
                cuenta.ingresar(generarCifra(), numCliente, this);
                try {
                    sleep(tiempo);
                } catch (InterruptedException e) {
                }
                if (terminar)
                    break;
            } else {
                cuenta.retirar(generarCifra(), numCliente, this);
                try {
                    sleep(tiempo);
                } catch (InterruptedException e) {
                }
                if (terminar)
                    break;
            }
        }
        this.finalClientes.countDown();
    }

    public int generarCifra() {
        return (int) (Math.random() * 1000 + 1);
    }

    public String getNombre() {
        return nombre;
    }

    public void terminar() {
        this.terminar = true;
    }
}