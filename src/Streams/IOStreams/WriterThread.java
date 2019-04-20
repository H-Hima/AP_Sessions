package Streams.IOStreams;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class WriterThread extends Thread{
	
	OutputStream pipedOutput;
	PrintStream printer;
	
	public WriterThread(OutputStream pipedOutput) throws IOException {
		// TODO Auto-generated constructor stub
		this.pipedOutput = pipedOutput;
		printer = new PrintStream(pipedOutput);
	}
	
	public void run() {
		int count = 0;
		while(count++ < 100) {
			try {
				printer.println(count);
				
				printer.flush();
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
