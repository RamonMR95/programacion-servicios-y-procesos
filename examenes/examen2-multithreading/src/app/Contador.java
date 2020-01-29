package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;

public class Contador extends Thread {

    public static int nOperaciones = 0;
    private BufferedReader flujoE;
    private boolean terminar;

    public Contador(PipedReader receptor) {
        this.flujoE = new BufferedReader(receptor);
        this.terminar = false;
    }

    @Override
    public void run() {
        while (!terminar) {
            try {
                nOperaciones = Integer.parseInt(flujoE.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void terminar() {
        this.terminar = true;
    }
}