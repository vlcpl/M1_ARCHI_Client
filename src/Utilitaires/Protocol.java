package Utilitaires;

import java.io.IOException;

public class Protocol {
	private Transport ts;
	
	public Protocol(String host, int port) {
		// On essaye d'accéder au serveur
		try {
			this.ts = new Transport(host, port);
		} catch (Exception e) {
			System.err.println("Unable to reach the server");
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Sends a file path to a server
	 * @param requestParameter
	 * @throws ClassNotFoundException
	 */
	public void sendRequest(String requestParameter) throws ClassNotFoundException {
		try {
			this.ts.envoyer(requestParameter);
		} catch (IOException e) {
			System.err.println("An error occured during the request, please retry later");
		}
	}
	
	/**
	 * Receives a response from the server
	 * @return
	 * @throws Exception
	 */
	public Object receiveResponse() throws Exception {
		String serverResponse = (String) this.ts.recevoir();
		if(serverResponse.equals("Good")) {
			String fileContent = (String) this.ts.recevoir();
			this.ts.fermer();
			return fileContent;
		} else {
			this.ts.fermer();
			throw new Exception ("The server did not send a good response");
		}
	}

	
}
