package app;

import java.io.*;
import java.util.concurrent.Semaphore;

public class Cuenta {
    private int saldoActual;
    private int saldoMaximo;
    private Semaphore concurrencia;
    private PrintWriter flujoS;

    public Cuenta(int saldoActual, int saldoMaximo, Semaphore concurrencia, PipedWriter emisor) {
        this.saldoActual = saldoActual;
        this.saldoMaximo = saldoMaximo;
        this.concurrencia = concurrencia;
        this.flujoS = new PrintWriter(emisor);
    }

    public void ingresar(int cantidad, int numCliente, Persona ultimoCliente) {
        if (this.concurrencia.tryAcquire()) {
            seccionCriticaIngreso(cantidad, ultimoCliente);
            this.flujoS.println(Contador.nOperaciones++);
            this.concurrencia.release();
        }

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
        }
    }

    public void retirar(int cantidad, int numCliente, Persona ultimoCliente) {
        if (this.concurrencia.tryAcquire()) {
            seccionCriticaRetirada(cantidad, ultimoCliente);
            this.flujoS.println(Contador.nOperaciones++);
            this.concurrencia.release();
        }

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
        }
    }

    private void seccionCriticaIngreso(int cantidad, Persona ultimoCliente) {
        if (saldoActual + cantidad <= saldoMaximo) {
            saldoActual = saldoActual + cantidad;
            System.out.println(ultimoCliente.getNombre() + " ingresado " + cantidad + " euros");
            System.out.println(ultimoCliente.getNombre() +
                    saldoActual);
        } else {
            System.out.println(ultimoCliente.getNombre() + "el ingreso máximo permitido");
            System.out.println(ultimoCliente.getNombre() + saldoActual);
            ultimoCliente.terminar();
        }
    }

    private void seccionCriticaRetirada(int cantidad, Persona ultimoCliente) {
        if (saldoActual - cantidad >= 0) {
            saldoActual = saldoActual - cantidad;
            System.out.println(ultimoCliente.getNombre() + " ha retirado " + cantidad + " euros");
            System.out.println(ultimoCliente.getNombre() + " Saldo: " +
                    saldoActual);
        } else {
            System.out.println(ultimoCliente.getNombre() + " ha superado la retirada máxima permitido");
            System.out.println(ultimoCliente.getNombre() + " Saldo: " +
                    saldoActual);
            ultimoCliente.terminar();
        }
    }




}