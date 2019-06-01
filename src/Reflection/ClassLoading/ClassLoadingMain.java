package Reflection.ClassLoading;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassLoadingMain {
	public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		URLClassLoader classLoader = new URLClassLoader(
				new URL[] {new URL("file:///D:/logger.jar")});
		
		Class loggerClass = classLoader.loadClass("Logger.Logger");
		Method getLogger = loggerClass.getMethod("getLogger");
		Object logger = getLogger.invoke(null);
		
		Method logMethod = loggerClass.getMethod("log", String.class, String.class);
		logMethod.invoke(logger, "Error","This is an error which raised from classLoading main function.");
	}
}
