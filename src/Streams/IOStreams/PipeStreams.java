package Streams.IOStreams;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class PipeStreams {
	public static void main(String[] args) throws IOException {
		PipedInputStream pipedInput = new PipedInputStream();
		PipedOutputStream pipedOutput = new PipedOutputStream();
		pipedInput.connect(pipedOutput);
		
		ReaderThread readerThread = new ReaderThread(
				new BufferedInputStream(pipedInput));
		
		WriterThread writerThread = new WriterThread(
				new BufferedOutputStream(pipedOutput));
		
		writerThread.start();
		readerThread.start();
	}
}
