package sriServer;

public class SRI {

	private SRIRespuesta[] clients;

	// constructor crea un array
	public SRI(int size) {
		clients = new SRIRespuesta[size];
		for (int i = 0; i < clients.length; i++) {
			clients[i] = new SRIRespuesta();
		}
	}

	public void contesta(int clientNumber, String resp) {
		SRIRespuesta client = clients[clientNumber];
		client.contesta(resp);
	}

	public String getRespuesta(int clientNumber) {
		SRIRespuesta client = clients[clientNumber];
		return client.getRespuesta();
	}
}
