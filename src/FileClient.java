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
	}
}
