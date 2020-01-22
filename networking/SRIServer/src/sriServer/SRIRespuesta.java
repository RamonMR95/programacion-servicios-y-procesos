package sriServer;

import java.util.concurrent.locks.*;

public class SRIRespuesta {

	private String respuesta;
	private Lock sriChangeLock;

	public SRIRespuesta() {
		respuesta = "";
		sriChangeLock = new ReentrantLock();
	}

	public SRIRespuesta(String aRespuesta) {
		respuesta = aRespuesta;
		sriChangeLock = new ReentrantLock();
	}

	public void contesta(String aRespuesta) {
		sriChangeLock.lock();
		try {
			respuesta = aRespuesta;
		} finally {
			sriChangeLock.unlock();
		}
	}// contesta

	public String getRespuesta() {
		return respuesta;
	}// getRespuesta
}
