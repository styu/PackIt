package edu.mit.packit.db;

public class TripDetails {

	private long id;
	private String trip_name;
	private String location;
	private String transportation;
	private String gender;
	private String from_date;
	private String to_date;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getTripName() {
		return trip_name;
	}
	
	public void setTripName(String trip_name) {
		this.trip_name = trip_name;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getTransportation() {
		return transportation;
	}
	
	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getFromDate() {
		return from_date;
	}
	
	public void setFromDate(String from_date) {
		this.from_date = from_date;
	}
	
	public String getToDate() {
		return to_date;
	}
	
	public void setToDate(String to_date) {
		this.to_date = to_date;
	}
}
