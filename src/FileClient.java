import Utilitaires.InterfaceProtocol;
import Utilitaires.Protocol;
import Utilitaires.Transport;

public class FileClient {
	private static final int PORT = 1234;

	private static boolean usageOk(String[] argv) {
		if (argv.length != 2) {
			String msg = "USAGE IS: " + "FileClient server-name file-name";
			System.out.println(msg);
			return false;
		}
		return true;
	}

	public static void main(String[] argv) throws Exception {
		if (!usageOk(argv))
			System.exit(1);
		
		InterfaceProtocol p = new Protocol(new Transport(argv[0], PORT));
		p.sendRequest(argv[1]);
		String serverResponse = (String) p.receiveResponse();
		System.out.println("RESPONSE FROM SERVER: " + serverResponse);
	}
}
