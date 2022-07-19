package models;

import java.time.LocalDateTime;

import org.bson.Document;

public class BikePath {
	
	private LocalDateTime departureTime;
	private LocalDateTime returnTime;
	private int departureStationId;
	private String departureStationName;
	private int returnStationId;
	private String returnStationName;
	private double distance;
	private double duration;
	
	public Document toDocument() {
		Document doc = new Document();
		
		doc.put("Departure", departureTime);
		doc.put("Return", returnTime);
		doc.put("Departure station id", departureStationId);
		doc.put("Departure station name", departureStationName);
		doc.put("Return station id", returnStationId);
		doc.put("Return station name", returnStationName);
		doc.put("Covered distance (m)", distance);
		doc.put("Duration (sec.)", duration);
		
		return doc;
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
}
