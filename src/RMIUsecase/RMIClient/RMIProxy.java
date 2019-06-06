package RMIUsecase.RMIClient;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

import com.sun.xml.internal.ws.message.ByteArrayAttachment;

public class RMIProxy {
	String IP;
	int port;
	Socket socket;
	
	BufferedInputStream bufferedInput;
	BufferedOutputStream bufferedOutput;
//	ObjectInputStream objectInput;
//	ObjectOutputStream objectOutput;
	
	public RMIProxy(String IP, int port) throws UnknownHostException, IOException {
		this.IP = IP;
		this.port = port;
		
		socket = new Socket(IP, port);
		
		bufferedOutput = new BufferedOutputStream(socket.getOutputStream());
		bufferedInput = new BufferedInputStream(socket.getInputStream());
		
	}
	
	public Class[] getParameterTypes(Object[] parameters) {
		Class[] result = new Class[parameters.length];
		for(int i=0;i<result.length;i++)
			result[i]=parameters[i].getClass();
		return result;
	}
	
	Object readObject(InputStream inputStream) throws ClassNotFoundException, IOException {
		byte[] data = new byte[10000];
		inputStream.read(data);
		try (ObjectInputStream objectInput = new ObjectInputStream(
				new ByteArrayInputStream(data))) {
			return objectInput.readObject();
		}
	}
	
	public <T extends RMIInterface> RMIInterface newInstance(Class<T> type, Object... parameters) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException, ClassNotFoundException {
		
		ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
		ObjectOutputStream objectOutput = new ObjectOutputStream(byteOutput);
		
		Class[] parameterTypes = getParameterTypes(parameters); 
		T object = (T) type.newInstance();
		
		System.out.println("Send create object " + object.hashCode() +"request to server ...");
		
		objectOutput.writeUTF("create");
		System.out.println("create");
		objectOutput.writeUTF(object.hashCode()+"");
		System.out.println(object.hashCode()+"");
		objectOutput.writeUTF(getRemoteClass(object));
		System.out.println(getRemoteClass(object));
		sendParameters(objectOutput, parameters);
		objectOutput.flush();
		
		bufferedOutput.write(byteOutput.toByteArray());
		bufferedOutput.flush();
		
		System.out.println("Create request sent.");
		
		readObject(bufferedInput);
		
		return (RMIInterface) Proxy.newProxyInstance(
				RMIInterface.class.getClassLoader(), 
				new Class[] {RMIInterface.class}, 
				new ProxyObject(object));
	}
	
	public void releaseInstance(RMIInterface object) throws IOException, ClassNotFoundException {
		System.out.println("Send release object " + object.hashCode() +"request to server ...");
		
		ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
		ObjectOutputStream objectOutput = new ObjectOutputStream(byteOutput);
		
		objectOutput.writeUTF("release");
		objectOutput.writeUTF(object.hashCode()+"");
		objectOutput.flush();
		
		bufferedOutput.write(byteOutput.toByteArray());
		bufferedOutput.flush();
		
		System.out.println("Release request sent.");
		
		readObject(bufferedInput);
	}
	
	public String getRemoteClass(Object object) {
		try {
			RemoteClassName annotation = object.getClass().getAnnotation(RemoteClassName.class); 
			return annotation.remoteClassName();
		}
		catch (Exception ex){
			return null;
		}
	}
	
	public void sendParameters(ObjectOutputStream objectOutput, Object... parameters) throws IOException {
		if (parameters == null) {
			parameters = new Object[0];
		}
		
		objectOutput.writeInt(parameters.length);
		System.out.println(parameters.length);
		for(Object parameter: parameters) {
			String remoteClass = getRemoteClass(parameter);
			if(remoteClass != null) {
				objectOutput.writeBoolean(true);
				System.out.println(true);
				objectOutput.writeUTF(parameter.hashCode()+"");
				System.out.println(parameter.hashCode()+"");
			}
			else {
				objectOutput.writeBoolean(false);
				System.out.println(false);
				objectOutput.writeObject(parameter);
				System.out.println(parameter);
			}
		}
	}
	
	public class ProxyObject implements InvocationHandler {
		RMIInterface originalObject;
		
		public ProxyObject(RMIInterface originalObject) {
			this.originalObject = originalObject;
		}
		
		@Override
		public Object invoke(Object object, Method method, Object[] parameters) throws Throwable {
			// TODO Auto-generated method stub
			
			ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
			ObjectOutputStream objectOutput = new ObjectOutputStream(byteOutput);
			
			System.out.println("Entered the proxy ...");
			
			if(method.getName().startsWith("hashCode"))
				return originalObject.hashCode();
			
			Class[] parameterTypes = method.getParameterTypes(); 
			objectOutput.writeUTF("invoke");
			System.out.println("invoke");
			objectOutput.writeUTF(originalObject.hashCode()+"");
			System.out.println(originalObject.hashCode()+"");
			objectOutput.writeUTF(method.getName());
			System.out.println(method.getName());
			sendParameters(objectOutput ,parameters);
			objectOutput.flush();
			
			bufferedOutput.write(byteOutput.toByteArray());
			bufferedOutput.flush();
			
			System.out.println("Invoke request sent.");
			
			return readObject(bufferedInput);
		}
	}
	
	public void closeProxy() throws IOException {
		System.out.println("Closing Connection");
		
		ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
		ObjectOutputStream objectOutput = new ObjectOutputStream(byteOutput);

		objectOutput.writeUTF("close");
		objectOutput.flush();
		
		bufferedOutput.write(byteOutput.toByteArray());
		bufferedOutput.flush();
	}
	
	public void finalize() throws IOException, InterruptedException {
		closeProxy();
	}
}
