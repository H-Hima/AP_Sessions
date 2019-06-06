package RMIUsecase.RMIServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerEngine extends Thread{
	int port;
	ServerSocket serverSocket;
	ArrayList<RMIService> threads = new ArrayList<>();
	
	public ServerEngine(int port) {
		this.port = port;
	}
	
	public void run() {
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Server started on port: "+port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			threads = new ArrayList<>();
			while(true) {
				Socket socket = serverSocket.accept();
				String clientName = socket.getRemoteSocketAddress().toString();
				
				System.out.println("Client request from: "+ clientName);
				
				RMIService newService = 
						new RMIService(
								clientName,
								socket.getInputStream(), 
								socket.getOutputStream());
				
				threads.add(newService);
				newService.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
