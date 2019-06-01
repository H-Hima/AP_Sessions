package ObjectStreams;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class WriterThread extends Thread{
	
	OutputStream pipedOutput;
	ObjectOutputStream objectPrinter;
	
	public WriterThread(OutputStream pipedOutput) {
		// TODO Auto-generated constructor stub
		this.pipedOutput = pipedOutput;
//		objectPrinter = new ObjectOutputStream(pipedOutput);
	}
	
	public void run() {
		
		try {
			objectPrinter = new ObjectOutputStream(pipedOutput);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			objectPrinter.writeObject("Check");
			objectPrinter.writeObject("Check");
			objectPrinter.writeObject("Check");
			objectPrinter.flush();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		int count = 0;
		while(count++ < 10) {
			try {
				objectPrinter.writeObject(count+"");
				objectPrinter.flush();
				Thread.sleep(100);
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
		}
		
		try {
			objectPrinter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
