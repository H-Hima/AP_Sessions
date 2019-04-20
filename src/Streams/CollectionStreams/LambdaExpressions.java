package Streams.CollectionStreams;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

public class LambdaExpressions {
	public static void actionHandler(String string, FirstInterface a) {
		System.out.println(a.action(string));
	}
	
	public static void actionHandler(String string, SecondInterface a) {
		
	}
	
	public static void actionHandler(ThirdInterface a) {
		System.out.println("Third");
	}
	
	public static int staticAction(String string) {
		return Integer.parseInt(string)+1;
	}
	
	public int myAction(String str) {
		return 1;
	}
	
	public int A(String string) {
		return Integer.parseInt(string)+1;
		//actionHandler(this::myAction);
	}

	static void action(ActionEvent e) {
	}
	
	public static void main(String[] args) {
		String str="100";
		
		actionHandler(str, (String string) -> Integer.parseInt(string));
		actionHandler(str, LambdaExpressions::staticAction);
		
		actionHandler(str, (String a, String b) -> a.length() + b.length());
		
		LambdaExpressions object = new LambdaExpressions();
		actionHandler(str, object::A);
		
		actionHandler(() -> {});
		
		if(1!=2) {
			return;
		}
		
		actionHandler(str, new FirstInterface() {
			
			@Override
			public int action(String string) {
				// TODO Auto-generated method stub
				return Integer.parseInt(string);
			}
		});
		
		actionHandler(str, LambdaExpressions::staticAction);
		actionHandler(str, Integer::new);
		
		JButton button = new JButton("");
		button.addActionListener(LambdaExpressions::action);
	}
	
}
