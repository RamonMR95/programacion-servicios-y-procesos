package com.ramonmr95.app;

import com.ramonmr95.app.cola.Cola;

public class Productor extends Thread {

	Cola cola;
	
	public Productor(Cola cola) {
		this.cola = cola;
	}

	@Override
	public void run() {

	}

}
