package Networking.TCP;

public class ClientMain {
	public static void main(String[] args) {
		SimpleClient simpleClient = new SimpleClient(
				"127.0.0.1", 
				9090);
		simpleClient.start();
	}
}
