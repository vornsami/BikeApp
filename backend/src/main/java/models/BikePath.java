package models;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.bson.Document;
import org.json.JSONObject;

public class BikePath {
	
	private LocalDateTime departureTime;
	private LocalDateTime returnTime;
	private int departureStationId;
	private String departureStationName;
	private int returnStationId;
	private String returnStationName;
	private double distance;
	private double duration;
	
	public static BikePath documentToBikePath(Document doc) {
		return new BikePath(
				((Date)  doc.get("Departure")).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
				((Date)  doc.get("Return")).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
				(int) 	 doc.get("Departure station id"),
				(String) doc.get("Departure station name"),
				(int) 	 doc.get("Return station id"),
				(String) doc.get("Return station name"),
				(double) doc.get("Distance"),
				(double) doc.get("Duration")
			);
	}
	
	public BikePath() {
	}
	
	public BikePath(LocalDateTime depT, LocalDateTime retT, int depId, String depName, int retId, String retName, double dis, double dur) {
		departureTime = depT;
		returnTime = retT;
		departureStationId = depId;
		departureStationName = depName;
		returnStationId = retId;
		returnStationName = retName;
		distance = dis;
		duration = dur;
	}
	
	
	public Document toDocument() {
		Document doc = new Document();
		
		doc.put("Departure", departureTime);
		doc.put("Return", returnTime);
		doc.put("Departure station id", departureStationId);
		doc.put("Departure station name", departureStationName);
		doc.put("Return station id", returnStationId);
		doc.put("Return station name", returnStationName);
		doc.put("Distance", distance);
		doc.put("Duration", duration);
		
		return doc;
	}
	public JSONObject toJSON() {
		
		JSONObject jsonObj = new JSONObject();
		
		jsonObj.put("Departure", departureTime);
		jsonObj.put("Return", returnTime);
		jsonObj.put("Departure station id", departureStationId);
		jsonObj.put("Departure station name", departureStationName);
		jsonObj.put("Return station id", returnStationId);
		jsonObj.put("Return station name", returnStationName);
		jsonObj.put("Distance", distance);
		jsonObj.put("Duration", duration);
		
		return jsonObj;
	}

	public LocalDateTime getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(LocalDateTime returnTime) {
		this.returnTime = returnTime;
	}

	public LocalDateTime getdepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalDateTime departure) {
		this.departureTime = departure;
	}

	public int getDepartureStationId() {
		return departureStationId;
	}

	public void setDepartureStationId(int departureStationId) {
		this.departureStationId = departureStationId;
	}

	public String getDepartureStationName() {
		return departureStationName;
	}

	public void setDepartureStationName(String departureStationName) {
		this.departureStationName = departureStationName;
	}

	public int getReturnStationId() {
		return returnStationId;
	}

	public void setReturnStationId(int returnStationId) {
		this.returnStationId = returnStationId;
	}

	public String getReturnStationName() {
		return returnStationName;
	}

	public void setReturnStationName(String returnStationName) {
		this.returnStationName = returnStationName;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}
	
	@Override
	public String toString() {
		return departureTime + " - " + returnTime + ": from " + departureStationId + ":" + departureStationName + " to " + returnStationId + ":" + returnStationName + ", " + distance + "m in " + duration;
	}
}
