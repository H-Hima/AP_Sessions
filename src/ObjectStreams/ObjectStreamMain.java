package ObjectStreams;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import sun.misc.GC;

public class ObjectStreamMain implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		
		PipedOutputStream pipedOutput = new PipedOutputStream();
		PipedInputStream pipedInput = new PipedInputStream();
		pipedInput.connect(pipedOutput);
		
//		ObjectOutputStream output = new ObjectOutputStream(pipedOutput);
//		ObjectInputStream input = new ObjectInputStream(pipedInput);
//		
//		output.writeObject("1");
//		output.writeObject("2");
//		output.writeObject("3");
//		
//		System.out.println(input.readObject());
//		System.out.println(input.readObject());
//		System.out.println(input.readObject());
//		
//		Object[] myArray = new Object[]{"1",new String[] {"1","2"}};
//		output.writeObject(myArray);
//		
//		output.reset();
//		
//		((String[])myArray[1])[1] = "new String";
//		output.writeUnshared(myArray);
//		
//		Object[] array = (Object[])input.readObject();
//		for(Object string: array)
//			System.out.println(string);
//		for(Object string: (String[])array[1])
//			System.out.println(string);
//		
//		System.out.println("===================");
//		
//		array = (Object[])input.readObject();
//		for(Object string: array)
//			System.out.println(string);
//		for(Object string: (String[])array[1])
//			System.out.println(string);
//		
//		System.out.println("===================");
//		
//		output.writeObject("Started");
//		
//		String string = (String)input.readObject();
//		System.out.println(string);
		
		WriterThread writerThread = new WriterThread(
				new BufferedOutputStream(pipedOutput));
		
		ReaderThread readerThread = new ReaderThread(
				new BufferedInputStream(pipedInput));
		
		writerThread.start();
		readerThread.start();
	}
}
