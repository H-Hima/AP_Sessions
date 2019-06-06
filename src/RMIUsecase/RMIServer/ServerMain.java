package RMIUsecase.RMIServer;

public class ServerMain {
	public static void main(String[] args) {
		ServerEngine server = new ServerEngine(9090);
		server.start();
	}
}
