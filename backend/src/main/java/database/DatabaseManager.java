package database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class DatabaseManager {
    private static DatabaseManager manager;
    MongoDatabase database;
    
    private DatabaseManager(String location, String name) {
    	MongoClient client = MongoClients.create(location);
    	database = client.getDatabase(name);
    }
    
    public static void init(String location, String name) {
    	manager = new DatabaseManager(location, name);
    }
    
    public static DatabaseManager getManager() {
    	return manager;
    }
    
    public MongoDatabase getDatabase() {
    	return database;
    }
}
