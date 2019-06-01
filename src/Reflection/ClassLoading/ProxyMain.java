package Reflection.ClassLoading;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyMain {
	public static void main(String[] args) {
		String string = "original String";
		
		Object proxy = (Comparable)Proxy.newProxyInstance(
				myInvocationHandler.class.getClassLoader(), 
				new Class[] {Comparable.class},
				new myInvocationHandler(string));
		
		//===================================
		
		System.out.println(proxy.toString());
		System.out.println(proxy.hashCode());
	}
}

class myInvocationHandler implements InvocationHandler {

	Object originalObject;
	
	public myInvocationHandler(Object proxyObject) {
		this.originalObject=proxyObject;
	}
	
	@Override
	public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {
		// TODO Auto-generated method stub
		
		System.out.println("Entered the proxy ...");
		
		if(arg1.getName().startsWith("toString")) {
			System.out.println("Change in behaviour ...");
			return arg1.invoke(originalObject, arg2) + " Through Proxy";
		}
		
		System.out.println("No change in behaviour");
		return arg1.invoke(originalObject, arg2);
	}
	
}