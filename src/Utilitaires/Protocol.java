package Utilitaires;

import java.io.IOException;

public class Protocol implements InterfaceProtocol {
	private Transport ts;
	
	public Protocol(Transport t) {
		// On essaye d'accéder au serveur
		try {
			this.ts = t;
		} catch (Exception e) {
			System.err.println("Unable to reach the server");
			e.printStackTrace();
			System.exit(1);
		}
	}

	/* (non-Javadoc)
	 * @see Utilitaires.InterfaceProtocol#sendRequest(java.lang.String)
	 */
	@Override
	public void sendRequest(String requestParameter) throws ClassNotFoundException {
		try {
			this.ts.envoyer(requestParameter);
		} catch (IOException e) {
			System.err.println("An error occured during the request, please retry later");
		}
	}
	
	/* (non-Javadoc)
	 * @see Utilitaires.InterfaceProtocol#receiveResponse()
	 */
	@Override
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
