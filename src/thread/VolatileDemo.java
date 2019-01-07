package thread;

import java.io.IOException;
import java.sql.Connection;

public class VolatileDemo {

	private static volatile VolatileDemo instance;

	public static VolatileDemo getInstance(Connection conn) throws IOException {
		if (instance == null) {
			synchronized (VolatileDemo.class) {
				if (instance == null)
					instance = new VolatileDemo(conn);
			}
		}
		return instance;
	}

	private VolatileDemo(Connection conn) throws IOException {
		// init factory using the database connection passed in
	}

}
/**
 * 
 * One common example for using volatile is for a flag to terminate a thread. If you’ve started a thread, and you want to be able to safely interrupt it from a different thread, you can have the thread periodically check a flag (i.e., to stop it, set the flag to true). By making the flag volatile, you can ensure that the thread that is checking its value will see that it has been set to true without even having to use a synchronized block.
 * 
 * **/
