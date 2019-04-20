package Streams.IOStreams;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Scanner;

public class ReaderThread extends Thread{
	
	InputStream pipedInput;
	Scanner scanner;
	
	public ReaderThread(InputStream pipedInput) throws IOException {
		// TODO Auto-generated constructor stub
		this.pipedInput = pipedInput;
		scanner = new Scanner(pipedInput);
	}
	
	public void run() {
		while(true) {
			System.out.println(
					"Read Input With Value: " + scanner.nextLine()); 
		}
	}
}
