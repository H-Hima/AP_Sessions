package Networking;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SimpleClient extends Thread{
	String IP;
	int port;
	Socket socket;
	
	public SimpleClient(String IP, int port) {
		// TODO Auto-generated constructor stub
		
		this.IP = IP;
		this.port = port;
	}
	
	public void run() {
		try {
			System.out.println("Client connected to server at: "+IP+":"+port);
			socket = new Socket(IP, port);
			PrintStream printer = new PrintStream(socket.getOutputStream());
			Scanner scanner = new Scanner(socket.getInputStream());
			for (int i=0;i<100;i++) {
				String str = "The String is: "+i;
				printer.println(str);
				printer.flush();
				System.out.println("Sent request for service with value: "+str);
				System.out.println("Recieved These values from server: ");
				System.out.println(scanner.nextLine());
				System.out.println(scanner.nextLine());
				Thread.sleep(1000);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
