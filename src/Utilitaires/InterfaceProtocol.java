package Utilitaires;

import java.io.IOException;

public interface InterfaceProtocol {

	/**
	 * Sends a file path to a server
	 * @param requestParameter
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	void sendRequest(String requestParameter) throws ClassNotFoundException, IOException;

	/**
	 * Receives a response from the server
	 * @return
	 * @throws Exception
	 */
	Object receiveResponse() throws Exception;

}