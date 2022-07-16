package database;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import models.BikePath;

public class CSVTranslator {
	public static List<BikePath> Translate(String filePath)  {
		Logger logger = Logger.getGlobal();
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
	
	private static BikePath dataToBikePath(String[] data) {
		
		BikePath bp = new BikePath();
    	bp.setDepartureTime(LocalDateTime.parse(data[0]));
    	bp.setReturnTime(LocalDateTime.parse(data[1]));
    	bp.setDepartureStationId(Integer.parseInt(data[2]));
    	bp.setDepartureStationName(data[3]);
    	bp.setReturnStationId(Integer.parseInt(data[4]));
    	bp.setReturnStationName(data[5]);
    	bp.setDistance(Integer.parseInt(data[6]));
    	bp.setDuration(Integer.parseInt(data[7]));
        
        return bp;
	}
}
