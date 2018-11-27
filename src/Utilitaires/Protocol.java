package Utilitaires;

import java.io.IOException;

public class Protocol implements InterfaceProtocol {
	private InterfaceTransport ts;
	
	public Protocol(InterfaceTransport t) {
		// On essaye d'accéder au serveur
			this.ts = t;
	}

	/* (non-Javadoc)
	 * @see Utilitaires.InterfaceProtocol#sendRequest(java.lang.String)
	 */
	@Override
	public void sendRequest(String requestParameter) throws ClassNotFoundException, IOException {
			this.ts.envoyer(requestParameter);
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
