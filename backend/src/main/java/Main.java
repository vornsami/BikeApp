
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import network.BikeServer;

public class Main {
	private static final String CONF_FILENAME = "server.conf";
	
	public static void main(String[] args) {

		Properties props = prepareProps();
		if(props == null) return;
		
		System.out.println("Starting " + props.getProperty("appname") + " server version " + props.getProperty("version") + " at port " + props.getProperty("serverport") + "...");
		
		int portnumber = Integer.parseInt(props.getProperty("serverport"));
		runServer(portnumber);
		
	}
	
	private static Properties prepareProps() {
		Properties props = new Properties();
		String fileName = CONF_FILENAME;

		try {
			FileInputStream fis = new FileInputStream(fileName);
			props.load(fis);
		}  catch (IOException e) {
		    System.out.println("Error loading config file,\n" + e);
		    return null;
		}
		return props;
	}
	
	private static void runServer(int port) {
		Logger logger = Logger.getGlobal();
		try {
			final BikeServer server = new BikeServer(port);
			server.start();
			System.out.println("Server is now running.");
			
			Runtime.getRuntime().addShutdownHook(new Thread(() -> {
				server.stop();
				System.out.println("Server stopped.");
			}));
			
			while (true) {
				Thread.sleep(100);
			}
			
		} catch (IOException e) {
			logger.warning(e.getMessage());
		} catch (InterruptedException e) {
			logger.warning(e.getMessage());
		}
	}

}
