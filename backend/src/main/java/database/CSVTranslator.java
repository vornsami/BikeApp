package database;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.bson.Document;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class CSVTranslator {
	private static final String CSV_DIRECTORY = "./csv";
	private static Logger logger = Logger.getGlobal();
	private static final int MIN_DISTANCE = 10;
	private static final int MIN_DURATION = 10;
	
	public static void translateAllToDatabase() {
		try {
			File csvDir = new File(CSV_DIRECTORY);
			List<String> csvFiles = Arrays.asList(csvDir.list(CSVTranslator.csvFilter()));
			DatabaseManager manager = DatabaseManager.getManager();

			logger.info("Accessing csv-files at " + csvDir.toString());
			
			for (String filename : csvFiles) {
				String path = CSV_DIRECTORY + "/" + filename;
				File f = new File(path);
				if(manager.isCSVNotInserted(f)) {
					CSVTranslator.translateToDatabase(path);
					manager.insertCSV(f);
				}
			}
			
		} catch (Exception e) {
			logger.warning(e.getMessage());
        }
		
		
	}
	
	public static void translateToDatabase(String filePath)  {
		logger.info("Translating " + filePath);
		try{
			DatabaseManager manager = DatabaseManager.getManager();
			FileReader fr = new FileReader(filePath);
            CSVReader reader = new CSVReaderBuilder(fr).withSkipLines(1).build();
            List<Document> bp = reader.readAll()
            		.stream()
            		.map(data -> dataToDocument(data))
            		.toList();
            manager.insertAll(bp);
        } catch (IOException e) {
			logger.warning(e.getMessage());
        }
	}
	
		
	public static List<Document> translateToDocuments(String filePath)  {
		try{
			FileReader fr = new FileReader(filePath, StandardCharsets.UTF_8);
			
            CSVReader reader = new CSVReaderBuilder(fr).withSkipLines(1).build();
            List<Document> bikePathList = 
            		reader
            		.readAll()
            		.stream()
            		.map(data -> dataToDocument(data))
            		.filter(data -> validate(data))
            		.collect(Collectors.toList());

            return bikePathList;
        } catch (IOException e) {
			logger.warning(e.getMessage());
        }
		return null;
	}
	
	public static Document dataToDocument(String[] data) {
		Document doc = new Document();
		
		doc.put("Departure", data[0]);
		doc.put("Return", data[1]);
		doc.put("Departure station id", Integer.parseInt(data[2]));
		doc.put("Departure station name", new String(data[3].getBytes(), 0, data[3].length(), StandardCharsets.UTF_8));
		doc.put("Return station id", Integer.parseInt(data[4]));
		doc.put("Return station name", new String(data[5].getBytes(), 0, data[5].length(), StandardCharsets.UTF_8));
		doc.put("Distance", Double.parseDouble(data[6]));
		doc.put("Duration", Double.parseDouble(data[7]));
		
		return doc;
	}
	
	private static FilenameFilter csvFilter() {
		FilenameFilter filter = new FilenameFilter(){
			public boolean accept(File f, String filename) {
				return filename.toLowerCase().endsWith(".csv");
			}
		};
		return filter;
	}
	
	private static boolean validate(Document data) {
		return (int)data.get("Distance") < MIN_DISTANCE && (int)data.get("Duration") < MIN_DURATION;
	}
}
