package Logger;

import java.io.FileNotFoundException;

public class Test {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Logger2.getLogger().error("This is a fake error");
		Logger2.getLogger().debug("This is a verbos data");
		Logger2.getLogger().info("This is a real information");
		
		System.out.println("Tested");
	}

}
