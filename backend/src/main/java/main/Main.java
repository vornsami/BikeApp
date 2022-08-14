package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import database.CSVTranslator;
import database.DatabaseManager;
import network.BikeServer;
import utils.MissingPropertyException;

public class Main {
	private static final String CONF_FILENAME = "server.conf";
	private static final String DATABASE_NAME = "BikeApp";
	
	public static void main(String[] args) {

		Properties props = prepareProps();
		if(props == null) return;
		
		System.out.println("Starting " + props.getProperty("appname") + " server version " + props.getProperty("version") + " at port " + props.getProperty("serverport") + "...");
		
		DatabaseManager.init(props.getProperty("databaselocation"), DATABASE_NAME);
		CSVTranslator.translateAllToDatabase();
		
		int portnumber = Integer.parseInt(props.getProperty("serverport"));
		runServer(portnumber);
		
	}
	
	private static Properties prepareProps() {
		Properties props = new Properties();

		try {
			
			FileInputStream fis = new FileInputStream(CONF_FILENAME);
			props.load(fis);
			validateProps(props);
			
		}  catch (MissingPropertyException e) {
			Logger.getGlobal().warning(e.getMessage());
		    return null;
		} catch (IOException e) {
			Logger.getGlobal().warning("Error loading config file,\n" + e.getMessage());
		    return null;
		}
		return props;
	}
	
	private static boolean validateProps(Properties props) throws MissingPropertyException {
		String[] importantProps = new String[] {"appname", "version", "serverport", "databaselocation", "databaseName"};
		String errMessage = "";
		
		for (String prop : importantProps) {
			if (props.getProperty(prop) == null) errMessage += ", " + prop;
		}
		if (errMessage.length() > 0) throw new MissingPropertyException("Missing properties: " + errMessage.substring(2));
		return true;
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
