package Logger;

import java.io.FileNotFoundException;

public class Test {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Logger.getLogger().error("This is a fake error");
		Logger.getLogger().debug("This is a verbos data");
		Logger.getLogger().info("This is a real information");
	}

}
