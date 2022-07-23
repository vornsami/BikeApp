package database;

import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import models.BikePath;

public class DatabaseManager {
	private static final String BIKE_COLLECTION_NAME = "bike";
	private static final String DATABASE_COLLECTION_NAME = "db";
	
    private static DatabaseManager manager;
    MongoDatabase database;
    MongoClient client;
    
    private DatabaseManager(String location, String name) {
    	client = MongoClients.create(location);
    	database = client.getDatabase(name);
    	
    	// Creates collections if they do not exist
    	database.getCollection(BIKE_COLLECTION_NAME);
    	database.getCollection(DATABASE_COLLECTION_NAME);
    }
    /**
     * 
     * @param location - The location of a mongodb database; for example mongodb://localhost:27017
     * @param name - The name of the database to connect to
     */
    public static void init(String location, String name) {
    	manager = new DatabaseManager(location, name);
    }
    
    /**
     * You need to first initialise the database using DatabaseManager.init before calling this.
     * 
     * This is so that you do not need to give the name and location of the database whenever you use it. 
     * 
     * If this is called before initialisation, this returns null, otherwise it returns the DatabaseManager.
     * */
    public static DatabaseManager getManager() {
    	return manager;
    }
    
    public MongoDatabase getDatabase() {
    	return database;
    }
    
    public void insertPath(BikePath path) {
    	database.getCollection(BIKE_COLLECTION_NAME).insertOne(path.toDocument());
    }
    public void insertAll(List<BikePath> paths) {
    	List<Document> asDocuments = paths.stream().map(b -> b.toDocument()).toList();
    	database.getCollection(BIKE_COLLECTION_NAME).insertMany(asDocuments);
    }
}
