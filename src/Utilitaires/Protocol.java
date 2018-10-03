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
	
	public Object protocolGetRequest(String path) {
		Object response = null;
		try {
			if(openConnectionRequest()) {
				response = this.sendRequest(path);
				this.closeConnectionRequest();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return response;
		}
		return response;
	}

	/**
	 * Méthode permettant de demander au serveur s'il est prêt à recevoir une requête
	 * @param host
	 * @param port
	 * @return true si le serveur est prêt à recevoir une requete, faux sinon
	 * @throws ClassNotFoundException
	 */
	public boolean openConnectionRequest() throws ClassNotFoundException {

		// Une fois le serveur atteint, on lui demande une autorisation d'envoi de
		// message
		try {
			ts.envoyer("Connection request");

			String response = (String) ts.recevoir();
			System.out.println(response);
			if (response.startsWith("Refused")) {
				System.out.println("Connection to server refused");
				ts.fermer();
				return false;
			} else {
				System.out.println("Accepted");
				return true;
			}
		} catch (IOException e) {
			System.err.println("An error occured during the connection, please retry later");
			try {
				ts.fermer();
			} catch (IOException e1) {
			}
			return false;
		}
	}
	
	public Object sendRequest(String requestParameter) throws ClassNotFoundException {
		try {
			ts.envoyer(requestParameter);

			String response = (String) ts.recevoir();
			return response;
		} catch (IOException e) {
			System.err.println("An error occured during the request, please retry later");
			try {
				ts.fermer();
			} catch (IOException e1) {
			}
			return null;
		}
	}
	
	public boolean closeConnectionRequest() throws ClassNotFoundException{
		try {
			System.out.println("Ending connection...");
			ts.envoyer("End of request");

			String response = (String) ts.recevoir();
			System.out.println(response);
			if (response.startsWith("Goodbye")) {
				ts.fermer();
				System.out.println("Connection to server ended");
				return true;
			} else {
				System.out.println("An error occured during the end of connection. Retrying...");
				return this.closeConnectionRequest();
			}
		} catch (IOException e) {
			System.err.println("Unable to end the connection. Force transport close");
			try {
				ts.fermer();
			} catch (IOException e1) {
			}
			return false;
		}
	}
	
}
