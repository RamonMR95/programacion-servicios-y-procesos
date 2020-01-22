package java;

import java.io.PipedWriter;
import java.io.PrintWriter;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Pintor extends Thread {

    private int pintor;
    private PrintWriter flujoEntrada;
    private Semaphore semaforo;
    private CyclicBarrier barrera;
    private CountDownLatch espera;

    public Pintor(int i, PipedWriter emisor, Semaphore seccionCritica, CyclicBarrier barrera, CountDownLatch espera) {
        this.pintor = i;
        this.flujoEntrada = new PrintWriter(emisor);
        this.semaforo = seccionCritica;
        this.barrera = barrera;
        this.espera = espera;
    }

    @Override
    public void run() {
        try {
            this.barrera.await();
            for (int i = 0; i < 2; i++) {
                this.semaforo.acquire();
                flujoEntrada.println("El pintor: " + pintor + " dice hola. " + i);
                this.semaforo.release();
            }
            espera.countDown();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}
