package com.ramonmr95.app;
import com.ramonmr95.app.cola.Cola;

public class Main {

	public static void main(String[] args) {
		Cola cola = new Cola();
		Productor p = new Productor(cola);
		Consumidor c = new Consumidor(cola);
		
		p.start();
		c.start();
				
	}
}
