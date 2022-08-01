package database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.bson.Document;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.time.LocalDateTime;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import models.BikePath;

public class CSVTranslator {
	private static final String CSV_DIRECTORY = "./csv";
	private static Logger logger = Logger.getGlobal();
	
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
	
	public static List<BikePath> translateAll() {
		try {
			File csvDir = new File(CSV_DIRECTORY);
			List<String> csvFiles = Arrays.asList(csvDir.list(CSVTranslator.csvFilter()));
			List<BikePath> bikePaths = new ArrayList<>();

			logger.info("Accessing csv-files at " + csvDir.toString());
			
			for (String filename : csvFiles) {
				String path = CSV_DIRECTORY + "/" + filename;
				bikePaths.addAll(CSVTranslator.translate(path));
			}
			
			return bikePaths;
			
		} catch (Exception e) {
			logger.warning(e.getMessage());
        }
		return null;
	}
	
	public static List<BikePath> translate(String filePath)  {
		try{
			FileReader fr = new FileReader(filePath);
            CSVReader reader = new CSVReaderBuilder(fr).withSkipLines(1).build();
            List<BikePath> bikePathList = 
            		reader
            		.readAll()
            		.stream()
            		.map(data -> dataToBikePath(data))
            		.collect(Collectors.toList());

            return bikePathList;
        } catch (IOException e) {
			logger.warning(e.getMessage());
        }
		return null;
	}
	
	public static List<Document> translateToDocuments(String filePath)  {
		try{
			FileReader fr = new FileReader(filePath);
            CSVReader reader = new CSVReaderBuilder(fr).withSkipLines(1).build();
            List<Document> bikePathList = 
            		reader
            		.readAll()
            		.stream()
            		.map(data -> dataToDocument(data))
            		.collect(Collectors.toList());

            return bikePathList;
        } catch (IOException e) {
			logger.warning(e.getMessage());
        }
		return null;
	}
	
	private static BikePath dataToBikePath(String[] data) {
		
		BikePath bp = new BikePath();
    	bp.setDepartureTime(LocalDateTime.parse(data[0]));
    	bp.setReturnTime(LocalDateTime.parse(data[1]));
    	bp.setDepartureStationId(Integer.parseInt(data[2]));
    	bp.setDepartureStationName(data[3]);
    	bp.setReturnStationId(Integer.parseInt(data[4]));
    	bp.setReturnStationName(data[5]);
    	bp.setDistance(Double.parseDouble(data[6]));
    	bp.setDuration(Double.parseDouble(data[7]));
        
        return bp;
	}
	
	public static Document dataToDocument(String[] data) {
		Document doc = new Document();
		
		doc.put("Departure", data[0]);
		doc.put("Return", data[1]);
		doc.put("Departure station id", data[2]);
		doc.put("Departure station name", data[3]);
		doc.put("Return station id", data[4]);
		doc.put("Return station name", data[5]);
		doc.put("Distance", data[6]);
		doc.put("Duration", data[7]);
		
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
}
