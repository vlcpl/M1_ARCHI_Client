import Utilitaires.Protocol;

public class FileClient {
	private static final int PORT = 1234;

	private static boolean usageOk(String[] argv) {
		if (argv.length != 2) {
			String msg = "usage is: " + "FileClient server-name file-name";
			System.out.println(msg);
			return false;
		}
		return true;
	}

	public static void main(String[] argv) throws ClassNotFoundException {
		if (!usageOk(argv))
			System.exit(1);
		
		Protocol p = new Protocol(argv[0], PORT);
		String serverResponse = (String) p.protocolGetRequest(argv[1]);
		System.out.println("RESPONSE FROM SERVER: " + serverResponse);
	}
}
