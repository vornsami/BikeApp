package database;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexModel;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Sorts;
import static com.mongodb.client.model.Aggregates.*;

public class DatabaseManager {
	private static final String BIKE_COLLECTION_NAME = "bike";
	private static final String DATABASE_COLLECTION_NAME = "db";
	
    private static DatabaseManager manager;
    MongoDatabase database;
    MongoClient client;
    
    private DatabaseManager(String location, String name) {
    	client = MongoClients.create(location);
    	database = client.getDatabase(name);

    	// Creates collections and indexes if they do not exist
    	Logger.getGlobal().info("Checking indexes...");
    	List<Bson> indexes = Arrays.asList(Indexes.descending("Departure"), Indexes.descending("Return"), Indexes.descending("Distance"), Indexes.descending("Duration"), Indexes.ascending("Departure station name"),Indexes.ascending("Return station name"), Indexes.ascending("Return station id"), Indexes.ascending("Departure station id"));
    	List<IndexModel> indexModels = indexes.stream().map(a -> new IndexModel(a)).collect(Collectors.toList());
    	database.getCollection(BIKE_COLLECTION_NAME).createIndexes(indexModels);
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
    
    public void insertAll(List<Document> documents) {
    	database.getCollection(BIKE_COLLECTION_NAME).insertMany(documents);
    }
    
    public boolean isCSVNotInserted(File csvFile) {
    	BasicDBObject obj = new BasicDBObject();
    	
    	obj.put("filename", csvFile.getName());
    	obj.put("lastmodified", csvFile.lastModified());
    	
    	return database.getCollection(DATABASE_COLLECTION_NAME).find(obj).first() == null;
    }
    
    public void insertCSV(File csvFile) {
    	Document doc = new Document();
    	
    	doc.put("filename", csvFile.getName());
    	doc.put("lastmodified", csvFile.lastModified());
    	
    	database.getCollection(DATABASE_COLLECTION_NAME).insertOne(doc);
    }
    
    private Bson selectSort(String sortBy) {
    	if (sortBy.equals("Departure station name") || sortBy.equals("Departure station id")  || sortBy.equals("Return station name") || sortBy.equals("Return station id")) {
    		return Sorts.ascending(sortBy);
    	}
    	return Sorts.descending(sortBy);
    }
    
    public void dropDatabase() {
    	database.drop();
    }
    
    public JSONArray getAsJSON(int amount, String sortBy, int offset) {
    	
    	List<JSONObject> bp = new ArrayList<>();
    	
    	List<Bson> request = Arrays.asList(
    			sort(selectSort(sortBy)),
    			skip(offset),
    			limit(amount)
    			);
    	
    	database.getCollection(BIKE_COLLECTION_NAME)
    		.aggregate(request)
    		.map(a -> new JSONObject(a.toJson()))
    		.into(bp);
    	
    	JSONArray jsonarr = new JSONArray(bp);
    	return jsonarr;
    }
    
}
