package Networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
	public static void main(String[] args) throws IOException {
		SimpleServer server = new SimpleServer(9090);
		server.start();
	}
}
