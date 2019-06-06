package RMIUsecase.RMIClient;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.UnknownHostException;

public class ClientMain {
	public static void main(String[] args) throws UnknownHostException, IOException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		RMIProxy proxy = new RMIProxy("127.0.0.1", 9090);
		
		RMIInterface remote = proxy.newInstance(RemoteClass.class, "The initial value.");
		System.out.println(remote.getUppercaseValue());
		proxy.releaseInstance(remote);
		proxy.closeProxy();
		
		RMIInterface object = new RemoteClass2();
		System.out.println(object.getUppercaseValue());
		
		RMIInterface object2 = new RemoteClass();
		System.out.println(object2.getUppercaseValue());
	}
}
