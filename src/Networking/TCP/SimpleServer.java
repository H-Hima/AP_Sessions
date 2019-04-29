package Networking.TCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SimpleServer extends Thread {
	ServerSocket serverSocket;
	int port;
	
	ArrayList<LengthCalculator> threads;
	
	public SimpleServer(int port) throws IOException {
		// TODO Auto-generated constructor stub
		this.port = port;
	}
	
	public void run() {
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Server started on port: "+port);
			
			threads = new ArrayList<>();
			while(true) {
				Socket socket = serverSocket.accept();
				LengthCalculator newService = 
						new LengthCalculator(
								threads.size(),
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
	
	public void stopService() {
		for (LengthCalculator thread:threads) {
			thread.stop();
		}
	}
}
