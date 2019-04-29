package Networking.TCP;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class LengthCalculator extends Thread{
	InputStream inputStream;
	OutputStream outputStream;
	int id;
	
	public LengthCalculator(int id,InputStream inputStream, OutputStream outputStream) {
		this.inputStream = new BufferedInputStream(inputStream);
		this.outputStream = new BufferedOutputStream(outputStream);
		this.id = id;
	}
	
	public void run() {
		Scanner scanner = new Scanner(inputStream);
		PrintStream printer = new PrintStream(outputStream);
		while(scanner.hasNextLine()) {
			String str = scanner.nextLine();
			System.out.println("Client number "+id+" requested service for \""+str+"\"");
			
			int result = str.length();
			String upper = str.toUpperCase();
			
			printer.println(result);
			printer.println(upper);
			printer.flush();
			
			System.out.println("Sent two lines to client number "+id+" for the requested value: \""+str+"\"");
		}
		scanner.close();
	}
}
