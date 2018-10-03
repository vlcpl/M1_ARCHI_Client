package Utilitaires;

import java.io.IOException;

public class Protocole {
	private Transport ts;
	
	public Protocole(String host, int port) {
		// On essaye d'accéder au serveur
		try {
			ts = new Transport(host, port);
		} catch (Exception e) {
			System.err.println("Unable to reach the server");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	Object protocolGetRequest(String path) {
		//if sendBeginConnectionRequest true then
		//envoyerRequete
		//envoyerFinConnexion
		try {
			if(openConnectionRequest()) {
				Object response = this.sendRequest(path);
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
			System.out.println(response);
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
