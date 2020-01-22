package com.ramonmr95.app;

import com.ramonmr95.app.cola.Cola;

public class Consumidor extends Thread {

	Cola cola;
	
	public Consumidor(Cola cola) {
		this.cola = cola;
	}
	
	@Override
	public void run() {

	}

}
