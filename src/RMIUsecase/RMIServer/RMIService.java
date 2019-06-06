package RMIUsecase.RMIServer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class RMIService extends Thread {
	
	InputStream inputStream;
	OutputStream outputStream;
	String name;
	
	TreeMap<String, Object> objects;
	
	public RMIService(String name, InputStream input, OutputStream output) {
		this.name = name;
		this.inputStream = input;
		this.outputStream = output;
	}
	  
	public ObjectInputStream getObjectInputStream(BufferedInputStream bufferedInputStream) throws IOException {
		byte[] data = new byte[10000];
		bufferedInputStream.read(data);
		ByteArrayInputStream byteInput = new ByteArrayInputStream(data);
		return new ObjectInputStream(byteInput);
	}
	
	public void writeObject(BufferedOutputStream bufferedOutput, Object object) throws IOException {
		ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
		ObjectOutputStream objectOutput = new ObjectOutputStream(byteOutput);
		
		objectOutput.writeObject(object);
		objectOutput.flush();
		
		bufferedOutput.write(byteOutput.toByteArray());
		bufferedOutput.flush();
	}
	
	public void run() {
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
		BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
		objects = new TreeMap<>();
		
		try {
			while(true) {
				ObjectInputStream objectInput = getObjectInputStream(bufferedInputStream);
				
				String command = objectInput.readUTF();
				System.out.println("Request for " + command + " from " + name);
				
				Object result = null;
				switch (command) {
					case "create":
						result = createObject(objectInput);
						break;
					case "release":
						result = releaseObject(objectInput);
						break;
					case "invoke":
						result = invokeMethod(objectInput);
						break;
					case "close":
						System.out.println("Connection Closed");
						return;
				}
				
				writeObject(bufferedOutputStream, result);
			}
			
		} catch (IOException | ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Class[] getParameterTypes(Object[] parameters) {
		if (parameters == null) {
			parameters = new Object[0];
		}
		
		Class[] result = new Class[parameters.length];
		for(int i=0;i<result.length;i++)
			result[i]=parameters[i].getClass();
		return result;
	}
	
	public boolean createObject(ObjectInputStream objectInput) throws IOException, NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String objectName = objectInput.readUTF();
		System.out.println(objectName);
		
		String typeName = objectInput.readUTF();
		Class<?> type = Class.forName(typeName);
		
		System.out.println("Create object " + objectName + " with type " + type + " for " + name);
		
		if(objects.containsKey(objectName))
			return false;
		
		Object[] parameters = readParameterList(objectInput);
		Class<?>[] parameterTypes = getParameterTypes(parameters); 
		
		Constructor<?> constructor = type.getConstructor(parameterTypes);
		Object newObject = constructor.newInstance(parameters);
		objects.put(objectName, newObject);

		System.out.println("Object " + objectName + " created " + " for " + name);
		
		return true;
	}
	
	public boolean releaseObject(ObjectInputStream objectInput) throws IOException {
		String objectName = objectInput.readUTF();
		
		System.out.println("Release object " + objectName + " for " + name);
		
		if(objects.containsKey(objectName)) {
			objects.remove(objectName);
			System.out.println("Object " + objectName + " released " + " for " + name);
			return true;
		}
		
		System.out.println("Object " + objectName + " not found for " + name);
		return false;
	}
	
	public Object invokeMethod(ObjectInputStream objectInput) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String objectName = objectInput.readUTF();
		String methodName = objectInput.readUTF();
		Object[] parameters = readParameterList(objectInput);
		Class<?>[] parameterTypes = getParameterTypes(parameters);
		
		System.out.println("Invoke method " + methodName + "on object " + objectName + " for " + name);
		
		Object object = objects.get(objectName);
		Class<?> type = object.getClass();
		Method method = type.getMethod(methodName, parameterTypes);
		
		Object result = method.invoke(object, parameters);
		
		System.out.println("Method " + methodName + "invoked on object " + objectName + " with result " + result + " for " + name);
		
		return result;
	}
	
	public Object[] readParameterList(ObjectInputStream objectInput) throws IOException, ClassNotFoundException {
		ArrayList<Object> params = new ArrayList<>();
		
		int numberOfParameters = objectInput.readInt();
		System.out.println(numberOfParameters);
		for(int i=0; i<numberOfParameters; i++) {
			params.add(readParameter(objectInput));
		}
		
		return params.toArray();
	}
	
	public Object readParameter(ObjectInputStream objectInput) throws IOException, ClassNotFoundException {
		boolean isRemote = objectInput.readBoolean();
		System.out.println(isRemote);
		if(isRemote) {
			String name = objectInput.readUTF();
			System.out.println(name);
			return objects.get(name);
		}
		Object result = objectInput.readObject();
		System.out.println(result);
		
		return result;
	}
}
