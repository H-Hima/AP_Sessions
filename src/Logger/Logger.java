package Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;

public class Logger {
	private static Logger loggerInstance = null;
	PrintWriter writer;
	private Logger() throws FileNotFoundException {
		writer = new PrintWriter(new File("log.txt"));
	}
	
	public static Logger getLogger()  {
		if(loggerInstance == null) {
			try {
				loggerInstance = new Logger();
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
