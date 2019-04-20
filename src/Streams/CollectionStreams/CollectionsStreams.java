package Streams.CollectionStreams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

public class CollectionsStreams {
	
	public static Boolean function(String string) {
		return true;
	}
	
	public static void main(String[] args) {
		
		ArrayList<String> list = new ArrayList<>(
				Arrays.asList(new String[] {"hamid", "ali", "sara", "hossein"}));
		
		Stream<String> stream = list.stream();
		Stream<String> stream2 = stream.filter(string -> string.startsWith("h"));
		stream2.forEach(System.out::println);
		
		System.out.println("===========================");
		
		stream2 = list.stream().filter(string -> string.startsWith("h"));
		stream2.forEach(System.out::println);
		
		System.out.println("===========================");
		
		list.stream().map(string -> string.length()).forEach(System.out::println);
		
		System.out.println("===========================");
		
		Optional<String> str = new ArrayList<String>().stream().reduce((string1, string2) -> string1 + "<->" + string2);
		System.out.println(str.orElse("Result is Null"));
		
		System.out.println(list.stream().min((a,b) -> a.compareTo(b)).orElse("Empty"));
		System.out.println(list.stream().max((a,b) -> a.compareTo(b)).orElse("Empty"));
		
		System.out.println("===========================");
		
		list.stream().parallel().forEach(System.out::println);
		
	}
}
