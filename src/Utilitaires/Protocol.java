package Utilitaires;

import java.io.IOException;

public class Protocol {
	private Transport ts;
	
	public Protocol(String host, int port) {
		// On essaye d'accéder au serveur
		try {
			ts = new Transport(host, port);
		} catch (Exception e) {
			System.err.println("Unable to reach the server");
			e.printStackTrace();
			System.exit(1);
		}
	}

	
	public void sendRequest(String requestParameter) throws ClassNotFoundException {
		try {
			ts.envoyer(requestParameter);
			ts.fermer();
		} catch (IOException e) {
			System.err.println("An error occured during the request, please retry later");
		}
	}
	
	public Object receiveResponse() throws Exception {
		String serverResponse = (String) ts.recevoir();
		if(serverResponse.equals("Good")) {
			String fileContent = (String) ts.recevoir();
			ts.fermer();
			return fileContent;
		} else {
			ts.fermer();
			throw new Exception ("The server did not send a good response");
		}
	}

	
}
