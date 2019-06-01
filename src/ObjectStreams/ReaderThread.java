package ObjectStreams;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Scanner;

public class ReaderThread extends Thread{
	
	InputStream pipedInput;
	ObjectInputStream objectReader;
	
	public ReaderThread(InputStream pipedInput) {
		// TODO Auto-generated constructor stub
		this.pipedInput = pipedInput;
//		objectReader = new ObjectInputStream(pipedInput);
	}
	
	public void run() {
		
		try {
			objectReader = new ObjectInputStream(pipedInput);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			System.out.println(objectReader.readObject());
			System.out.println(objectReader.readObject());
			System.out.println(objectReader.readObject());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		while(true) {
			try {
				Object object = objectReader.readObject();
				System.out.println(
						"Read Input With Value: " + object);
				
				System.out.println(object.getClass());
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (EOFException e) {
				break;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
		}
	}
}
