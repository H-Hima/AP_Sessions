package Generics;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;

public class Parent {
	
	public static <T extends Number> void print(ArrayList<T> list) {
		for(Object item: list) {
			System.out.println(item);
			System.out.println(item.getClass());
		}
	}
	
	public static void printWithGeneralWildCard(ArrayList<?> list) {
		for(Object item: list) {
			System.out.println(item);
			System.out.println(item.getClass());
		}
	}
	
	public static void printWithExtendsWildCard(ArrayList<? extends Number> list) {
		for(Object item: list) {
			System.out.println(item);
			System.out.println(item.getClass());
		}
	}
	
	public static void printWithSuperWildCard(ArrayList<? super Double> list) {
		list.add(343545.5);
		for(Object item: list) {
			System.out.println(item);
			System.out.println(item.getClass());
		}
	}
	
	public static void main(String[] args) {
		ArrayList<Number> list=new ArrayList<>();
		list.add(5435);
		list.add(4353);
		
		ArrayList list2 = list;
		list2.add(900);
		
		
		print(list);
		
		System.out.println(list.getClass());
		System.out.println(list2.getClass());
		
		ArrayList<MyNumber> myList=new ArrayList<>();
		print(myList);
	}
}

class MyNumber extends Number {

	@Override
	public double doubleValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float floatValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int intValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long longValue() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}