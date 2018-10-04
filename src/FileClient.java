import Utilitaires.Protocol;

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
		
		Protocol p = new Protocol(argv[0], PORT);
		p.sendRequest(argv[1]);
		String serverResponse = (String) p.receiveResponse();
		System.out.println("RESPONSE FROM SERVER: " + serverResponse);
	}
}
