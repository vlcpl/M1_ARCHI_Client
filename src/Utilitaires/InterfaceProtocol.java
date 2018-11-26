package Utilitaires;

public interface InterfaceProtocol {

	/**
	 * Sends a file path to a server
	 * @param requestParameter
	 * @throws ClassNotFoundException
	 */
	void sendRequest(String requestParameter) throws ClassNotFoundException;

	/**
	 * Receives a response from the server
	 * @return
	 * @throws Exception
	 */
	Object receiveResponse() throws Exception;

}