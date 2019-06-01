package Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;

public class Logger2 {
	private static Logger2 loggerInstance = null;
	PrintWriter writer;
	private Logger2() throws FileNotFoundException {
		writer = new PrintWriter(new File("newFile.txt"));
	}
	
	public static Logger2 getLogger()  {
		if(loggerInstance == null) {
			try {
				loggerInstance = new Logger2();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return loggerInstance;
	}
	
	public void error(String message) {
		log("ERROR",message);
	}
	
	public void info(String message) {
		log("INFO",message);
	}
	
	public void debug(String message) {
		log("DEBUG",message);
	}
	
	public void log(String level, String message) {
		writer.println(level+": "+new Date().toString()+": "+message);
		writer.flush();
	}
}
